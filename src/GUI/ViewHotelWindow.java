/**
 * Class that creates a window that allows the user to select a hotel from a list of hotels
 * <p> The user can view the details of the selected hotel.
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0 
 */
package GUI;

import hotel.HotelManager;
import hotel.Hotel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ViewHotelWindow extends JFrame {
    private JComboBox<Hotel> hotelComboBox;
    private JButton confirmButton;
    private HotelManager hotelManager = HotelManager.getInstance();
    private List<Hotel> hotels = hotelManager.getHotels();
    
    // Constructor
    public ViewHotelWindow() {
        initializeGUI();
    }
    /**
     * Initializes the GUI for the View Hotel Window.
     */
    public void initializeGUI() {
        setTitle("View Hotel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 150);
        setLayout(new GridLayout(2, 1));
        setLocationRelativeTo(null); // makes it appear in the center of the screen

        JPanel hotelPanel = new JPanel();
        hotelPanel.setBorder(BorderFactory.createTitledBorder("Select Hotel"));
        add(hotelPanel);
        // Hotel Selection
        JLabel hotelLabel = new JLabel("Select Hotel:");
        hotelPanel.add(hotelLabel);
        DefaultComboBoxModel<Hotel> model = new DefaultComboBoxModel<>();
        for (Hotel hotel : hotels) {
            model.addElement(hotel);
        }
        hotelComboBox = new JComboBox<>(model);
        // Set the preferred size of the combo box
        hotelComboBox.setPreferredSize(new Dimension(200, 20)); // Adjust the width and height as needed
        hotelPanel.add(hotelComboBox);

        // confirm button
        confirmButton = new JButton("Confirm");
        add(confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Hotel selectedHotel = (Hotel) hotelComboBox.getSelectedItem();
                new SelectedViewHotelWindow(selectedHotel).setVisible(true);
                dispose();
            }
        });
    }
    
}
