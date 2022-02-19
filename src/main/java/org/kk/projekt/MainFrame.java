package org.kk.projekt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class MainFrame extends JFrame {
    private final App app;
    private JPanel panelMain;
    private JLabel currentLat;
    private JButton confirmLang;
    private JButton confirmLong;
    private JLabel labelTemp;
    private JLabel labelWind;
    private JButton buttonMeteo;
    private JLabel currentLong;
    private JLabel currentCity;
    private JButton buttonGeo;
    private JTextField textWind;
    private boolean fetchingData;

    public MainFrame(App app){
        super("App");
        this.app = app;
        this.setContentPane(panelMain);
        this.pack();

//        this.currentLat.setText("");
        buttonMeteo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fetchingData) {
                    JOptionPane.showMessageDialog(null, "Dane się pobierają, proszę czekać");
                } else {
                    fetchingData = true;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            refreshData();
                        }
                    }).start();
                }
            }
        });
        buttonGeo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fetchingData) {
                    JOptionPane.showMessageDialog(null, "Dane się pobierają, proszę czekać");
                } else {
                    fetchingData = true;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            refreshGeo();
                        }
                    }).start();
                }
            }
        });
    }

    private void refreshGeo() {
        var geo = app.fetchfromGeoApi();
        var latitude = geo.latitude;
        var longitude = geo.longitude;
        var city = geo.city;

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                currentLat.setText("Latitude: " + latitude);
                currentLong.setText("Longitude: " + longitude);
                currentCity.setText("City: " + city);
                fetchingData = false;
            }
        });
    }

    private void refreshData() {
        var now = LocalDateTime.now();
        var geo = app.fetchfromGeoApi();
        var weather = app.fetchfromMeteoApi(geo);
        var prediction = weather.getValue(now.getHour());
        var temp = prediction.temp;
        var wind = prediction.windSpeed;
//        var latitude = geo.latitude;
//        var longitude = geo.longitude;
//        var city = geo.city;

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                labelTemp.setText("Temperatura: " + temp);
                labelWind.setText("Prędkość wiatru: " + wind);
//                currentLat.setText("Latitude: " + latitude);
//                currentLong.setText("Longitude: " + longitude);
//                currentCity.setText("City: " + city);
                fetchingData = false;
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}