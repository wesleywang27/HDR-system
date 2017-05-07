package wdy;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Eric on 2017/5/5.
 */
public class MainFrame
{
    public static JMenuBar getMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu m1 = new JMenu();
        m1.setText("文件");
        JMenu m2 = new JMenu();
        m2.setText("编辑");
        JMenu m3 = new JMenu();
        m3.setText("帮助");

        JMenuItem item11 = new JMenuItem();
        item11.setText("打开");
        JMenuItem item12 = new JMenuItem();
        item12.setText("保存");
        JMenuItem item13 = new JMenuItem();
        item13.setText("退出");

        JMenuItem item21 = new JMenuItem();
        item21.setText("复制");
        JMenuItem item22 = new JMenuItem();
        item22.setText("拷贝");
        JMenuItem item23 = new JMenuItem();
        item23.setText("剪切");

        JMenuItem item31 = new JMenuItem();
        item31.setText("静音");
        JMenuItem item32 = new JMenuItem();
        item32.setText("关于");
        JMenuItem item33 = new JMenuItem();
        item33.setText("版本信息");

        m1.add(item11);
        m1.add(item12);
        m1.add(item13);

        m2.add(item21);
        m2.add(item22);
        m2.add(item23);

        m3.add(item31);
        m3.add(item32);
        m3.add(item33);

        menuBar.add(m1);
        menuBar.add(m2);
        menuBar.add(m3);

        return menuBar;
    }

    public static JPanel getMainPanel(){
        JPanel panel = new JPanel();

        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        JPanel panel_top = new JPanel();

        String urlString = "src/wdy/img/logo.png";
        JLabel label_img = new JLabel(new ImageIcon(urlString));
        panel_top.add(label_img);

        JPanel panel_bottom = new JPanel();

        GridBagLayout lay = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        panel_bottom.setLayout(lay);

        JLabel label1 = new JLabel("上传文件：");
        label1.setFont(new Font("宋体", Font.BOLD, 12));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(3, 10, 3, 5);
        panel_bottom.add(label1, c);

        JLabel label2 = new JLabel("");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        panel_bottom.add(label2, c);

        JButton bt1 = new JButton("上传");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        panel_bottom.add(bt1, c);

        JLabel label3 = new JLabel("保存路径：");
        label3.setFont(new Font("宋体", Font.BOLD, 12));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        panel_bottom.add(label3, c);

        JTextField tf1 = new JTextField("C:\\Users\\Public\\Documents");
        tf1.setEditable(false);
        tf1.setBorder(BorderFactory.createEmptyBorder());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        panel_bottom.add(tf1, c);

        JButton bt2 = new JButton("更改");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        panel_bottom.add(bt2, c);

        JLabel label4 = new JLabel("文件名称：");
        label4.setFont(new Font("宋体", Font.BOLD, 12));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        panel_bottom.add(label4, c);

        JTextField tf2 = new JTextField("");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        panel_bottom.add(tf2, c);

        JComboBox cb = new JComboBox();
        cb.addItem(".xlsx");
        cb.addItem(".xls");
        cb.addItem(".csv");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        panel_bottom.add(cb, c);

        JButton bt3 = new JButton("执行");
        bt3.setForeground(Color.white);
        bt3.setFont(new Font("粗体", Font.BOLD, 12));
        bt3.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
        panel_bottom.add(bt3, c);

        panel.add(panel_top);
        panel.add(panel_bottom);

        return panel;
    }

    public static JPanel getRightPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(250,220));

        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        JLabel label = new JLabel("识别状态:");
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder());

        panel.add(label);
        panel.add(textArea);

        return panel;
    }

    public static JToolBar getToolBar(){
        JLabel label = new JLabel("状态栏");
        label.setFont(new Font("宋体", Font.PLAIN, 12));

        String urlString = "src/wdy/img/red.png";
        JLabel label_img = new JLabel(new ImageIcon(urlString));

        JToolBar toolBar = new JToolBar();

        toolBar.add(label_img);
        toolBar.add(label);

        return toolBar;
    }

    public static void initFrame(){
        // 创建 JFrame 实例
        JFrame frame = new JFrame("中科大成绩识别系统");
        // 设置 JFrame 尺寸 居中
        frame.setSize(640, 360);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建面板
        JPanel panel = new JPanel();
        // 添加面板
        frame.add(panel);
        // 设置布局方式
        BorderLayout lay = new BorderLayout();
        panel.setLayout(lay);

        // 定义上部菜单栏
        JMenuBar menuBar = getMenuBar();

        // 定义主面板
        JPanel panel_center = getMainPanel();

        // 定义右侧面板
        JPanel panel_right = getRightPanel();

        // 定义下部状态栏
        JToolBar toolBar = getToolBar();

        // 添加到布局
        panel.add(menuBar, "North");
        panel.add(panel_center, "Center");
        panel.add(panel_right, "East");
        panel.add(toolBar, "South");

        // 设置界面可见
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // 显示应用 GUI
        try
        {
            //UIManager.put("RootPane.setupButtonVisible", false);
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            System.out.println(" -- Exception --");
        }
        finally
        {
            initFrame();

            try
            {
                File f = new File("src/wdy/audio/bgm.wav"); // 引号里面的是音乐文件所在的路径
                URL cb = f.toURL();
                AudioClip aau = Applet.newAudioClip(cb);
                aau.loop();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
}
