package DataModels;

public class Monomial {
	private int coef;
	private int power;

	public Monomial(int coef, int power) {
		this.coef = coef;
		this.power = power;
	}

	public int getCoef() {
		return coef;
	}

	public void setCoef(int coef) {
		this.coef = coef;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String toString() {
		return "POWER: " + this.power + " " + "COEF: " + this.coef;
	}

	public void addCoef(int x) {
		this.coef += x;
	}

	public void subbCoef(int x) {
		this.coef -= x;
	}
}
