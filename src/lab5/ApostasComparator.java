package lab5;
import java.util.*;

public class ApostasComparator implements Comparator<Cenario> {
	
	public int compare(Cenario c1, Cenario c2) {
		if(c1.totalApostas() == c2.totalApostas()) {
			return 0;
		}
		if(c1.totalApostas() < c2.totalApostas()) {
			return 1;
		}else {
			return -1;
		}
	}
	
	/*public void ordenaCenarios(ArrayList<Cenario> cenarios) {
		Collections.sort(cenarios, new ApostasComparator());
	}*/
	
}
