package view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class RecordFrame extends JFrame {
    private final int WIDTH;
    private final int HEIGTH;
    private static JPanel panel;
    public  static RecordFrame recordFrame;
    private RecordFrame(int width, int height){
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

        setVisible(true);
    }
    public static RecordFrame getRecordFrame(int width, int height){
        if(recordFrame==null){recordFrame=new RecordFrame(width,height);}
        return recordFrame;
    }
}
