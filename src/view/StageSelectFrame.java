package view;

import controller.GameController;
import model.Chessboard;
import setting.buttonbgm1;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class StageSelectFrame extends JFrame{
    private static StageSelectFrame stageSelectFrame;
    private final int WIDTH;
    private final int HEIGTH;
    public JLabel label1;
    private final JPanel panel;
    private ArrayList<JButton> buttons=new ArrayList<>();

    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    public ArrayList<JButton> getNullbuttons() {
        return nullbuttons;
    }

    private ArrayList<JButton> nullbuttons=new ArrayList<>();

    public int getOnStage() {
        return onStage;
    }

    public void setOnStage(int onStage) {
        this.onStage = onStage;
    }

    private int onStage=1;
//    private static JPanel panel1;
//    private static JPanel panel2;
//    private static JPanel panel3;
    private StageSelectFrame(int width, int height) {
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
        URL resource1 = BeginningFrame.class.getResource( "background1.JPG");
        label1=new JLabel(new ImageIcon(resource1));
        panel.add(label1);label1.setBounds(0,0,WIDTH,HEIGTH);

//        JButton button1 = new JButton("stage1");
//        JButton button2 = new JButton("stsge2");
//        addNextStepButton();
//        panel.add(button1);
//        panel.add(button2);


        for (int i =0; i <88; i++) {
            buttons.add(new JButton("第"+(1+i)+"关"));
            buttons.get(i).setBounds((i%11)*100,(i/11)*100,80,80);
            int finalI = i;//??????

            buttons.get(i).addActionListener((e) -> {ChessGameFrame mainFrame = new ChessGameFrame(1100, 810, finalI+1);
                GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard(0),this);
                gameController.setChessGameFrame(mainFrame);

                mainFrame.setVisible(true);
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
            if(i<onStage){
            label1.add(buttons.get(finalI));}
        }
        //ArrayList<ChessGameFrame> chessGameFrameArrayList=new ArrayList<>();
        for (int i = 0; i < 88; i++) {

            nullbuttons.add(new JButton("locked"));

            nullbuttons.get(i).setBounds((i%11)*100,(i/11)*100,80,80);//??
            nullbuttons.get(i).setBackground(Color.BLACK);//??
            int finalI = i;
            //nullbuttons.get(i-onStage).addActionListener((e) ->{replacebutton(label1,nullbuttons.get(finalI),buttons.get(finalI));repaint();});

            if (i>=onStage){
            label1.add(nullbuttons.get(i));}

        }
//        JButton nullButton=new JButton("Locked");
//        nullButton.setBackground(Color.BLACK);


        setVisible(true);

    }

    public static StageSelectFrame getStageSelectFrame(int width, int height){
        if(stageSelectFrame==null){stageSelectFrame=new StageSelectFrame(width,height);}
        return stageSelectFrame;
    }
    private void initiatestage(int onStages){
        for (int i = 0; i < onStages; i++) {

        }
    }
//    public void openNewStage(JLabel label,JButton button1,JButton button2){
//        replacebutton(label, button1, button2);
//    }

    public void replacebutton(JLabel label,JButton button1,JButton button2){
        if(true) {//
            label.remove(button1);
            label.add(button2);
        }
    }
//youwu
    private void addNextStepButton() {

        JButton button = new JButton("选择关卡");
        button.addActionListener((e) -> {ChessGameFrame mainFrame = new ChessGameFrame(1100, 810,onStage);
            GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard(0),this);
            gameController.setChessGameFrame(mainFrame);
            mainFrame.setVisible(true);});
        //button.setLocation(HEIGTH, HEIGTH / 10 + 280);
        button.setSize(50, 50);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(button);
    }
}
