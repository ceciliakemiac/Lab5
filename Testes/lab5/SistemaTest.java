package lab5;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class SistemaTest {
	private Sistema sistema;
	
	@Before
	public void criaSistema() {
		sistema = new Sistema();
	}
	
	/**
	 * Testa se é possível inicializar o caixa.
	 */
	@Test
	public void testInicializa() {
		Caixa caixaa = new Caixa(10, 0.01);
		assertEquals(this.sistema.inicializa(10, 0.01), caixaa);
	}
	
	/**
	 * Testa se é possível inicializar o caixa com valor menor que 0.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInicializaValorNegativo() {
		this.sistema.inicializa(-1, 0.01);
	}
	
	/**
	 * Testa se é possível inicializar o caixa com taxa negativa.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInicializaTaxaNegativa() {
		this.sistema.inicializa(0, -1);
	}
	
	/**
	 * Testa se o valor do caixa retornado está correto.
	 */
	@Test
	public void testGetCaixa() {
		this.sistema.inicializa(10, 0.8);
		assertEquals(10, this.sistema.getCaixa());
	}
	
	/**
	 * Testa se é possível cadastrar um cenario nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testcadastrarCenarioNulo() {
		this.sistema.cadastrarCenario(null);
	}
	
	/**
	 * Testa se é possível cadastrar um cenario vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testcadastrarCenarioVazio() {
		this.sistema.cadastrarCenario("");
	}
	
	/**
	 * Testa se é possível cadastrar um cenario normal, e se o número de retorno
	 * é o correspondente ao número do cenario.
	 */
	@Test
	public void testCadastrarCenario() {
		assertEquals(this.sistema.cadastrarCenario("descrição"), 1);
	}
	
	/**
	 * Testa se é possível cadastrar um cenário bonus, e se o número de retorno
	 * é o correspondente ao número do cenario.
	 */
	@Test
	public void testCadastrarCenarioBonus() {
		this.sistema.inicializa(10, 0.01);
		assertEquals(this.sistema.cadastrarCenario("descrição", 5), 1);
	}
	
	/**
	 * Testa se o valor do bonus foi retirado corretamente do caixa do sistema.
	 */
	@Test
	public void testCaixaDepoisDoBonus() {
		this.sistema.inicializa(10, 0.01);
		this.sistema.cadastrarCenario("d", 4);
		assertEquals(this.sistema.getCaixa(), 6);
	}
	
	/**
	 * Testa se a String retornada representa o cenario cujo número foi passado
	 * como parâmetro.
	 */
	@Test
	public void testExibirCenario() {
		this.sistema.cadastrarCenario("aaa");
		assertEquals(this.sistema.exibirCenario(1), "1 - aaa - Nao finalizado");
	}
	
	/**
	 * Testa se é possível exibir um cenario cujo número passado como parâmetro é
	 * inválido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExibirCenarioInvalido() {
		this.sistema.exibirCenario(-2);
	}
	
	/**
	 * Testa se é possível exibir um cenario ainda não cadastrado.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testExibirCenarioNulo() {
		this.sistema.cadastrarCenario(",,,,,");
		this.sistema.exibirCenario(2);
	}
	
	/**
	 * Testa se a exibição de todos os cenários cadastrados no sistema está correta.
	 */
	@Test
	public void testExibirCenarios() {
		HashSet<String> cenarios = new HashSet<>();
		this.sistema.cadastrarCenario("aaa");
		this.sistema.cadastrarCenario("bbb");
		cenarios.add("1 - aaa - Nao finalizado");
		cenarios.add("2 - bbb - Nao finalizado");
		assertEquals(cenarios, this.sistema.exibirCenarios());
	}
	
	/**
	 * Testa se é possível cadastrar uma aposta em um cenario já cadastrado.
	 */
	@Test
	public void testCadastrarAposta() {
		 this.sistema.cadastrarCenario("aaa");
		 assertTrue(this.sistema.cadastrarAposta(1, "André", 500, "VAI ACONTECER"));
	}
	
	/**
	 * Testa se é possível cadastrar uma aposta assegurada por valor em um cenário
	 * já cadastrado.
	 */
	@Test
	public void testCadastrarApostaSeguraValor() {
		this.sistema.inicializa(100, 0.05);
		this.sistema.cadastrarCenario("bbb");
		assertEquals(1, this.sistema.cadastrarApostaSeguraValor(1, "raimundo", 90, "VAI ACONTECER", 10, 1));
	}
	
	/**
	 * Testa se é possível cadastrar uma aposta assegurada por taxa em um cenário
	 * já cadastrado.
	 */
	@Test
	public void testCadastrarApostaSeguraTaxa() {
		this.sistema.inicializa(100, 0.05);
		this.sistema.cadastrarCenario("bbb");
		this.sistema.cadastrarApostaSeguraValor(1, "raimundo", 90, "VAI ACONTECER", 10, 1);
		assertEquals(2, this.sistema.cadastrarApostaSeguraTaxa(1, "raimundo", 90, "VAI ACONTECER", 0.01, 1));
	}
	
	/**
	 * Testa se uma aposta assegurada por taxa é alterada corretamente para seguro por valor.
	 */
	@Test
	public void testAlterarSeguroValor() {
		this.sistema.inicializa(10, 0.01);
		this.sistema.cadastrarCenario("jkl");
		this.sistema.cadastrarApostaSeguraTaxa(1, "apostador", 100, "VAI ACONTECER", 0.1, 50);
		assertEquals(this.sistema.alterarSeguroValor(1, 1, 200), 1);
	}
	
	/**
	 * Testa se o valor total das apostas cadastradas no sistema está correto.
	 */
	@Test
	public void testValorTotalApostas() {
		this.sistema.cadastrarCenario("abc");
		this.sistema.cadastrarAposta(1, "André", 500, "VAI ACONTECER");
		this.sistema.cadastrarAposta(1, "Andreia", 500, "N VAI ACONTECER");
		assertEquals(1000, this.sistema.valorTotalDeApostas(1));
	}
	
	/**
	 * Testa se o número total de apostas cadastradas no sistema está correto.
	 */
	@Test
	public void testTotalDeApostas() {
		this.sistema.cadastrarCenario("descrição");
		this.sistema.cadastrarAposta(1, "André", 500, "VAI ACONTECER");
		this.sistema.cadastrarAposta(1, "Andreia", 500, "N VAI ACONTECER");
		assertEquals(2, this.sistema.totalDeApostas(1));
	}
	
	/**
	 * Testa se é mesmo 0 o valor total de apostas ao não se cadastrar apostas.
	 */
	@Test
	public void testTotalDeApostasSemApostas() {
		this.sistema.cadastrarCenario("Tudo pode acontecer");
		assertEquals(0, this.sistema.totalDeApostas(1));
	}
	
	/**
	 * Testa se a representação das apostas cadastradas num cenario está correta.
	 */
	@Test
	public void testRepresentacaoApostas() {
		HashSet<String> apostas = new HashSet<>();
		this.sistema.cadastrarCenario("rrr");
		this.sistema.cadastrarAposta(1, "André", 500, "VAI ACONTECER");
		this.sistema.cadastrarAposta(1, "Andreia", 500, "N VAI ACONTECER");
		apostas.add(" - André - R$5,00 - VAI ACONTECER");
		apostas.add(" - Andreia - R$5,00 - N VAI ACONTECER");
		assertEquals(apostas, this.sistema.representacaoApostas(1));
	}
	
	/**
	 * Testa se é possível fechar uma aposta com estado não ocorrido.
	 */
	@Test
	public void testfecharApostaNaoOcorrido() {
		this.sistema.inicializa(10, 0.01);
		this.sistema.cadastrarCenario("rr");
		this.sistema.fecharAposta(1, false);
		assertEquals("Finalizado(n ocorreu)", this.sistema.getCenario(1).getEstado());
	}
	
	/**
	 * Testa se é possível fechar uma aposta com estado ocorrido.
	 */
	@Test
	public void testFecharApostaOcorrido() {
		this.sistema.inicializa(10, 0.01);
		this.sistema.cadastrarCenario("yy");
		this.sistema.fecharAposta(1, true);
		assertEquals("Finalizado(ocorreu)", this.sistema.getCenario(1).getEstado());
	}
	
	/**
	 * Testa se o valor retornado para o caixa do sistema está correto.
	 */
	@Test
	public void testGetCaixaCenario() {
		this.sistema.inicializa(10, 0.01);
		this.sistema.cadastrarCenario(".");
		Cenario cen = this.sistema.getCenario(1);
		cen.setEstado("Finalizado(n ocorreu)");
		this.sistema.cadastrarAposta(1, "Abc", 20000, "VAI ACONTECER");
		this.sistema.cadastrarAposta(1, "Xyz", 30000, "N VAI ACONTECER");
		this.sistema.cadastrarAposta(1, "Zero", 199, "VAI ACONTECER");
		assertEquals(201, this.sistema.getCaixaCenario(1));
	}
	
	/**
	 * Testa se o valor que será distribuído para as apostas que venceram está correto.
	 */
	@Test
	public void testTotalRateioCenario() {
		this.sistema.inicializa(10, 0.01);
		this.sistema.cadastrarCenario("hjk");
		Cenario cen = this.sistema.getCenario(1);
		cen.setEstado("Finalizado(n ocorreu)");
		this.sistema.cadastrarAposta(1, "Abc", 20000, "VAI ACONTECER");
		this.sistema.cadastrarAposta(1, "Xyz", 30000, "N VAI ACONTECER");
		this.sistema.cadastrarAposta(1, "Zero", 199, "VAI ACONTECER");
		assertEquals(19998, this.sistema.getTotalRateioCenario(1));
	}
	
	/**
	 * Testa se o valor que será distribuído para as apostas que venceram em um cenario 
	 * bonus está correto.
	 */
	@Test
	public void testTotalRateioCenarioBonus() {
		this.sistema.inicializa(10, 0.01);
		this.sistema.cadastrarCenario("hjk", 4);
		Cenario cen = this.sistema.getCenario(1);
		cen.setEstado("Finalizado(n ocorreu)");
		this.sistema.cadastrarAposta(1, "Abc", 20000, "VAI ACONTECER");
		this.sistema.cadastrarAposta(1, "Xyz", 30000, "N VAI ACONTECER");
		this.sistema.cadastrarAposta(1, "Zero", 199, "VAI ACONTECER");
		assertEquals(20002, this.sistema.getTotalRateioCenario(1));
	}

}
