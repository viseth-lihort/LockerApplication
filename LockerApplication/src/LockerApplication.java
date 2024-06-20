import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LockerApplication {
    private JFrame frame;
    private String storedPassword = null;
    private StringBuilder enteredPassword = new StringBuilder();

    public void setupFrame() {

        frame = new JFrame("Locker Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(3, 3));


        JButton[] numberButtons = new JButton[9];
        for (int i = 1; i <= 9; i++) {
            numberButtons[i - 1] = new JButton(String.valueOf(i));
            numberButtons[i - 1].addActionListener(new NumberButtonListener());
            numberPanel.add(numberButtons[i - 1]);
        }

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JTextField passwordField = new JTextField(10);
        passwordField.setEditable(false);
        JButton enterButton = new JButton("Enter");
        JButton clearButton = new JButton("Clear");

        controlPanel.add(clearButton);
        controlPanel.add(passwordField);
        controlPanel.add(enterButton);

        JLabel statusLabel = new JLabel("", SwingConstants.CENTER);


        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (storedPassword == null) {

                    storedPassword = enteredPassword.toString();
                    statusLabel.setText("Password Set");
                } else {

                    if (storedPassword.equals(enteredPassword.toString())) {
                        statusLabel.setText("Correct Password");
                        JOptionPane.showMessageDialog(frame, "Correct Password", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        statusLabel.setText("Incorrect Password");
                        JOptionPane.showMessageDialog(frame, "Incorrect Password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                enteredPassword.setLength(0);
                passwordField.setText("");
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enteredPassword.setLength(0);
                passwordField.setText("");
                statusLabel.setText("");
                storedPassword = null;
            }
        });


        frame.add(numberPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.add(statusLabel, BorderLayout.NORTH);


        frame.setVisible(true);
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            enteredPassword.append(source.getText());
            JTextField passwordField = (JTextField) ((JPanel) source.getParent().getParent().getComponent(1)).getComponent(1);
            passwordField.setText(enteredPassword.toString());
        }
    }
}
