package lab5;

/**
 * Representação de uma aposta assegurada, que além de possuir os atributos
 * normais de aposta possui um seguro associado.
 * 
 * @author Cecília Kemiac
 */
public class ApostaAssegurada extends Aposta{
	private Seguro seguro;
	
	/**
	 * Constrói a aposta assegurada através dos dados do apostador, valor, 
	 * previsão, valor assegurado e custo.
	 * 
	 * @param apostador o nome do apostador
	 * @param valor o valor da aposta
	 * @param previsao a previsão de ocorrência ou não.
	 * @param valorAssegurado o valor assegurado da aposta.
	 * @param custo o custo da aposta.
	 */
	public ApostaAssegurada(String apostador, int valor, String previsao, int valorAssegurado, int custo) {
		super(apostador, valor, previsao);
		this.seguro = new SeguroValor(custo, valorAssegurado);
	}
	
	/**
	 * Constrói a aposta assegurada através dos dados do apostador, valor, 
	 * previsão, taxa e custo.
	 * 
	 * @param apostador o nome do apostador
	 * @param valor o valor da aposta
	 * @param previsao a previsão de ocorrência ou não.
	 * @param taxa a taxa da aposta.
	 * @param custo o custo da aposta.
	 */
	public ApostaAssegurada(String apostador, int valor, String previsao, double taxa, int custo) {
		super(apostador, valor, previsao);
		this.seguro = new SeguroTaxa(custo, this.getValor(), taxa);
	}
	
	/**
	 * Retorna o atributo seguro associado a aposta.
	 * 
	 * @return o seguro.
	 */
	public Seguro getSeguro() {
		return this.seguro;
	}
	
	/**
	 * Altera o seguro associado a aposta, normalmente o formato do seguro que é
	 * alterado.
	 * 
	 * @param seguro.
	 */
	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}
	
	/**
	 * Retorna o valor assegurado que será somado ao caixa, excluindo-se o custo,
	 * caso a aposta perca.
	 * 
	 * @return valor assegurado.
	 */
	public int getCaixa() {
		return this.seguro.getCaixa();
	}
	
	/**
	 * Retorna a String que representa a aposta assegurada. A representação segue o 
	 * formato APOSTADOR - VALOR - PREVISÃO - SEGURO - VALOR OU TAXA.
	 * 
	 * @return a representação em String da aposta assegurada.
	 */
	@Override
	public String toString() {
		return super.toString() + this.seguro.toStringAposta();
	}
	
}