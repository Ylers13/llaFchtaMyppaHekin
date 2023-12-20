package view;

import setting.BGM;
import setting.buttonbgm1;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class BeginningFrame extends JFrame {
    private final int WIDTH;
    private final int HEIGTH;
    private static JPanel panel;
    private static JPanel panel1;
    private static JPanel panel2;
    private static JPanel panel3;


    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        BeginningFrame beginningFrame=new BeginningFrame(1100,810);
        beginningFrame.add(panel);
        beginningFrame.setVisible(true);
    }


    public BeginningFrame(int width, int height) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        setTitle("2023 CS109 Project Demo"); //设置标题
        //SettingFrame.bgm=new BGM();

        this.WIDTH = width;
        this.HEIGTH = height;
        //this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;
        setMaximumSize(new Dimension(WIDTH, HEIGTH));
        setMinimumSize(new Dimension(WIDTH, HEIGTH));

        //setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        panel =new JPanel(null);panel2=new JPanel();
/*

        panel = new JPanel(new BorderLayout());
        panel1=new JPanel(new GridLayout(4, 1,10,10));
        panel3=new JPanel();

        panel.add(panel1,BorderLayout.CENTER);
        panel.add(panel2,BorderLayout.EAST);
        panel.add(panel3,BorderLayout.WEST);

        // 创建标题组件
        JLabel titleLabel = new JLabel("消消乐", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        panel1.add(titleLabel);

        // 创建按钮组件
        JButton button1 = new JButton("设置");
        JButton button2 = new JButton("查看记录");
        URL resource1 = BeginningFrame.class.getResource( "IMG_3118_sd.jpg");
        URL resource2 = BeginningFrame.class.getResource( "IMG_3118_nk.jpg");
        Icon icon1 = new ImageIcon (resource1);
        Icon icon2 = new ImageIcon (resource2);

        JButton button3 = new JButton();
        JButton button4 = new JButton();
        button3.setIcon(icon1);
        button4.setIcon(icon2);
        button3.setToolTipText("tupiananniu");
        addNextStepButton();
        panel1.add(button1);
        panel1.add(button2);
        panel2.add(button3);
        panel3.add(button4);


        //panel1.setLayout(); //三行一列布局


*/      //setMenuBar();
        //panel=new JPanel();
        //panel.setLayout(null);
        URL resource1 = BeginningFrame.class.getResource( "background1.JPG");//图片来源b站视频封面
        JLabel label1=new JLabel(new ImageIcon(resource1));
        panel.add(label1);label1.setBounds(0,0,WIDTH,HEIGTH);


        ArrayList<JButton> buttons=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            buttons.add(new JButton());
            label1.add(buttons.get(i));
        }
        buttons.get(0).setBounds(600,300,250,125);
        buttons.get(0).setText("选择关卡");
        buttons.get(0).setFont(new Font("Rockwell", Font.BOLD, 30));
        buttons.get(0).addActionListener((e) ->{
           StageSelectFrame stageSelectFrame= StageSelectFrame.getStageSelectFrame(1100,810);
           stageSelectFrame.setVisible(true);
            try {
                new buttonbgm1();
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        }); //new StageSelectFrame(1100, 810));
        buttons.get(1).setBounds(600,460,250,125);
        buttons.get(1).setText("设置");
        buttons.get(1).setFont(new Font("Rockwell", Font.BOLD, 30));
        buttons.get(1).addActionListener((e) -> {
                    SettingFrame settingFrame = SettingFrame.getSettingFrame(1100, 810);
                    settingFrame.setVisible(true);
                    try {
                        new buttonbgm1();
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    }
                });


        buttons.get(2).setBounds(600,620,250,125);
        buttons.get(2).setText("查看记录");
        buttons.get(2).setFont(new Font("Rockwell", Font.BOLD, 30));
        buttons.get(2).addActionListener((e) -> {
            RecordFrame recordFrame = RecordFrame.getRecordFrame(1100, 810);
            recordFrame.setVisible(true);
            try {
                new buttonbgm1();
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        });



        JLabel label2=new JLabel("妮可消消乐");
        label2.setFont(new Font("Arial", Font.LAYOUT_NO_LIMIT_CONTEXT, 100));
        label2.setForeground(Color.ORANGE);
        label2.setBounds(500,50,500,300);
        label1.add(label2);
    }

    private void addNextStepButton() {

        JButton button = new JButton("选择关卡");
        button.addActionListener((e) -> {
            StageSelectFrame stageSelectFrame=StageSelectFrame.getStageSelectFrame(1100,810);
        });//new StageSelectFrame(1100, 810));
        //button.setLocation(HEIGTH, HEIGTH / 10 + 280);
        button.setSize(50, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel1.add(button);
    }
}
