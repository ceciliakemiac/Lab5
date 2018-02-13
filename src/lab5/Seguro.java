package lab5;

/**
 * Representação geral de um seguro. Todo seguro contém em comum pelo menos
 * um valor para custo.
 * 
 * @author Cecília Kemiac
 */
public abstract class Seguro {
	private int custo;
	
	/**
	 * Constrói um seguro através do valor de custo.
	 * 
	 * @param custo o custo do seguro.
	 */
	public Seguro(int custo) {
		this.custo = custo;
	}
	
	/**
	 * Retorna o valor do custo.
	 * 
	 * @return o custo.
	 */
	public int getCusto() {
		return this.custo;
	}
	
	/**
	 * Método abstrato que quer retornar o valor assegurado que será destinado ao caixa.
	 */
	public abstract int getCaixa();
	
	/**
	 * Retorna a representação em string do segura da aposta que conterá esse seguro.
	 */
	public abstract String toStringAposta();
	
}
