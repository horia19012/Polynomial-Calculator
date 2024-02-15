import static org.junit.Assert.assertEquals;

import org.junit.Test;

import BusinessLogic.Operation;
import DataModels.Monomial;
import DataModels.Polynomial;

public class JunitTesting {
	@Test
	public void addingTest() {
		// 3x^2-x^1+1
		// x^1-2
		Polynomial p1 = new Polynomial();
		p1.addMonomial(new Monomial(3, 2));
		p1.addMonomial(new Monomial(-1, 1));
		p1.addMonomial(new Monomial(1, 0));

		Polynomial p2 = new Polynomial();
		p2.addMonomial(new Monomial(1, 1));
		p2.addMonomial(new Monomial(-2, 0));

		Polynomial result = Operation.adding(p1, p2);
		assertEquals("3x^2-1", result.toString());
	}
	@Test
	public void addingTest2() {
		// 3x^2-x^1+1
		// x^1-2
		Polynomial p1 = new Polynomial();
		p1.addMonomial(new Monomial(3, 2));
		p1.addMonomial(new Monomial(-1, 1));
		p1.addMonomial(new Monomial(1, 0));

		Polynomial p2 = new Polynomial();
		p2.addMonomial(new Monomial(1, 1));
		p2.addMonomial(new Monomial(-2, 0));

		Polynomial result = Operation.adding(p1, p2);
		assertEquals("10x^4+3x^3-1", result.toString());
	}

	

	@Test
	public void subtractingTest() {
		// 5x^10+3x^2+4x^1+7
		// 4x^3+2x^1+10
		Polynomial p1 = new Polynomial();
		p1.addMonomial(new Monomial(5, 10));
		p1.addMonomial(new Monomial(3, 2));
		p1.addMonomial(new Monomial(4, 1));
		p1.addMonomial(new Monomial(7, 0));

		Polynomial p2 = new Polynomial();
		p2.addMonomial(new Monomial(4, 3));
		p2.addMonomial(new Monomial(2, 1));
		p2.addMonomial(new Monomial(10, 0));

		Polynomial result = Operation.subtracting(p1, p2);
		assertEquals("5x^10-4x^3+3x^2+2x^1-3", result.toString());
	}

	@Test
	public void multiplyingTest() {
		// 3x^2-x^1+1
		// x^1-2
		Polynomial p1 = new Polynomial();
		p1.addMonomial(new Monomial(3, 2));
		p1.addMonomial(new Monomial(-1, 1));
		p1.addMonomial(new Monomial(1, 0));

		Polynomial p2 = new Polynomial();
		p2.addMonomial(new Monomial(1, 1));
		p2.addMonomial(new Monomial(-2, 0));

		Polynomial result = Operation.multiplying(p1, p2);
		assertEquals("3x^3-7x^2+3x^1-2", result.toString());
	}

	@Test
	public void dividingTest() throws Exception {
		// x^3-2x^2+6x^1-5
		// x^2-1
		Polynomial p1 = new Polynomial();
		p1.addMonomial(new Monomial(1, 3));
		p1.addMonomial(new Monomial(-2, 2));
		p1.addMonomial(new Monomial(6, 1));
		p1.addMonomial(new Monomial(-5, 0));

		Polynomial p2 = new Polynomial();
		p2.addMonomial(new Monomial(1, 2));
		p2.addMonomial(new Monomial(-1, 0));

		Polynomial[] result = new Polynomial[2];
		
		
		result = Operation.dividing(p1, p2);
		System.out.println(result[0].toString());
		System.out.println(result[1].toString());
		

		assertEquals("7x^1-7", result[0].toString());
		assertEquals("1x^1-3", result[1].toString());
	}
	@Test
	public void dividingTest2() throws Exception {
		// x^3-2x^2+6x^1-5
		// x^2-1
		Polynomial p1 = new Polynomial();
		p1.addMonomial(new Monomial(1, 3));
		p1.addMonomial(new Monomial(-2, 2));
		p1.addMonomial(new Monomial(6, 1));
		p1.addMonomial(new Monomial(-5, 0));

		Polynomial p2 = new Polynomial();
		p2.addMonomial(new Monomial(1, 2));
		p2.addMonomial(new Monomial(-1, 0));

		Polynomial[] result = new Polynomial[2];
		
		
		result = Operation.dividing(p1, p2);
		System.out.println(result[0].toString());
		System.out.println(result[1].toString());
		

		assertEquals("0", result[0].toString());
		assertEquals("1x^1-3", result[1].toString());
	}
	@Test
	public void inetegratingTest() {
		//5x^10+3x^2+4x^1+7
		Polynomial p1=new Polynomial();
		p1.addMonomial(new Monomial(5,10));
		p1.addMonomial(new Monomial(3,2));
		p1.addMonomial(new Monomial(4,1));
		p1.addMonomial(new Monomial(7,0));
		
		assertEquals("+(5/11)x^11+1x^3+2x^2+7x^1",Operation.integ(p1));
		
	}
	@Test
	public void derivingTest() {
		//10x^6-3x^5+8x^3-100x^2+1
		Polynomial p1=new Polynomial();
		p1.addMonomial(new Monomial(10,6));
		p1.addMonomial(new Monomial(-3,5));
		p1.addMonomial(new Monomial(8,3));
		p1.addMonomial(new Monomial(-100,2));
		p1.addMonomial(new Monomial(1,0));
		
		assertEquals("60x^5-15x^4+24x^2-200x^1",Operation.deriv(p1).toString());
		
	}

}
