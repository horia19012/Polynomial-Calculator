package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import BusinessLogic.Operation;
import DataModels.Monomial;
import DataModels.Polynomial;

public class Controller {

	Polynomial polynomial1;
	Polynomial polynomial2;
	Polynomial result = new Polynomial();
	GUI view;

	public Controller(GUI view, Polynomial p1, Polynomial p2) {
		this.view = view;
		this.polynomial1 = p1;
		this.polynomial2 = p2;
		view.addButtonListener(new AddButton());
		view.subButtonListener(new SubButton());
		view.derivButtonListener(new DerivButton());
		view.integButtonListener(new IntegButton());
		view.divButtonListener(new DivButton());
		view.mulButtonListener(new MultiplicationButton());

	}

	public class DivButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println(view.getPol1());
			try {
				try {
					Controller.validatePolynomials(view.getPol1(), polynomial1);
				} catch (Exception ex) {
					view.showMessage("Invalid Polynomial 1!!");
					throw new Exception();
				}
				try {
					Controller.validatePolynomials(view.getPol2(), polynomial2);
				} catch (Exception ex) {
					view.showMessage("Invalid Polynomial 2!!");
					throw new Exception();
				}
//			polynomial1.showMonomials();
				Polynomial[] result;
				try {
					result = Operation.dividing(polynomial1, polynomial2);
					view.writeToResultText("Quotient: " + result[1].toString(), "| Remainder: " + result[0].toString());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					view.showMessage("Division by zero!!");
					view.writeToResultText("Invalid inputs!", "");
				}
			} catch (Exception exec) {
				view.writeToResultText("Invalid inputs!", "");
			}

			polynomial1.getPolynomial().clear();
			polynomial2.getPolynomial().clear();
		}

	}

	public class MultiplicationButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				try {
					Controller.validatePolynomials(view.getPol1(), polynomial1);
				} catch (Exception ex) {
					view.showMessage("Invalid Polynomial 1!!");
					throw new Exception();
				}
				try {
					Controller.validatePolynomials(view.getPol2(), polynomial2);
				} catch (Exception ex) {
					view.showMessage("Invalid Polynomial 2!!");
					throw new Exception();
				}
				result = Operation.multiplying(polynomial1, polynomial2);
				view.writeToResultText(result.toString(), "");
				polynomial1.getPolynomial().clear();
				polynomial2.getPolynomial().clear();
			} catch (Exception exec) {
				view.writeToResultText("Invalid inputs!", "");
			}
		}
	}

	public class AddButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				try {
					Controller.validatePolynomials(view.getPol1(), polynomial1);
				} catch (Exception ex) {
					view.showMessage("Invalid Polynomial 1!!");
					throw new Exception();
				}
				try {
					Controller.validatePolynomials(view.getPol2(), polynomial2);
				} catch (Exception ex) {
					view.showMessage("Invalid Polynomial 2!!");
					throw new Exception();
				}
//			polynomial1.showMonomials();
				result = Operation.adding(polynomial1, polynomial2);
				view.writeToResultText(result.toString(), "");
				polynomial1.getPolynomial().clear();
				polynomial2.getPolynomial().clear();
			} catch (Exception exc) {
				view.writeToResultText("Invalid inputs!", "");
			}
		}
	}

	public class SubButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				try {
					Controller.validatePolynomials(view.getPol1(), polynomial1);
				} catch (Exception ex) {
					view.showMessage("Invalid Polynomial 1!!");
					throw new Exception();
				}
				try {
					Controller.validatePolynomials(view.getPol2(), polynomial2);
				} catch (Exception ex) {
					view.showMessage("Invalid Polynomial 2!!");
					throw new Exception();
				}
				// polynomial1.showMonomials();
				result = Operation.subtracting(polynomial1, polynomial2);
				view.writeToResultText(result.toString(), "");
				polynomial1.getPolynomial().clear();
				polynomial2.getPolynomial().clear();

			} catch (Exception exc) {
				view.writeToResultText("Invalid inputs!", "");
			}
		}
	}

	public class DerivButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				try {
					Controller.validatePolynomials(view.getPol1(), polynomial1);
				} catch (Exception ex) {
					view.showMessage("Invalid Polynomial 1!!");
					throw new Exception();
				}
				result = Operation.deriv(polynomial1);
				view.writeToResultText(result.toString(), "");
				polynomial1.getPolynomial().clear();
				polynomial2.getPolynomial().clear();
			} catch (Exception exec) {
				view.writeToResultText("Invalid Polynomial 1!", "");
			}
		}
	}

	public class IntegButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				try {
					Controller.validatePolynomials(view.getPol1(), polynomial1);
				} catch (Exception ex) {
					view.showMessage("Invalid Polynomial 1!!");
					throw new Exception();
				}
				String resString = Operation.integ(polynomial1);
				view.writeToResultText(resString, "");
				polynomial1.getPolynomial().clear();
				polynomial2.getPolynomial().clear();
			} catch (Exception exec) {
				view.writeToResultText("Invalid Polynomial 1!", "");
			}
		}
	}

	public static void validatePolynomials(String pol, Polynomial polynomial) throws Exception {

		String pattern = "^([+-]?\\d*x\\^\\d+([+-]\\d*x\\^\\d+)*([+-]\\d+)?)$";
//		^([+-]?\\d*[a-zA-Z]\\^[1-9]\\d*(?:[+-]\\d*[a-zA-Z]\\^[1-9]\\d*)*(?:[+-]\\d+)?)+$
		// ^([+-]?\\d*[a-zA-Z]\\^\\d+([+-]\\d*[a-zA-Z]\\^\\d+)*([+-]\\d+)?)*$
		// ^([+-]?\\d*[a-zA-Z]\\^\\d+([+-]\\d*[a-zA-Z]\\^\\d+)*([+-]\\d+)?)*$
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(pol);
		if (m.matches() == false) {
			throw new Exception("");
		}
		List<String> monomials = new ArrayList<>();
		String[] monomialStrings = pol.split("(?=[+-])");
		for (String monomial : monomialStrings) {
			if (!monomial.isEmpty()) {
//				System.out.println(term);
				monomials.add(monomial.trim());
			}
		}
		for (String i : monomials) {
			String power = "";
			String coef = "";
			int indexOfX = i.indexOf('x');
			if (indexOfX == 0) {
				coef = "1";
				power = i.substring(indexOfX + 2, i.length());
			} else if (indexOfX == 1 && i.charAt(0) == '+') {
				coef = "1";
				power = i.substring(indexOfX + 2, i.length());
			} else if (indexOfX == 1 && i.charAt(0) == '-') {
				coef = "-1";
				power = i.substring(indexOfX + 2, i.length());
			} else if (indexOfX == -1) {
				coef = i;
				power = "0";
			} else {
				coef = i.substring(0, i.indexOf('x'));
				power = i.substring(indexOfX + 2, i.length());
			}
			System.out.println(coef + " " + power);
			Monomial monomial = new Monomial(Integer.parseInt(coef), Integer.parseInt(power));
			polynomial.addMonomial(monomial);
		}
	}
}
