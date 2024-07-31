package manageHotelGUIs;

import hotel.Hotel;
import hotel.HotelManager;
import Controller.HotelController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Represents a window for adding rooms to a hotel.
 * 
 * <p>This class handles the GUI and functionality for adding rooms to a hotel.
 * 
 * @author Kian Daylag
 * @version 1.0
 */

public class AddRoomsWindow extends JFrame {
    private HotelController hotelController = new HotelController();
    private HotelManager hotelManager = HotelManager.getInstance();
    private JTextField baseRoomsField;
    private JTextField deluxeRoomsField;
    private JTextField executiveRoomsField;
    private JButton saveButton;


    /**
     * Creates a window for adding rooms to a hotel.
     * 
     * @param hotel the hotel to add rooms to
     */
    
    public AddRoomsWindow(Hotel hotel) {
        setTitle("Add Rooms");
        setSize(400, 250);
        setLayout(new GridLayout(7, 1));

        int baseRoomCount = hotel.countBaseRooms();
        int deluxeRoomCount = hotel.countDeluxeRooms();
        int executiveRoomCount = hotel.countExecutiveRooms();

        add(new JLabel("Add base rooms (" + baseRoomCount + "): "));
        baseRoomsField = new JTextField();
        add(baseRoomsField);

        add(new JLabel("Add deluxe rooms (" + deluxeRoomCount + "): "));
        deluxeRoomsField = new JTextField();
        add(deluxeRoomsField);

        add(new JLabel("Add executive rooms (" + executiveRoomCount + "): "));
        executiveRoomsField = new JTextField();
        add(executiveRoomsField);

        saveButton = new JButton("Save");
        add(saveButton);

        saveButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if(!hotelController.validateAddedNumberOfRooms(baseRoomsField.getText(), deluxeRoomsField.getText(), executiveRoomsField.getText(), hotel)){
                JOptionPane.showMessageDialog(AddRoomsWindow.this, hotelController.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                int result = JOptionPane.showConfirmDialog(AddRoomsWindow.this, "Are you sure you want to add " + baseRoomsField.getText() + " base rooms, " + deluxeRoomsField.getText() +" deluxe rooms, and " + executiveRoomsField.getText() + " executive rooms?", "Confirm Change", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    int baseRooms = Integer.parseInt(baseRoomsField.getText());
                    int deluxeRooms = Integer.parseInt(deluxeRoomsField.getText());
                    int executiveRooms = Integer.parseInt(executiveRoomsField.getText());

                    hotelManager.addRooms(baseRooms, deluxeRooms, executiveRooms, hotel);

                    JOptionPane.showMessageDialog(AddRoomsWindow.this, "Rooms added successfully.");
                    dispose();
                }

            }

          } 
        });
        

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
