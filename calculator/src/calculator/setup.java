package calculator;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class setup {
	
	execute exec = new execute();
	
	JFrame frame = new JFrame();
	JPanel buttonspanel = new JPanel(new GridLayout(5, 4));
	JPanel displaypanel = new JPanel(new BorderLayout());
	JLabel displaytext = new JLabel();
	//JTextField displaytext = new JTextField("0");

	String[] buttonvalues = {"(", ")", "C", "DEL",
							 "7", "8", "9", "/",
							 "4", "5", "6", "*",
							 "1", "2", "3", "-",
							 "0", ".", "=", "+"};
	
	String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};
	String[] greys = {"(", ")", "C", "<", "DEL", "/", "*", "-", "+"};
	String[] ops = {"(", ")", "/", "*", "-", "+"};
	
	Color customblue = new Color(76, 173, 228);	
	
	setup() {
		
		buttonspanelsetup();
		displaypanelsetup();
		framesetup();
		
	}
	
	public void framesetup() {
		
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout ());
		frame.add((displaypanel), BorderLayout.NORTH);
		frame.add(buttonspanel);
		
		frame.setVisible(true);	
	}
	
	public void buttonspanelsetup() {
		
		for (int i = 0; i < 20; i++) {
			
			JButton button = new JButton();
			String buttonval = buttonvalues[i];
			button.setFont(new Font("Arial", Font.PLAIN, 15));
			button.setText(buttonval);
			button.setBorder(new LineBorder(Color.black));
			button.setFocusable(false);
			
			if (Arrays.asList(nums).contains(buttonval)) {
				button.setBackground(Color.white);
				button.setForeground(Color.black);
			}
			else if(Arrays.asList(greys).contains(buttonval)) {
				button.setBackground(Color.gray);
				button.setForeground(Color.black);
			}
			else {
				button.setBackground(customblue);
				button.setForeground(Color.white);
			}
			buttonspanel.add(button);
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if (displaytext.getText().equals("Syntax Error")) {
						displaytext.setText("");
					}
					
					JButton button = (JButton) e.getSource();
					String buttonvalue = button.getText();
					
					
					if (Arrays.asList(nums).contains(buttonvalue) || Arrays.asList(ops).contains(buttonvalue)) {
						displaytext.setText(displaytext.getText() + buttonvalue);
					}
					
					if (buttonvalue.equals("DEL")) {
						
						if(displaytext.getText().equals("Syntax Error")) {
							displaytext.setText("");
						}
						if ((displaytext.getText()).length() > 0) {
							displaytext.setText(displaytext.getText().substring(0, (displaytext.getText().length()-1)));
						}
						else {
							displaytext.setText("");
						}
					}
					
					if (buttonvalue.equals("C")) {
						displaytext.setText("");
					}

					if (buttonvalue.equals("=")) {
						String info = displaytext.getText();
						displaytext.setText("");
						String result = (exec.handle(info));
						displaytext.setText(result);
						

					}
				}
				
			});
				
			}
	}
		
		
	
	
	public void displaypanelsetup() {
		
		displaytextsetup();
		displaypanel.add(displaytext);
		
		
	}//display setup
	
	public void displaytextsetup() {
		displaytext.setPreferredSize(new Dimension(0,50));
		displaytext.setHorizontalAlignment(JLabel.RIGHT);
		displaytext.setFont(new Font("Arial", Font.BOLD, 28));
		displaytext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		
	}// display text setup
	
	
		
		
		
}

	
	
	

