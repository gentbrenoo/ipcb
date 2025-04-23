package securest.recurso;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase representa a central de controlo do sistema Possui as listas de
 * instalações e de funcionários Sugere-se que usem um mapa para melhorar a
 * pesquisa por id de funcionários e de instalações
 */
public class CentralControlo {

	private Map<Integer, Instalacao> instalacoes = new HashMap<Integer, Instalacao>();
	private Map<Integer, Funcionario> funcionarios = new HashMap<Integer, Funcionario>();

	public CentralControlo() {
	}

	/**
	 * adicionar um funcionário à central de controlo
	 * 
	 * @param f novo funcionário a adicionar
	 */
	
	public void addFuncionario(Funcionario f) {
		funcionarios.put(f.getId(),f);
	}

	/**
	 * devolve o funcionário com o identificador especificado
	 * 
	 * @param id identificador do Funcionario a obter
	 * @return Funcionário com o identificador especificado
	 */
	
	public Funcionario getFuncionario(int id) {
		return funcionarios.get(id);
	}

	/**
	 * remove o funcionário do sistema
	 * 
	 * @param id identificador do Funcionario a remover
	 */
	
	public void removeFuncionario(int id) {
		funcionarios.remove(id);
	}

	/**
	 * devolve uma coleção com todos os funcionários existentes no sistema
	 * 
	 * @return uma coleção com todas os funcionários existentes no sistema
	 */

	public Collection<Funcionario> getFuncionario(){
		return Collections.unmodifiableCollection(funcionarios.values());
	}

	/**
	 * adiciona uma instalação ao sistema
	 * 
	 * @param inst instalação a adicionar
	 */
	
	public void addInstalacao(Instalacao inst) {
		instalacoes.put(inst.getId(), inst);
	}

	/**
	 * devolve a instalação com o identificador especificado
	 * 
	 * @param id identificador da instalação pretendida
	 * @return a instalação com o identificador especificado
	 */
	
	public Instalacao getInstalacao(int id) {
		return instalacoes.get(id);
	}

	/**
	 * remove uma instalação do sistema
	 * 
	 * @param id identificador da instalação a remover
	 */
	
	public void removeInstalacao(int id) {
		instalacoes.remove(id);
	}

	/**
	 * devolve uma coleção com todas as instalações existentes no sistema
	 * 
	 * @return uma coleção com todas as instalações existentes no sistema
	 */
	
	public Collection<Instalacao> getInstalacoes() {
		return Collections.unmodifiableCollection(instalacoes.values());
	}





} 


