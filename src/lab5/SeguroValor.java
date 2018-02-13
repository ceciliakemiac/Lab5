package lab5;

/**
 * Representação de um seguro por valor, que além de possuir os atributos de seguro
 * possui o valor assegurado.
 * 
 * @author Cecília Kemiac
 */
public class SeguroValor extends Seguro{
	private int valorAssegurado;
	
	/**
	 * Constrói o seguro por valor através do custo e do valor assegurado.
	 * 
	 * @param custo o custo do seguro. o valor assegurado do seguro.
	 * @param valorAssegurado
	 */
	public SeguroValor(int custo, int valorAssegurado) {
		super(custo);
		this.valorAssegurado = valorAssegurado;
	}
	
	/**
	 * Retorna o valor em reais do valor assegurado.
	 * @return
	 */
	public double getValorReais() {
		return this.valorAssegurado / 100.0;
	}
	
	/**
	 * Retorna o valor assegurado que será destinado ao caixa.
	 */
	public int getCaixa() {
		int valor = 0;
		valor -= this.valorAssegurado;
		return valor;
	}
	
	/**
	 * Representação em String para a aposta que o conter.
	 */
	public String toStringAposta() {
		return String.format(" - ASSEGURADA(VALOR) - R$ %.2f", getValorReais());
	}
	
}
