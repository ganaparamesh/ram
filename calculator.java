import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculator extends JFrame implements ActionListener {
    
    // Create the frame
    private JFrame frame;
    
    // Create a text field
    private JTextField textField;
    
    // Store operator and operands
    private String operator = "";
    private double num1 = 0;
    private boolean start = true;

    public calculator() {
        // Create a new frame
        frame = new JFrame("Calculator");
        
        // Create a text field
        textField = new JTextField();
        textField.setEditable(false);

        // Create buttons
        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
        }

        JButton addButton = new JButton("+");
        JButton subButton = new JButton("-");
        JButton mulButton = new JButton("*");
        JButton divButton = new JButton("/");
        JButton eqButton = new JButton("=");
        JButton clrButton = new JButton("C");

        // Create panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        
        // Add number buttons to panel
        for (int i = 1; i < 10; i++) {
            panel.add(numberButtons[i]);
        }
        panel.add(new JLabel("")); // Empty label
        panel.add(numberButtons[0]);
        
        // Add operation buttons to panel
        panel.add(addButton);
        panel.add(subButton);
        panel.add(mulButton);
        panel.add(divButton);
        panel.add(eqButton);
        panel.add(clrButton);
        
        // Add ActionListeners to buttons
        for (int i = 0; i < 10; i++) {
            numberButtons[i].addActionListener(this);
        }
        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        eqButton.addActionListener(this);
        clrButton.addActionListener(this);
        
        // Create layout and add components
        frame.setLayout(new BorderLayout());
        frame.add(textField, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        // Set frame properties
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            if (start) {
                textField.setText(command);
                start = false;
            } else {
                textField.setText(textField.getText() + command);
            }
        } else if (command.charAt(0) == 'C') {
            textField.setText("");
            operator = "";
            num1 = 0;
            start = true;
        } else if (command.charAt(0) == '=') {
            double num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case "+":
                    textField.setText(String.valueOf(num1 + num2));
                    break;
                case "-":
                    textField.setText(String.valueOf(num1 - num2));
                    break;
                case "*":
                    textField.setText(String.valueOf(num1 * num2));
                    break;
                case "/":
                    textField.setText(String.valueOf(num1 / num2));
                    break;
            }
            operator = "";
            start = true;
        } else {
            if (!operator.isEmpty()) {
                return;
            }
            operator = command;
            num1 = Double.parseDouble(textField.getText());
            start = true;
        }
    }

    public static void main(String[] args) {
        new calculator();
    }
}