package manageHotelGUIs;

import hotel.Hotel;
import hotel.HotelManager;
import Controller.HotelController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class ChangeHotelNameWindow extends JFrame {
    private HotelController hotelController = new HotelController();
    private HotelManager hotelManager = HotelManager.getInstance();
    private JTextField nameField;
    private JButton saveButton;
    private JButton backButton;

    public ChangeHotelNameWindow(Hotel hotel) {
        setTitle("Change Hotel Name");
        setSize(300, 130);
        setLayout(new GridLayout(4, 1)); // Adjust grid layout to fit all components

        add(new JLabel("New Hotel Name:"));
        nameField = new JTextField(hotel.getName(), 20);
        add(nameField);
        saveButton = new JButton("Save");
        
        add(new JLabel());
        add(saveButton);
        


        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newName = nameField.getText();

                if (newName.length() > 20) {
                    JOptionPane.showMessageDialog(ChangeHotelNameWindow.this, "Hotel name cannot be longer than 20 characters.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!hotelController.isHotelNameUnique(newName, hotelManager.getHotels())) {
                    JOptionPane.showMessageDialog(ChangeHotelNameWindow.this, "Hotel name already exists! Please choose a different name.");
                } else {
                    int result = JOptionPane.showConfirmDialog(ChangeHotelNameWindow.this, "Are you sure you want to change the hotel name to \"" + newName + "\"?", "Confirm Change", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        hotel.setName(newName);
                        JOptionPane.showMessageDialog(ChangeHotelNameWindow.this, "Hotel name changed to: " + newName);
                        dispose(); // Close the window
                    }
                    // If result is NO_OPTION, do nothing (name not changed)
                }
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // makes it appear in the center of the screen
    }
}
