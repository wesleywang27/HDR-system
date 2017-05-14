package wdy;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

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

        Process proc = Runtime.getRuntime().exec("python src/wdy/py/unzip.py " + srcPath + " " + outPath);
        proc.waitFor();

        p.setMsg("\n100% 文件解压成功！");
        p.print();
    }

    private void split() throws IOException, InterruptedException {
        Printer p = new Printer(ta, "\n\t....正在处理图片....");
        p.print();

        Process proc = Runtime.getRuntime().exec("python src/wdy/py/split.py " + outPath);
        proc.waitFor();

        p.setMsg("\n100% 图片处理成功！");
        p.print();
    }

    private void recognize() throws IOException, InterruptedException {
        Printer p = new Printer(ta, "\n\t....正在识别成绩....");
        p.print();

        Process proc = Runtime.getRuntime().exec("python src/wdy/py/recognize.py " + outPath);
        proc.waitFor();

        p.setMsg("\n100% 成绩识别成功！");
        p.print();
    }

    private void generate() throws IOException, InterruptedException {
        Printer p = new Printer(ta, "\n\t....正在生成文件....");
        p.print();

        Process proc = Runtime.getRuntime().exec("python src/wdy/py/generate.py " + outPath + " " + file);
        proc.waitFor();

        p.setMsg("\n100% 文件生成成功！");
        p.print();
    }

    private void upload() throws SQLException {
        Printer p = new Printer(ta, "\n\t....正在上传数据....");
        p.print();

        DBManager dbm = new DBManager(outPath, file);
        dbm.upload();

        p.setMsg("\n100% 数据上传成功！");
        p.print();
    }

    private void clean() throws IOException, InterruptedException {
        Printer p = new Printer(ta, "\n\t....正在清理环境....");
        p.print();

        Process proc = Runtime.getRuntime().exec("python src/wdy/py/clean.py " + outPath);
        proc.waitFor();

        p.setMsg("\n100% 环境清理成功！");
        p.print();
    }

    void stop() throws IOException, InterruptedException {
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
            this.split();
            this.recognize();
            this.generate();
            this.upload();
            this.clean();

            Printer p = new Printer(ta, "\n识别成功！");
            p.print();

            Runtime  run  =  Runtime.getRuntime();
            run.exec("cmd /c start " + outPath + "\\" + file);
        } catch (IOException | SQLException | InterruptedException e) {
            e.printStackTrace();
        }

        btn.setText("执行");
        lb.setIcon((new ImageIcon("src/wdy/img/red.png")));
    }
}
