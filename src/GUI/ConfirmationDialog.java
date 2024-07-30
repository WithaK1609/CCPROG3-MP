package GUI;

import javax.swing.*;
import java.awt.*;

public class ConfirmationDialog extends JDialog {

    private boolean confirmed;

    public ConfirmationDialog(Frame parent, String message, String title) {
        super(parent, title, true);
        setSize(300, 150);
        setLayout(new GridLayout(3, 1));

        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        yesButton.addActionListener(e -> {
            confirmed = true;
            dispose();
        });

        noButton.addActionListener(e -> {
            confirmed = false;
            dispose();
        });

        add(messageLabel);
        add(yesButton);
        add(noButton);

        setLocationRelativeTo(parent);
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
