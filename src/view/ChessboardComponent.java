package view;


import controller.GameController;
import fighting.GameControllerFF;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

import static model.Constant.CHESSBOARD_COL_SIZE;
import static model.Constant.CHESSBOARD_ROW_SIZE;

/**
 * This class represents the checkerboard component object on the panel
 */
public class ChessboardComponent extends JComponent implements MouseMotionListener {
    public int getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(int totalscore) {
        this.totalscore = totalscore;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getScoregoal() {
        return scoregoal;
    }

    public void setScoregoal(int scoregoal) {
        this.scoregoal = scoregoal;
    }

    public int getReset() {
        return reset;
    }

    public void setReset(int reset) {
        this.reset = reset;
    }
    public int getBooms() {
        return booms;
    }

    public void setBooms(int booms) {
        this.booms = booms;
    }

    public int getcolBooms() {
        return colbooms;
    }

    public void setcolBooms(int booms) {
        this.colbooms = booms;
    }
    public int getrowBooms() {
        return rowbooms;
    }

    public void setrowBooms(int booms) {
        this.rowbooms = booms;
    }
    // have finished:基础分数系统和显示  步数和显示
    private int totalscore=0;
    //private int steps=0;
    private int steps;
    private int scoregoal;
    private int reset=3;
    private int booms=1;
    private int rowbooms=1;
    private int colbooms=1;

    public ChessboardPoint getCurrentGridPoint() {
        return currentGridPoint;
    }

    private ChessboardPoint currentGridPoint = new ChessboardPoint(0,0);


    private final CellComponent[][] gridComponents = new CellComponent[CHESSBOARD_ROW_SIZE.getNum()][CHESSBOARD_COL_SIZE.getNum()];
    private final int CHESS_SIZE;
    private final Set<ChessboardPoint> riverCell = new HashSet<>();

    private GameController gameController;
    private GameControllerFF gameControllerFF;

    public CellComponent[][] getGridComponents() {
        return gridComponents;
    }

    public ChessboardComponent(int chessSize) {
        CHESS_SIZE = chessSize;
        int width = CHESS_SIZE * 8;
        int height = CHESS_SIZE * 8;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);// Allow mouse events to occur
        setLayout(null); // Use absolute layout.
        setSize(width, height);

        //gameController.setONE_CHESS_SIZE(CHESS_SIZE);
        System.out.printf("chessboard width, height = [%d : %d], chess size = %d\n", width, height, CHESS_SIZE);

        initiateGridComponents();

        addMouseMotionListener(this);

        JPanel rupanel=new JPanel();

        //rupanel.setBounds();
        rupanel.setBackground(Color.cyan);
        rupanel.setVisible(false);
        add(rupanel);

        rupanel.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseExited(MouseEvent e) {
                    System.out.println("cfascas");
                    getGridComponents()[currentGridPoint.getRow()][currentGridPoint.getCol()].setBackground(Color.LIGHT_GRAY);
                    getGridComponents()[currentGridPoint.getRow()][currentGridPoint.getCol()].setbackground(Color.LIGHT_GRAY);
                    repaint();
                }});

    }

//                    for (int row = 0; row < getGridComponents().length; row++) {
//                        for (int col = 0; col < getGridComponents()[row].length; col++) {
//                            getGridComponents()[row][col].setbackground(Color.LIGHT_GRAY);
//                            getGridComponents()[row][col].setBackground(Color.LIGHT_GRAY);
//                        }
//                        repaint();
//                    }
    /**
     * This method represents how to initiate ChessComponent
     * according to Chessboard information
     */
    public void initiateChessComponent(Chessboard chessboard) {
        gameController.setONE_CHESS_SIZE(2*CHESS_SIZE);
        Cell[][] grid = chessboard.getGrid();
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                // TODO: Implement the initialization checkerboard

                if (grid[i][j].getPiece() != null) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    gridComponents[i][j].add(new ChessComponent(CHESS_SIZE, chessPiece));

                }
            }
        }

    }

    public void initiateGridComponents() {

        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessboardPoint temp = new ChessboardPoint(i, j);
                CellComponent cell;
                if (riverCell.contains(temp)) {
                    cell = new CellComponent(Color.CYAN, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                } else {
                    cell = new CellComponent(Color.LIGHT_GRAY, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                }
                gridComponents[i][j] = cell;
            }
        }
    }

    public void registerController(GameController gameController) {
        this.gameController = gameController;
    }
    public void registerControllerFF(GameControllerFF gameController) {
        this.gameControllerFF = gameController;
    }

    public void setChessComponentAtGrid(ChessboardPoint point, ChessComponent chess) {
        getGridComponentAt(point).add(chess);
    }

    public ChessComponent removeChessComponentAtGrid(ChessboardPoint point) {
        // Note re-validation is required after remove / removeAll.
        ChessComponent chess = (ChessComponent) getGridComponentAt(point).getComponents()[0];
        getGridComponentAt(point).removeAll();
        getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        return chess;
    }

    public CellComponent getGridComponentAt(ChessboardPoint point) {
        return gridComponents[point.getRow()][point.getCol()];
    }

    private ChessboardPoint getChessboardPoint(Point point) {
        //System.out.println("[" + point.y/CHESS_SIZE +  ", " +point.x/CHESS_SIZE + "] Clicked");
        return new ChessboardPoint(point.y/CHESS_SIZE, point.x/CHESS_SIZE);
    }
    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }

    public void swapChess(){
        gameController.onPlayerSwapChess();
    }

    public void AutoswapChess() throws InterruptedException {
        gameController.onPlayerAutoSwapChess();
    }

    public void nextStep(){
        gameController.onPlayerNextStep();
    }

    public void onplayerboom(){gameController.boom();}
    public void onplayerboomrow(){gameController.boomrow();}
    public void onplayerboomcol(){gameController.boomcol();}


    public void RestChessBoard(){
        gameController.onPlayerRestChessBoard();
    }

    public boolean checkBoardNull(){return gameController.checkBoardNull(gameController.getModel());}//change1

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            JComponent clickedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            if (clickedComponent.getComponentCount() == 0) {
                System.out.print("None chess here and ");
                gameController.onPlayerClickCell(getChessboardPoint(e.getPoint()), (CellComponent) clickedComponent);
            } else {
                System.out.print("One chess here and ");
                gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (ChessComponent) clickedComponent.getComponents()[0]);
            }
        }
    }



    private void movieswap(){

    }
    private void moviefall(Chessboard chessboard){

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }



    @Override
    public void mouseMoved(MouseEvent e) {

        ChessboardPoint mousegrid=getChessboardPoint(e.getPoint());
        //System.out.println(e.getX()+"-----"+e.getY());
        if((!currentGridPoint.equals(mousegrid))){
        getGridComponents()[currentGridPoint.getRow()][currentGridPoint.getCol()].setBackground(Color.LIGHT_GRAY);
            getGridComponents()[currentGridPoint.getRow()][currentGridPoint.getCol()].setbackground(Color.LIGHT_GRAY);

        }
        currentGridPoint=mousegrid;
        getGridComponents()[mousegrid.getRow()][mousegrid.getCol()].setBackground(Color.CYAN);
        getGridComponents()[mousegrid.getRow()][mousegrid.getCol()].setbackground(Color.CYAN);

        //getGridComponents()[mousegrid.getRow()][mousegrid.getRow()]
        repaint();
    }

    public void addsteps() {
        steps+=1;
    }
}
