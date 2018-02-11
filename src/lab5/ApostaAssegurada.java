package lab5;

public class ApostaAssegurada extends Aposta{
	private Seguro seguro;
	
	public ApostaAssegurada(String apostador, int valor, String previsao, int valorAssegurado, int custo) {
		super(apostador, valor, previsao);
		seguro = new SeguroValor(custo, valorAssegurado);
	}
	
	public ApostaAssegurada(String apostador, int valor, String previsao, double taxa, int custo) {
		super(apostador, valor, previsao);
		seguro = new SeguroTaxa(custo, this.getValor(), taxa);
	}
	
	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}
	
	public int retornoCaixa(double taxaCaixa) {
		double valor = this.getValor() * taxaCaixa;
		return (int)valor;
	}
	
	public int getCaixa() {
		return this.seguro.getCaixa();
	}
	
	public String toString() {
		return super.toString() + " " + this.seguro.toStringAposta();
	}
	
}
