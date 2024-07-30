package GUI;

import hotel.HotelManager;
import hotel.Hotel;
import rooms.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SelectHotelWindow extends JFrame {
    private CreateBookingWindow createBookingWindow;
    private JComboBox<Hotel> hotelComboBox;
    private JComboBox<Room> roomComboBox;
    private JTextArea roomDescriptionArea;
    private JButton confirmButton;
    private HotelManager hotelManager = HotelManager.getInstance();
    private List<Hotel> hotels = hotelManager.getHotels();

    public SelectHotelWindow(CreateBookingWindow createBookingWindow, String guestName, String checkIn, String checkOut) {
        this.createBookingWindow = createBookingWindow;
        initializeGUI(guestName, checkIn, checkOut);
    }

    private void initializeGUI(String guestName, String checkIn, String checkOut) {
        setTitle("Select Hotel");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a panel for the hotel selection
        JPanel hotelPanel = new JPanel();
        hotelPanel.setBorder(BorderFactory.createTitledBorder("Select Hotel"));
        add(hotelPanel, BorderLayout.NORTH);

        // Hotel Selection
        JLabel hotelLabel = new JLabel("Select Hotel:");
        hotelPanel.add(hotelLabel);
        DefaultComboBoxModel<Hotel> model = new DefaultComboBoxModel<>();
        for (Hotel hotel : hotels) {
            model.addElement(hotel);
        }
        hotelComboBox = new JComboBox<>(model);
        hotelPanel.add(hotelComboBox);

        // Create a panel for the room selection
        JPanel roomPanel = new JPanel();
        roomPanel.setBorder(BorderFactory.createTitledBorder("Select Room"));
        roomPanel.setPreferredSize(new Dimension(200, 100));
        add(roomPanel, BorderLayout.CENTER);

        // Room Selection
        JLabel roomLabel = new JLabel("Select Room:");
        roomPanel.add(roomLabel);
        DefaultComboBoxModel<Room> roomModel = new DefaultComboBoxModel<>();
        roomComboBox = new JComboBox<>(roomModel);
        roomPanel.add(roomComboBox);

        int checkInDate = Integer.parseInt(checkIn);
        int checkOutDate = Integer.parseInt(checkOut);

        hotelComboBox.setSelectedIndex(0);

        // Create a panel for the room description
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setBorder(BorderFactory.createTitledBorder("Room Description"));
        add(descriptionPanel, BorderLayout.SOUTH);

        // Room Description Area
        roomDescriptionArea = new JTextArea();
        roomDescriptionArea.setEditable(false);
        roomDescriptionArea.setLineWrap(true);
        roomDescriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(roomDescriptionArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        descriptionPanel.add(scrollPane);
        // Create the confirm button
        confirmButton = new JButton("Confirm");
        // Add the confirm button to the description panel
        descriptionPanel.add(confirmButton);

        hotelComboBox.setPreferredSize(new Dimension(200, 25));
        roomComboBox.setPreferredSize(new Dimension(200, 25));
        roomDescriptionArea.setPreferredSize(new Dimension(300, 200));

        // Add a listener to the hotelComboBox to update the roomComboBox
        hotelComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hotel selectedHotel = (Hotel) hotelComboBox.getSelectedItem();
                roomModel.removeAllElements();
                if (selectedHotel != null) {
                    for (Room room : selectedHotel.getRooms()) {
                        if (selectedHotel.isRoomAvailable(room, checkInDate, checkOutDate)) {
                            roomModel.addElement(room);
                        }
                    }
                }
                roomComboBox.setModel(roomModel); // Update the roomComboBox with the new model
            }
        });

        // Add ActionListener to roomComboBox
        roomComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Room selectedRoom = (Room) roomComboBox.getSelectedItem();
                if (selectedRoom != null) {
                    roomDescriptionArea.setText(selectedRoom.showRoomDescription());
                }
            }
        });

        // Add ActionListener to confirmButton
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (roomComboBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(SelectHotelWindow.this, "Please select a room", "Error", JOptionPane.ERROR_MESSAGE);
                }

                else if (hotelComboBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(SelectHotelWindow.this, "Please select a hotel", "Error", JOptionPane.ERROR_MESSAGE);
                }

                else {
                    Room selectedRoom = (Room) roomComboBox.getSelectedItem();
                    Hotel selectedHotel = (Hotel) hotelComboBox.getSelectedItem();
                    new ConfirmBookingWindow(createBookingWindow, selectedHotel, selectedRoom, guestName, checkInDate, checkOutDate).setVisible(true);
                    dispose();
                    
                }
            }
        });

        

        setVisible(true);
    }
}