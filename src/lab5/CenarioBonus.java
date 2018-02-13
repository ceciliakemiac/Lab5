package lab5;

/**
 * Representação de um cenário bonus. Cada cenário possui uma descrição, um número
 * e o valor do bonus.
 * 
 * @author Cecília Kemiac
 */
public class CenarioBonus extends Cenario{
	private int bonus;

	/**
	 * Constrói o cenário bonus através de seus atributos.
	 * 
	 * @param descricao a descrição do cenário.
	 * @param numero o número que representa o cenário.
	 * @param bonus o valor do bonus.
	 */
	public CenarioBonus(String descricao, int numero, int bonus) {
		super(descricao, numero);
		
		if(bonus <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Bonus invalido");
		}
		this.bonus = bonus;
	}
	
	/**
	 * Retorna o valor do bonus do cenário.
	 */
	@Override
	public int getBonus() {
		return this.bonus;
	}
	
	/**
	 * Retorna o valor do bonus do cenário em reais.
	 * 
	 * @return o bonus em reais.
	 */
	public double getBonusReais() {
		return this.bonus / 100.00;
	}
	
	/**
	 * Retorna a representação em String do cenário bonus, no formato padrão de um cenário
	 * adicionado o valor do bonus.
	 */
	@Override
	public String toString() {
		return super.toString() + String.format(" - R$ %.2f", getBonusReais());
	}
	
}
