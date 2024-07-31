/**
 * This class is responsible for creating the GUI for the Select View Hotel Window.
 *
 * <p> The user can view the details of the selected hotel.
 *
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
import java.util.List;

public class SelectedViewHotelWindow extends JFrame{
    private ViewHotelController viewHotelController = new ViewHotelController();
    private JButton viewRoomAvailButton;
    private JButton viewSpecificRoomButton;
    private JButton viewSpecificBookingButton;
    private JTextArea hotelDescriptionArea;

    // Constructor
    public SelectedViewHotelWindow(Hotel selectedHotel) {
        initializeGUI(selectedHotel);
    }

    /**
     * Initializes the GUI for the Selected View Hotel Window.
     * @param selectedHotel
     */
    public void initializeGUI(Hotel selectedHotel) {
        setTitle("View Hotel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setLayout(new GridLayout(4, 1));
        setLocationRelativeTo(null); // makes it appear in the center of the screen

        // Create a panel for the room description
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setBorder(BorderFactory.createTitledBorder("Hotel Information"));
        add(descriptionPanel);

        // Hotel Description Area
        hotelDescriptionArea = new JTextArea();
        hotelDescriptionArea.setEditable(false);
        hotelDescriptionArea.setLineWrap(true);
        hotelDescriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(hotelDescriptionArea);
        hotelDescriptionArea.setPreferredSize(new Dimension(300, 100));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        hotelDescriptionArea.setText(viewHotelController.viewHighLevelInfo(selectedHotel));
        descriptionPanel.add(scrollPane);

        // add buttons
        viewRoomAvailButton = new JButton("View Which Rooms are Available");
        viewSpecificRoomButton = new JButton("View Specific Room Details");
        viewSpecificBookingButton = new JButton("View Specific Reservation Details");
        add(viewRoomAvailButton);
        add(viewSpecificRoomButton);
        add(viewSpecificBookingButton);

        viewRoomAvailButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewRoomAvailabilityWindow(selectedHotel).setVisible(true);
            }
        });

        viewSpecificRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewSpecificRoomWindow(selectedHotel).setVisible(true);

            }
        });

        viewSpecificBookingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewSpecificBookingWindow(selectedHotel).setVisible(true);
            }
        });
        
    }
}