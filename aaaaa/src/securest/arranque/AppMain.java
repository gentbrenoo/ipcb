package securest.arranque;

import java.awt.Toolkit;
import java.util.List;

import poo.tempo.Hora;
import poo.tempo.Horario;
import poo.tempo.Periodo;
import securest.app.JanelaCentral;
import securest.app.Porta;
import securest.recurso.CentralControlo;
import securest.recurso.Funcionario;
import securest.recurso.Instalacao;

public class AppMain {

	/**
	 * despoleta a aplicação
	 */
	public static void main(String[] args) {

		// criar a central de controlo
		CentralControlo central = new CentralControlo();

		/*
		 * TODO Criar aqui as instalações e funcionários indicados no enunciado
		 */
		//central.addFuncionario(null);
		//criar instalacoes
		Funcionario f1=new Funcionario(11,"Asbrubal",2);
		central.addFuncionario(f1);
		
		central.addFuncionario(new Funcionario(12,"Josefina",1));
		
		
		Horario h1=new Horario();
		h1.addPeriodo(new Periodo(new Hora(0,0,0),new Hora(20,0,0)));
		Instalacao i1 = new Instalacao(21,"Sala 1",1,h1);
		central.addInstalacao(i1);

	
		// criar a aplicação propriamente dita
		JanelaCentral app = new JanelaCentral("SecurEST", central);
		final int espacoEntreJanelas = 20;
		int x = espacoEntreJanelas, y = espacoEntreJanelas;
		app.setLocation(x, y);
		app.setVisible(true);

		// criar as portas à direita da janela principal
		int xEsquerda = x + app.getWidth();
		int larguraEcran = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		x = xEsquerda;

		// TODO percorrer as instalações todas e criar uma porta para elas
		for (Instalacao i : central.getInstalacoes()) {
			Porta p = new Porta(app, central, i);
			p.setLocation(x, y);
			p.setVisible(true);
			// calcular o x da próxima janela
			x += p.getWidth() + espacoEntreJanelas;
			if (x > larguraEcran - p.getWidth()) {
				x = xEsquerda;
				y += p.getHeight() + espacoEntreJanelas;
			}
		}

	}

}
