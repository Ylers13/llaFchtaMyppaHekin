package fighting;

import setting.buttonbgm2;
import setting.buttonbgm3;
import view.BeginningFrame;
import view.ChessboardComponent;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class serverboard extends JFrame {
    private static int ONE_CHESS_SIZE;
    private final int WIDTH;
    private final int HEIGTH;
    private final JLabel label1;
    private ChessboardComponent chessboardComponent1;
    private ChessboardComponent chessboardComponent2;

    private JTextPane scoretextpane1;
    private JTextPane scoretextpane2;
    public serverboard(int width, int height) {
        setTitle("2023 CS109 Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;
        URL resource1 = serverboard.class.getResource( "bkgrd.jpeg");
        label1=new JLabel(new ImageIcon(resource1));
        //jLabel=label1;
        setSize(WIDTH, HEIGTH);
        this.add(label1);
        label1.setBounds(-285,0,WIDTH,HEIGTH);


        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addChessboard(label1);
        scoretextpane1=addJTextPane(label1,chessboardComponent1);
        scoretextpane2=addJTextPane2(label1,chessboardComponent2);

        addSwapConfirmButton(label1,chessboardComponent1,scoretextpane1,300,700);
        addSwapConfirmButton(label1,chessboardComponent2,scoretextpane2,1100,700);

        addNextStepButton(label1,chessboardComponent1,scoretextpane1,500,700);
        addNextStepButton(label1,chessboardComponent2,scoretextpane2,1300,700);

        addRestButton(label1,chessboardComponent1,700,700);
        addRestButton(label1,chessboardComponent2,1500,700);


        setVisible(true);
    }

    public ChessboardComponent getChessboardComponent1() {
        return chessboardComponent1;
    }

    public void setChessboardComponent1(ChessboardComponent chessboardComponent) {
        this.chessboardComponent1 = chessboardComponent;
    }

    public ChessboardComponent getChessboardComponent2() {
        return chessboardComponent2;
    }

    public void setChessboardComponent2(ChessboardComponent chessboardComponent) {
        this.chessboardComponent2 = chessboardComponent;
    }

    private void addChessboard(JLabel jlabel) {
        chessboardComponent1 = new ChessboardComponent(ONE_CHESS_SIZE);
        chessboardComponent1.setLocation(300, 100);
        chessboardComponent1.setSteps(10);
        //chessboardComponent.setScoregoal(STAGE*100);
        jlabel.add(chessboardComponent1);

        chessboardComponent2= new ChessboardComponent(ONE_CHESS_SIZE);
        chessboardComponent2.setLocation(1100, 100);
        chessboardComponent2.setSteps(10);
        //chessboardComponent.setScoregoal(STAGE*100);
        jlabel.add(chessboardComponent2);


    }

    private JTextPane addJTextPane(JLabel jLabel,ChessboardComponent chessboardComponent) {
        JTextPane jTextPane = new JTextPane();
        jTextPane.setText("Score:"+chessboardComponent.getTotalscore()+"\nSteps:"+chessboardComponent.getSteps());
        jTextPane.setLocation(300, 10);
        jTextPane.setSize(200, 90);
        jTextPane.setFont(new Font("Rockwell", Font.BOLD, 20));
        jTextPane.setEnabled(false);
        jLabel.add(jTextPane);
        return jTextPane;
    }
    private JTextPane addJTextPane2(JLabel jLabel,ChessboardComponent chessboardComponent) {
        JTextPane jTextPane = new JTextPane();
        jTextPane.setText("Score:"+chessboardComponent.getTotalscore()+"\nSteps:"+chessboardComponent.getSteps());
        jTextPane.setLocation(1100, 10);
        jTextPane.setSize(200, 90);
        jTextPane.setFont(new Font("Rockwell", Font.BOLD, 20));
        jTextPane.setEnabled(false);
        jLabel.add(jTextPane);
        return jTextPane;
    }
    private void addSwapConfirmButton(JLabel jLabel,ChessboardComponent chessboardComponent,JTextPane scoretextpane,int x,int y) {
        JButton button = new JButton("Confirm Swap");


        //button.addActionListener((e) -> {chessboardComponent.swapChess();this.scorelabel.setText("Score:"+chessboardComponent.getTotalscore()+"\nSteps:"+chessboardComponent.getSteps());});
        button.addActionListener((e) -> {
            try {
                new buttonbgm2();
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
            chessboardComponent.swapChess();scoretextpane.setText("Score:"+chessboardComponent.getTotalscore()+"\nSteps:"+chessboardComponent.getSteps());});

        button.setLocation(x,y);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        jLabel.add(button);
    }
    private void addNextStepButton(JLabel jLabel,ChessboardComponent chessboardComponent,JTextPane scoretextpane,int x,int y) {
        JButton button = new JButton("Next Step");
        button.addActionListener((e) -> {
            try {
                new buttonbgm2();
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }

            chessboardComponent.nextStep();
            scoretextpane.setText("Score:" + chessboardComponent.getTotalscore() + "\nSteps:" + chessboardComponent.getSteps());

        });


        button.setLocation(x, y);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        jLabel.add(button);
    }
    private void addRestButton(JLabel jLabel,ChessboardComponent chessboardComponent,int x,int y) {
        //GameTool.Reset gametool=new GameTool.Reset();
        JButton button = new JButton("Reset:"+chessboardComponent.getReset());
        button.setLocation(x, y);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        jLabel.add(button);

        button.addActionListener(e -> {
            try {
                new buttonbgm3();
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
            reset(chessboardComponent);
            button.setText("Reset:"+chessboardComponent.getReset());
        });
    }
    private void reset(ChessboardComponent chessboardComponent){
        if(chessboardComponent.getReset()>0&&chessboardComponent.checkBoardNull()){//change1
            System.out.println("Click Reset");
            chessboardComponent.RestChessBoard();
            chessboardComponent.repaint();
            chessboardComponent.setReset(chessboardComponent.getReset()-1);

        }
        else if (chessboardComponent.getReset()>0&&!chessboardComponent.checkBoardNull()){
            JOptionPane.showMessageDialog(this, "Can‘t do that");
        }
        else{
            JOptionPane.showMessageDialog(this, "Used Up");
        }

    }

}
