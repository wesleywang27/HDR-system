package wdy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProcessBarDemo extends JFrame{
    private static final long serialVersionUID = 1L;
    private JProgressBar processBar;

    public ProcessBarDemo(){
        setTitle("处理中..");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setBounds(100, 100, 250, 120);

        JPanel contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));

        this.processBar = new JProgressBar();

        processBar.setStringPainted(true);

        processBar.setBackground(Color.LIGHT_GRAY);

        new Thread(() -> {
            for (int i = 0; i < 101; i++) {
                try {
                    Thread.sleep(1627);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                processBar.setValue(i);
            }
            try {
                processBar.setString("处理成功!");
                Thread.sleep(1627);
                this.setVisible(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        contentPane.add(processBar);
    }
}