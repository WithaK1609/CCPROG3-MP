package GUI;
import hotel.Hotel;
import hotel.HotelManager;
import manageHotelGUIs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ManageHotelWindow extends JFrame{

    private List<Hotel> hotels = new ArrayList<>();
    private JButton changeHotelName, addRooms, removeRooms, updateRoomsPrice, removeReservation, removeHotel, modifyDatePrice;
    private JComboBox<Hotel> hotelComboBox;

    public ManageHotelWindow() {
        hotels = HotelManager.getInstance().getHotels();
        prepareGUI();
    }

    private Hotel getSelectedHotel() {
        return (Hotel) hotelComboBox.getSelectedItem();
    }

    private void prepareGUI() {
        setTitle("Manage Hotel");
        setSize(400, 300);
        setLayout(new GridLayout(8, 1));

        hotelComboBox = new JComboBox<>(hotels.toArray(new Hotel[0]));

        changeHotelName = new JButton("Change Hotel Name");
        addRooms = new JButton("Add Rooms");
        removeRooms = new JButton("Remove Rooms");
        updateRoomsPrice = new JButton("Update Rooms Price");
        removeReservation = new JButton("Remove Reservation");
        removeHotel = new JButton("Remove Hotel");
        modifyDatePrice = new JButton("Modify Date Price");

        Hotel selectedHotel = getSelectedHotel();

        changeHotelName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                new ChangeHotelNameWindow(selectedHotel).setVisible(true);
            }
        });

        addRooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddRoomsWindow(selectedHotel).setVisible(true);
            }
        });

        removeRooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // new RemoveRoomsWindow().setVisible(true);
            }
        });

        updateRoomsPrice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UpdateRoomPriceWindow(selectedHotel).setVisible(true);
            }
        });

        removeReservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RemoveBookingWindow(selectedHotel).setVisible(true);
            }
        });

        removeHotel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RemoveHotelWindow(selectedHotel, ManageHotelWindow.this).setVisible(true);
            }
        });

        modifyDatePrice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ModifyDatePriceWindow(selectedHotel).setVisible(true);
            }
        });

        add(hotelComboBox);
        add(changeHotelName);
        add(addRooms);
        add(removeRooms);
        add(updateRoomsPrice);
        add(removeReservation);
        add(removeHotel);
        add(modifyDatePrice);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // makes it appear in the center of the screen
    }

}
