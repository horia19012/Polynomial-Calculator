package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import DataModels.Polynomial;

public class GUI extends JFrame {

	
	private JTextField pol1;
	private JTextField pol2;
	private JTextField result;
	private JButton addBtn;
	private JButton derivBtn;
	private JButton subBtn;
	private JButton intgBtn;
	private JButton mulBtn;
	private JButton divBtn;

	public GUI() {

	}

	public GUI(Polynomial polynomial1, Polynomial polynomial2) {

		JFrame frame = new JFrame("Polynomial Calculator");
		
		JLabel title = new JLabel("POLYNOMIAL CALCULATOR");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(550, 400);
		
		JPanel allPanel=new JPanel();
		frame.setContentPane(allPanel);
		

		

		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));

		title.setFont(new Font("Arial", Font.BOLD, 15));
		title.setAlignmentX(CENTER_ALIGNMENT);
		

		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new FlowLayout());
		resultPanel.add(new JLabel("RESULT: "));
		result = new JTextField();
		result.setPreferredSize(new Dimension(200, 30));

		JScrollBar scroll3 = new JScrollBar();
		scroll3.setModel(result.getHorizontalVisibility());
		scroll3.setOrientation(JScrollBar.HORIZONTAL);

		result.setEditable(false);
		resultPanel.add(result);
		resultPanel.add(scroll3);
		resultPanel.setBackground(Color.getHSBColor(100, 200, 150));

		JPanel pol1Panel = new JPanel();
		pol1Panel.setLayout(new FlowLayout());
		pol1Panel.add(new JLabel("Polynomial 1: "));
		pol1 = new JTextField();
		pol1.setPreferredSize(new Dimension(200, 30));

		JScrollBar scroll1 = new JScrollBar();
		scroll1.setModel(pol1.getHorizontalVisibility());
		scroll1.setOrientation(JScrollBar.HORIZONTAL);

		pol1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		pol1Panel.add(pol1);
		pol1Panel.add(scroll1);
		pol1Panel.setBackground(Color.getHSBColor(100, 200, 150));

		JPanel pol2Panel = new JPanel();
		pol2Panel.setLayout(new FlowLayout());
		pol2Panel.add(new JLabel("Polynomial 2: "));
		pol2 = new JTextField();
		pol2.setPreferredSize(new Dimension(200, 30));

		JScrollBar scroll2 = new JScrollBar();
		scroll2.setModel(pol2.getHorizontalVisibility());
		scroll2.setOrientation(JScrollBar.HORIZONTAL);

		pol2Panel.add(pol2);
		pol2Panel.add(scroll2);
		pol2Panel.setBackground(Color.getHSBColor(100, 200, 150));

		JPanel firstRowBtn = new JPanel();
		firstRowBtn.setLayout(new FlowLayout());
		addBtn = new JButton("+");
		addBtn.setPreferredSize(new Dimension(70, 40));
		subBtn = new JButton("-");
		subBtn.setPreferredSize(new Dimension(70, 40));
		mulBtn = new JButton("X");
		mulBtn.setPreferredSize(new Dimension(70, 40));
		firstRowBtn.add(addBtn);
		firstRowBtn.add(subBtn);
		firstRowBtn.add(mulBtn);

		JPanel secondRowBtn = new JPanel();
		secondRowBtn.setLayout(new FlowLayout());
		divBtn = new JButton("/");
		divBtn.setPreferredSize(new Dimension(70, 40));
		derivBtn = new JButton("Deriv");
		derivBtn.setPreferredSize(new Dimension(70, 40));
		intgBtn = new JButton("Intg");
		intgBtn.setPreferredSize(new Dimension(70, 40));
		secondRowBtn.add(divBtn);
		secondRowBtn.add(derivBtn);
		secondRowBtn.add(intgBtn);

		upperPanel.add(title);
		upperPanel.add(resultPanel);
		upperPanel.add(pol1Panel);
		upperPanel.add(pol2Panel);

		JLabel formatLabel = new JLabel("Format: ax^n+...+bx^1+c");
		JLabel observation = new JLabel("*Deriving and Integration only for Polynomial 1");
		formatLabel.setFont(new Font("Arial", Font.BOLD, 15));
		formatLabel.setAlignmentX(CENTER_ALIGNMENT);
		observation.setAlignmentX(CENTER_ALIGNMENT);

		allPanel.setLayout(new BoxLayout(allPanel,BoxLayout.Y_AXIS));
		allPanel.add(upperPanel);
		allPanel.add(formatLabel);
		allPanel.add(observation);
		allPanel.add(firstRowBtn);
		allPanel.add(secondRowBtn);
		allPanel.setBackground(Color.getHSBColor(100, 200, 150));
		
		frame.setVisible(true);
	}

	public void addButtonListener(ActionListener x) {
		addBtn.addActionListener(x);
	}

	public void subButtonListener(ActionListener x) {
		subBtn.addActionListener(x);
	}

	public void derivButtonListener(ActionListener x) {
		derivBtn.addActionListener(x);
	}

	public void integButtonListener(ActionListener x) {
		intgBtn.addActionListener(x);
	}

	public void divButtonListener(ActionListener x) {
		divBtn.addActionListener(x);
	}

	public String getPol1() {
		return this.pol1.getText();
	}

	public String getPol2() {
		return this.pol2.getText();
	}

	public void showMessage(String errMessage) {
		JOptionPane.showMessageDialog(this, errMessage);
	}

	public JTextField getResText() {
		return this.result;
	}

	public void mulButtonListener(ActionListener x) {
		mulBtn.addActionListener(x);
	}

	public void writeToResultText(String s, String s1) {
		this.result.setText("");
		this.result.setText(s + s1);
		this.result.repaint();
	}
}
