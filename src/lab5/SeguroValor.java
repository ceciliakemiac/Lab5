package lab5;

public class SeguroValor extends Seguro{
	private int valorAssegurado;
	
	public SeguroValor(int custo, int valorAssegurado) {
		super(custo);
		this.valorAssegurado = valorAssegurado;
	}
	
	public double getValorReais() {
		return this.valorAssegurado / 100.0;
	}
	
	public int getCaixa() {
		int valor = 0;
		valor -= this.valorAssegurado;
		return valor;
	}
	
	public String toStringAposta() {
		return String.format(" - ASSEGURADA(VALOR) - R$ %.2f", getValorReais());
	}
	
}
