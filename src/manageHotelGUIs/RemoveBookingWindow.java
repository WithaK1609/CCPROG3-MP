package manageHotelGUIs;

import hotel.Hotel;
import rooms.Room;
import booking.Booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RemoveBookingWindow extends JDialog {
    private JComboBox<Booking> bookingList;
    private JButton removeButton;

    private Booking getSelectedBooking() {
        return (Booking) bookingList.getSelectedItem();
    }

    public RemoveBookingWindow(Hotel hotel) {
        setTitle("Remove Booking");
        setSize(700, 90);
        setLayout(new GridLayout(2, 1));

        // Get bookings from the hotel
        List<Booking> bookings = hotel.getReservationDetails();

        // Create a JComboBox to list all bookings
        bookingList = new JComboBox<>();
        for (Booking booking : bookings) {
            bookingList.addItem(booking);
        }
        add(bookingList);

        // Remove Button
        removeButton = new JButton("Remove");
        add(removeButton);

        // Add action listener to remove button
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Booking bookingToRemove = getSelectedBooking();
                if (bookingToRemove != null) {
                    int result = JOptionPane.showConfirmDialog(RemoveBookingWindow.this, "Are you sure you want to remove " + bookingToRemove.getGuestName() + "'s booking?","Confirm Change", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        // Remove reservations from the room
                        Room room = bookingToRemove.getRoom();
                        for (int i = bookingToRemove.getCheckIn(); i <= bookingToRemove.getCheckOut(); i++) {
                            room.getReservations().remove(Integer.valueOf(i));
                        }

                        // Remove booking from the hotel's booking list
                        hotel.getReservationDetails().remove(bookingToRemove);

                        JOptionPane.showMessageDialog(RemoveBookingWindow.this, "Booking removed successfully.",
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose(); // Close the dialog
                    }
                } 
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
