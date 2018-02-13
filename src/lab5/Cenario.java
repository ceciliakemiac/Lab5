package lab5;
import java.util.*;

/**
 * Representação de um cenário de apostas. Cada cenário contém uma descrição,
 * um estado, um número e um Set de apostas.
 * 
 * @author Cecília Kemiac
 */
public class Cenario {
	private String descricao;
	private String estado = "Nao finalizado";
	private int numero;
	private HashSet<Aposta> apostas = new HashSet<>();
	private HashMap<Integer, ApostaAssegurada> apostasAsseguradas = new HashMap<>();
	
	/**
	 * Constrói o cenário a partir dos valores de descrição e do número do cenário.
	 * 
	 * @param descricao a descrição do cenário.
	 * @param numero o número do cenário.
	 */
	public Cenario(String descricao, int numero) {
		if(descricao == null) {
			throw new NullPointerException("Descrição nula");
		}
		if(descricao.equals("") || descricao.equals(" ")) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
		}
		
		this.descricao = descricao;
		this.numero = numero;
	}
	
	/**
	 * Retorna o número do cenário.
	 * 
	 * @return o número.
	 */
	public int getNumero() {
		return this.numero;
	}
	
	/**
	 * Retorna o estado do cenário.
	 * 
	 * @return o estado.
	 */
	public String getEstado() {
		return this.estado;
	}
	
	/**
	 * Muda o valor do estado do cenário a partir da String passada como parâmetro,
	 * tendo ele ocorrido ou não.
	 * 
	 * @param estado o estado do cenário.
	 */
	public void setEstado(String estado) {
		if(!estado.equals("Finalizado(ocorreu)") && !estado.equals("Finalizado(n ocorreu)")) {
			throw new IllegalArgumentException("Estado inexistente...");
		}
		
		this.estado = estado;
	}
	
	/**
	 * Retorna o bonus do cenario.
	 * 
	 * @return 0 para cenarios sem bonus.
	 */
	public int getBonus() {
		return 0;
	}
	
	/**
	 * Retorna uma aposta assegurada de acordo com seu número correspondente passado
	 * como parâmetro.
	 * 
	 * @param aposta o número da aposta assegurada.
	 * @return a aposta assegurada.
	 */
	public ApostaAssegurada getApostaAssegurada(int aposta) {
		return this.apostasAsseguradas.get(aposta);
	}
	
	/**
	 * Retorna um valor booleano para o estado do cenário, dependendo se ele ocorreu
	 * ou não.
	 * 
	 * @return um boolean.
	 */
	public boolean booleanaEstado() {
		if(this.estado.equals("Nao finalizado")) {
			throw new IllegalArgumentException("Cenário nao finalizado");
		}
		return this.estado.equals("Finalizado(ocorreu)");
	}
	
	/**
	 * Cadastra a aposta passada como parâmetro no cenario em questão.
	 * 
	 * @param aposta a aposta a ser cadastrada.
	 * @return um boolean indicando se a aposta foi cadastrada.
	 */
	public boolean cadastrarAposta(Aposta aposta) {
		if(aposta == null) {
			return false;
		}else {
			this.apostas.add(aposta);
			return true;
		}
	}
	
	/**
	 * Cadastrar a aposta assegurada passada como parâmetro no cenario em questão.
	 * 
	 * @param aposta a aposta a ser cadastrada.
	 * @return o número que representa a aposta assegurada cadastrada.
	 */
	public int cadastrarApostaAssegurada(ApostaAssegurada aposta) {
		if(aposta == null) {
			return -1;
		}else {
			this.apostas.add(aposta);
			this.apostasAsseguradas.put(this.apostasAsseguradas.size() + 1, aposta);
			return this.apostasAsseguradas.size();
		}
	}
	
	/**
	 * Retorna o valor total de todas as apostas do cenario em questão.
	 * 
	 * @return o valor total do cenario.
	 */
	public int valorTotalApostas() {
		int valor = 0;
		for(Aposta aposta : this.apostas) {
			valor += aposta.getValor();
		}
		return valor;
	}
	
	/**
	 * Retorna o total de apostas do cenario em questão.
	 * 
	 * @return total de apostas.
	 */
	public int totalApostas() {
		return this.apostas.size();
	}
	
	/**
	 * Retorna um HashSet com as Strings da representação de cada aposta do cenario.
	 * 
	 * @return o HashSet.
	 */
	public HashSet<String> representacaoApostas() {
		HashSet<String> apostas = new HashSet<>();
		for(Aposta aposta : this.apostas) {
			apostas.add(" - " + aposta.toString());
		}
		return apostas;
	}
	
	/**
	 * Retorna um HashSet de apostas com as apostas perdedoras (previsão estava errada),
	 * de acordo com o valor booleano passado como parâmetro que representa se o cenário
	 * ocorreu ou não.
	 * 
	 * @param ocorreu o valor booleano do estado do cenario.
	 * @return o HashSet de apostas perdedoras.
	 */
	public HashSet<Aposta> apostasPerdedoras(boolean ocorreu) {
		HashSet<Aposta> apostas = new HashSet<>();
		for(Aposta aposta : this.apostas) {
			if(aposta.booleanPrevisao() != ocorreu) {
				apostas.add(aposta);
			}
		}
		return apostas;
	}
	
	/**
	 * Retorna o valor assegurado das apostas asseguradas perdedoras que será destinado
	 * ao caixa do sistema. O parâmetro booleano representa se o cenário ocorreu ou não,
	 * e assim descobrimos quais apostas serão consideradas perdedoras.
	 * 
	 * @param ocorreu o valor booleano do estado do cenário.
	 * @return o valor dos seguros destinado ao caixa.
	 */
	public int apostasAsseguradasPerdedoras(boolean ocorreu) {
		int valor = 0;
		for(ApostaAssegurada aposta : this.apostasAsseguradas.values()) {
			if(aposta.booleanPrevisao() != ocorreu) {
				valor += aposta.getCaixa();
			}
		}
		return valor;
	}
	
	/**
	 * Lança o HashCode do cenario.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + numero;
		return result;
	}

	/**
	 * Analisa se dois cenário são iguais identificando-os a partir da descrição
	 * e do número.
	 * 
	 * @return um boolean.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cenario other = (Cenario) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (numero != other.numero)
			return false;
		return true;
	}

	/**
	 * Retorna a String que representa o cenário. A representação segue o formato
	 * NUMERO - DESCRIÇÃO, - ESTADO.
	 * 
	 * @return a representação em String do cenario.
	 */
	public String toString() {
		return this.numero + " - " + this.descricao + " - " + this.estado;
	}
	
}