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
 * Created by Eric on 2017/5/8.
 */
public class HDR_Frame {
    private JFrame mainFrame;
    private AudioClip bgm;
    private JLabel icon;

    private JMenuItem  item11;
    private JMenuItem  item12;
    private JMenuItem  item13;
    private JMenuItem  item21;
    private JMenuItem  item22;
    private JMenuItem  item23;
    private JMenuItem  item31;
    private JMenuItem  item32;
    private JMenuItem  item33;

    private JLabel uploadStatus;
    private JButton upload;
    private JTextField savedURL;
    private JButton updateURL;
    private JTextField fileName;
    private JComboBox fileType;
    private JButton execute;
    private JTextArea status;

    public HDR_Frame(){
        this.mainFrame = new JFrame("中科大成绩识别系统");
        this.mainFrame.setSize(720, 405);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        // 添加面板
        this.mainFrame.add(panel);
        // 设置布局方式
        BorderLayout lay = new BorderLayout();
        panel.setLayout(lay);

        // 定义上部菜单栏
        JMenuBar menuBar = this.getMenuBar();

        // 定义主面板
        JPanel panel_center = this.getMainPanel();

        // 定义右侧面板
        JPanel panel_right = this.getRightPanel();

        // 定义下部状态栏
        JToolBar toolBar = this.getToolBar();

        // 添加到布局
        panel.add(menuBar, "North");
        panel.add(panel_center, "Center");
        panel.add(panel_right, "East");
        panel.add(toolBar, "South");

        try {
            File f = new File("src/wdy/audio/bgm.wav"); // 引号里面的是音乐文件所在的路径
            URL cb = f.toURL();
            this.bgm = Applet.newAudioClip(cb);
            this.bgm.loop();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void init(){
        this.mainFrame.setVisible(true);
    }

    public JMenuBar getMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu m1 = new JMenu();
        m1.setText("文件");
        JMenu m2 = new JMenu();
        m2.setText("编辑");
        JMenu m3 = new JMenu();
        m3.setText("帮助");

        this.item11 = new JMenuItem();
        item11.setText("打开");
        this.item12 = new JMenuItem();
        item12.setText("保存");
        this.item13 = new JMenuItem();
        item13.setText("退出");

        this.item21 = new JMenuItem();
        item21.setText("复制");
        this.item22 = new JMenuItem();
        item22.setText("拷贝");
        this.item23 = new JMenuItem();
        item23.setText("剪切");

        this.item31 = new JMenuItem();
        item31.setText("静音");
        this.item32 = new JMenuItem();
        item32.setText("关于");
        this.item33 = new JMenuItem();
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

    public JPanel getMainPanel(){
        JPanel panel = new JPanel();

        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        JPanel panel_top = new JPanel();

        String urlString = "src/wdy/img/logo.png";
        this.icon = new JLabel(new ImageIcon(urlString));
        panel_top.add(icon);

        JPanel panel_bottom = new JPanel();

        GridBagLayout lay = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        panel_bottom.setLayout(lay);

        JLabel label1 = new JLabel("上传文件：");
        label1.setFont(new Font("宋体", Font.BOLD, 16));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 30, 5, 0);
        c.gridx = 0;
        c.gridy = 0;
        panel_bottom.add(label1, c);

        this.uploadStatus = new JLabel("");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 0, 5, 10);
        c.gridx = 1;
        c.gridy = 0;
        panel_bottom.add(uploadStatus, c);

        this.upload = new JButton("上传");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 0, 5, 30);
        c.gridx = 2;
        c.gridy = 0;
        panel_bottom.add(upload, c);

        JLabel label3 = new JLabel("保存路径：");
        label3.setFont(new Font("宋体", Font.BOLD, 16));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 30, 5, 0);
        c.gridx = 0;
        c.gridy = 1;
        panel_bottom.add(label3, c);

        this.savedURL = new JTextField("C:\\Users\\Public\\Documents");
        savedURL.setEditable(false);
        savedURL.setBorder(BorderFactory.createEmptyBorder());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 0, 5, 10);
        c.gridx = 1;
        c.gridy = 1;
        panel_bottom.add(savedURL, c);

        this.updateURL = new JButton("更改");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 0, 5, 30);
        c.gridx = 2;
        c.gridy = 1;
        panel_bottom.add(updateURL, c);

        JLabel label4 = new JLabel("文件名称：");
        label4.setFont(new Font("宋体", Font.BOLD, 16));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 30, 5, 0);
        c.gridx = 0;
        c.gridy = 2;
        panel_bottom.add(label4, c);

        this.fileName = new JTextField("");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 0, 5, 10);
        c.gridx = 1;
        c.gridy = 2;
        panel_bottom.add(fileName, c);

        this.fileType = new JComboBox();
        fileType.addItem(".xlsx");
        fileType.addItem(".xls");
        fileType.addItem(".csv");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 0, 5, 30);
        c.gridx = 2;
        c.gridy = 2;
        panel_bottom.add(fileType, c);

        this.execute = new JButton("执行");
        execute.setForeground(Color.white);
        execute.setFont(new Font("粗体", Font.BOLD, 12));
        execute.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
        panel_bottom.add(execute, c);

        panel.add(panel_top);
        panel.add(panel_bottom);

        return panel;
    }

    public JPanel getRightPanel(){
        JPanel panel = new JPanel();

        panel.setPreferredSize(new Dimension(288,380));

        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        JLabel label = new JLabel("********************识别状态*******************");
        this.status = new JTextArea();
        status.setEditable(false);
        status.setBorder(BorderFactory.createEmptyBorder());

        panel.add(label);
        panel.add(status);

        return panel;
    }

    public JToolBar getToolBar(){
        JToolBar toolBar = new JToolBar();

        JLabel label = new JLabel("状态栏");
        label.setFont(new Font("宋体", Font.PLAIN, 12));

        String urlString = "src/wdy/img/red.png";
        this.icon = new JLabel(new ImageIcon(urlString));

        toolBar.add(icon);
        toolBar.add(label);

        return toolBar;
    }

    public static void main(String[] args){
        try
        {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            System.out.println(" -- Exception --");
        }
        finally
        {
            HDR_Frame mFrame = new HDR_Frame();
            mFrame.init();
        }
    }
}
