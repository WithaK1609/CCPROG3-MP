/**
 * This class is responsible for creating the GUI for the View Specific Booking Window.
 * <p> The user can check specific bookings
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0 
 */
package GUI;

import hotel.Hotel;
import booking.Booking;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

public class ViewSpecificBookingWindow extends JFrame{
    private JButton confirmButton;
    
    // Constructor
    public ViewSpecificBookingWindow(Hotel selectedHotel) {
        initializeGUI(selectedHotel);
    }

    /**
     * Initializes the GUI for the View Specific Room Window.
     * @param selectedHotel
     */
    public void initializeGUI(Hotel selectedHotel) {
        
        setTitle("Select Booking");
        setSize(700, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 1));
        setLocationRelativeTo(null); // makes it appear in the center of the screen

        // Create a panel for the room selection
        JPanel reservationsPanel = new JPanel();
        reservationsPanel.setBorder(BorderFactory.createTitledBorder("Select Booking"));
        add(reservationsPanel);

        // Initialize the roomComboBox
        JComboBox<Booking> bookingComboBox = new JComboBox<>();
        DefaultComboBoxModel<Booking> model = new DefaultComboBoxModel<>();
        for (Booking booking : selectedHotel.getReservationDetails()) {
            model.addElement(booking);
        }
        bookingComboBox.setModel(model);
        reservationsPanel.add(bookingComboBox);

        // Confirm button
        confirmButton = new JButton("Confirm");
        add(confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Booking selectedBooking = (Booking) bookingComboBox.getSelectedItem();
                new SelectedBookingView(selectedHotel, selectedBooking).setVisible(true);
                dispose();
            }
        });
    }

    private class SelectedBookingView extends JFrame {
        JLabel selectedRoomLabel, guestNameLabel, checkInLabel, checkOutLabel, baseRoomPriceLabel;
        // Constructor
        public SelectedBookingView(Hotel selectedHotel, Booking selectedBooking) {
            initializeGUI(selectedHotel, selectedBooking);
        }

        /**
         * Initializes the GUI for the Selected Room View Window.
         */
        public void initializeGUI(Hotel selectedHotel, Booking selectedBooking) {
            setTitle("Booking Details");
            setSize(400, 500);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(null);

            // Guest Panel
            JPanel guestPanel = new JPanel();
            guestPanel.setBorder(BorderFactory.createTitledBorder(
                                    new LineBorder(Color.GRAY, 2), // 2 is the thickness of the border
                                    "Guest Name: "
                                ));
            guestPanel.setBounds(40, 10, 300, 50);
            guestNameLabel = new JLabel(selectedBooking.getGuestName());
            guestNameLabel.setBounds(40, 10, 200, 30);
            guestPanel.add(guestNameLabel);
            add(guestPanel);

            // Room Panel
            JPanel roomPanel = new JPanel();
            roomPanel.setBorder(BorderFactory.createTitledBorder(
                                    new LineBorder(Color.GRAY, 2), // 2 is the thickness of the border
                                    "Staying at Room: "
                                ));
            roomPanel.setBounds(40, 70, 300, 50);
            selectedRoomLabel = new JLabel(selectedBooking.getRoom().getName());
            selectedRoomLabel.setBounds(40, 10, 200, 30);
            roomPanel.add(selectedRoomLabel);
            add(roomPanel);

            // Check In Panel
            JPanel checkInPanel = new JPanel();
            checkInPanel.setBorder(BorderFactory.createTitledBorder(
                                    new LineBorder(Color.GRAY, 2), // 2 is the thickness of the border
                                    "Check In Date: "
                                ));
            checkInPanel.setBounds(40, 130, 300, 50);
            checkInLabel = new JLabel(Integer.toString(selectedBooking.getCheckIn()));
            checkInLabel.setBounds(40, 10, 200, 30);
            checkInPanel.add(checkInLabel);
            add(checkInPanel);

            // Check Out Panel
            JPanel checkOutPanel = new JPanel();
            checkOutPanel.setBorder(BorderFactory.createTitledBorder(
                                    new LineBorder(Color.GRAY, 2), // 2 is the thickness of the border
                                    "Check Out Date: "
                                ));
            checkOutPanel.setBounds(40, 190, 300, 50);
            checkOutLabel = new JLabel(Integer.toString(selectedBooking.getCheckOut()));
            checkOutLabel.setBounds(40, 10, 200, 30);
            checkOutPanel.add(checkOutLabel);
            add(checkOutPanel);

            // Base Room Price Panel
            JPanel baseRoomPricePanel = new JPanel();
            baseRoomPricePanel.setBorder(BorderFactory.createTitledBorder(
                                    new LineBorder(Color.GRAY, 2), // 2 is the thickness of the border
                                    "Total Reservation Price: "
                                ));
            baseRoomPricePanel.setBounds(40, 250, 300, 50);
            baseRoomPriceLabel = new JLabel("P" + String.format("%.2f", selectedBooking.getTotalPrice()));
            baseRoomPriceLabel.setBounds(40, 10, 200, 30);
            baseRoomPricePanel.add(baseRoomPriceLabel);
            add(baseRoomPricePanel);
        }
    }
}