/**
 *  This class is responsible for creating the GUI for the Create Hotel Window.
 *
 * <p>This class provides the GUI for creating a hotel.
 *
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */
package GUI;
import javax.swing.*;
import Controller.HotelController;
import hotel.HotelManager;
import java.awt.*;
import java.awt.event.*;

public class CreateHotelWindow extends JFrame {
    private JTextField hotelNameField;
    private JTextField baseRoomsField;
    private JTextField deluxeRoomsField;
    private JTextField executiveRoomsField;
    private JButton createButton, backButton;
    private HotelController hotelController = new HotelController();
    private HotelManager hotelManager = HotelManager.getInstance();
    
    // Constructor
    public CreateHotelWindow() {
        initializeGUI();
    }
    
    /**
     * Initializes the GUI for the Create Hotel Window.
     */
    public void initializeGUI() {
        setTitle("Hotel Manager");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Add components
        add(new JLabel("Hotel Name:"));
        hotelNameField = new JTextField();
        add(hotelNameField);

        add(new JLabel("Base Rooms:"));
        baseRoomsField = new JTextField();
        add(baseRoomsField);

        add(new JLabel("Deluxe Rooms:"));
        deluxeRoomsField = new JTextField();
        add(deluxeRoomsField);

        add(new JLabel("Executive Rooms:"));
        executiveRoomsField = new JTextField();
        add(executiveRoomsField);

        backButton = new JButton("Back");
        add(backButton);

        createButton = new JButton("Create Hotel");
        add(createButton);

        


        // Initialize variables to check if the input is valid
       

        // Add action listener to the button
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hotelNameField.getText().length() > 20){
                    JOptionPane.showMessageDialog(CreateHotelWindow.this, "Hotel name is too long! Please enter a name with less than 20 characters."); 
                    return;
                }

                else if (!hotelController.isHotelNameUnique(hotelNameField.getText(), hotelManager.getHotels())) {
                    JOptionPane.showMessageDialog(CreateHotelWindow.this, "Hotel name already exists! Please choose a different name.");
                    return;
                }

                else if (!hotelController.validateNumberofRooms(baseRoomsField.getText(), deluxeRoomsField.getText(), executiveRoomsField.getText())) {
                    JOptionPane.showMessageDialog(CreateHotelWindow.this, "Please enter a number between 1 and 50 for each room type. The total number of rooms should not exceed 50.");
                }

                else if (hotelNameField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(CreateHotelWindow.this, "Please enter a valid hotel name!");
                    return;
                }

                else {
                    // Create the hotel
                    hotelController.isHotelCreated(hotelNameField.getText(), baseRoomsField.getText(), deluxeRoomsField.getText(), executiveRoomsField.getText());
                    // Display success message
                    JOptionPane.showMessageDialog(CreateHotelWindow.this, "Hotel created successfully!");
                    dispose();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setLocationRelativeTo(null); // makes it appear in the center of the screen
    }
}
