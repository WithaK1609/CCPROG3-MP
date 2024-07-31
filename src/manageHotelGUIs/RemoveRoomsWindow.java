package manageHotelGUIs;

import hotel.Hotel;
import rooms.DeluxeRoom;
import rooms.ExecutiveRoom;
import rooms.Room;

import javax.swing.*;
import Controller.HotelController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

/**
 * Represents a window for removing rooms from a hotel.
 *
 * <p>This class handles the GUI and functionality for removing rooms from a hotel.
 *
 * @author Kian Daylag
 * @version 1.0
 */

public class RemoveRoomsWindow extends JFrame {
    private HotelController hotelController = new HotelController();
    private JTextField baseTextField;
    private JTextField deluxeTextField;
    private JTextField executiveTextField;
    private JButton saveButton;

    /**
     * Creates a window for removing rooms from a hotel.
     *
     * @param hotel the hotel to remove rooms from
     */

    public RemoveRoomsWindow(Hotel hotel) {
        setTitle("Remove Rooms");
        setSize(400, 250);
        setLayout(new GridLayout(7, 1));

        int baseRoomCount = hotel.countBaseRooms();
        int deluxeRoomCount = hotel.countDeluxeRooms();
        int executiveRoomCount = hotel.countExecutiveRooms();

        add(new JLabel("Remove base rooms (" + baseRoomCount + "): "));
        baseTextField = new JTextField();
        add(baseTextField);

        add(new JLabel("Remove deluxe rooms (" + deluxeRoomCount + "): "));
        deluxeTextField = new JTextField();
        add(deluxeTextField);

        add(new JLabel("Remove executive rooms (" + executiveRoomCount + "): "));
        executiveTextField = new JTextField();
        add(executiveTextField);

        saveButton = new JButton("Save");
        add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!hotelController.validateRemovedNumberOfRooms(baseTextField.getText(), deluxeTextField.getText(), executiveTextField.getText(), hotel)) {
                    JOptionPane.showMessageDialog(RemoveRoomsWindow.this, hotelController.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int result = JOptionPane.showConfirmDialog(RemoveRoomsWindow.this, "Are you sure you want to remove " + baseTextField.getText() + " base rooms, " + deluxeTextField.getText() + " deluxe rooms, and " + executiveTextField.getText() + " executive rooms?", "Confirm Change", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        int baseRoomsToRemove = Integer.parseInt(baseTextField.getText());
                        int deluxeRoomsToRemove = Integer.parseInt(deluxeTextField.getText());
                        int executiveRoomsToRemove = Integer.parseInt(executiveTextField.getText());

                        // remove rooms (from the top)
                        List<Room> baseRooms = hotel.getRooms().stream()
                                .filter(room -> !(room instanceof ExecutiveRoom) && !(room instanceof DeluxeRoom) && room.getReservations().isEmpty())
                                .collect(Collectors.toList());
                        Collections.reverse(baseRooms); // reverse the list
                        for (int i = 0; i < baseRoomsToRemove && i < baseRooms.size(); i++) {
                            hotel.getRooms().remove(baseRooms.get(i));
                        }

                        
                        List<Room> deluxeRooms = hotel.getRooms().stream()
                                .filter(room -> room instanceof DeluxeRoom && room.getReservations().isEmpty())
                                .collect(Collectors.toList());
                        Collections.reverse(deluxeRooms); 
                        for (int i = 0; i < deluxeRoomsToRemove && i < deluxeRooms.size(); i++) {
                            hotel.getRooms().remove(deluxeRooms.get(i));
                        }

                        
                        List<Room> executiveRooms = hotel.getRooms().stream()
                                .filter(room -> room instanceof ExecutiveRoom && room.getReservations().isEmpty())
                                .collect(Collectors.toList());
                        Collections.reverse(executiveRooms); 
                        for (int i = 0; i < executiveRoomsToRemove && i < executiveRooms.size(); i++) {
                            hotel.getRooms().remove(executiveRooms.get(i));
                        }

                        JOptionPane.showMessageDialog(RemoveRoomsWindow.this, "Rooms removed successfully.");
                        dispose();
                    }
                }
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
