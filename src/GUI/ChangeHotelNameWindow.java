package GUI;

import hotel.Hotel;
import hotel.HotelManager;

import javax.swing.*;
import Controller.HotelController;
import java.awt.*;

public class ChangeHotelNameWindow extends JDialog {
    private HotelController hotelController = new HotelController();
    private HotelManager hotelManager = HotelManager.getInstance();
    private JTextField nameField;
    private JButton saveButton;
    private JButton backButton;

    public ChangeHotelNameWindow(Hotel hotel) {
        setTitle("Change Hotel Name");
        setSize(300, 100);
        setLayout(new GridLayout(2, 1)); // Adjust grid layout to fit all components

        nameField = new JTextField(hotel.getName(), 20);
        saveButton = new JButton("Save");
        backButton = new JButton("Back");

        saveButton.addActionListener(e -> {
            String newName = nameField.getText();

            if (newName.length() > 20) {
                JOptionPane.showMessageDialog(this, "Hotel name cannot be longer than 20 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!hotelController.isHotelNameUnique(newName, hotelManager.getHotels())) {
                JOptionPane.showMessageDialog(this, "Hotel name already exists! Please choose a different name.");
            } else {
                int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to change the hotel name to \"" + newName + "\"?", "Confirm Change", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    hotel.setName(newName);
                    JOptionPane.showMessageDialog(this, "Hotel name changed to: " + newName);
                    dispose(); // Close the window
                }
                // If result is NO_OPTION, do nothing (name not changed)
            }
        });

        backButton.addActionListener(e -> dispose()); // Close the window without making changes

        add(new JLabel("New Hotel Name:"));
        add(nameField);
        add(saveButton);
        add(backButton);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
