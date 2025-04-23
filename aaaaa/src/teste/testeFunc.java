package teste;

import poo.tempo.Hora;
import poo.tempo.Horario;
import securest.recurso.Funcionario;
import securest.recurso.Instalacao;

public class testeFunc {

	public static void main(String[] args) {
		Funcionario f1=new Funcionario(1, "CR", 5);
		System.out.println(f1);
		
		Horario h=new Horario();
		
		Instalacao i=new Instalacao(1, "sala a2", 5,h);
		System.out.println(i);
		
		System.out.println();
		System.out.println(i.podeEntrar(f1,new Hora())?"pode":"nao pode");
		i.addAutorizacao(f1);
		System.out.println(i.podeEntrar(f1,new Hora())?"pode":"nao pode");
		i.removeAutorizado(f1);
		System.out.println(i.podeEntrar(f1,new Hora())?"pode":"nao pode");

		/*
		 * Da pra alterar usando um get ver no trab
		 * Falha segunraca
		 * fez alteracaoes no funcionario lista unmodid
		 * 
		 
		i.getAutorizados().add(new Funcionario(2, "Rwad", 2));
		for(Funcionario f:i.getAutorizados()) {
			System.out.println(f);
		}
		*/
		
		Funcionario f=new Funcionario(2,"rui",2);
		i.addAutorizacao(f);
		i.entrar(f);
		System.out.println(i.podeEntrar(f1,new Hora())?"pode":"nao pode");
		i.sair(f);
	}

}
