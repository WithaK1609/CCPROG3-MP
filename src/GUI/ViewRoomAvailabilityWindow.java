/**
 * This class is responsible for creating the GUI for the View Room Availability Window.
 * <p> The user can view the availability of rooms in a hotel by entering the check-in and check-out dates.
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0 
 */
package GUI;

import hotel.Hotel;
import Controller.ViewHotelController;
import rooms.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ViewRoomAvailabilityWindow extends JFrame {
    private ViewHotelController viewHotelController = new ViewHotelController();
    private JTextField checkInField;
    private JTextField checkOutField;
    private JButton findRoomsButton;
    private JComboBox<Room> availableRoomsComboBox;
    private JTextArea availabilityField;

    // Constructor
    public ViewRoomAvailabilityWindow(Hotel selectedHotel) {
        initializeGUI(selectedHotel);
    }

    /**
     * Initializes the GUI for the View Room Availability Window.
     * @param selectedHotel
     */
    public void initializeGUI(Hotel selectedHotel) {
        setTitle("Room Availability");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        // Create input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        inputPanel.add(new JLabel("Check-in Date:"));
        checkInField = new JTextField();
        inputPanel.add(checkInField);

        inputPanel.add(new JLabel("Check-out Date:"));
        checkOutField = new JTextField();
        inputPanel.add(checkOutField);

        add(inputPanel, BorderLayout.NORTH);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        findRoomsButton = new JButton("Find Available Rooms");
        buttonPanel.add(findRoomsButton);

        add(buttonPanel, BorderLayout.CENTER);


        // Create available rooms panel
        JPanel availableRoomsPanel = new JPanel();
        availableRoomsPanel.setLayout(new BorderLayout());
        availableRoomsPanel.add(new JLabel("Available Rooms:"), BorderLayout.NORTH);
        DefaultComboBoxModel<Room> roomModel = new DefaultComboBoxModel<>();
        availableRoomsComboBox = new JComboBox<>(roomModel);
        availableRoomsComboBox.setEditable(false);
        availableRoomsPanel.add(availableRoomsComboBox, BorderLayout.CENTER);

        add(availableRoomsPanel, BorderLayout.EAST);

        // Create availability panel
        JPanel availabilityPanel = new JPanel();
        availabilityPanel.setLayout(new BorderLayout());
        availabilityField = new JTextArea(10, 20);
        availabilityField.setEditable(false);
        availabilityPanel.add(new JScrollPane(availabilityField), BorderLayout.CENTER);

        add(availabilityPanel, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(400, 400));
        pack();
         
        findRoomsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkInField.getText().isEmpty() || checkOutField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in the check in and check out dates.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!viewHotelController.validateBookingDates(checkInField.getText(), checkOutField.getText())) {
                    JOptionPane.showMessageDialog(null, viewHotelController.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {
                    roomModel.removeAllElements();
                    int checkInDate = Integer.parseInt(checkInField.getText());
                    int checkOutDate = Integer.parseInt(checkOutField.getText());
                    if (selectedHotel != null) {
                        for (Room room : selectedHotel.getRooms()) {
                            if (selectedHotel.isRoomAvailable(room, checkInDate, checkOutDate)) {
                                roomModel.addElement(room);
                            }
                        }
                    }
                    availableRoomsComboBox.setModel(roomModel); // Update the roomComboBox with the new model
                    availabilityField.setText(viewHotelController.viewWhichRoomsareAvailable(selectedHotel, checkInField.getText(), checkOutField.getText()));
                }
                
            }
        });

    }
}