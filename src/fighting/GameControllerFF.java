package fighting;

import GameTool.Reset;
import listener.GameListener;
import model.*;
import view.CellComponent;
import view.ChessComponent;
import view.ChessboardComponent;
import view.StageSelectFrame;

import javax.swing.*;

public class GameControllerFF implements GameListener {
    private ChessboardComponent view;
    private Chessboard model;
    private boolean state=true;

    public static Cell nullcell=new Cell(new ChessPiece(""));
    private Reset reset=new Reset();

    private int count2=0;
    private boolean isState=false;

    public boolean isStatemovie() {
        return statemovie;
    }
    private boolean statemovie=false;//动画中为true

    private ChessboardPoint selectedPoint;
    private ChessboardPoint selectedPoint2;


    public GameControllerFF(ChessboardComponent view, Chessboard model) {
//        this.view = view;
//        this.model = model;
//        //this.stageSelectFrame=stageSelectFrame;
//        view.registerControllerFF(this);
//        initialize();
//        state=true;
//        view.initiateChessComponent(model);
//
//        while (checkAndRemove(model)){fall(model);readd(model);}
//        while(!checkPossibleMatch(model)){reset.RestChessBoard(ONE_CHESS_SIZE,model,view);}
//        view.repaint();
//        isState=true;
    }
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {

    }

    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, ChessComponent component) {

    }

    @Override
    public void onPlayerSwapChess() {
//        // TODO: Init your swap function here
//        // Have Finished:确认交换   如果没消交换回来 如果没点下一步则不能点交换 弹出框提示
//        //
//        //onPlayerNextStepstate=true;
//        if(!state){return;}
//        if(statemovie){return;}
//        if(!checkBoardNull(model)){
//            JOptionPane.showMessageDialog(chessGameFrame, "Can't do that.");return;}
//        if(check(model)){JOptionPane.showMessageDialog(chessGameFrame, "There are 3-matchs remain.");return;}
//        if (selectedPoint==null&&selectedPoint2==null){
//            return;
//        }
//        if(!checkBoardNull(model)){return;}
//        if(!checkStepGoneThenOver(view)){return;}
//        step:
//        {
//            if (selectedPoint == null || selectedPoint2 == null) {
//                break step;
//            }
//            swap(selectedPoint,selectedPoint2);
//        }
//        //view.removeChessComponentAtGrid(selectedPoint);
//        //view.removeChessComponentAtGrid(selectedPoint2);
//
//
//
//        if(selectedPoint!=null){
//            var point1=(ChessComponent)view.getGridComponentAt(selectedPoint).getComponent(0);
//            point1.setSelected(false);
//            point1.repaint();
//        }
//        if(selectedPoint2!=null){
//            var point2=(ChessComponent)view.getGridComponentAt(selectedPoint2).getComponent(0);
//            point2.setSelected(false);
//            point2.repaint();
//        }
//
//        if((!checkAndRemove(model))&&selectedPoint != null && selectedPoint2 != null){
//
//            swap(selectedPoint,selectedPoint2);
//            JOptionPane.showMessageDialog(view, "No remove");
//            return;
//        }
//
//
//        view.setTotalscore(view.getTotalscore()+count2*10);
//        count2=0;
//        if(selectedPoint!=null&&selectedPoint2!=null){
//            view.setSteps(view.getSteps()-1);
//            view.repaint();}
//        selectedPoint=null;
//        selectedPoint2=null;
//        checkScoreEnoughThenOver(view.getTotalscore(), view.getScoregoal());
//        //checkStepGoneThenOver(view);
//        System.out.println("Implement your swap here.");
//        if(!checkSteps(view.getSteps())){judgeResult();}

    }

    @Override
    public void onPlayerNextStep() {

    }

    @Override
    public void onPlayerRestChessBoard() {

    }

    private void initialize() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {

            }
        }
    }
}
