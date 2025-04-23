package securest.recurso;

/**
 * Esta classe representa um funcionário da empresa que pode ou não entrar numa
 * instalação Cada funcionário deve ter um código identificador que permite
 * identificar inequivocamente o funcionário Cada funcionário também deve
 * possuir um nível de acesso que indica quais as instalações em que tem
 * autorização de entrar
 */
public class Funcionario {

	//criar atributos id nome,instlacao,nivel

	private int id=0;
	private String nome;
	private Instalacao instalacao=null;
	private int nivel;

	public Funcionario(int id, String nome, int nivel) {
		this.id = id;
		this.nome = checkNome(nome);
		this.nivel = Instalacao.checkNivel(nivel);
	}


	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = checkNome(nome);
	}

	public String checkNome(String nome) {

		if(nome==null || nome.isBlank())
			throw new IllegalArgumentException();

		return nome;
	}

	public Instalacao getInstalacao() {
		return instalacao;
	}

	public void setInstalacao(Instalacao instalacao) {
		this.instalacao = instalacao;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}


	public boolean equals(Funcionario f) {
		return id ==f.id;
	}

	public boolean estaPresente() {
		return instalacao!=null;
	}

	@Override
	public String toString() {
		return "O funcionario "+nome+" com id nº"+id+" trabalha na "+instalacao+" no nivel "+nivel;
	}

	public void entrar(Instalacao inst) {
		this.instalacao=inst;	
	}

	public void sair() {
		instalacao=null;
	}



}
