package GUI;

import hotel.Hotel;
import rooms.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import Controller.BookingController;
import java.awt.*;
import java.awt.event.*;

public class ConfirmBookingWindow extends JFrame {
    private BookingController bookingController = new BookingController();
    private CreateBookingWindow createBookingWindow;
    private JLabel selectedRoomLabel, guestNameLabel, checkInLabel, checkOutLabel, baseRoomPriceLabel, totalPriceLabel;
    private JButton confirmButton, backButton;

    public ConfirmBookingWindow(CreateBookingWindow createBookingWindow, Hotel selectedHotel, Room selectedRoom, String guestName, int checkIn, int checkOut) {
        this.createBookingWindow = createBookingWindow;
        initializeGUI(createBookingWindow, selectedHotel, selectedRoom, guestName, checkIn, checkOut);
    }

    public void initializeGUI(CreateBookingWindow createBookingWindow, Hotel selectedHotel, Room selectedRoom, String guestName, int checkIn, int checkOut) {
        // Set up the frame
        setTitle("Confirm Booking");
        setSize(400, 500); // Increased height to accommodate the additional text field
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        
        // Guest Panel
        JPanel guestPanel = new JPanel();
        guestPanel.setBorder(BorderFactory.createTitledBorder(
                                new LineBorder(Color.GRAY, 2), // 2 is the thickness of the border
                                "Guest Name: "
                            ));
        guestPanel.setBounds(40, 10, 300, 50);
        guestNameLabel = new JLabel(guestName);
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
        selectedRoomLabel = new JLabel(selectedRoom.getName());
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
        checkInLabel = new JLabel(Integer.toString(checkIn));
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
        checkOutLabel = new JLabel(Integer.toString(checkOut));
        checkOutLabel.setBounds(40, 10, 200, 30);
        checkOutPanel.add(checkOutLabel);
        add(checkOutPanel);

        // Base Room Price Panel
        JPanel baseRoomPricePanel = new JPanel();
        baseRoomPricePanel.setBorder(BorderFactory.createTitledBorder(
                                new LineBorder(Color.GRAY, 2), // 2 is the thickness of the border
                                "Base Room Price: "
                            ));
        baseRoomPricePanel.setBounds(40, 250, 300, 50);
        baseRoomPriceLabel = new JLabel("P" + String.format("%.2f", selectedRoom.getPrice()));
        baseRoomPriceLabel.setBounds(40, 10, 200, 30);
        baseRoomPricePanel.add(baseRoomPriceLabel);
        add(baseRoomPricePanel);

        // Total Price Panel
        JPanel totalPricePanel = new JPanel();
        totalPricePanel.setBorder(BorderFactory.createTitledBorder(
                                new LineBorder(Color.GRAY, 2), // 2 is the thickness of the border
                                "Total Price: "
                            ));
        totalPricePanel.setBounds(40, 310, 300, 50);
        String couponText = createBookingWindow.couponField.getText();  // initialize couponText with the value from the couponField
        double totalPrice = bookingController.calculateTotalPrice(couponText, selectedRoom, checkIn, checkOut);  // calculate the total price
        totalPriceLabel = new JLabel("P" + String.format("%.2f", totalPrice));
        totalPriceLabel.setBounds(40, 10, 200, 30);
        totalPricePanel.add(totalPriceLabel);
        add(totalPricePanel); 
        
        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(40, 370, 100, 30);
        add(backButton);

        // Confirm Button
        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(230, 370, 100, 30);
        add(confirmButton);
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfirmBookingWindow.this.dispose();
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bookingController.isBookingCreated(selectedHotel, guestName, selectedRoom, checkIn, checkOut, totalPrice)) {
                    JOptionPane.showMessageDialog(ConfirmBookingWindow.this, "Booking confirmed!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    ConfirmBookingWindow.this.dispose();
                    createBookingWindow.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(ConfirmBookingWindow.this, "Booking failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
                
        });
        setLocationRelativeTo(null); // makes it appear in the center of the screen
    }

}