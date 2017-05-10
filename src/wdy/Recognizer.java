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

    void setVar(String srcPath, String outPath, String file, JButton btn, JLabel lb, JTextArea ta){
        this.srcPath = srcPath;
        this.outPath = outPath;
        this.file = file;
        this.btn = btn;
        this.lb = lb;
        this.ta = ta;
    }

    void init(){

        thread = new Thread(this);
        thread.start();

        ta.setText("\n\t ......程序启动......");
    }

    private void unzip(){
        Printer p = new Printer(ta, "\n\t解压缩");
        p.print();
    }

    private void split(){
        Printer p = new Printer(ta, "\n\t图片分割");
        p.print();
    }

    private void recognize(){
        Printer p = new Printer(ta, "\n\t成绩识别");
        p.print();
    }

    private void generate(){
        Printer p = new Printer(ta, "\n\t生成文件");
        p.print();
    }

    private void upload(){
        Printer p = new Printer(ta, "\n\t上传数据");
        p.print();
    }

    private void clean(){
        Printer p = new Printer(ta, "\n\t清理环境");
        p.print();
    }

    void stop(){

        thread.stop();

        this.clean();

        String str = this.ta.getText();
        str += "\n\t ......程序停止......";
        ta.setText(str);
    }

    @Override
    public void run() {
        this.unzip();
        this.split();
        this.recognize();
        this.generate();
        this.upload();
        this.clean();

        for(int i = 0; i < 1000; i++){
            for(int j = 0; j < 1000; j++){
                System.out.println(this.srcPath+this.outPath+this.file+this.btn.getText()+this.lb.getText()+this.ta.getText());
            }
        }

        btn.setText("执行");
        lb.setIcon((new ImageIcon("src/wdy/img/red.png")));
    }
}
