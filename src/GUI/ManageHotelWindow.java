package GUI;
import hotel.Hotel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ManageHotelWindow extends JFrame{

    private List<Hotel> hotels;
    private JButton changeHotelName, addRooms, removeRooms, updateRoomsPrice, removeReservation, removeHotel, modifyDatePrice;
    private JComboBox<Hotel> hotelComboBox;


    public ManageHotelWindow(List<Hotel> hotels) {
        this.hotels = hotels;
        prepareGUI();
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

        changeHotelName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // new ChangeHotelNameWindow().setVisible(true);
            }
        });

        addRooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // new AddRoomsWindow().setVisible(true);
            }
        });

        removeRooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // new RemoveRoomsWindow().setVisible(true);
            }
        });

        updateRoomsPrice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // new UpdateRoomsPriceWindow().setVisible(true);
            }
        });

        removeReservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // new RemoveReservationWindow().setVisible(true);
            }
        });

        removeHotel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // new RemoveHotelWindow().setVisible(true);
            }
        });

        modifyDatePrice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // new ModifyDatePriceWindow().setVisible(true);
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
    }

}
