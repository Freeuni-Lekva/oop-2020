import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame {

    public static final int CALC_WIDTH = 300;
    public static final int CALC_HEIGHT = 300;

    private final JTextField inputField;
    private String operator = "";
    private double leftOperand = 0;
    private boolean clear = false;

    public Calculator() {
        // set general options
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        int x = (screenSize.width - CALC_WIDTH) / 2;
        int y = (screenSize.height - CALC_HEIGHT) / 2;

        this.setSize(CALC_WIDTH, CALC_HEIGHT);
        this.setLocation(x, y);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set main layout
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        borderLayout.setVgap(10);
        this.setLayout(borderLayout);

        // create input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        inputField.setEditable(false);
        inputPanel.add(inputField);

        // create value button panel
        JPanel valueButtonPanel = new JPanel(getGridLayout(4, 3));
        for (int i = 0; i <= 9; ++i) {
            valueButtonPanel.add(getCalcButton("" + i, new ValueButtonListener()));
        }
        valueButtonPanel.add(getCalcButton(".", new ValueButtonListener()));
        valueButtonPanel.add(getCalcButton("=", new ResultButtonListener()));

        // create command button panel
        JPanel commandButtonPanel = new JPanel(getGridLayout(4, 1));
        String[] commands = {"+", "-", "*", "/"};
        for (String c : commands) {
            commandButtonPanel.add(getCalcButton(c, new CommandButtonListener()));
        }

        this.add(inputPanel, BorderLayout.NORTH);
        this.add(valueButtonPanel, BorderLayout.CENTER);
        this.add(commandButtonPanel, BorderLayout.EAST);
    }

    private GridLayout getGridLayout(int rows, int cols) {
        GridLayout gridLayout = new GridLayout(rows, cols);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        return gridLayout;
    }

    private JButton getCalcButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        return button;
    }

    private class ValueButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonClicked = (JButton) e.getSource();
            String digit = buttonClicked.getText();

            if (clear) {
                clear = false;
                inputField.setText("");
            }
            inputField.setText(inputField.getText() + digit);
        }
    }

    private class CommandButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonClicked = (JButton) e.getSource();

            String input = inputField.getText();

            if (!("").equals(input)) {
                operator = buttonClicked.getText();
                leftOperand = Double.parseDouble(input);
                clear = true;
            }
        }
    }

    private class ResultButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (("").equals(operator)) {
                return;
            }

            double rightOperand = Double.parseDouble(inputField.getText());
            double result = 0;

            switch (operator) {
                case "+":
                    result = leftOperand + rightOperand;
                    break;
                case "-":
                    result = leftOperand - rightOperand;
                    break;
                case "*":
                    result = leftOperand * rightOperand;
                    break;
                case "/":
                    if (rightOperand == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    result = leftOperand / rightOperand;
                    break;
                default:
                    throw new AssertionError("Unknown operator");
            }

            operator = "";
            leftOperand = result;
            inputField.setText("" + result);
        }
    }
}