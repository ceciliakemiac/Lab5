package lab5;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testes referentes à classe aposta assegurada.
 * 
 * @author Cecília Kemiac.
 */
public class ApostaAsseguradaTest {
	private ApostaAssegurada aposta;
	
	@Before
	public void testCriaAposta() {
		aposta = new ApostaAssegurada("joão", 1000, "VAI ACONTECER", 300, 50);
	}
	
	/**
	 * Testa se um dos construtores da aposta gera o seguro correto.
	 */
	@Test
	public void testSeguroValor() {
		Seguro seguro = new SeguroValor(3000, 50);
		assertEquals(seguro.getClass(), aposta.getSeguro().getClass());
	}
	
	/**
	 * Testa se um dos construtores da aposta gera o seguro correto.
	 */
	@Test
	public void testSeguroTaxa() {
		Seguro seguro = new SeguroTaxa(400, 40, 0.5);
		ApostaAssegurada aposta2 = new ApostaAssegurada("joana", 1000, "N VAI ACONTECER", 0.05, 40);
		assertEquals(seguro.getClass(), aposta2.getSeguro().getClass());
	}
	
	/**
	 * Testa se o método setSeguro realmente altera o tipo do seguro corretamente.
	 */
	@Test
	public void testSetSeguro() {
		Seguro seguro = new SeguroTaxa(400, 40, 0.05);
		Seguro seguro2 = aposta.getSeguro();
		aposta.setSeguro(seguro);
		assertNotEquals(seguro2.getClass(), aposta.getSeguro().getClass());
	}
	
	/**
	 * Testa se o valor assegurado que será somado ao caixa está correto, no caso
	 * de uma aposta com seguro valor.
	 */
	@Test
	public void testGetCaixaApostaAsseguradaValor() {
		assertEquals(-300, aposta.getCaixa());
	}
	
	/**
	 * Testa se o valor assegurado que será somado ao caixa está correto, no caso
	 * de uma aposta com seguro taxa.
	 */
	@Test
	public void testGetCaixaApostaAsseguradaTaxa() {
		ApostaAssegurada aposta2 = new ApostaAssegurada("joana", 1000, "N VAI ACONTECER", 0.05, 40);
		assertEquals(-50, aposta2.getCaixa());
	}
	
	/**
	 * Testa se o toString para o primeiro construtor está correto.
	 */
	@Test
	public void testToStringValor() {
		assertEquals(aposta.toString(), "joão - R$10,00 - VAI ACONTECER - ASSEGURADA(VALOR) - R$ 3,00");
	}
	
	/**
	 * Testa se o toString para o segundo construtor está correto.
	 */
	@Test
	public void testToStringTaxa() {
		ApostaAssegurada aposta2 = new ApostaAssegurada("joana", 1000, "N VAI ACONTECER", 0.05, 40);
		assertEquals(aposta2.toString(), "joana - R$10,00 - N VAI ACONTECER - ASSEGURADA(TAXA) - 5%");
	}
	
}
