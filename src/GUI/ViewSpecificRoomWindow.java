/**
 * This class is responsible for creating the GUI for the View Specific Room Window.
 * <p> The user can check specific rooms
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0 
 */
package GUI;

import hotel.Hotel;
import rooms.*;
import Controller.ViewHotelController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ViewSpecificRoomWindow extends JFrame{
    private JButton confirmButton;
    
    // Constructor
    public ViewSpecificRoomWindow(Hotel selectedHotel) {
        initializeGUI(selectedHotel);
    }

    /**
     * Initializes the GUI for the View Specific Room Window.
     * @param selectedHotel
     */
    public void initializeGUI(Hotel selectedHotel) {
        

        setTitle("Select Room");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 1));
        setLocationRelativeTo(null); // makes it appear in the center of the screen

        setLocationRelativeTo(null); // makes it appear in the center of the screen

        // Create a panel for the room selection
        JPanel roomPanel = new JPanel();
        roomPanel.setBorder(BorderFactory.createTitledBorder("Select Room"));
        add(roomPanel);

        // Initialize the roomComboBox
        JComboBox<Room> roomComboBox = new JComboBox<>();
        DefaultComboBoxModel<Room> model = new DefaultComboBoxModel<>();
        for (Room room : selectedHotel.getRooms()) {
            model.addElement(room);
        }
        roomComboBox.setModel(model);
        roomPanel.add(roomComboBox);

        // Confirm button
        confirmButton = new JButton("Confirm");
        add(confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Room selectedRoom = (Room) roomComboBox.getSelectedItem();
                new SelectedRoomView(selectedHotel, selectedRoom).setVisible(true);
                dispose();
            }
        });
    }

    private class SelectedRoomView extends JFrame {
        private JTextArea roomDescriptionArea;
        private ViewHotelController viewHotelController = new ViewHotelController();

        // Constructor
        public SelectedRoomView(Hotel selectedHotel, Room selectedRoom) {
            initializeGUI(selectedHotel, selectedRoom);
        }

        /**
         * Initializes the GUI for the Selected Room View Window.
         */
        public void initializeGUI(Hotel selectedHotel, Room selectedRoom) {
            setTitle("View Room");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(570, 200);
            setLayout(new GridLayout(1, 1));
            setLocationRelativeTo(null); // makes it appear in the center of the screen
            setVisible(true);

            // Create a panel for the room description
            JPanel descriptionPanel = new JPanel();
            descriptionPanel.setBorder(BorderFactory.createTitledBorder("Room Information"));
            add(descriptionPanel);

            // Room Description Area
            roomDescriptionArea = new JTextArea();
            roomDescriptionArea.setEditable(false);
            roomDescriptionArea.setLineWrap(true);
            roomDescriptionArea.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(roomDescriptionArea);
            roomDescriptionArea.setPreferredSize(new Dimension(470, 100));
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            roomDescriptionArea.setText(viewHotelController.viewSpecificRoomDetails(selectedHotel, selectedRoom));
            descriptionPanel.add(scrollPane);
        }
    }
}