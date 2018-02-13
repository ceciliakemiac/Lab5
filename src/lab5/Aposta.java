package lab5;

/**
 * Representação de uma aposta. Toda aposta contém o nome do apostador,
 * o valor e a previsão de ocorrência ou não.
 * 
 * @author Cecília Kemiac
 */
public class Aposta {
	private String apostador;
	private int valor;
	private String previsao;
	
	/**
	 * Constrói a aposta a partir dos dados do apostador, valor e previsão.
	 * 
	 * @param apostador o nome do apostador.
	 * @param valor o valor da aposta.
	 * @param previsao a previsão de ocorrência ou não.
	 */
	public Aposta(String apostador, int valor, String previsao) {
		if(apostador == null) {
			throw new NullPointerException("Apostador nulo");
		}
		if(apostador.equals("") || apostador.equals("  ")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		}
		if(valor <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		}
		if(previsao.equals("   ") || previsao.equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		}
		if(!previsao.equals("VAI ACONTECER") && !previsao.equals("N VAI ACONTECER")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao invalida");
		}
		
		this.apostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
	}
	
	/**
	 * Retorna a String que representa o nome do apostador
	 * 
	 * @return o apostador
	 */
	public String getApostador() {
		return this.apostador;
	}
	
	/**
	 * Retorna o valor em centavos da aposta.
	 * 
	 * @return o valor.
	 */
	public int getValor() {
		return this.valor;
	}
	
	/**
	 * Retorna o valor em reais da aposta.
	 * 
	 * @return o valor em reais.
	 */
	public double getValorReais() {
		return valor / 100.0;
	}
	
	/**
	 * Retorna a String que representa a previsão da aposta.
	 * 
	 * @return a previsão.
	 */
	public String getPrevisao() {
		return this.previsao;
	}
	
	/**
	 * Retorna um valor booleano para a previsão, dependendo se ela indica que
	 * vai acontecer ou não.
	 * 
	 * @return um boolean.
	 */
	public boolean booleanPrevisao() {
		return this.previsao.equals("VAI ACONTECER");
	}
	
	/**
	 * Retorna o valor da aposta que deve ser mandado para o caixa caso ela perca.
	 * 
	 * @param taxaCaixa
	 * @return 
	 */
	public int retornoCaixa(double taxaCaixa) {
		double valor = this.getValor() * taxaCaixa;
		return (int)valor;
	}

	/**
	 * Retorna a String que representa a aposta. A representação segue o formato
	 * APOSTADOR - VALOR - PREVISÃO.
	 * 
	 * @return a representação em String da aposta.
	 */
	public String toString() {
		String result = String.format("%s - R$%.2f - %s", this.apostador, getValorReais()
				, this.previsao);
		return result;
	}
	
}
