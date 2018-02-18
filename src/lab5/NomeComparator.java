package lab5;
import java.util.*;

public class NomeComparator implements Comparator<Cenario> {

	public int compare(Cenario c1, Cenario c2) {
		return c1.getDescricao().compareTo(c2.getDescricao());
	}
	
	/*public void ordenaCenarios(ArrayList<Cenario> cenarios) {
		Collections.sort(cenarios, new NomeComparator());
	}*/
	
}
