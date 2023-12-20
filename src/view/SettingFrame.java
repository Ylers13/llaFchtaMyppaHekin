package view;

import model.ChessPiece;
import model.Constant;
import setting.BGM;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SettingFrame extends JFrame{
    private final int WIDTH;
    private final int HEIGTH;
    private static JPanel panel;
    public  static SettingFrame settingFrame;
    public static BGM bgm;
    private SettingFrame(int width, int height){
        setTitle("2023 CS109 Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        //this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;
        setMaximumSize(new Dimension(1100, 810));
        setMinimumSize(new Dimension(1100, 810));

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        panel=new JPanel(null);
        //panel.setBackground(Color.BLUE);
        add(panel);
        URL resource1 = BeginningFrame.class.getResource( "clouds-7613361_1280.JPG");
        JLabel label1=new JLabel(new ImageIcon(resource1));
        panel.add(label1);label1.setBounds(0,0,WIDTH,HEIGTH);

        JLabel label2=new JLabel();
        label2.setFont(new Font("Rockwell", Font.BOLD, 30));
        label2.setBounds(100,100,100,100);
        label2.setText("BGM:");

        JRadioButton openbutton=new JRadioButton("open",true);
        openbutton.setBounds(400,100,200,100);
        openbutton.setFont(new Font("Rockwell", Font.BOLD, 30));
        openbutton.addActionListener((e)->{
            try {
                if (bgm == null ) { // 检查音频剪辑是否正在运行
                    bgm=new BGM();
                }
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        });

        JRadioButton closebutton=new JRadioButton("close");
        closebutton.setBounds(700,100,200,100);
        closebutton.setFont(new Font("Rockwell", Font.BOLD, 30));
        closebutton.addActionListener((e)->{bgm.close();bgm=null;});




        JLabel label3=new JLabel();
        label3.setFont(new Font("Rockwell", Font.BOLD, 30));
        label3.setBounds(100,200,200,100);
        label3.setText("Restart:");

        JButton restartbutton=new JButton("restart");
        restartbutton.setBounds(400,200,200,100);
        restartbutton.setFont(new Font("Rockwell", Font.BOLD, 30));
        restartbutton.addActionListener((e)->{
            StageSelectFrame stageSelectFrame=StageSelectFrame.getStageSelectFrame(1100,810);

            for (int i = 1; i < stageSelectFrame.getOnStage(); i++) {
                stageSelectFrame.replacebutton(stageSelectFrame.label1,stageSelectFrame.getButtons().get(i),stageSelectFrame.getNullbuttons().get(i));

            }
            stageSelectFrame.setOnStage(1);
        });


        JLabel label4=new JLabel();
        label4.setFont(new Font("Rockwell", Font.BOLD, 30));
        label4.setBounds(100,300,200,100);
        label4.setText("Mode:");

        JRadioButton Manualbutton=new JRadioButton("Manual",true);
        Manualbutton.setBounds(400,300,200,100);
        Manualbutton.setFont(new Font("Rockwell", Font.BOLD, 30));
        Manualbutton.addActionListener((e)->{
            ChessGameFrame.mode=true;
        });

        JRadioButton Autobutton=new JRadioButton("Auto");
        Autobutton.setBounds(700,300,200,100);
        Autobutton.setFont(new Font("Rockwell", Font.BOLD, 30));
        Autobutton.addActionListener((e)->{  ChessGameFrame.mode=false;   });


        JLabel label5=new JLabel();
        label5.setFont(new Font("Rockwell", Font.BOLD, 30));
        label5.setBounds(100,400,200,100);
        label5.setText("chess:");

        JRadioButton picture1button=new JRadioButton("picture1",true);
        picture1button.setBounds(400,400,200,100);
        picture1button.setFont(new Font("Rockwell", Font.BOLD, 30));
        picture1button.addActionListener((e)->{
            ChessPiece.statechess=false;
        });

        JRadioButton picture2button=new JRadioButton("picture2");
        picture2button.setBounds(700,400,200,100);
        picture2button.setFont(new Font("Rockwell", Font.BOLD, 30));
        picture2button.addActionListener((e)->{
            ChessPiece.statechess=true;
        });


        //需要将多个按钮分到一个组中
        ButtonGroup group=new ButtonGroup();
        group.add(openbutton);
        group.add(closebutton);
        label1.add(label2);
        label1.add(openbutton);
        label1.add(closebutton);

        label1.add(label3);
        label1.add(restartbutton);

        ButtonGroup group1=new ButtonGroup();
        group1.add(Manualbutton);
        group1.add(Autobutton);
        label1.add(label4);
        label1.add(Manualbutton);
        label1.add(Autobutton);

        ButtonGroup group2=new ButtonGroup();
        group2.add(picture1button);
        group2.add(picture2button);
        label1.add(label5);
        label1.add(picture1button);
        label1.add(picture2button);

        setVisible(true);
    }
    public static SettingFrame getSettingFrame(int width, int height){
        if(settingFrame==null){settingFrame=new SettingFrame(width,height);}
        return settingFrame;
    }

}
