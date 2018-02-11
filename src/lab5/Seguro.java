package lab5;

public abstract class Seguro {
	private int custo;
	
	public Seguro(int custo) {
		this.custo = custo;
	}
	
	public int getCusto() {
		return this.custo;
	}
	
	public abstract int getCaixa();
	
	public abstract String toStringAposta();
	
}
