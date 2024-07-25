package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainMenu {
    
    private JFrame mainFrame;
    private JButton manageHotelButton;
    private JButton viewHotelButton;
    private JButton createHotelButton;
    private JButton createBookingButton;

    public MainMenu() {
        prepareGUI();
    }


    private void prepareGUI() {
        mainFrame = new JFrame("Hotel Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new FlowLayout());
        mainFrame.setSize(400, 350);
        mainFrame.setLayout(new GridLayout(4, 1));

        manageHotelButton = new JButton("Manage Hotel");
        viewHotelButton = new JButton("View Hotel");
        createHotelButton = new JButton("Create Hotel");
        createBookingButton = new JButton("Create Booking");

        createHotelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // new CreateHotelWindow(mainFrame, hotels).setVisible(true);
            }
        });

        viewHotelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // new ViewHotelWindow(hotels).setVisible(true);
            }
        });

        manageHotelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // new ManageHotelWindow(hotels).setVisible(true);
            }
        });

        createBookingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

    public static void main(String[] args) {
        new MainMenu();
    }


}
