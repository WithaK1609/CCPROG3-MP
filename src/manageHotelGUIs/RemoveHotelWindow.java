package manageHotelGUIs;

import hotel.Hotel;
import hotel.HotelManager;
import GUI.ManageHotelWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

    /**
     * Represents a window for removing a hotel.
     * 
     * <p>This class handles the GUI and functionality for removing a hotel.
     * 
     * @author Kian Daylag
     * @version 1.0
     */

public class RemoveHotelWindow extends JFrame {

    private HotelManager hotelManager = HotelManager.getInstance();
    private JButton removeButton;
    private JButton cancelButton;

    /**
     * Creates a window for removing a hotel.
     * 
     * @param hotel the hotel to remove
     * @param parentWindow the parent window
     */
    
    public RemoveHotelWindow(Hotel hotel, ManageHotelWindow parentWindow) {
        setTitle("Remove Hotel");
        setSize(300, 80);
        setLayout(new GridLayout(1, 1));

        removeButton = new JButton("Remove");
        add(removeButton);
        cancelButton = new JButton("Cancel");
        add(cancelButton);


        removeButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e){
                int result = JOptionPane.showConfirmDialog(RemoveHotelWindow.this, "Are you sure you want to remove the hotel \"" + hotel.getName() + "\"?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    hotelManager.removeHotel(hotel);
                    JOptionPane.showMessageDialog(RemoveHotelWindow.this, "Hotel \"" + hotel.getName() + "\" has been removed.");
                    dispose(); 
    
                    if (hotelManager.getHotels().isEmpty()) { // closes the parent window if there are no hotels left
                        parentWindow.dispose();
                    }
                }
            }
        });

        cancelButton.addActionListener(e -> dispose()); 

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null); // makes it appear in the center of the screen
    }
}
