package wdy;

import javax.swing.*;

/**
 * Created by Eric on 2017/5/9.
 */
public class Recognizer implements Runnable{
    private String srcPath;
    private String outPath;
    private String file;

    private JButton btn;
    private JLabel lb;
    private JTextArea ta;

    public Recognizer(String srcPath, String outPath, String file, JButton btn, JLabel lb, JTextArea ta){
        this.srcPath = srcPath;
        this.outPath = outPath;
        this.file = file;
        this.btn = btn;
        this.lb = lb;
        this.ta = ta;
    }

    public void recognize(){
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++){
            for(int j = 0; j < 100; j++){
                System.out.println(this.srcPath+this.outPath+this.file+this.btn.getText()+this.lb.getText()+this.ta.getText());
            }
        }
        lb.setIcon((new ImageIcon("src/wdy/img/red.png")));
    }
}
