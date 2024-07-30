package GUI;
import javax.swing.*;
import Controller.BookingController;
import java.awt.*;
import java.awt.event.*;

public class CreateBookingWindow extends JFrame {
    private BookingController bookingController = new BookingController();
    private JTextField guestNameField;
    private JTextField checkInField;
    private JTextField checkOutField;
    public JTextField couponField;
    private JButton nextButton;
    JLabel selectedRoomLabel;
    
    public CreateBookingWindow() {
        initializeGUI();
    }
    
    public void initializeGUI() {
        setTitle("Booking");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(9, 2));

        // Add components
        add(new JLabel("Guest Name:"));
        guestNameField = new JTextField();
        add(guestNameField);

        add(new JLabel("Check In Date:"));
        checkInField = new JTextField();
        add(checkInField);

        add(new JLabel("Check Out Date: "));
        checkOutField = new JTextField();
        add(checkOutField);

        add(new JLabel("Coupon Code:"));
        couponField = new JTextField();
        add(couponField);

        // Button
        nextButton = new JButton("Next");
        add(nextButton);

        // Add a listener to the hotelComboBox to update the roomComboBox
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bookingController.validateCouponCode(couponField.getText(), checkInField.getText(), checkOutField.getText())) {
                    JOptionPane.showMessageDialog(CreateBookingWindow.this, bookingController.getCouponMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                if (!bookingController.validateBookingDates(checkInField.getText(), checkOutField.getText())) {
                    JOptionPane.showMessageDialog(CreateBookingWindow.this, bookingController.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                else if (!bookingController.validateGuestName(guestNameField.getText())) {
                    JOptionPane.showMessageDialog(CreateBookingWindow.this, bookingController.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                else if (!bookingController.validateCouponCode(couponField.getText(), checkInField.getText(), checkOutField.getText())) {
                    JOptionPane.showMessageDialog(CreateBookingWindow.this, bookingController.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                else {
                    new SelectHotelWindow(CreateBookingWindow.this, guestNameField.getText(), checkInField.getText(), checkOutField.getText()).setVisible(true);
                }
                
            }
        });
        setVisible(true);
        setLocationRelativeTo(null); // makes it appear in the center of the screen
    }
}
