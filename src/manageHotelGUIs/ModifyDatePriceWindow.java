package manageHotelGUIs;

import hotel.Hotel;
import rooms.Room;
import Controller.HotelController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Represents a window for modifying the price of a room for a specific date range.
 * 
 * <p>This class handles the GUI and functionality for modifying the price of a room for a specific date range.
 * 
 * @author Kian Daylag
 * @version 1.0
 * 
 */

public class ModifyDatePriceWindow extends JFrame{
    private HotelController hotelController = new HotelController();
    private JComboBox<Room> roomComboBox;
    private JTextField pricePercentagTextField;
    private JTextField startDateTextField;
    private JTextField endDateTextField;
    private JButton saveButton;

    /**
     * returns the selected room
     * 
     * @return the selected room
     */

    private Room getSelectedRoom(){
        return (Room) roomComboBox.getSelectedItem();
    }

    /**
     * Creates a window for modifying the price of a room for a specific date range.
     * 
     * @param hotel the hotel to modify the price of
     */

    public ModifyDatePriceWindow(Hotel hotel){
        setTitle("Modify Date Price");
        setSize(400, 250);
        setLayout(new GridLayout(10, 1));


        JLabel roomLabel = new JLabel("Select Room:");
        add(roomLabel);

        roomComboBox = new JComboBox<>();
        for (Room room : hotel.getRooms()) {
            roomComboBox.addItem(room);
        }
        add(roomComboBox);

        add(new JLabel("Enter the percentage increase in price: "));
        pricePercentagTextField = new JTextField();
        add(pricePercentagTextField);

        add(new JLabel("Enter the start date: "));
        startDateTextField = new JTextField();
        add(startDateTextField);

        add(new JLabel("Enter the end date: "));
        endDateTextField = new JTextField();
        add(endDateTextField);

        add(new JLabel());

        saveButton = new JButton("Save");
        add(saveButton);

        Room selectedRoom = getSelectedRoom();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(!hotelController.validateDatePrice(startDateTextField.getText(), endDateTextField.getText(), pricePercentagTextField.getText())){
                    JOptionPane.showMessageDialog(ModifyDatePriceWindow.this, hotelController.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    int result = JOptionPane.showConfirmDialog(ModifyDatePriceWindow.this, "Are you sure you want to modify the price by " + pricePercentagTextField.getText() + "% from " + startDateTextField.getText() + " to " + endDateTextField.getText() + "?", "Confirm Change", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        double pricePercentage = Double.parseDouble(pricePercentagTextField.getText());
                        int startDate = Integer.parseInt(startDateTextField.getText());
                        int endDate = Integer.parseInt(endDateTextField.getText());

                        for(int date = startDate; date <= endDate; date++){
                            selectedRoom.getDatePriceModifier().setPriceRate(date, pricePercentage);;
                        }

                        //hotelManager.modifyDatePrice(pricePercentage, startDate, endDate, hotel);

                        JOptionPane.showMessageDialog(ModifyDatePriceWindow.this, "Price modified successfully.");
                        dispose();
                    }
                }
            } 
          });
          
  
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setLocationRelativeTo(null);
    }

}
