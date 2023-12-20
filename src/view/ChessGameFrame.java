package view;

import controller.GameController;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import setting.buttonbgm2;
import setting.buttonbgm3;


/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    private final JLabel label1;

    public boolean sound=true;
    public static boolean mode=true;//true->Manual

    public int getSTAGE() {
        return STAGE;
    }

    private final int STAGE;//关卡“第”+STAGE+“关”

    private final int ONE_CHESS_SIZE;
    private JLabel scorelabel;
    private JTextPane scoretextpane;
    private ChessboardComponent chessboardComponent;
    //public JLabel jLabel;



    public ChessGameFrame(int width, int height,int stage) {
        setTitle("2023 CS109 Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;
        URL resource1 = BeginningFrame.class.getResource( "background1.JPG");
        label1=new JLabel(new ImageIcon(resource1));
        //jLabel=label1;
        this.add(label1);
        label1.setBounds(0,0,WIDTH,HEIGTH);

        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                chessboardComponent.getGridComponents()[chessboardComponent.getCurrentGridPoint().getRow()][chessboardComponent.getCurrentGridPoint().getCol()].setBackground(Color.LIGHT_GRAY);
                chessboardComponent.getGridComponents()[chessboardComponent.getCurrentGridPoint().getRow()][chessboardComponent.getCurrentGridPoint().getCol()].setbackground(Color.LIGHT_GRAY);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {

//
            }});


        this.STAGE=stage;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addChessboard(label1);
        //scorelabel=addLabel();
        //scorelabel2=addLabel();
        scoretextpane=addJTextPane(label1);
        addHelloButton(label1);
        if(mode) {
            addSwapConfirmButton(label1);
            addNextStepButton(label1);
        }else{
            addAutoButton(label1);
        }
        addRestButton(label1);
        addBoomButton(label1);
        addBoomRowButton(label1);
        addBoomcolButton(label1);
        addAddStepsButton(label1);
//        addMouseListener(new MouseAdapter() {
//        });

        //addLabel();

    }

    public ChessboardComponent getChessboardComponent() {
        return chessboardComponent;
    }

    public void setChessboardComponent(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }

    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard(JLabel jlabel) {
        chessboardComponent = new ChessboardComponent(ONE_CHESS_SIZE);
        chessboardComponent.setLocation(HEIGTH / 5, HEIGTH / 10);
        chessboardComponent.setSteps((STAGE/5)+5);
        chessboardComponent.setScoregoal(STAGE*100);
        jlabel.add(chessboardComponent);

    }

    /**
     * 在游戏面板中添加标签
     */
    private JLabel addLabel(JLabel jLabel) {
        JLabel statusLabel = new JLabel("Score:"+chessboardComponent.getTotalscore()+"\nSteps:"+chessboardComponent.getSteps());
        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        jLabel.add(statusLabel);
        return statusLabel;
    }

    private JTextPane addJTextPane(JLabel jLabel) {
        JTextPane jTextPane = new JTextPane();
        jTextPane.setText("Score:"+chessboardComponent.getTotalscore()+"\nSteps:"+chessboardComponent.getSteps()+"\nscoregoal:"+STAGE*100);
        jTextPane.setLocation(HEIGTH, HEIGTH / 10);
        jTextPane.setSize(200, 90);
        jTextPane.setFont(new Font("Rockwell", Font.BOLD, 20));
        jTextPane.setEnabled(false);
        jLabel.add(jTextPane);
        return jTextPane;
    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addHelloButton(JLabel jLabel) {
        JButton button = new JButton("Hints");
        button.addActionListener((e) ->
        {
            try {
                new buttonbgm2();
            } catch (UnsupportedAudioFileException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(this, "You need hints for such a simple game?");

        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        jLabel.add(button);
    }

    private void addSwapConfirmButton(JLabel jLabel) {
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
            chessboardComponent.swapChess();this.scoretextpane.setText("Score:"+chessboardComponent.getTotalscore()+"\nSteps:"+chessboardComponent.getSteps()+"\nscoregoal:"+STAGE*100);});

        button.setLocation(HEIGTH, HEIGTH / 10 + 200);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        jLabel.add(button);
    }

    private void addAutoButton(JLabel jLabel) {
        JButton button = new JButton("Swap");


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
            try {
                chessboardComponent.AutoswapChess();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            this.scoretextpane.setText("Score:"+chessboardComponent.getTotalscore()+"\nSteps:"+chessboardComponent.getSteps()+"\nscoregoal:"+STAGE*100);});

        button.setLocation(HEIGTH, HEIGTH / 10 + 200);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        jLabel.add(button);
    }

    private void addNextStepButton(JLabel jLabel) {
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
                this.scoretextpane.setText("Score:" + chessboardComponent.getTotalscore() + "\nSteps:" + chessboardComponent.getSteps() + "\nscoregoal:" + STAGE * 100);

        });


        button.setLocation(HEIGTH, HEIGTH / 10 + 280);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        jLabel.add(button);
    }


    private void addAddStepsButton(JLabel jLabel) {

        JButton button = new JButton("Add Steps");
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

            new InputWindow().setVisible(true);




        });


        button.setLocation(HEIGTH, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));

        jLabel.add(button);
    }

    private class InputWindow extends JFrame {
        private JTextField textField;
        private JLabel resultLabel;

        public InputWindow() {
            super("输入窗口");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setSize(300, 150);
            setLayout(new FlowLayout());

            textField = new JTextField(20);
            add(textField);

            resultLabel = new JLabel("请输入我是笨蛋");
            add(resultLabel);

            textField.addActionListener(e -> {
                String input = textField.getText();
                //resultLabel.setText("请输入我是笨蛋");
                if ("我是笨蛋".equals(input)) {
                    // 执行某一函数，例如打印一句话到resultLabel中
                    resultLabel.setText("步数加一");
                    chessboardComponent.addsteps();
                    scoretextpane.setText("Score:" + chessboardComponent.getTotalscore() + "\nSteps:" + chessboardComponent.getSteps() + "\nscoregoal:" + STAGE * 100);
                    textField.setText("");
                } else {
                    resultLabel.setText("请输入我是笨蛋");
                    textField.setText("");
                }
            });
        }
    }
//    private void addLoadButton() {
//        JButton button = new JButton("Load");
//        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
//        button.setSize(200, 60);
//        button.setFont(new Font("Rockwell", Font.BOLD, 20));
//        add(button);
//
//        button.addActionListener(e -> {
//            System.out.println("Click load");
//            String path = JOptionPane.showInputDialog(this,"Input Path here");
//            gameController.loadGameFromFile(path);
//        });
//    }
private void addRestButton(JLabel jLabel) {
        //GameTool.Reset gametool=new GameTool.Reset();
        JButton button = new JButton("Reset:"+chessboardComponent.getReset());
        button.setLocation(HEIGTH/10, HEIGTH / 10 +600);
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
            reset();
            button.setText("Reset:"+chessboardComponent.getReset());
        });
    }
    private void reset(){
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


    private  void addBoomButton(JLabel jLabel){
        JButton button = new JButton("Booms:"+chessboardComponent.getBooms());
        button.setLocation(HEIGTH/10+200, HEIGTH / 10 +600);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
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
            if (chessboardComponent.getBooms()<=0){JOptionPane.showMessageDialog(this,"Used Up");return;}
            chessboardComponent.onplayerboom();

            //chessboardComponent.setBooms(chessboardComponent.getBooms()-1);


            button.setText("Booms:"+chessboardComponent.getBooms());
        });
        jLabel.add(button);
    }

    private  void addBoomRowButton(JLabel jLabel){
        JButton button = new JButton("RowBooms:"+chessboardComponent.getcolBooms());
        button.setLocation(HEIGTH/10+400, HEIGTH / 10 +600);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
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
            if (chessboardComponent.getrowBooms()<=0){JOptionPane.showMessageDialog(this,"Used Up");return;}
            chessboardComponent.onplayerboomrow();

            //chessboardComponent.setrowBooms(chessboardComponent.getrowBooms()-1);


            button.setText("RowBooms:"+chessboardComponent.getrowBooms());
        });
        jLabel.add(button);
    }
    private  void addBoomcolButton(JLabel jLabel){
        JButton button = new JButton("ColBooms:"+chessboardComponent.getcolBooms());
        button.setLocation(HEIGTH/10+600, HEIGTH / 10 +600);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
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
            if (chessboardComponent.getcolBooms()<=0){JOptionPane.showMessageDialog(this,"Used Up");return;}

            chessboardComponent.onplayerboomcol();

            //chessboardComponent.setcolBooms(chessboardComponent.getcolBooms()-1);


            button.setText("ColBooms:"+chessboardComponent.getcolBooms());
        });
        jLabel.add(button);
    }
}
