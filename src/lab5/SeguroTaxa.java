package lab5;

/**
 * Representação de um seguro por taxa, que além de possuir os atributos de seguro
 * possui taxa e valor(é o valor da aposta que conterá esse seguro).
 * 
 * @author Cecília Kemiac
 */
public class SeguroTaxa extends Seguro{
	private double taxa;
	private int valor;
	
	/**
	 * Constrói o seguro por taxa através de seus atributos, além dos de seu pai.
	 * 
	 * @param custo o custo do seguro.
	 * @param valor o valor da aposta.
	 * @param taxa a taxa do seguro.
	 */
	public SeguroTaxa(int custo, int valor, double taxa) {
		super(custo);
		this.taxa = taxa;
		this.valor = valor;
	}
	
	/**
	 * Retorna o valor assegurado que deverá ser destinado ao caixa.
	 */
	public int getCaixa() {
		double valor = 0;
		valor -= this.taxa * this.valor;
		return (int)valor;
	}
	
	/**
	 * Representação em String para a aposta que o conter.
	 */
	public String toStringAposta() {
		return String.format(" - ASSEGURADA(TAXA) - %d%%", (int)(this.taxa * 100));
	}
	
}
