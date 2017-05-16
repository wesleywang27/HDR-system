package wdy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProcessBarMaker extends JFrame{
    private static final long serialVersionUID = 1L;
    private JProgressBar processBar;

    public ProcessBarMaker(){
        setTitle("处理中..");

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        setSize(250, 120);

        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));

        this.processBar = new JProgressBar();

        processBar.setStringPainted(true);

        processBar.setBackground(Color.LIGHT_GRAY);

        new Thread(() -> {
            for (int i = 0; i < 96; i++) {
                try {
                    Thread.sleep(1300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                processBar.setValue(i);
            }
            for (int i = 96; i < 100; i++) {
                try {
                    Thread.sleep(1700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                processBar.setValue(i);
            }
        }).start();

        contentPane.add(processBar);
    }

    void setProcessBar(String str){
        this.processBar.setString(str);
    }
}