package securest.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Collection;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import poo.tempo.Periodo;
import securest.recurso.CentralControlo;
import securest.recurso.Funcionario;
import securest.recurso.Instalacao;

@SuppressWarnings("serial")
public class JanelaCentral extends JFrame {

	private CentralControlo central;

	/* elementos para a interface */
	private DefaultTableModel funcionariosModel;
	private JLabel infoLbl;
	private JLabel nivelLbl;
	private JTextArea horarioTA;
	private JList<Instalacao> listaInsts;
	private DefaultListModel<Funcionario> funcAutorizados;
	private DefaultListModel<Funcionario> funcPresentes;

	/**
	 * cria a janela da central
	 * 
	 * @param title   título a parecer na janela
	 * @param central central que é controlada pela interface
	 */
	public JanelaCentral(String title, CentralControlo central) {
		super(title);
		this.central = central;
		JPanel funcPanel = setupFuncionarios();
		Collection<Instalacao> instalacoes = central.getInstalacoes();
		JPanel instPanel = setupInstalacoes(instalacoes);

		setupInterface(funcPanel, instPanel);
		listaInsts.setSelectedIndex(0); // Selecionar a primeira instalação
	}

	/**
	 * Método chamado quando uma instalação é selecionada pelo utilizador
	 * 
	 * @param selec a instalação selecionada
	 */
	private void instalacaoSelecionada(Instalacao selec) {
		// TODO colocar a info certa nas variáveis
		int id = selec.getId();
		String nome = selec.getNome();
		int nivel =selec.getNivel();
		List<Funcionario> autorizados = selec.getAutorizados();
		List<Funcionario> presentes = selec.getPresentes();

		// TODO para cada periodo de tempo do horário
		for (Periodo p : selec.getFuncionamento().getPeriodos()) {
			// TODO colocar as horas e minutos de inicio e fim de cada período
			String info = String.format("%02d:%02d - %02d:%02d\n", p.getInicio().getHoras(), p.getInicio().getMinutos(), p.getFim().getHoras(), p.getFim().getMinutos());
			horarioTA.append(info);
		}

		infoLbl.setText(id + " - " + nome);
		nivelLbl.setText("" + nivel);
		funcAutorizados.addAll(autorizados);
		funcPresentes.addAll(presentes);
	}

	/**
	 * Método chamado a cada segundo, para atualizar a informação dos funcionários
	 * da central
	 */
	private void updateFuncionarios() {
		// TODO para cada funcionário da central atualizar a info
		for (Funcionario f : central.getFuncionario()) {
			int id = f.getId();
			String nome = f.getNome();
			int nivel = f.getNivel();

			String ondeEsta = f.estaPresente() ?f.getInstalacao().getNome() : "---";

			Object data[] = { id, nome, nivel, ondeEsta };
			funcionariosModel.addRow(data);
		}
	}

	/**
	 * Método chamado a cada segundo, para atualizar os funcionários presentes numa
	 * instalação
	 * 
	 * @param inst a instalação da qual se querem listar os funcionários presentes
	 */
	private void updatePresentes(Instalacao inst) {
		funcPresentes.addAll(inst.getPresentes());
	}

	/**
	 * classe usada para mostrar a informação numa lista de instalações
	 */
	private class InstalacaoRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Instalacao inst = (Instalacao) value;

			// TODO colocar o nome da instalação
			String nome = inst.getNome();

			return super.getListCellRendererComponent(list, nome, index, isSelected, cellHasFocus);
		}
	}

	/**
	 * classe usada para mostrar a informação numa lista de funcionários
	 */
	private class FuncionarioRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Funcionario f = (Funcionario) value;
			
			String nome = f.getNome();

			return super.getListCellRendererComponent(list, nome, index, isSelected, cellHasFocus);
		}
	}

	/**
	 * chamado automaticamente a cada segundo para atualizar os funcionarios da
	 * central e os presentes na instalação selecionada
	 */
	private void updateInfo() {
		funcionariosModel.setRowCount(0);
		updateFuncionarios();
		if (listaInsts.getSelectedIndex() != -1) {
			funcPresentes.clear();
			updatePresentes(listaInsts.getSelectedValue());
		}
	}

	/**
	 * Chamado quando o utilizador seleciona uma instalação. limpa as informações
	 * anteriores e chama o método que as vai atualizar.
	 */
	private void updateInstalacao() {
		// limpar as infos da anterior instalação
		horarioTA.setText("");
		funcPresentes.clear();
		funcAutorizados.clear();
		// atualizar as infos da nova instalação
		instalacaoSelecionada(listaInsts.getSelectedValue());
	}

	/**
	 * Prepara a interface da aplicação
	 * 
	 * @param funcPanel o painel com a info dos funcionários
	 * @param instPanel o painel com a info das instalações
	 */
	private void setupInterface(JPanel funcPanel, JPanel instPanel) {
		setSize(500, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagConstraints gbc = new GridBagConstraints();
		GridBagLayout gbl = new GridBagLayout();
		JPanel panel = new JPanel(gbl);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = GridBagConstraints.REMAINDER;
		gbc.weighty = 0;
		gbl.addLayoutComponent(funcPanel, gbc);
		gbc.weighty = 1;
		gbc.weightx = 1;
		gbl.addLayoutComponent(instPanel, gbc);
		panel.add(funcPanel);
		panel.add(instPanel);
		getContentPane().add(panel);

		// prepara o timer que atualiza a informação a cada segundo
		Timer t = new Timer(1000, e -> updateInfo());
		t.start();
	}

	/**
	 * prepara o painel para apresentar as informações dos funcionários da central
	 * 
	 * @return o painel preparado
	 */
	private JPanel setupFuncionarios() {
		JPanel panel = new JPanel(new BorderLayout());
		String nomesColunas[] = { "Id", "Nome", "Nível", "Presente em" };
		funcionariosModel = new DefaultTableModel(nomesColunas, 0);

		JTable tabela = new JTable(funcionariosModel);
		tabela.setEnabled(false);
		tabela.getColumnModel().getColumn(0).setMaxWidth(30);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(2).setMaxWidth(30);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(100);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tabela.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tabela.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		panel.add(new JScrollPane(tabela));
		panel.setBorder(BorderFactory.createTitledBorder("Funcionários"));
		panel.setMinimumSize(new Dimension(200, 150));
		panel.setPreferredSize(new Dimension(200, 150));
		panel.setMaximumSize(new Dimension(200, 200));
		return panel;
	}

	/**
	 * prepara o painel com as informações das instalações da central
	 * 
	 * @param is a lista com as instalações da central
	 * @return o painel preparado
	 */
	private JPanel setupInstalacoes(Collection<Instalacao> is) {
		SpringLayout layout = new SpringLayout();
		JPanel panel = new JPanel(layout);
		listaInsts = new JList<Instalacao>(is.toArray(new Instalacao[is.size()]));
		listaInsts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaInsts.addListSelectionListener(e -> updateInstalacao());
		listaInsts.setCellRenderer(new InstalacaoRenderer());
		infoLbl = new JLabel("123 - nome da instalação");
		Font ff = infoLbl.getFont().deriveFont(18.0f);
		infoLbl.setFont(ff);
		nivelLbl = new JLabel("1");

		funcAutorizados = new DefaultListModel<Funcionario>();
		JList<Funcionario> autorizadosList = new JList<Funcionario>(funcAutorizados);
		autorizadosList.setCellRenderer(new FuncionarioRenderer());
		JScrollPane scrollAutorizados = new JScrollPane(autorizadosList);
		scrollAutorizados.setBorder(BorderFactory.createTitledBorder("Autorizados:"));

		horarioTA = new JTextArea(4, 50);
		horarioTA.setBorder(BorderFactory.createTitledBorder("Horário:"));
		horarioTA.setEditable(false);

		funcPresentes = new DefaultListModel<Funcionario>();
		JList<Funcionario> presentesList = new JList<Funcionario>(funcPresentes);
		presentesList.setCellRenderer(new FuncionarioRenderer());
		JScrollPane scroll = new JScrollPane(listaInsts);
		JScrollPane scrollPre = new JScrollPane(presentesList);

		panel.add(scroll);
		panel.add(infoLbl);
		panel.add(nivelLbl);
		panel.add(scrollPre);
		panel.add(scrollAutorizados);
		panel.add(horarioTA);
		scrollPre.setBorder(BorderFactory.createTitledBorder("Presentes:"));

		layout.putConstraint(SpringLayout.WEST, scroll, 2, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, scroll, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.SOUTH, scroll, -10, SpringLayout.SOUTH, panel);
		layout.putConstraint(SpringLayout.EAST, scroll, 150, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, infoLbl, 12, SpringLayout.EAST, scroll);
		layout.putConstraint(SpringLayout.NORTH, infoLbl, 2, SpringLayout.NORTH, scroll);
		layout.putConstraint(SpringLayout.WEST, nivelLbl, 0, SpringLayout.WEST, infoLbl);
		layout.putConstraint(SpringLayout.NORTH, nivelLbl, 2, SpringLayout.SOUTH, infoLbl);

		layout.putConstraint(SpringLayout.WEST, horarioTA, 0, SpringLayout.WEST, infoLbl);
		layout.putConstraint(SpringLayout.NORTH, horarioTA, 2, SpringLayout.SOUTH, nivelLbl);
		layout.putConstraint(SpringLayout.EAST, horarioTA, 2, SpringLayout.EAST, panel);

		layout.putConstraint(SpringLayout.WEST, scrollAutorizados, 0, SpringLayout.WEST, infoLbl);
		layout.putConstraint(SpringLayout.NORTH, scrollAutorizados, 2, SpringLayout.SOUTH, horarioTA);
		layout.putConstraint(SpringLayout.EAST, scrollAutorizados, 2, SpringLayout.EAST, panel);

		layout.putConstraint(SpringLayout.WEST, scrollPre, 0, SpringLayout.WEST, infoLbl);
		layout.putConstraint(SpringLayout.NORTH, scrollPre, 2, SpringLayout.SOUTH, scrollAutorizados);
		layout.putConstraint(SpringLayout.SOUTH, scrollPre, 0, SpringLayout.SOUTH, scroll);
		layout.putConstraint(SpringLayout.EAST, scrollPre, 2, SpringLayout.EAST, panel);
		panel.setBorder(BorderFactory.createTitledBorder("Instalações"));
		panel.setMinimumSize(new Dimension(200, 250));
		panel.setPreferredSize(new Dimension(200, 150));
		return panel;
	}

}
