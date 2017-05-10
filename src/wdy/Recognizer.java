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

    private static Thread thread;

    public void setVar(String srcPath, String outPath, String file, JButton btn, JLabel lb, JTextArea ta){
        this.srcPath = srcPath;
        this.outPath = outPath;
        this.file = file;
        this.btn = btn;
        this.lb = lb;
        this.ta = ta;
    }

    public void init(){
        ta.setText("    .........正在启动程序.........");

        this.thread = new Thread(this);
        this.thread.start();
    }

    public void recognize(){

    }

    public void stop(){
        String str = this.ta.getText();
        str += "\n\t ......程序停止......";
        ta.setText(str);

        this.thread.stop();
    }

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++){
            for(int j = 0; j < 1000; j++){
                System.out.println(this.srcPath+this.outPath+this.file+this.btn.getText()+this.lb.getText()+this.ta.getText());
            }
        }

        btn.setText("执行");
        lb.setIcon((new ImageIcon("src/wdy/img/red.png")));
    }
}
