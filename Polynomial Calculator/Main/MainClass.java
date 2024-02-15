package Main;
import DataModels.Polynomial;
import GUI.Controller;
import GUI.GUI;

public class MainClass {

	public static void main(String[] args) {
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		GUI view = new GUI(p1,p2);
		view.setVisible(true);
		Controller controller = new Controller(view, p1, p2);

	}

}
