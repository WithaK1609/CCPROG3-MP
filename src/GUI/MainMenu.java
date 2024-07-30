package GUI;
import javax.swing.*;

import hotel.HotelManager;

import java.awt.*;
import java.awt.event.*;

public class MainMenu {
    private HotelManager hotelManager = HotelManager.getInstance();
    private JFrame mainFrame;
    private JButton manageHotelButton, viewHotelButton, createHotelButton, createBookingButton; // buttons

    public MainMenu() {
        GUI();
    }


    private void GUI() {
        mainFrame = new JFrame("Hotel Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 350);
        mainFrame.setLayout(new GridLayout(4, 1));

        manageHotelButton = new JButton("Manage Hotel");
        viewHotelButton = new JButton("View Hotel");
        createHotelButton = new JButton("Create Hotel");
        createBookingButton = new JButton("Create Booking");

        createHotelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateHotelWindow().setVisible(true);
            }
        });

        viewHotelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (hotelManager.getHotels().isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame, "No hotels available. Please create a hotel first.");
                    return;
                }
                // new ViewHotelWindow(hotels).setVisible(true);
            }
        });

        manageHotelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (hotelManager.getHotels().isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame, "No hotels available. Please create a hotel first.");
                    return;
                }
                else {
                    new ManageHotelWindow().setVisible(true);
                }
                
            }
        });

        createBookingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (hotelManager.getHotels().isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame, "No hotels available. Please create a hotel first.");
                    return;
                }
                // new CreateBookingWindow();
            }
        });

        mainFrame.add(createHotelButton);
        mainFrame.add(createBookingButton);
        mainFrame.add(manageHotelButton);
        mainFrame.add(viewHotelButton);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

}
