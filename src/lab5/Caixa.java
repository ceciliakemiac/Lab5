package lab5;

/**
 * Representação do caixa do sistema. Todo caixa contém um valor e uma taxa.
 * 
 * @author Cecília Kemiac
 */
public class Caixa {
	private int valor;
	private double taxa;
	
	/**
	 * Constrói o caixa a partir de seu valor e taxa.
	 * 
	 * @param valor o valor inicial do caixa.
	 * @param taxa a taxa do caixa.
	 */
	public Caixa(int valor, double taxa) {
		if(valor < 0) {
			throw new IllegalArgumentException("Erro na inicializacao: Caixa nao pode ser inferior a 0");
		}
		if(taxa < 0) {
			throw new IllegalArgumentException("Erro na inicializacao: Taxa nao pode ser inferior a 0");
		}
		if(taxa > 1) {
			throw new IllegalArgumentException("Erro na inicializacao: Taxa nao pode ser superior a 1");
		}
		
		this.valor = valor;
		this.taxa = taxa;
	}
	
	/**
	 * Retorna o valor do caixa em centavos.
	 * 
	 * @return o valor.
	 */
	public int getValor() {
		return this.valor;
	}
	
	/**
	 * Altera o valor do atributo valor do caixa.
	 * 
	 * @param valor o novo valor.
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	/**
	 * Retorna a taxa do caixa.
	 * 
	 * @return a taxa.
	 */
	public double getTaxa() {
		return this.taxa;
	}
	
	/**
	 * Adiciona o valor passado como parâmetro ao caixa.
	 * 
	 * @param valor o valor a ser adicionado.
	 */
	public void adicionaValor(int valor) {
		this.valor  += valor;
	}

	/**
	 * Lança o HashCode do caixa.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(taxa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + valor;
		return result;
	}

	/**
	 * Analisa se dois caixas são iguais identificando-os a partir da taxa e do valor.
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
		Caixa other = (Caixa) obj;
		if (Double.doubleToLongBits(taxa) != Double.doubleToLongBits(other.taxa))
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}

	/**
	 * Retorna a String que representa o caixa. A representação segue o formato
	 * Caixa [valor= CAIXA, taxa=TAXA].
	 * 
	 * @return a representação em String do caixa.
	 */
	@Override
	public String toString() {
		return "Caixa [valor=" + this.valor + ", taxa=" + this.taxa + "]";
	}
	
}