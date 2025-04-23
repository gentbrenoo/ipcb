package poo.tempo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Horario {
	//add lista pra o periodo
	private ArrayList<Periodo> periodos=new ArrayList<>();
	public Horario() {

	}

	public List<Periodo> getPeriodos(){
		return Collections.unmodifiableList(periodos);
	}

	@Override
	public String toString() {
		return "Horario: " + periodos;
	}

	public void removePeriodo(int idx) {
		periodos.remove(idx);
	}

	public boolean estaDentro(Hora h) {
		for(Periodo p:periodos) {
			if(p.estaDentro(h))
				return true;
		}

		return false;
	}

	public void addPeriodo(Periodo p) {
		for(int i=periodos.size()-1;i>=0;i--) {
			Periodo op= periodos.get(i);
			
			if(op.getFim().compareTo(p.getInicio())<0){
				periodos.add(i + 1,p);
				return;
			}
			
			if(op.interseta(p)) {
				periodos.remove(i);
				p.junta(op);
			}
		}
		
		periodos.add(0,p);
	
	}
}
