package lab5;
import java.util.*;

public class CadastroComparator implements Comparator<Cenario> {
	
	public int compare(Cenario c1, Cenario c2) {
		if(c1.getNumero() == c2.getNumero()) {
			return 0;
		}
		if(c1.getNumero() < c2.getNumero()) {
			return -1;
		}else {
			return 1;
		}
	}
	
	/*public void ordenaCenarios(ArrayList<Cenario> cenarios) {
		Collections.sort(cenarios, new CadastroComparator());
	}*/
	
}
