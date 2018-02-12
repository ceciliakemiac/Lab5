package lab5;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CaixaTest {
	private Caixa caixa;
	
	@Before
	public void criaCaixa() {
		caixa = new Caixa(100, 0.01);
	}
	
	/**
	 * Testa se é possível iniciar um caixa com valor negativo.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testValorNegativo() {
		new Caixa(-1, 0.03);
	}
	
	/**
	 * Testa se é possível iniciar um caixa com taxa negativa.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTaxaNegativa() {
		new Caixa(8, -9);
	}
	
	/**
	 * Testa se é possível iniciar um caixa com taxa maior que 1.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTaxaInvalida() {
		new Caixa(8, 2);
	}
	
	/**
	 * Testa se o valor do caixa está retornando corretamente.
	 */
	@Test
	public void testGetValor() {
		assertEquals(100, this.caixa.getValor());
	}
	
	/**
	 * Testa se o valor da taxa está retornando corretamente.
	 */
	@Test
	public void testGetTaxa() {
		assertEquals(0.01, this.caixa.getTaxa(), 0.000001);
	}
	
	/**
	 * Testa se o valor é adicionado corretamente ao caixa.
	 */
	@Test
	public void testAdicionaValor() {
		this.caixa.adicionaValor(10);
		assertEquals(110, this.caixa.getValor());
	}
	
	/**
	 * Testa se o hashCode está comparando corretamente.
	 */
	@Test
	public void testHashCode() {
		Caixa caixa2 = new Caixa(100, 0.01);
		assertEquals(this.caixa.hashCode(), caixa2.hashCode());
	}
	
	/**
	 * Testa se o método Equals está comparando corretamente.
	 */
	@Test
	public void testEquals() {
		Caixa caixa2 = new Caixa(100, 0.01);
		assertTrue(caixa.equals(caixa2));
	}
	
	/**
	 * Testa se o toString está retornando a String que representa o caixa corretamente.
	 */
	@Test
	public void testToString() {
		assertEquals(this.caixa.toString(), "Caixa [valor=100, taxa=0.01]");
	}
	

}
