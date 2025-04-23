package securest.recurso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import poo.tempo.Hora;
import poo.tempo.Horario;

/**
 * Esta classe representa uma instalação segura. Possui um identificador único e
 * um nível de acesso. Possui ainda um horário de funcionamento e uma lista de
 * funcionários com credenciais inferiores que podem aceder
 */
public class Instalacao {

	private static final int NIVEL_MIN=1;
	private static final int NIVEL_MAX=5;


	private int id;
	private String nome;
	private int nivel;
	private Horario funcionamento=new Horario();
	private ArrayList<Funcionario> autorizados = new ArrayList<Funcionario>();
	private ArrayList<Funcionario> presentes = new ArrayList<Funcionario>();


	public Instalacao(int id, String nome, int nivel, Horario h) {

		this.id = id;
		this.nome = checkNome(nome);
		this.nivel = nivel;
		funcionamento=Objects.requireNonNull(h);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Horario getFuncionamento() {
		return funcionamento;
	}

	public void setFuncionamento(Horario funcionamento) {
		this.funcionamento = funcionamento;
	}


	//-----------------------------------EXEMPLO TRAB---------------------------------------

	public List<Funcionario> getAutorizados() {
		return Collections.unmodifiableList(autorizados);
	}



	public void setAutorizados(ArrayList<Funcionario> autorizados) {
		this.autorizados = autorizados;
	}



	public ArrayList<Funcionario> getPresentes() {
		return presentes;
	}



	public void setPresentes(ArrayList<Funcionario> presentes) {
		this.presentes = presentes;
	}



	public static int getNivelMin() {
		return NIVEL_MIN;
	}



	public static int getNivelMax() {
		return NIVEL_MAX;
	}



	public String checkNome(String nome) {

		if(nome==null || nome.isBlank())
			throw new IllegalArgumentException();

		return nome;
	}


	static int checkNivel(int nivel) {
		if(nivel<NIVEL_MIN || nivel>NIVEL_MAX) {
			throw new IllegalArgumentException();
		}

		return nivel;
	}

	public boolean equals(Instalacao i) {
		return id==i.id;
	}


	public String toString() {
		return nome+ " id: "+id+", nivel = "+nivel;
	}

	public void addAutorizacao(Funcionario f) {
		autorizados.add(f);
	}

	public void removeAutorizado(Funcionario f) {
		autorizados.remove(f);
	}

	public boolean podeEntrar(Funcionario f,Hora h) {
		if(!funcionamento.estaDentro(h))
			return false;
		if(f.estaPresente())
			return false;
		if(autorizados.contains(f))
			return true;
		return f.getNivel()>nivel;
	}

	public boolean entrar(Funcionario f) {
		if(!podeEntrar(f, new Hora()))
			return false;
		presentes.add(f);
		f.entrar(this);
		return true;
	}

	public boolean sair(Funcionario f) {
		if(presentes.contains(f)) {
			presentes.remove(f);
			f.sair();
			return true;
		}

		return false;

	}



}
