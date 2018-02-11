package lab5;
import java.util.*;

/**
 * Representação do sistema de apostas. Cada sistema contém um caixa e um ArrayList
 * de cenarios.
 * 
 * @author Cecília Kemiac
 */
public class Sistema {
	private Caixa caixa;
	private ArrayList<Cenario> cenarios = new ArrayList<>();
	
	/**
	 * Inicializa o caixa, de acordo com os parâmetros que representam o valor e a taxa.
	 * 
	 * @param valor o valor do caixa do sistema.
	 * @param taxa a taxa do caixa do sistema.
	 * @return o caixa.
	 */
	public Caixa inicializa(int valor, double taxa) {
		caixa = new Caixa(valor, taxa);
		return caixa;
	}
	
	/**
	 * Retorna o valor em centavos do caixa do sistema.
	 * 
	 * @return o valor do caixa.
	 */
	public int getCaixa() {
		return this.caixa.getValor();
	}
	
	/**
	 * Cadastra um cenario no sistema a partir da descrição fornecida como parâmetro
	 * e retorna o número gerado do cenario cadastrado.
	 * 
	 * @param descricao a descrição do cenário
	 * @return
	 */
	public int cadastrarCenario(String descricao) {
		if(descricao == null) {
			throw new NullPointerException("Descrição nula");
		}
		
		Cenario cen = new Cenario(descricao, this.cenarios.size() + 1);
		this.cenarios.add(cen);
		return cen.getNumero();
	}
	
	/**
	 * Cadastrar um cenarioBonus no sistema apartir da descrição fornecida como parâmetro
	 * e do bônus e retorna o número gerado do cenário cadastrado.
	 * 
	 * @param descricao a descrição do cenário
	 * @param bonus o valor do bonus
	 * @return
	 */
	public int cadastrarCenario(String descricao, int bonus) {
		if(descricao == null) {
			throw new NullPointerException("Descrição nula");
		}
		
		if(bonus > this.caixa.getValor()) {
			this.caixa.setValor(0);
		}else {
			this.caixa.setValor(this.caixa.getValor() - bonus);
		}
		
		CenarioBonus cen = new CenarioBonus(descricao, this.cenarios.size() + 1, bonus);
		this.cenarios.add(cen);
		return cen.getNumero();
	}
	
	/**
	 * Retorna o cenario a partir do número passado como parâmetro.
	 * 
	 * @param num o número do cenario retornado.
	 * @return o tal cenario.
	 */
	public Cenario getCenario(int num) {
		return this.cenarios.get(num - 1);
	}
	
	/**
	 * Retorna a String que representa o cenario cujo número foi passado como parâmetro.
	 * 
	 * @param num o número do cenario.
	 * @return a representação em String do cenario.
	 */
	public String exibirCenario(int num) {
		if(num <= 0) {
			throw new IllegalArgumentException("Erro na consulta de cenario: Cenario invalido");
		}
		if(num > this.cenarios.size()) {
			throw new IndexOutOfBoundsException("Erro na consulta de cenario: Cenario nao cadastrado");
		}
		
		Cenario cen = getCenario(num);
		return cen.toString();
	}
	
	/**
	 * Retorna um HashSet de Strings das representações de todos os cenarios cadastrados
	 * no sistemha.
	 * 
	 * @return o HashSet de Strings.
	 */
	public HashSet<String> exibirCenarios() {
		HashSet<String> cenarios = new HashSet<>();
		for(int i = 0; i < this.cenarios.size(); i++) {
			cenarios.add(exibirCenario(i + 1));
		}
		return cenarios;
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
		if(cenario <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Cenario invalido");
		}
		if(cenario > this.cenarios.size()) {
			throw new IndexOutOfBoundsException("Erro no cadastro de aposta: Cenario nao cadastrado");
		}
		
		Aposta aposta = new Aposta(apostador, valor, previsao);
		Cenario cen = getCenario(cenario);
		return cen.cadastrarAposta(aposta);
	}
	
	/**
	 * Cadastrar uma aposta assegurada por valor no cenário passado como parâmetro.
	 * 
	 * @param cenario o número do cenário
	 * @param apostador o nome do apostador
	 * @param valor o valor da aposta
	 * @param previsao a previsão da aposta.
	 * @param valorAssegurado o valor assegurado da aposta.
	 * @param custo o custo da aposta.
	 * @return o inteiro que representa a aposta.
	 */
	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo) {
		if(cenario <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Cenario invalido");
		}
		if(cenario > this.cenarios.size()) {
			throw new IndexOutOfBoundsException("Erro no cadastro de aposta: Cenario nao cadastrado");
		}
		if(apostador == null || apostador.equals("") || apostador.equals("   ")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Apostador nao pode ser vazio ou nulo");
		}
		if(valor <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Valor nao pode ser menor ou igual a zero");
		}
		if(previsao.equals("  ") || previsao.equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Previsao nao pode ser vazia ou nula");
		}
		if(!previsao.equals("VAI ACONTECER") && !previsao.equals("N VAI ACONTECER")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Previsao invalida");
		}
		
		this.caixa.setValor(this.caixa.getValor() + custo);
		
		ApostaAssegurada aposta = new ApostaAssegurada(apostador, valor, previsao, valorAssegurado, custo);
		Cenario cen = getCenario(cenario);
		return cen.cadastrarApostaAssegurada(aposta);
	}
	
	/**
	 * Cadastrar uma aposta assegurada por taxa no cenário passado como parâmetro.
	 * 
	 * @param cenario o número do cenário
	 * @param apostador o nome do apostador
	 * @param valor o valor da aposta
	 * @param previsao a previsão da aposta.
	 * @param taxa a taxa da aposta.
	 * @param custo o custo da aposta.
	 * @return o inteiro que representa a aposta.
	 */
	public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
		if(cenario <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Cenario invalido");
		}
		if(cenario > this.cenarios.size()) {
			throw new IndexOutOfBoundsException("Erro no cadastro de aposta assegurada por taxa: Cenario nao cadastrado");
		}
		if(apostador == null || apostador.equals("") || apostador.equals("   ")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Apostador nao pode ser vazio ou nulo");
		}
		if(valor <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Valor nao pode ser menor ou igual a zero");
		}
		if(previsao.equals("  ") || previsao.equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Previsao nao pode ser vazia ou nula");
		}
		if(!previsao.equals("VAI ACONTECER") && !previsao.equals("N VAI ACONTECER")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Previsao invalida");
		}
		
		this.caixa.adicionaValor(custo);
		
		ApostaAssegurada aposta = new ApostaAssegurada(apostador, valor, previsao, taxa, custo);
		Cenario cen = getCenario(cenario);
		return cen.cadastrarApostaAssegurada(aposta);
	}
	
	/**
	 * Altera o valor do seguro de uma aposta assegurada por taxa para assegurada por valor.
	 * 
	 * @param cenario o cenario da aposta
	 * @param apostaAssegurada a representação numérica da aposta.
	 * @param valor o valor da aposta
	 * @return a representação numérica da aposta.
	 */
	public int alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
		Cenario cen = getCenario(cenario);
		ApostaAssegurada aposta = cen.getApostaAssegurada(apostaAssegurada);
		Seguro novoSeguro = new SeguroValor(aposta.getCusto(), valor);
		aposta.setSeguro(novoSeguro);
		return cen.setApostaAssegurada(apostaAssegurada, aposta);
	}
	
	/**
	 * Altera o valor do seguro de uma aposta assegurada por valor para assegurada por taxa
	 * 
	 * @param cenario o cenario da aposta
	 * @param apostaAssegurada a representação numérica da aposta
	 * @param taxa a taxa da aposta
	 * @return representação numérica da aposta
	 */
	public int alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa) {
		Cenario cen = getCenario(cenario);
		ApostaAssegurada aposta = cen.getApostaAssegurada(apostaAssegurada);
		Seguro novoSeguro = new SeguroTaxa(aposta.getCusto(), aposta.getValor(), taxa);
		aposta.setSeguro(novoSeguro);
		return cen.setApostaAssegurada(apostaAssegurada, aposta);
	}
	
	/**
	 * O valor total das apostas do cenario passado como parâmetro.
	 * 
	 * @param cenario o número do cenario.
	 * @return o valor total das apostas.
	 */
	public int valorTotalDeApostas(int cenario) {
		if(cenario <= 0) {
			throw new IllegalArgumentException("Erro na consulta do valor total de apostas: Cenario invalido");
		}
		if(cenario > this.cenarios.size()) {
			throw new IndexOutOfBoundsException("Erro na consulta do valor total de apostas: Cenario nao cadastrado");
		}

		Cenario cen = getCenario(cenario);
		return cen.valorTotalApostas();
	}
	
	/**
	 * Retorna o total de apostas do cenario passado como parâmetro.
	 * 
	 * @param cenario o número do cenario.
	 * @return o valor total.
	 */
	public int totalDeApostas(int cenario) {
		if(cenario <= 0) {
			throw new IllegalArgumentException("Erro na consulta do total de apostas: Cenario invalido");
		}
		if(cenario > this.cenarios.size()) {
			throw new IndexOutOfBoundsException("Erro na consulta do total de apostas: Cenario nao cadastrado");
		}
		
		Cenario cen = getCenario(cenario);
		return cen.totalApostas();
	}
	
	/**
	 * Retorna um HashSet que contém a representação das apostas do cenario passado como
	 * parâmetro.
	 * 
	 * @param cenario o número do cenario.
	 * @return o HashSet.
	 */
	public HashSet<String> representacaoApostas(int cenario) {
		Cenario cen = getCenario(cenario);
		return cen.representacaoApostas();
	}
	
	/**
	 * Fecha o cenario passado como parâmetro através da variável booleana também passada
	 * como parâmetro, que indica se ele ocorreu ou não.
	 * 
	 * @param cenario o número do cenario.
	 * @param ocorreu a variável booleana.
	 */
	public void fecharAposta(int cenario, boolean ocorreu) {
		if(cenario <= 0) {
			throw new IllegalArgumentException("Erro ao fechar aposta: Cenario invalido");
		}
		if(cenario > this.cenarios.size()) {
			throw new IndexOutOfBoundsException("Erro ao fechar aposta: Cenario nao cadastrado");
		}
		
		Cenario cen = getCenario(cenario);
		if(!cen.getEstado().equals("Nao finalizado")) {
			throw new IllegalArgumentException("Erro ao fechar aposta: Cenario ja esta fechado");
		}
		
		if(ocorreu) {
			cen.setEstado("Finalizado(ocorreu)");
		}else {
			cen.setEstado("Finalizado(n ocorreu)");
		}
		mandaProCaixa(cenario);
	}
	
	/**
	 * Retorna o valor total das apostas perdedoras do cenario passado como parâmetro.
	 * 
	 * @param cenario o número do cenario.
	 * @return o valor das apostas perdedoras
	 */
	private int getTotalPerdedores(int cenario) {
		int total = 0;
		Cenario cen = getCenario(cenario);
		HashSet<Aposta> apostasPerdedoras = cen.apostasPerdedoras(cen.booleanaEstado());
		for(Aposta aposta : apostasPerdedoras) {
			total += aposta.getValor();
		}
		return total;
	}
	
	/**
	 * Retorna o valor em centavos de um cenario encerrado que será destinado
	 * ao caixa.
	 * 
	 * @param cenario o número do cenario.
	 * @return o valor em cantavos.
	 */
	public int getCaixaCenario(int cenario) {
		if(cenario <= 0) {
			throw new IllegalArgumentException("Erro na consulta do caixa do cenario: Cenario invalido");
		}
		if(cenario > this.cenarios.size()) {
			throw new IndexOutOfBoundsException("Erro na consulta do caixa do cenario: Cenario nao cadastrado");
		}
		
		Cenario cen = getCenario(cenario);
		return this.caixa.getCaixaCenario(cen);
	}
	
	/**
	 * Manda para o caixa o valor destinado ao mesmo do cenario encerrado passado
	 * como parâmetro.
	 * 
	 * @param cenario o número do cenario.
	 */
	private void mandaProCaixa(int cenario) {
		this.caixa.adicionaValor(getCaixaCenario(cenario));
		Cenario cen = getCenario(cenario);
		this.caixa.adicionaValor(cen.apostasAsseguradasPerdedoras(cen.booleanaEstado()));
	}
	
	/**
	 * Retorna o valor total de um cenario encerrado que será destinado a distribuição
	 * entre as apostas vencedoras.
	 * 
	 * @param cenario o numero do cenario.
	 * @return o valor em centavos destinado as apostas vencedoras.
	 */
	public int getTotalRateioCenario(int cenario) {
		if(cenario <= 0) {
			throw new IllegalArgumentException("Erro na consulta do total de rateio do cenario: Cenario invalido");
		}
		if(cenario > this.cenarios.size()) {
			throw new IndexOutOfBoundsException("Erro na consulta do total de rateio do cenario: Cenario nao cadastrado");
		}
		
		Cenario cen = getCenario(cenario);
		if(cen.getEstado().equals("Nao finalizado")) {
			throw new IllegalArgumentException("Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");
		}
		
		int perdedor = getTotalPerdedores(cenario);
		int taxa = getCaixaCenario(cenario);
		return perdedor - taxa + cen.getBonus();
	}
	
}

