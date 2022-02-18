package org.kk.projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class MainFrame extends JFrame {
    private final App app;
    private JPanel panelMain;
    private JLabel currentGeo;
    private JTextArea dataLang;
    private JTextArea dataLong;
    private JButton confirmLang;
    private JButton confirmLong;
    private JTable table1;
    private JButton buttonConfirm;

    public MainFrame(App app){
        super("App");
        this.app = app;
        this.setContentPane(panelMain);
        this.pack();

        this.currentGeo.setText("");
        confirmLang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshData();
            }
        });
    }

    private void refreshData() {
        var now = LocalDateTime.now();
        var weather = app.fetchfromMeteoApi(app.fetchfromGeoApi());
        var temp = weather.getValue(now.getHour());
        this.dataLang.setText("" + temp);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}