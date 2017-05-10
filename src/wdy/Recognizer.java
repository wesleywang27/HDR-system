package wdy;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

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

        ta.setText("..............程序启动..............");
    }

    private void unzip(){
        Printer p = new Printer(ta, "\n\t....正在解压文件....");
        p.print();

        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("src/wdy/py/unzip.py");
        PyFunction func = interpreter.get("adder", PyFunction.class);

        int a = 2010, b = 7;
        PyObject obj = func.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println("answer = " + obj.toString());

        p.setMsg("\n100% 解压文件成功！");
        p.print();
    }

    private void split(){
        Printer p = new Printer(ta, "\n\t....正在图片分割....");
        p.print();

        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("src/wdy/py/split.py");

        p.setMsg("\n100% 图片分割成功！");
        p.print();
    }

    private void recognize(){
        Printer p = new Printer(ta, "\n\t....正在成绩识别....");
        p.print();

        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("src/wdy/py/recognize.py");

        p.setMsg("\n100% 成绩识别成功！");
        p.print();
    }

    private void generate(){
        Printer p = new Printer(ta, "\n\t....正在生成文件....");
        p.print();

        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("src/wdy/py/generate.py");

        p.setMsg("\n100% 生成文件成功！");
        p.print();
    }

    private void upload(){
        Printer p = new Printer(ta, "\n\t....正在上传数据....");
        p.print();

        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("src/wdy/py/upload.py");

        p.setMsg("\n100% 上传数据成功！");
        p.print();
    }

    private void clean(){
        Printer p = new Printer(ta, "\n\t....正在清理环境....");
        p.print();

        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("src/wdy/py/clean.py");

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
        this.unzip();
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
