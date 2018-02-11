package lab5;

public class SeguroTaxa extends Seguro{
	private double taxa;
	private int valor;
	
	public SeguroTaxa(int custo, int valor, double taxa) {
		super(custo);
		this.taxa = taxa;
		this.valor = valor;
	}
	
	public int getCaixa() {
		double valor = 0;
		valor -= this.taxa * this.valor;
		return (int)valor;
	}
	
	public String toStringAposta() {
		return String.format(" - ASSEGURADA(TAXA) - %d%%", (int)(this.taxa * 100));
	}
	
}
