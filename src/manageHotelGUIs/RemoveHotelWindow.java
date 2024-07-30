package manageHotelGUIs;

import hotel.Hotel;
import hotel.HotelManager;
import GUI.ManageHotelWindow;

import javax.swing.*;
import java.awt.*;

public class RemoveHotelWindow extends JDialog {

    private HotelManager hotelManager = HotelManager.getInstance();
    private JButton removeButton;
    private JButton cancelButton;

    public RemoveHotelWindow(Hotel hotel, ManageHotelWindow parentWindow) {
        setTitle("Remove Hotel");
        setSize(300, 80);
        setLayout(new GridLayout(1, 1));

        removeButton = new JButton("Remove");
        cancelButton = new JButton("Cancel");

        removeButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove the hotel \"" + hotel.getName() + "\"?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                hotelManager.removeHotel(hotel);
                JOptionPane.showMessageDialog(this, "Hotel \"" + hotel.getName() + "\" has been removed.");
                dispose(); 

                if (hotelManager.getHotels().isEmpty()) { // closes the parent window if there are no hotels left
                    parentWindow.dispose();
                }
            }

        });

        cancelButton.addActionListener(e -> dispose()); 

        add(removeButton);
        add(cancelButton);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null); // makes it appear in the center of the screen
    }
}
