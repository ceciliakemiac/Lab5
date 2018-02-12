package lab5;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class CenarioTest {
	private Cenario cenario;
	
	@Before
	public void criaCenario() {
		cenario = new Cenario("O mundo vai acabar amanhã", 2);
	}
	
	/**
	 * Testa se é possível criar um cenario com descrição nula.
	 */
	@Test(expected = NullPointerException.class)
	public void testCriaCenarioNulo() {
		new Cenario(null, 0);
	}
	
	/**
	 * Testa se é possível criar um cenario com descrição vazia."
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriaCenarioVazio() {
		new Cenario(" ", 0);
	}
	
	/**
	 * Testa se é possível modificar o estado do cenario a partir de uma String inválida.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetEstadoInvalido() {
		this.cenario.setEstado("jjj");
	}
	
	/**
	 * Testa se o estado do cenario realmente ocorre ao alterá-lo para ocorrido."
	 */
	@Test
	public void testSetEstadoOcorreu() {
		this.cenario.setEstado("Finalizado(ocorreu)");
		assertEquals("Finalizado(ocorreu)", this.cenario.getEstado());
	}
	
	/**
	 * Testa se o estado do cenario realmente não ocorre ao alterá-lo para não
	 * ocorrido."
	 */
	@Test
	public void testSetEstadoNaoOcorreu() {
		this.cenario.setEstado("Finalizado(n ocorreu)");
		assertEquals("Finalizado(n ocorreu)", this.cenario.getEstado());
	}
	
	public void testSetApostaAssegurada() {
		ApostaAssegurada aposta = new ApostaAssegurada("a", 1000, "VAI ACONTECER", 200, 50);
		ApostaAssegurada aposta2 = new ApostaAssegurada("b", 1000, "VAI ACONTECER", 200, 50);
		this.cenario.cadastrarApostaAssegurada(aposta);
		assertEquals(1, this.cenario.setApostaAssegurada(1, aposta2));
	}
	
	/**
	 * Testa se o valor booleano true derivado do estado "ocorreu" está correto.
	 */
	@Test
	public void testBooleanaEstadoOcorreu() {
		this.cenario.setEstado("Finalizado(ocorreu)");
		assertTrue(this.cenario.booleanaEstado());
	}
	
	/**
	 * Testa se o valor booleano false derivado do estado "não ocorreu" está correto.
	 */
	@Test
	public void testBooleanaEstadoNaoOcorreu() {
		this.cenario.setEstado("Finalizado(n ocorreu)");
		assertFalse(this.cenario.booleanaEstado());
	}
	
	/**
	 * Testa se é possível cadastrar uma aposta nula.
	 */
	@Test
	public void testcadastrarApostaNula() {
		assertFalse(this.cenario.cadastrarAposta(null));
	}
	
	/**
	 * Testa se é possível cadastrar uma aposta."
	 */
	@Test
	public void testCadastrarAposta() {
		Aposta aposta = new Aposta("a", 2, "N VAI ACONTECER");
		assertTrue(this.cenario.cadastrarAposta(aposta));
	}
	
	/**
	 * Testa se o valor total de apostas do cenario está correto.
	 */
	@Test
	public void testValorTotalApostas() {
		Aposta aposta = new Aposta("a", 1, "N VAI ACONTECER");
		Aposta aposta2 = new Aposta("b", 2, "VAI ACONTECER");
		this.cenario.cadastrarAposta(aposta);
		this.cenario.cadastrarAposta(aposta2);
		assertEquals(this.cenario.valorTotalApostas(), 3);
	}
	
	/**
	 * Testa se o número total de apostas do cenário está correto.
	 */
	@Test
	public void testTotalApostas() {
		Aposta aposta = new Aposta("a", 1, "N VAI ACONTECER");
		Aposta aposta2 = new Aposta("b", 2, "VAI ACONTECER");
		this.cenario.cadastrarAposta(aposta);
		this.cenario.cadastrarAposta(aposta2);
		assertEquals(2, this.cenario.totalApostas());
	}
	
	/**
	 * Testa se o total de apostas é 0 quando não há aposta cadastrada no cenario.
	 */
	@Test
	public void testTotalApostasSemApostas() {
		assertEquals(0, this.cenario.totalApostas());
	}
	
	/**
	 * Testa se a representação das apostas do cenario está correta.
	 */
	@Test
	public void testRepresentacaoApostas() {
		HashSet<String> apostas = new HashSet<>();
		Aposta aposta = new Aposta("a", 100, "N VAI ACONTECER");
		Aposta aposta2 = new Aposta("b", 200, "VAI ACONTECER");
		this.cenario.cadastrarAposta(aposta);
		this.cenario.cadastrarAposta(aposta2);
		apostas.add(" - a - R$1,00 - N VAI ACONTECER");
		apostas.add(" - b - R$2,00 - VAI ACONTECER");
		assertEquals(apostas, this.cenario.representacaoApostas());
	}
	
	/**
	 * Testa se as apostas consideradas perdedoras de acordo com o estado do cenario
	 * são realmente as perdedoras.
	 */
	@Test
	public void testApostasPerdedoras() {
		Aposta aposta = new Aposta("a", 9, "VAI ACONTECER");
		Aposta apostei = new Aposta("z", 19, "N VAI ACONTECER");
		this.cenario.cadastrarAposta(aposta);
		this.cenario.cadastrarAposta(apostei);
		this.cenario.setEstado("Finalizado(ocorreu)");
		HashSet<Aposta> perdedores = new HashSet<>();
		perdedores.add(apostei);
		assertEquals(perdedores, this.cenario.apostasPerdedoras(true));
	}
	
	/**
	 * Testa se a representação em String do cenario está correta.
	 */
	@Test
	public void testToString() {
		assertEquals(this.cenario.toString(), "2 - O mundo vai acabar amanhã - Nao finalizado");
	}

}