package lab5;

public class CenarioBonus extends Cenario{
	private int bonus;

	public CenarioBonus(String descricao, int numero, int bonus) {
		super(descricao, numero);
		
		if(bonus <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Bonus invalido");
		}
		this.bonus = bonus;
	}
	
	@Override
	public int getBonus() {
		return this.bonus;
	}
	
	public double getBonusReais() {
		return this.bonus / 100.00;
	}
	
	@Override
	public String toString() {
		return super.toString() + String.format(" - R$ %.2f", getBonusReais());
	}
	
}
