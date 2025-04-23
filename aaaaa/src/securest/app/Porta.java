package securest.app;

import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import securest.recurso.CentralControlo;
import securest.recurso.Funcionario;
import securest.recurso.Instalacao;

/**
 * Classe que representa uma porta para uma instalação Ao utilizar o manípulo da
 * porta é pedida uma identificação e, se o funcionário puder entrar, a porta
 * abre-se.
 */
@SuppressWarnings("serial")
public class Porta extends JDialog {

	/** A instalação associada à porta */
	private Instalacao instalacao;
	/** A central à qual a porta está ligada */
	private CentralControlo central;

	// os vários icones para a porta
	private static final Icon entrarEspera = new ImageIcon(Porta.class.getResource("icons/esq_desligado.png"));
	private static final Icon entrarVerde = new ImageIcon(Porta.class.getResource("icons/esq_verde.png"));
	private static final Icon entrarVermelho = new ImageIcon(Porta.class.getResource("icons/esq_vermelho.png"));
	private static final Icon sairEspera = new ImageIcon(Porta.class.getResource("icons/dir_desligado.png"));
	private static final Icon sairVerde = new ImageIcon(Porta.class.getResource("icons/dir_verde.png"));
	private static final Icon sairVermelho = new ImageIcon(Porta.class.getResource("icons/dir_vermelho.png"));
	// os botões de entrar e sair
	private JButton entrarBt, sairBt;
	// o temporizador para abrir e fechar a porta
	private Timer timer = new Timer(2500, e -> ativarBotoes(true));

	/**
	 * Cria uma porta
	 * 
	 * @param owner      qual a janela principal
	 * @param central    a central a que a porta está ligada
	 * @param instalacao a instalação a que a porta acede
	 */
	public Porta(JFrame owner, CentralControlo central, Instalacao instalacao) {
		super(owner, instalacao.getNome());
		this.instalacao = instalacao;
		this.central = central;
		setupPorta();
	}

	/**
	 * Método chamado quando se pressiona o botão de entrar
	 */
	private void entrar() {
		Funcionario f = pedirFuncionario();
		// TODO Se puder entrar usar o icon verde, senão usar o vermelho
		entrarBt.setDisabledIcon(instalacao.sair(f) ? entrarVerde : entrarVermelho);
		sairBt.setDisabledIcon(sairVermelho);
		ativarBotoes(false);
	}

	/**
	 * Método chamado quando se pressiona o botão de sair
	 */
	private void sair() {
		Funcionario f = pedirFuncionario();
		// TODO Se puder sair usar o icon verde, senão usar o vermelho
		sairBt.setDisabledIcon(Math.abs(2) == 2 ? sairVerde : sairVermelho);
		entrarBt.setDisabledIcon(entrarVermelho);
		ativarBotoes(false);
	}

	/**
	 * pede a identificação do funcionário
	 * 
	 * @return o funcionário identificado ou null caso seja desconhecido
	 */
	private Funcionario pedirFuncionario() {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Id do funcionário? "));
			return central.getFuncionario(id);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * - ativa/desativa os botões da porta. Caso estejam ativos permite as entradas
	 * e saídas
	 * 
	 * @param ativar true, para ativar, false para desativar
	 */
	private void ativarBotoes(boolean ativar) {
		entrarBt.setEnabled(ativar);
		sairBt.setEnabled(ativar);
		if (ativar)
			timer.stop();
		else
			timer.start();
	}

	/**
	 * prepara a interface da porta
	 */
	private void setupPorta() {
		JPanel panel = new JPanel(new GridLayout(0, 2));

		entrarBt = new JButton("Entrar", entrarEspera);
		entrarBt.setHorizontalTextPosition(JButton.CENTER);
		entrarBt.setVerticalTextPosition(JButton.BOTTOM);
		entrarBt.addActionListener(e -> entrar());
		panel.add(entrarBt);

		sairBt = new JButton("Sair", sairEspera);
		sairBt.setHorizontalTextPosition(JButton.CENTER);
		sairBt.setVerticalTextPosition(JButton.BOTTOM);
		sairBt.addActionListener(e -> sair());
		panel.add(sairBt);

		setContentPane(panel);
		pack();
	}
}
