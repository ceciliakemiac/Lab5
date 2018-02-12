package lab5;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testes referentes à classe Aposta.
 * 
 * @author Cecília Kemiac
 */
public class ApostaTest {
	private Aposta aposta;
	
	@Before
	public void criaAposta() {
		aposta = new Aposta("Apostador", 10000, "VAI ACONTECER");
	}
	
	/**
	 * Testa se é possível iniciar uma aposta com apostador nulo.
	 */
	@Test(expected = NullPointerException.class)
	public void testApostadorNulo() {
		new Aposta(null, 10000, "VAI ACONTECER");
	}
	
	/**
	 * Testa se é possível iniciar uma aposta com apostador vazio.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testApostadorVazio() {
		new Aposta("", 10000, "VAI ACONTECER");
	}
	
	/**
	 * Testa se é possível iniciar uma aposta com previsão vazia.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPrevisaoVazia() {
		new Aposta("f", 333, "");
	}
	
	/**
	 * Testa se é possível iniciar uma aposta com previsão fora dos padrões.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPrevisaoInadequada() {
		new Aposta("i", 2, "dhgxvl");
	}
	
	/**
	 * Testa se é possível iniciar uma aposta com previsão negativa.
	 */
	@Test
	public void testCriaApostaNegativa() {
		new Aposta("z", 3333, "N VAI ACONTECER");
	}
	
	/**
	 * Testa se o valor da aposta retornado está correto.
	 */
	@Test
	public void testGetValor() {
		assertEquals(10000, aposta.getValor());
	}

	/**
	 * Testa se o valor em reais retornado da aposta está correto.
	 */
	@Test
	public void testGetValorReais() {
		assertEquals(aposta.getValorReais(), 100.0, 0.0001);
	}
	
	/**
	 * Testa se o valor booleano true derivado da previsão VAI ACONTECER está correto.
	 */
	@Test
	public void testBooleanPrevisao() {
		assertTrue(aposta.booleanPrevisao());
	}
	
	/**
	 * Testa se o valor booleano false derivado da previsão N VAI ACONTECER está
	 * correto.
	 */
	@Test
	public void testBooleanPrevisaoFalso() {
		Aposta aposta2 = new Aposta("xxx", 444, "N VAI ACONTECER");
		assertFalse(aposta2.booleanPrevisao());
	}
	
	/**
	 * Testa se qualquer aposta é realmente diferente.
	 */
	@Test
	public void testApostasDiferentes() {
		Aposta aposta2 = new Aposta("Apostador", 10000, "VAI ACONTECER");
		assertNotEquals(aposta, aposta2);
	}
	
	/**
	 * Testa se o valor adicionado ao caixa caso a aposta perca está correto.
	 */
	@Test
	public void testRetornoCaixa() {
		Caixa caixa = new Caixa(1000, 0.01);
		assertEquals(100, aposta.retornoCaixa(caixa.getTaxa()));
	}
	
	/**
	 * Testa se o tostring está correto.
	 */
	@Test
	public void testToString() {
		assertEquals(aposta.toString(), "Apostador - R$100,00 - VAI ACONTECER");
	}
	
}

