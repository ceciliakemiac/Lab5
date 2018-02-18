package lab5;
import java.util.*;

public class Ordem {
	private String ordem = "cadastro";
	
	public void setOrdem(String ordem) {
		if(ordem.equals(" ") || ordem == null) {
			throw new IllegalArgumentException("Erro ao alterar ordem: Ordem nao pode ser vazia ou nula");
		}
		if(!ordem.equals("nome") && !ordem.equals("cadastro") && !ordem.equals("apostas")) {
			throw new IllegalArgumentException("Erro ao alterar ordem: Ordem invalida");
		}
		
		this.ordem = ordem;
	}
	
	public void ordenaCenarios(ArrayList<Cenario> cenarios) {
		if(ordem.equals("cadastro")) {
			Collections.sort(cenarios, new CadastroComparator());
		}else if(ordem.equals("nome")) {
			Collections.sort(cenarios, new NomeComparator());
		}else if(ordem.equals("apostas")) {
			Collections.sort(cenarios, new ApostasComparator());
		}
	}
	
}
