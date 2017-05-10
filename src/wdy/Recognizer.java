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

    private Thread thread;

    public void setVar(String srcPath, String outPath, String file, JButton btn, JLabel lb, JTextArea ta){
        this.srcPath = srcPath;
        this.outPath = outPath;
        this.file = file;
        this.btn = btn;
        this.lb = lb;
        this.ta = ta;
    }

    public void init() throws InterruptedException{
        this.thread = new Thread(this);
        this.thread.start();
        System.out.println( "Starting thread..." );
    }

    public void recognize(){

    }

    public void stop() throws InterruptedException {
        //thread.sleep(5000);
        //this.thread.interrupt();
        System.out.println( "Interrupting thread........................................................................................................................................" );
        System.out.println("线程是否中断：" + thread.isInterrupted());
    }

    @Override
    public void run() {
        try{
            for(int i = 0; i < 1000; i++){
                for(int j = 0; j < 1000; j++){
                    System.out.println( "My Thread is running..." );
                    //System.out.println(this.srcPath+this.outPath+this.file+this.btn.getText()+this.lb.getText()+this.ta.getText());
                }
            }
            thread.sleep(5000);
        }catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }


        btn.setText("执行");
        lb.setIcon((new ImageIcon("src/wdy/img/red.png")));
    }
}
