package BusinessLogic;

import DataModels.Monomial;
import DataModels.Polynomial;

public class Operation {

	public static Polynomial adding(Polynomial pol1, Polynomial pol2) {
		Polynomial result = new Polynomial();
		for (Integer i : pol1.getPolynomial().keySet()) {
			Monomial monomial = pol1.getPolynomial().get(i);
			result.getPolynomial().put(i, monomial);
		}

		for (Integer i : pol2.getPolynomial().keySet()) {

			Monomial monomial = pol2.getPolynomial().get(i);
			if (result.getPolynomial().containsKey(i)) {
				result.getPolynomial().get(i).addCoef(monomial.getCoef());
			} else {
				result.getPolynomial().put(i, monomial);
			}
		}

		return result;
	}



	public static Polynomial subtracting(Polynomial pol1, Polynomial pol2) {
		pol1.showMonomials();
		System.out.println();
		pol2.showMonomials();
		Polynomial result = new Polynomial();
		for (Integer i : pol1.getPolynomial().keySet()) {
			Monomial monomial = pol1.getPolynomial().get(i);
			System.out.println(monomial);
			result.getPolynomial().put(i, monomial);
		}
		for (Integer i : pol2.getPolynomial().keySet()) {
			Monomial monomial = pol2.getPolynomial().get(i);
			if (result.getPolynomial().containsKey(i)) {
				result.getPolynomial().get(i).subbCoef(monomial.getCoef());
			} else {
				result.getPolynomial().put(i, new Monomial(-monomial.getCoef(), monomial.getPower()));
			}
		}
		result.showMonomials();
		return result;
	}

	// Multiplying a single monomial with a polynomial
	public static Polynomial monMulPol(Monomial monomial, Polynomial pol) {
		Polynomial result = new Polynomial();
		for (Integer i : pol.getPolynomial().keySet()) {
			int newPow = pol.getPolynomial().get(i).getPower() + monomial.getPower();
			int newCoef = pol.getPolynomial().get(i).getCoef() * monomial.getCoef();
			System.out.println(newCoef + " " + newPow);
			result.addMonomial(new Monomial(newCoef, newPow));
		}
		return result;
	}

	public static Polynomial multiplying(Polynomial pol1, Polynomial pol2) {
		Polynomial result = new Polynomial();
		for (Integer j : pol2.getPolynomial().keySet()) {
			
			//iterating through monomyals in polynomial 2
			Polynomial aux = new Polynomial(pol1);
			//making a copy of polynomial 1 so then i can multiply every monomial from
			//polynomial 1 with the monomyal of index j from polynomial 2 witch I consider a polynomial itself
			//and then I add it to the result
			aux = Operation.monMulPol(pol2.getPolynomial().get(j), pol1);
			result = Operation.adding(result, aux);
		}
		return result;
	}

	public static Polynomial deriv(Polynomial pol) {
		Polynomial result = new Polynomial();
		for (Integer i : pol.getPolynomial().keySet()) {
			System.out.println(i);
			if (i != 0) {
				Monomial monomial = pol.getPolynomial().get(i);
				int newPow = monomial.getPower() - 1;
				int newCoef = monomial.getCoef() * monomial.getPower();

				result.getPolynomial().put(newPow, new Monomial(newCoef, newPow));
				System.out.println(newCoef + " " + newPow);
			}
		}

		return result;
	}

	public static String integ(Polynomial pol) {
		String result = "";
		for (Integer i : pol.getPolynomial().keySet()) {
			String sign = "";
			Monomial monomial = pol.getPolynomial().get(i);
			if (monomial.getCoef() > 0) {
				sign = "+";
			}
			int newPow = monomial.getPower() + 1;
			String newCoef = "";
			if (monomial.getCoef() % newPow == 0) {
				newCoef = String.valueOf(monomial.getCoef() / newPow);
			} else {
				newCoef = "(" + monomial.getCoef() + "/" + newPow + ")";
			}
			result = sign + newCoef + "x^" + newPow + result;
		}

		return result;

	}

	public static Polynomial[] dividing(Polynomial dividend, Polynomial divisor) throws Exception {
		
		Polynomial quotient = new Polynomial();
		Polynomial remainder = new Polynomial(dividend);
		Polynomial aux=new Polynomial();
		
		//Verifying if dividend and divisor are equal
		aux=Operation.subtracting(dividend, divisor);
		aux.removeZeroes();
		if(aux.getPolynomial().isEmpty()==true) {
			Polynomial[] result = { new Polynomial(new Monomial(0,0)), new Polynomial(new Monomial(1,0)) };
			return result;
		}
		
		// Check if the divisor is not zero
		if (divisor.getPolynomial().isEmpty() || divisor.getPolynomial().lastEntry().getValue().getCoef() == 0) {
			throw new Exception("Divide by zero");
		}
		// Divide the polynomials
		while (remainder.getPolynomial().isEmpty()==false && remainder.getPolynomial().lastKey() >= divisor.getPolynomial().lastKey()) {
			
			int powerDiff = remainder.getPolynomial().lastKey() - divisor.getPolynomial().lastKey();
			int coefDiff = remainder.getPolynomial().lastEntry().getValue().getCoef()/ divisor.getPolynomial().lastEntry().getValue().getCoef();
			
			Monomial quotientMonomial = new Monomial(coefDiff, powerDiff);
			aux = new Polynomial(quotientMonomial);
			
			quotient = Operation.adding(quotient, aux);
			System.out.println(coefDiff + " " + powerDiff);
			
			Polynomial subtractedPolynomial = Operation.monMulPol(new Monomial(-coefDiff, powerDiff), divisor);
			remainder = Operation.adding(remainder, subtractedPolynomial);
			remainder.showMonomials();
			// Remove any zero terms from the remainder
			remainder.removeZeroes();
		
		}
		// Return the quotient and remainder
		Polynomial[] result = { remainder, quotient };
		return result;
	}
}
