package DataModels;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Polynomial {
	private TreeMap<Integer, Monomial> polynomial = new TreeMap<Integer, Monomial>();
	
	public TreeMap<Integer, Monomial> getPolynomial() {
		return this.polynomial;
	}
	public Polynomial() {

	}

	public Polynomial(Monomial monomial) {
		polynomial = new TreeMap<>();
		polynomial.put(monomial.getPower(), monomial);

	}

	public Polynomial(Polynomial p) {
		this.polynomial = p.getPolynomial();
	}

	public void setPolynomial(TreeMap<Integer, Monomial> polynomial) {
		this.polynomial = polynomial;
	}

	public void addMonomial(Monomial m) {
		Integer aux = new Integer(m.getPower());
		this.polynomial.put(aux, m);

	}

	public void showMonomials() {
		for (Integer i : this.getPolynomial().keySet()) {
			System.out.println(this.getPolynomial().get(i));
		}
	}
	public void removeZeroes() {
		Polynomial aux=new Polynomial();
		aux.setPolynomial(this.getPolynomial());
		
		Set<Integer> keysToRemove=new HashSet<>();
		for(Map.Entry<Integer, Monomial> entry: aux.getPolynomial().entrySet()) {
			if(entry.getValue().getCoef()==0) {
				keysToRemove.add(entry.getKey());
			}
		}
		for(Integer key:keysToRemove) {
			aux.getPolynomial().remove(key);
		}
		this.setPolynomial(aux.getPolynomial());
	}

	public String toString() {
		String pol = "";
		for (Integer i : polynomial.keySet()) {
			Monomial monomial = new Monomial(polynomial.get(i).getCoef(), polynomial.get(i).getPower());
			String sign = "";
			if (polynomial.get(i).getCoef() != 0) {
				if (polynomial.get(i).getCoef() > 0) {
					sign = "+";
				}
				if (i == 0) {
					pol = pol + sign + polynomial.get(i).getCoef();
				} else {
					pol = sign + polynomial.get(i).getCoef() + "x^" + polynomial.get(i).getPower() + pol;
				}
				sign = "";
			}
		}
		if (pol.equals("")) {
			pol = "0";
			return pol;
		} else {
			if (pol.charAt(0) == '+') {
				return pol.substring(1, pol.length());
			}else return pol;
		}
	}
}
