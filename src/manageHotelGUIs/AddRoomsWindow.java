package manageHotelGUIs;

import hotel.Hotel;
import hotel.HotelManager;
import Controller.HotelController;

import javax.swing.*;
import java.awt.*;

public class AddRoomsWindow extends JDialog {
    private HotelController hotelController = new HotelController();
    private HotelManager hotelManager = HotelManager.getInstance();
    private JTextField baseRoomsField;
    private JTextField deluxeRoomsField;
    private JTextField executiveRoomsField;
    private JButton saveButton;

    public AddRoomsWindow(Hotel hotel) {
        setTitle("Add Rooms");
        setSize(400, 250);
        setLayout(new GridLayout(7, 1));

        add(new JLabel("Add base rooms: "));
        baseRoomsField = new JTextField();
        add(baseRoomsField);

        add(new JLabel("Add deluxe rooms: "));
        deluxeRoomsField = new JTextField();
        add(deluxeRoomsField);

        add(new JLabel("Add executive rooms: "));
        executiveRoomsField = new JTextField();
        add(executiveRoomsField);

        saveButton = new JButton("Save");
        add(saveButton);

        saveButton.addActionListener(e -> {
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
