package wdy;

import javax.swing.*;
import java.io.IOException;

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
        srcPath = srcPath.replace("\\", "\\\\");
        this.srcPath = srcPath;
        outPath = outPath.replace("\\", "\\\\");
        this.outPath = outPath;
        this.file = file;
        this.btn = btn;
        this.lb = lb;
        this.ta = ta;
    }

    void init(){
        thread = new Thread(this);
        thread.start();

        ta.setText("..............程序启动..............");
    }

    private void unzip() throws IOException, InterruptedException {
        Printer p = new Printer(ta, "\n\t....正在解压文件....");
        p.print();

        Process proc = Runtime.getRuntime().exec("python src/wdy/py/unzip.py " + srcPath + " " + outPath + "\\HDR-system\\src");
        proc.waitFor();

        p.setMsg("\n100% 解压文件成功！");
        p.print();
    }

    private void split(){
        Printer p = new Printer(ta, "\n\t....正在图片分割....");
        p.print();



        p.setMsg("\n100% 图片分割成功！");
        p.print();
    }

    private void recognize(){
        Printer p = new Printer(ta, "\n\t....正在成绩识别....");
        p.print();



        p.setMsg("\n100% 成绩识别成功！");
        p.print();
    }

    private void generate(){
        Printer p = new Printer(ta, "\n\t....正在生成文件....");
        p.print();



        p.setMsg("\n100% 生成文件成功！");
        p.print();
    }

    private void upload(){
        Printer p = new Printer(ta, "\n\t....正在上传数据....");
        p.print();



        p.setMsg("\n100% 上传数据成功！");
        p.print();
    }

    private void clean(){
        Printer p = new Printer(ta, "\n\t....正在清理环境....");
        p.print();



        p.setMsg("\n100% 清理环境成功！");
        p.print();
    }

    void stop(){
        thread.stop();

        this.clean();

        String str = this.ta.getText();
        str += "\n..............程序结束..............";
        ta.setText(str);
    }

    @Override
    public void run() {
        try {
            this.unzip();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.split();
        this.recognize();
        this.generate();
        this.upload();
        this.clean();

        Printer p = new Printer(ta, "\n识别成功！");
        p.print();

        btn.setText("执行");
        lb.setIcon((new ImageIcon("src/wdy/img/red.png")));
    }
}
