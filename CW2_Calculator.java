import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * CW2_Calculation
 * 
 * Module to perform calculation from GUI
 * 
 * @author    Connor Healy / Matthew Gordon 
 * 
 */
public class CW2_Calculator implements ActionListener{

    private JFrame frame;
    private JTextField boxfield;
    private JButton[] numberButtons = new JButton[10];
	private JButton[] functionButtons = new JButton[9];
	private JButton addition,subtraction,multiplication,division;
	private JButton decimal, equals, delete, clear, negative;
	private JPanel window;
	private boolean calcuating = false;

	// set a private Calculation
    private CW2_Calculation calc = new CW2_Calculation();

   /**
   * Constructor
   * Configure and display the Swing GUI
   */ 
    CW2_Calculator() 
	{
		// Set the Java Swing
        frame = new JFrame("CW2 Calculator");
		frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(425, 550);
        frame.setLayout(null);

        boxfield = new JTextField();
        boxfield.setBounds(50, 25, 300, 40);
        boxfield.setEditable(false);
		boxfield.setBackground(Color.WHITE);
		boxfield.setText("0");
        boxfield.setHorizontalAlignment(SwingConstants.RIGHT);
		Font displayFont = new Font("Courier", Font.BOLD,18);
		boxfield.setFont(displayFont);

        addition = new JButton("+");
        subtraction = new JButton("-");
        multiplication = new JButton("X");
        decimal = new JButton(".");
        division = new JButton("÷");
        equals = new JButton("=");
        delete = new JButton("Del");
        clear = new JButton("Clr");
        negative = new JButton("(-)");
        
        functionButtons[0] = addition;
		functionButtons[1] = subtraction;
		functionButtons[2] = multiplication;
		functionButtons[3] = decimal;
		functionButtons[4] = division;
		functionButtons[5] = equals;
		functionButtons[6] = delete;
		functionButtons[7] = clear;
		functionButtons[8] = negative;

		for(int i =0;i<9;i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFocusable(false);
			functionButtons[i].setFont(displayFont);
		}
		
		for(int i =0;i<10;i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFocusable(false);
			numberButtons[i].setFont(displayFont);
		}
		
		negative.setBounds(50,430,100,50);
		delete.setBounds(150,430,100,50);
		clear.setBounds(250,430,100,50);
		
		window = new JPanel();
		window.setBounds(50, 100, 300, 300);
		window.setLayout(new GridLayout(4,4,10,10));

		window.add(numberButtons[1]);
		window.add(numberButtons[2]);
		window.add(numberButtons[3]);
		window.add(addition);
		window.add(numberButtons[4]);
		window.add(numberButtons[5]);
		window.add(numberButtons[6]);
		window.add(subtraction);
		window.add(numberButtons[7]);
		window.add(numberButtons[8]);
		window.add(numberButtons[9]);
		window.add(multiplication);
		window.add(decimal);
		window.add(numberButtons[0]);
		window.add(equals);
		window.add(division);
		
		frame.add(window);
		frame.add(negative);
		frame.add(delete);
		frame.add(clear);
		frame.add(boxfield);
		frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		// Get the key pressed
		String keyPressed = e.getActionCommand();
		// Get the text of the current display
		String currentDisplay = boxfield.getText();
		// If the key is a number or decimal point
		if (keyPressed.charAt(0) >= '0' && keyPressed.charAt(0) <= '9' || (keyPressed.charAt(0) == '.') && !currentDisplay.contains(".")) {
			// if the display is 0 and a decimal point is pressed or the calculator is calcuating switch to input mode
			if (currentDisplay.equals("0") && (!keyPressed.equals(".")) || calcuating ) {
				// then just display the key pressed
				boxfield.setText(keyPressed);
			} else {
				// extend the current key with the current display
				boxfield.setText(currentDisplay + keyPressed);
			}
			// set calculating to false as now entering numbers
			calcuating = false;
		}
		// If the del key is pressed
		if (keyPressed == "Del") {
			// If the current display is just one character
			if (currentDisplay.length() == 1) {
				// then set the display to zero
				boxfield.setText("0");
			// else
			} else {
				// remove a character from the display
				boxfield.setText(currentDisplay.substring(0, currentDisplay.length() - 1));
			}
		}
		// Switch sign of number
		if (keyPressed == "(-)") {
			// if the current display is negative
			if (currentDisplay.substring(0,1).equals("-")) {
				// set display to positive
				boxfield.setText(currentDisplay.substring(1));
			} else {
				// set display to negative
				boxfield.setText("-" + currentDisplay);
			}
			// if the last calculation was an =, set the sign of display to the current result of the calculator
			if (calc.getLastOperator() == "=") {
				calc.setResult(Double.valueOf(boxfield.getText()));
			}
		}
		// Clear the display and calculator
		if (keyPressed == "Clr") {
			boxfield.setText("0");
			// Clear the calculator
			calc.clear();
		}
		// If an operator was pressed
		if (keyPressed == "+" || keyPressed == "-" || keyPressed == "X" || keyPressed == "÷" || keyPressed == "=") {
			// Get the result of the calculator
			Double result = calc.calculate(keyPressed, Double.valueOf(boxfield.getText()));
			// Update the display
			boxfield.setText(String.valueOf(result));
			// Set calculating to true
			calcuating = true;
		}
    }
}
