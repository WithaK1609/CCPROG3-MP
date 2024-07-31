package manageHotelGUIs;

import hotel.Hotel;
import rooms.*;


import javax.swing.*;

import Controller.HotelController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a window for updating the price of rooms in a hotel.
 * 
 * <p>This class handles the GUI and functionality for updating the price of rooms in a hotel.
 * 
 * @author Kian Daylag
 * @version 1.0
 * 
 */

public class UpdateRoomPriceWindow extends JFrame {
    private HotelController hotelController = new HotelController();
    private JTextField priceField;
    private JButton updateButton;

    /**
     * Creates a window for updating the base price of rooms in a hotel.
     * 
     * @param hotel the hotel to update the price of
     */

    public UpdateRoomPriceWindow(Hotel hotel) {
        setTitle("Update Room Prices");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        add(new JLabel("Enter new base room price:"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel());

        
        updateButton = new JButton("Save");
        add(updateButton);


        // Button actions
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPrice = priceField.getText();
                if(!hotelController.isValidPrice(newPrice)){
                    JOptionPane.showMessageDialog(UpdateRoomPriceWindow.this, hotelController.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(!hotelController.isBookingsNotEmpty(hotel)){
                    JOptionPane.showMessageDialog(UpdateRoomPriceWindow.this, hotelController.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int result = JOptionPane.showConfirmDialog(UpdateRoomPriceWindow.this, "Are you sure you want to change the base price of the rooms in Hotel " + hotel.getName() + "?", "Confirm Update", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                         double newPrice2 = Double.parseDouble(newPrice);
                        for (Room room : hotel.getRooms()) {
                            room.setPrice(newPrice2);
                            if (room instanceof DeluxeRoom) {
                                room.setPrice(RoomPricer.DeluxePrice(newPrice2));
                            } else if (room instanceof ExecutiveRoom) {
                                room.setPrice(RoomPricer.ExecutivePrice(newPrice2));
                            }
                        }
                        JOptionPane.showMessageDialog(UpdateRoomPriceWindow.this, "Room prices updated successfully.");
                        dispose();
                    }
                }
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}
