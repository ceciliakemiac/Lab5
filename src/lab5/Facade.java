package lab5;

import java.util.HashSet;

import easyaccept.EasyAccept;

public class Facade {
	private Sistema sistema;
	
	/**
	 * Método para rodar os testes de unidade.
	 * 
	 * @param args os argumentos.
	 */
	public static void main(String[] args) {
		args = new String[] {"lab5.Sistema", "testes_de_aceitacao/us1_test.txt", "testes_de_aceitacao/us2_test.txt",
				"testes_de_aceitacao/us3_test.txt", "testes_de_aceitacao/us4_test.txt", "testes_de_aceitacao/us5_test.txt",
				"testes_de_aceitacao/us6_test.txt"};
		EasyAccept.main(args);
	}
	
	public int cadastrarCenario(String descricao) {
		return sistema.cadastrarCenario(descricao);
	}
	
	public int cadastrarCenario(String descricao, int bonus) {
		return sistema.cadastrarCenario(descricao, bonus);
	}
	
	/**
	 * Retorna a String que representa o cenario cujo número foi passado como parâmetro.
	 * 
	 * @param num o número do cenario.
	 * @return a representação em String do cenario.
	 */
	public String exibirCenario(int num) {
		return sistema.exibirCenario(num);
	}
	
	/**
	 * Retorna um HashSet de Strings das representações de todos os cenarios cadastrados
	 * no sistemha.
	 * 
	 * @return o HashSet de Strings.
	 */
	public HashSet<String> exibirCenarios() {
		return sistema.exibirCenarios();
	}
	
	/**
	 * Cadastra uma aposta no cenario passado como parâmetro.
	 * 
	 * @param cenario o número do cenario
	 * @param apostador o nome do apostador.
	 * @param valor o valor da aposta.
	 * @param previsao a previsão da aposta.
	 * @return um boolean representando se a aposta foi cadastrada ou não.
	 */
	public boolean cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		return sistema.cadastrarAposta(cenario, apostador, valor, previsao);
	}
	
	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo) {
		return sistema.cadastrarApostaSeguraValor(cenario, apostador, valor, previsao, valorAssegurado, custo);
	}
	
	public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
		return sistema.cadastrarApostaSeguraTaxa(cenario, apostador, valor, previsao, taxa, custo);
	}
	
	public int alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
		return sistema.alterarSeguroValor(cenario, apostaAssegurada, valor);
	}
	
	public int alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa) {
		return sistema.alterarSeguroTaxa(cenario, apostaAssegurada, taxa);
	}
	
	/**
	 * O valor total das apostas do cenario passado como parâmetro.
	 * 
	 * @param cenario o número do cenario.
	 * @return o valor total das apostas.
	 */
	public int valorTotalDeApostas(int cenario) {
		return sistema.valorTotalDeApostas(cenario);
	}
	
	/**
	 * Retorna o total de apostas do cenario passado como parâmetro.
	 * 
	 * @param cenario o número do cenario.
	 * @return o valor total.
	 */
	public int totalDeApostas(int cenario) {
		return sistema.totalDeApostas(cenario);
	}
	
	/**
	 * Retorna um HashSet que contém a representação das apostas do cenario passado como
	 * parâmetro.
	 * 
	 * @param cenario o número do cenario.
	 * @return o HashSet.
	 */
	public HashSet<String> representacaoApostas(int cenario) {
		return sistema.representacaoApostas(cenario);
	}
	
	/**
	 * Fecha o cenario passado como parâmetro através da variável booleana também passada
	 * como parâmetro, que indica se ele ocorreu ou não.
	 * 
	 * @param cenario o número do cenario.
	 * @param ocorreu a variável booleana.
	 */
	public void fecharAposta(int cenario, boolean ocorreu) {
		sistema.fecharAposta(cenario, ocorreu);
	}
	
	/**
	 * Retorna o valor em centavos de um cenario encerrado que será destinado
	 * ao caixa.
	 * 
	 * @param cenario o número do cenario.
	 * @return o valor em cantavos.
	 */
	public int getCaixaCenario(int cenario) {
		return sistema.getCaixaCenario(cenario);
	}
	
	/**
	 * Retorna o valor total de um cenario encerrado que será destinado a distribuição
	 * entre as apostas vencedoras.
	 * 
	 * @param cenario o numero do cenario.
	 * @return o valor em centavos destinado as apostas vencedoras.
	 */
	public int getTotalRateioCenario(int cenario) {
		return sistema.getTotalRateioCenario(cenario);
	}
	
}