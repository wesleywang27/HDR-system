package wdy;

import javax.swing.*;

/**
 * Created by Eric on 2017/5/10.
 */
public class Printer implements Runnable {
    private JTextArea ta;
    private String msg;

    Printer(JTextArea ta, String msg){
        this.ta = ta;
        this.msg = msg;
    }

    void setMsg(String msg){
        this.msg = msg;
    }

    void print(){
        Thread thread = new Thread(this);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.run();
    }

    @Override
    public void run() {
        String str = this.ta.getText();
        str += this.msg;
        ta.setText(str);
    }
}
