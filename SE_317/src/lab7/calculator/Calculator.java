package lab7.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.*;

public class Calculator extends JFrame {

    protected JTextField num1Field;
    protected JTextField num2Field;
    protected JLabel resultLabel;

    protected JTextField displayField;
    protected JTextField outputField;
    protected JButton memOut;
    protected JButton equalOut;
    
    protected String operand1 = "";
    protected String operand2 = "";
    protected String operator = "";
    protected String equals = "0";
    protected String mem = "0";
    
    protected JButton add;
    protected JButton sub;
    protected JButton mult;
    protected JButton div;
    
    protected boolean isFirstInput = true;

    public Calculator() {

        // Set the look and feel to the system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        
        JPanel outPanel = new JPanel();
        outPanel.setLayout(new BorderLayout());
        
//        outputField = new JTextField();
//        outputField.setEditable(false);
//        outputField.setHorizontalAlignment(JTextField.RIGHT);
        memOut = new JButton("Mem ");
        equalOut = new JButton("= ");
        
        JPanel out = new JPanel();
        out.setLayout(new GridLayout(2, 1, 6, 4));
        out.add(memOut);
        out.add(equalOut);
        
        outPanel.add(out, BorderLayout.NORTH);

        // Create the display field
        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        
        outPanel.add(displayField, BorderLayout.SOUTH);

        mainPanel.add(outPanel, BorderLayout.NORTH);
        
        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 3, 3));

        String[] buttonLabels = {
        	"M+", "clr", "\u221A", "#\u00B2", "del",
            "M-", "7", "8", "9", "/",
            "M-R", "4", "5", "6", "*",
            "M-C", "1", "2", "3", "-",
            "-#", ".", "0", "=", "+"
        };

        for (String label : buttonLabels) {
        	if(label.equals("+")) {
        		add = new JButton("+");
        		add.addActionListener(new ButtonClickListener());
        		buttonPanel.add(add);
        		continue;
        	} else if(label.equals("-")) {
        		sub = new JButton("-");
        		sub.addActionListener(new ButtonClickListener());
        		buttonPanel.add(sub);
        		continue;
        	} else if(label.equals("*")) {
        		mult = new JButton("*");
        		mult.addActionListener(new ButtonClickListener());
        		buttonPanel.add(mult);
        		continue;
        	} else if(label.equals("/")) {
        		div = new JButton("/");
        		div.addActionListener(new ButtonClickListener());
        		buttonPanel.add(div);
        		continue;
        	}
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the main panel to the frame
        add(mainPanel);

        // Set frame properties
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	JButton b = (JButton) e.getSource();
            String command = e.getActionCommand();
            switch (command) {
            	case "M+":
            		if(!(equals).equals("0") ) {
            			mem = String.valueOf(Double.parseDouble(mem) + Double.parseDouble(equals));
            			equals = "0";
            		}
            		else {
            			JOptionPane.showMessageDialog(null, "No valid result to add to memory.", "Error", JOptionPane.ERROR_MESSAGE);
            			break;
            		}
            		memOut.setText("Mem " + mem);
            		equalOut.setText("= ");
            		break;
            	case "M-":
            		if(!(equals).equals("0") ) {
            			mem = String.valueOf(Double.parseDouble(mem) - Double.parseDouble(equals));
            		}
            		else {
            			JOptionPane.showMessageDialog(null, "No valid result to subtract from memory.", "Error", JOptionPane.ERROR_MESSAGE);
            			break;
            		}
            		memOut.setText("Mem " + mem);
            		break;
            	case "M-R":
            		if(mem.equals("0")) {
            			JOptionPane.showMessageDialog(null, "No valid result to add to memory.", "Error", JOptionPane.ERROR_MESSAGE);
            			break;
            		}
            		operand1 = mem;
            		displayField.setText(operand1);
            		break;
            	case "M-C":
            		mem = "0";
            		memOut.setText("Mem ");
            		break;
            	case "del":
            		if( (displayField.getText()).length() > 0 ) {
            			operand2 =  ((displayField.getText()).substring(0,displayField.getText().length()-1));
	                    displayField.setText(operand2);
            		}
                    break;
                case "clr":
                    displayField.setText("");
                    equalOut.setText("= "); 
                    memOut.setText("Mem " );
                    
                    operand1 = "";
                    operand2 = "";
                    operator = "";
                    equals = "0";
                    mem = "0";
                    isFirstInput = true;
                    add.setBackground(Color.WHITE);
                	sub.setBackground(Color.WHITE);
                	mult.setBackground(Color.WHITE);
                	div.setBackground(Color.WHITE);
                    break;
                case "\u221A":
            	case "#\u00B2":
            		if((displayField.getText()).length() == 0) {
            			JOptionPane.showMessageDialog(null, "Please enter an initial value to use this operation", "Error", JOptionPane.ERROR_MESSAGE);
            			break;
            		}
                    operand2 = displayField.getText();
                    operator = command;
                    equals = String.valueOf(calculateResult());
                    equalOut.setText("= " + equals); 
                    operand2 = "";
                    displayField.setText("");
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                	b.setBackground(Color.RED);
                    if (!isFirstInput) {
                        operand1 = displayField.getText();
                    } 
                    else if( String.valueOf(operand1).equals(mem) ) {
                    	
                    }
                    else {
                    	JOptionPane.showMessageDialog(null, "Please enter an initial value.", "Error", JOptionPane.ERROR_MESSAGE);
                    	add.setBackground(Color.WHITE);
                    	sub.setBackground(Color.WHITE);
                    	mult.setBackground(Color.WHITE);
                    	div.setBackground(Color.WHITE);
                    }
                    operator = command;
                    
                    displayField.setText("");
                    isFirstInput = true;
                    break;
                case "=":
                	add.setBackground(Color.WHITE);
                	sub.setBackground(Color.WHITE);
                	mult.setBackground(Color.WHITE);
                	div.setBackground(Color.WHITE);
                	
                    if (!isFirstInput) {
                        operand2 = displayField.getText();
                    }
                    equals =  String.valueOf(calculateResult());
                    equalOut.setText("= " + equals);
                    displayField.setText("");
                    isFirstInput = true;
                    operand2 = "";
                    break;
                case "-#":
                	if((displayField.getText()).length() == 0) {
            			JOptionPane.showMessageDialog(null, "Please enter a number before making it negative", "Error", JOptionPane.ERROR_MESSAGE);
            			break;
            		}
                	displayField.setText(String.valueOf(0 - Double.parseDouble(displayField.getText())));
                	break;
                default:
                	if(command.equals(".")) {
                		if((displayField.getText()).contains(".")) {
                			JOptionPane.showMessageDialog(null, "A number can only have one decmil point.", "Error", JOptionPane.ERROR_MESSAGE);
                			break;
                		}
                	}
                    displayField.setText(displayField.getText() + command);
                    isFirstInput = false;
                    break;
            }
        }

        private double calculateResult() {
            double result = 0;
            switch (operator) {
            	case "\u221A":
            		result = Math.sqrt(Double.parseDouble(operand2));
            		break;
            	case "#\u00B2":
            		result = Double.parseDouble(operand2) * Double.parseDouble(operand2);
            		break;
                case "+":
                    result = Double.parseDouble(operand1) + Double.parseDouble(operand2);
                    break;
                case "-":
                    result = Double.parseDouble(operand1) - Double.parseDouble(operand2);
                    break;
                case "*":
                    result = Double.parseDouble(operand1) * Double.parseDouble(operand2);
                    break;
                case "/":
                    if (Double.parseDouble(operand2) != 0) {
                        result = Double.parseDouble(operand1) / Double.parseDouble(operand2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
            }
            return result;
        }
    }
}
