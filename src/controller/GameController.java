package controller;

import GameTool.Reset;
import listener.GameListener;
import model.*;
import view.*;
import java.util.TimerTask;
import java.util.Timer;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CountDownLatch;

import static model.Constant.CHESSBOARD_COL_SIZE;
import static model.Constant.CHESSBOARD_ROW_SIZE;


/**
 * Controller is the connection between model and view,
 * when a Controller receive a request from a view, the Controller
 * analyzes and then hands over to the model for processing
 * [in this demo the request methods are onPlayerClickCell() and
 * onPlayerClickChessPiece()]
 *
 */
public class GameController implements GameListener {
    private int ONE_CHESS_SIZE;
    private Timer timer;
    public void setONE_CHESS_SIZE(int ONE_CHESS_SIZE){
        this.ONE_CHESS_SIZE=ONE_CHESS_SIZE;
    }
private boolean state=true;

    public boolean isStatemovie() {
        return statemovie;
    }

    private boolean statemovie=false;//动画中为true
    //private boolean statefall=false;
    //private boolean statenext=false;
    private Chessboard model;
    //change1
    public Chessboard getModel() {
        return model;
    }



    public ChessboardComponent getView() {
        return view;
    }

    public void setView(ChessboardComponent view) {
        this.view = view;
    }

    private ChessboardComponent view;

    public ChessGameFrame getChessGameFrame() {
        return chessGameFrame;
    }

    public void setChessGameFrame(ChessGameFrame chessGameFrame) {
        this.chessGameFrame = chessGameFrame;
    }

    private final StageSelectFrame stageSelectFrame;
    private ChessGameFrame chessGameFrame;
    public static Cell nullcell=new Cell(new ChessPiece(""));
    private Reset reset=new Reset();

    private int count2=0;
    private boolean isState=false;


    // Record whether there is a selected piece before
    private ChessboardPoint selectedPoint;
    private ChessboardPoint selectedPoint2;

    public GameController(ChessboardComponent view, Chessboard model,StageSelectFrame stageSelectFrame) {
        this.view = view;
        this.model = model;
        this.stageSelectFrame=stageSelectFrame;
        view.registerController(this);
        initialize();
        state=true;
        view.initiateChessComponent(model);

        while (checkAndRemove(model)){fall(model);readd(model);}
        while(!checkPossibleMatch(model)){reset.RestChessBoard(ONE_CHESS_SIZE,model,view);}
        view.repaint();
        isState=true;
    }

    private void initialize() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {

            }
        }
    }

    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
    }

    private int COUNT_DOWN_LATCH=0;
    private CountDownLatch countDownLatch;
    public void onPlayerAutoSwapChess() throws InterruptedException {
//        if(!state){return;}
//        if(statemovie){return;}
//        if(!checkBoardNull(model)){JOptionPane.showMessageDialog(chessGameFrame, "Can't do that.");return;}
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
//
//        if(!checkSteps(view.getSteps())){judgeResult();}
        onPlayerSwapChess();


        for (int i = 0; i < 50; i++) {

            System.out.println("ingjigning");
            //onPlayerNextStep();
            if(!state){return;}
            if(statemovie){return;}

            if(!checkFall(model)) {
                if(!statemovie){
                    fall(model);}else{return;}
            } else if(checkBoardNull(model)){
                if(statemovie){return;}
                checkAndRemove(model);
                view.setTotalscore(view.getTotalscore()+count2*10);
                count2=0;
                //checkPossibleMatch(model);
            }else{
                if(!statemovie){
                    readd(model);
                }else{return;}
            }
//        if(!checkBoardNull(model)&&checkFall(model)){
//            checkStepGoneThenOver(view);}
            checkScoreEnoughThenOver(view.getTotalscore(), view.getScoregoal());
            //如果检测到死胡同，提示玩家使用洗牌道具。
            if(!checkPossibleMatch(model)&&checkBoardNull(model)){JOptionPane.showMessageDialog(chessGameFrame, "No possible 3-match,please use reset tool.");}
            System.out.println("Implement your next step here.");
            //countDownLatch.await();
        }

        boolean st=true;

//        while(st){
//            System.out.println("running");
//        mythread ti=new mythread(this);
//            st=ti.st();
//            ti.start();
//            ti.join();
//
//            };




    }



    @Override
    public void onPlayerSwapChess(){
        // TODO: Init your swap function here
        // Have Finished:确认交换   如果没消交换回来 如果没点下一步则不能点交换 弹出框提示
        //
        //onPlayerNextStepstate=true;
        if(!state){return;}
        if(statemovie){return;}
        if(!checkBoardNull(model)){JOptionPane.showMessageDialog(chessGameFrame, "Can't do that.");return;}
        if(check(model)){JOptionPane.showMessageDialog(chessGameFrame, "There are 3-matchs remain.");return;}
        if (selectedPoint==null&&selectedPoint2==null){
            return;
        }
        if(!checkBoardNull(model)){return;}
        if(!checkStepGoneThenOver(view)){return;}
        step:
        {
            if (selectedPoint == null || selectedPoint2 == null) {
                break step;
            }
             swap(selectedPoint,selectedPoint2);
        }
        //view.removeChessComponentAtGrid(selectedPoint);
        //view.removeChessComponentAtGrid(selectedPoint2);



        if(selectedPoint!=null){
            var point1=(ChessComponent)view.getGridComponentAt(selectedPoint).getComponent(0);
            point1.setSelected(false);
            point1.repaint();
        }
        if(selectedPoint2!=null){
            var point2=(ChessComponent)view.getGridComponentAt(selectedPoint2).getComponent(0);
            point2.setSelected(false);
            point2.repaint();
        }

        if((!checkAndRemove(model))&&selectedPoint != null && selectedPoint2 != null){

            swap(selectedPoint,selectedPoint2);
            JOptionPane.showMessageDialog(view, "No remove");
            return;
        }


        view.setTotalscore(view.getTotalscore()+count2*10);
        count2=0;
        if(selectedPoint!=null&&selectedPoint2!=null){
        view.setSteps(view.getSteps()-1);
        view.repaint();}
        selectedPoint=null;
        selectedPoint2=null;
        checkScoreEnoughThenOver(view.getTotalscore(), view.getScoregoal());
        //checkStepGoneThenOver(view);
        System.out.println("Implement your swap here.");
        if(!checkSteps(view.getSteps())){judgeResult();}

    }


    @Override
    public void onPlayerNextStep(){
        // TODO: Init your next step function here.
        if(!state){return;}
        if(statemovie){return;}

        if(!checkFall(model)) {
            if(!statemovie){
            fallmovie(model);}else{return;}
        } else if(checkBoardNull(model)){
            if(statemovie){return;}
            checkAndRemove(model);
            view.setTotalscore(view.getTotalscore()+count2*10);
            count2=0;
            //checkPossibleMatch(model);
        }else{
            if(!statemovie){
                readdmovie2(model);
            }else{return;}
        }
//        if(!checkBoardNull(model)&&checkFall(model)){
//            checkStepGoneThenOver(view);}
        checkScoreEnoughThenOver(view.getTotalscore(), view.getScoregoal());
        //如果检测到死胡同，提示玩家使用洗牌道具。
        if(!checkPossibleMatch(model)&&checkBoardNull(model)){JOptionPane.showMessageDialog(chessGameFrame, "No possible 3-match,please use reset tool.");}
        System.out.println("Implement your next step here.");
    }


    //:重置棋盘工具
        @Override
        public void onPlayerRestChessBoard(){
            if(!state){return;}
            if(statemovie){return;}
            if(check(model)){JOptionPane.showMessageDialog(chessGameFrame, "There are 3-matchs remain.");return;}

            reset.RestChessBoard(ONE_CHESS_SIZE,model,view);
            while (checkAndRemove(model)){
                fall(model);readd(model);
                view.repaint();
            }


        }
    //

    // click a cell with a chess
    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, ChessComponent component) {
        if(selectedPoint2 != null){
            var distance2point1 = Math.abs(selectedPoint.getCol() - point.getCol()) + Math.abs(selectedPoint.getRow() - point.getRow());
            var distance2point2 = Math.abs(selectedPoint2.getCol() - point.getCol()) + Math.abs(selectedPoint2.getRow() - point.getRow());
            var point1 = (ChessComponent)view.getGridComponentAt(selectedPoint).getComponent(0);
            var point2 = (ChessComponent)view.getGridComponentAt(selectedPoint2).getComponent(0);
            if(distance2point1 == 0 && point1!= null){
                point1.setSelected(false);
                point1.repaint();
                selectedPoint = selectedPoint2;
                selectedPoint2 = null;
            }else if(distance2point2 == 0 && point2!= null){
                point2.setSelected(false);
                point2.repaint();
                selectedPoint2 = null;
            }else if(distance2point1 == 1 && point2!= null){
                point2.setSelected(false);
                point2.repaint();
                selectedPoint2 = point;
                component.setSelected(true);
                component.repaint();
            }else if(distance2point2 == 1 && point1!= null){
                point1.setSelected(false);
                point1.repaint();
                selectedPoint = selectedPoint2;
                selectedPoint2 = point;
                component.setSelected(true);
                component.repaint();
            }
            return;
        }




        if (selectedPoint == null) {
            selectedPoint = point;
            component.setSelected(true);
            component.repaint();
            return;
        }
        
        var distance2point1 = Math.abs(selectedPoint.getCol() - point.getCol()) + Math.abs(selectedPoint.getRow() - point.getRow()); 
        
        if(distance2point1 == 0){
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
            return;
        }

        if(distance2point1 == 1){
            selectedPoint2 = point;
            component.setSelected(true);
            component.repaint();
        }else{
            selectedPoint2 = null;
            
            var grid = (ChessComponent)view.getGridComponentAt(selectedPoint).getComponent(0);
            if(grid == null) return;            
            grid.setSelected(false);
            grid.repaint();
            
            selectedPoint = point;
            component.setSelected(true);
            component.repaint();
        }
    }





 /////////////////////////////////
    private void swap(ChessboardPoint selectedPoint,ChessboardPoint selectedPoint2){
        model.swapChessPiece(selectedPoint, selectedPoint2);
//            view.getGridComponentAt(selectedPoint).removeAll();
//            view.getGridComponentAt(selectedPoint2).removeAll();
        view.removeChessComponentAtGrid(selectedPoint);
        view.removeChessComponentAtGrid(selectedPoint2);
        //selectedPoint
        view.setChessComponentAtGrid(selectedPoint, new ChessComponent(ONE_CHESS_SIZE, model.getGrid()[selectedPoint.getRow()][selectedPoint.getCol()].getPiece()));
        view.setChessComponentAtGrid(selectedPoint2, new ChessComponent(ONE_CHESS_SIZE, model.getGrid()[selectedPoint2.getRow()][selectedPoint2.getCol()].getPiece()));

    }
//如果没有进行三消及以上则false，有则true//进行分数计算
    public boolean checkAndRemove(Chessboard chessboard){
        Cell[][] grid2=new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()+4][Constant.CHESSBOARD_COL_SIZE.getNum()+4];
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                model.setCount(i,j,0);
            }
        }
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum()+4; i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum()+4; j++) {
                if(i<=1||i>=Constant.CHESSBOARD_ROW_SIZE.getNum()+2||j<=1||j>=Constant.CHESSBOARD_COL_SIZE.getNum()+2){
                grid2[i][j]=nullcell;}else{grid2[i][j]= chessboard.getGrid()[i-2][j-2];}
            }
        }
        for (int i = 2; i < Constant.CHESSBOARD_ROW_SIZE.getNum()+2; i++) {
            for (int j = 2; j < Constant.CHESSBOARD_COL_SIZE.getNum()+2; j++) {
                if((grid2[i][j].getPiece().equals(grid2[i][j+1].getPiece())&&grid2[i][j].getPiece().equals(grid2[i][j-1].getPiece()))||(grid2[i][j].getPiece().equals(grid2[i][j+1].getPiece())&&grid2[i][j].getPiece().equals(grid2[i][j+2].getPiece()))||((grid2[i][j].getPiece().equals(grid2[i][j-1].getPiece()))&&grid2[i][j].getPiece().equals(grid2[i][j-2].getPiece()))){
                    model.setCount(i-2,j-2,model.getCount()[i-2][j-2]+1);
                }

            }
        }
        for (int i = 2; i < Constant.CHESSBOARD_ROW_SIZE.getNum()+2; i++) {
            for (int j = 2; j < Constant.CHESSBOARD_COL_SIZE.getNum()+2; j++) {
                if((grid2[i][j].getPiece().equals(grid2[i+1][j].getPiece())&&grid2[i][j].getPiece().equals(grid2[i+2][j].getPiece()))||(grid2[i][j].getPiece().equals(grid2[i-1][j].getPiece()))&&grid2[i][j].getPiece().equals(grid2[i+1][j].getPiece())||(grid2[i][j].getPiece().equals(grid2[i-1][j].getPiece())&&grid2[i][j].getPiece().equals(grid2[i-2][j].getPiece()))){
                    model.setCount(i-2,j-2,model.getCount()[i-2][j-2]+1);
                }
            }
        }
        int count1;
        count1=0;
        for (int i = 0; i <Constant.CHESSBOARD_ROW_SIZE.getNum() ; i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if(model.getCount()[i][j]>0){
                    model.getGrid()[i][j].removePiece();
                    view.getGridComponents()[i][j].removeAll();
                    view.repaint();
                    count1+=model.getCount()[i][j];
                }
            }
        }
        count2=count1;
        if(count1!=0){return true;}
        return false;
    }

    public boolean check(Chessboard chessboard) {
        Cell[][] grid2 = new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum() + 4][Constant.CHESSBOARD_COL_SIZE.getNum() + 4];

        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                model.setCount(i, j, 0);
            }
        }

        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum() + 4; i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum() + 4; j++) {
                if (i <= 1 || i >= Constant.CHESSBOARD_ROW_SIZE.getNum() + 2 || j <= 1 || j >= Constant.CHESSBOARD_COL_SIZE.getNum() + 2) {
                    grid2[i][j] = nullcell;
                } else {
                    grid2[i][j] = chessboard.getGrid()[i - 2][j - 2];
                }
            }
        }

        for (int i = 2; i < Constant.CHESSBOARD_ROW_SIZE.getNum() + 2; i++) {
            for (int j = 2; j < Constant.CHESSBOARD_COL_SIZE.getNum() + 2; j++) {
                if ((grid2[i][j].getPiece().equals(grid2[i][j + 1].getPiece()) && grid2[i][j].getPiece().equals(grid2[i][j - 1].getPiece())) ||
                        (grid2[i][j].getPiece().equals(grid2[i][j + 1].getPiece()) && grid2[i][j].getPiece().equals(grid2[i][j + 2].getPiece()))) {
                    model.setCount(i - 2, j - 2, model.getCount()[i - 2][j - 2] + 1);
                }
            }
        }

        for (int i = 2; i < Constant.CHESSBOARD_ROW_SIZE.getNum() + 2; i++) {
            for (int j = 2; j < Constant.CHESSBOARD_COL_SIZE.getNum() + 2; j++) {
                if ((grid2[i][j].getPiece().equals(grid2[i + 1][j].getPiece()) && grid2[i][j].getPiece().equals(grid2[i - 1][j].getPiece())) ||
                        (grid2[i][j].getPiece().equals(grid2[i - 1][j].getPiece()) && grid2[i][j].getPiece().equals(grid2[i - 2][j].getPiece()))) {
                    model.setCount(i - 2, j - 2, model.getCount()[i - 2][j - 2] + 1);
                }
            }
        }
        int count1 = 0;
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if (model.getCount()[i][j] > 0) {
                    //model.getGrid()[i][j].removePiece();
                    //view.getGridComponents()[i][j].removeAll();
                    //view.repaint();
                    count1 += model.getCount()[i][j];
                }
            }
        }
        if (count1 != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void fall(Chessboard chessboard){
        //for (int i1 = 0; i1 < Constant.CHESSBOARD_ROW_SIZE.getNum(); i1++) {

            for (int i = Constant.CHESSBOARD_COL_SIZE.getNum()-1; i >=0 ; i--) {
                for (int j = Constant.CHESSBOARD_ROW_SIZE.getNum()-1; j >0 ; j--) {
//                    if (chessboard.getGrid()[j][i].getPiece()==null){
//                        chessboard.getGrid()[j][i].setPiece(nullcell.getPiece());
//                    }
                    if(chessboard.getGrid()[j][i].getPiece().getName().isEmpty()&&!chessboard.getGrid()[j-1][i].getPiece().getName().isEmpty()){

                        //if(!chessboard.getGrid()[j-1][i].getPiece().getName().isEmpty())
                        for (int k = 1; k <= j; k++) {
                            if(!chessboard.getGrid()[j-k][i].getPiece().getName().isEmpty()){
                                chessboard.getGrid()[j-k][i].swapPiece(chessboard.getGrid()[j-k+1][i]);
//                                model.getGrid()[j-k][i].removePiece();
//                                model.getGrid()[j-k+1][i].removePiece();
//                                model.getGrid()[j-k][i].add(new ChessComponent(ONE_CHESS_SIZE, chessboard.getGrid()[j-k][i].getPiece()));
//                                model.getGrid()[j-k+1][i].add(new ChessComponent(ONE_CHESS_SIZE, chessboard.getGrid()[j-k+1][i].getPiece()));


                                view.getGridComponents()[j-k][i].removeAll();
                                view.getGridComponents()[j-k+1][i].removeAll();

                                view.getGridComponents()[j-k][i].add(new ChessComponent(ONE_CHESS_SIZE, chessboard.getGrid()[j-k][i].getPiece()));
                                view.getGridComponents()[j-k+1][i].add(new ChessComponent(ONE_CHESS_SIZE, chessboard.getGrid()[j-k+1][i].getPiece()));
                                view.repaint();

                            }

                        }

                    }
                }
                view.repaint();
            }
        //}
    }



    public void fallmovie(Chessboard chessboard){
        Timer timer = new Timer();
        countDownLatch=new CountDownLatch(8);
        //for (int i1 = 0; i1 < Constant.CHESSBOARD_ROW_SIZE.getNum(); i1++) {
        timer.schedule(   new TimerTask(){
            int count3=0;
                @Override
                public void run() {
                    statemovie=true;
                    //for (int i1 = 0; i1 < Constant.CHESSBOARD_ROW_SIZE.getNum(); i1++) {

                    for (int i = Constant.CHESSBOARD_COL_SIZE.getNum() - 1; i >= 0; i--) {
                        for (int j = Constant.CHESSBOARD_ROW_SIZE.getNum() - 1; j > 0; j--) {
//                    if (chessboard.getGrid()[j][i].getPiece()==null){
//                        chessboard.getGrid()[j][i].setPiece(nullcell.getPiece());
//                    }
                            if (chessboard.getGrid()[j][i].getPiece().getName().isEmpty() && !chessboard.getGrid()[j - 1][i].getPiece().getName().isEmpty()) {

                                //if(!chessboard.getGrid()[j-1][i].getPiece().getName().isEmpty())
                                for (int k = 1; k <= j; k++) {
                                    if (!chessboard.getGrid()[j - k][i].getPiece().getName().isEmpty()) {
                                        chessboard.getGrid()[j - k][i].swapPiece(chessboard.getGrid()[j - k + 1][i]);
//                                model.getGrid()[j-k][i].removePiece();
//                                model.getGrid()[j-k+1][i].removePiece();
//                                model.getGrid()[j-k][i].add(new ChessComponent(ONE_CHESS_SIZE, chessboard.getGrid()[j-k][i].getPiece()));
//                                model.getGrid()[j-k+1][i].add(new ChessComponent(ONE_CHESS_SIZE, chessboard.getGrid()[j-k+1][i].getPiece()));


                                        view.getGridComponents()[j - k][i].removeAll();
                                        view.getGridComponents()[j - k + 1][i].removeAll();

                                        view.getGridComponents()[j - k][i].add(new ChessComponent(ONE_CHESS_SIZE, chessboard.getGrid()[j - k][i].getPiece()));
                                        view.getGridComponents()[j - k + 1][i].add(new ChessComponent(ONE_CHESS_SIZE, chessboard.getGrid()[j - k + 1][i].getPiece()));
                                        view.repaint();

                                    }

                                }

                            }
                        }
                        view.repaint();
                    }
                    //}

                    if(checkFall(model)){statemovie=false;while(countDownLatch.getCount()>0){countDownLatch.countDown();};timer.cancel();}
                }
            },0,500);

       // }
    }

    public void shunxv(int x,ChessComponent[] chessComponents,Chessboard chessboard){
        for (int i = 0; i < chessComponents.length; i++) {
            for (int j = 0; j <= i; j++) {
                if(view.getGridComponents()[j][x].getComponents().length!=0){view.getGridComponents()[j][x].removeAll();}

                view.getGridComponents()[j][x].add(new ChessComponent(ONE_CHESS_SIZE, chessboard.getGrid()[j][i].getPiece()));
            }
            view.repaint();
        }
    }


//如果有chess还没掉落则false，全掉落则true//如果全满则true
    public boolean checkFall(Chessboard chessboard){
        for (int i = Constant.CHESSBOARD_COL_SIZE.getNum()-1; i >=0 ; i--) {
            for (int j = Constant.CHESSBOARD_ROW_SIZE.getNum()-1; j >=0 ; j--) {
                if(chessboard.getGrid()[j][i]==nullcell||chessboard.getGrid()[j][i].getPiece()==nullcell.getPiece()) {
                    for (int k = 1; k <= j; k++) {
                        if(!chessboard.getGrid()[j-k][i].getPiece().getName().isEmpty()){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
                }

    public void readd(Chessboard chessboard){
        for (int i = Constant.CHESSBOARD_COL_SIZE.getNum()-1; i >=0 ; i--) {
            for (int j = Constant.CHESSBOARD_ROW_SIZE.getNum()-1; j >=0 ; j--) {
//                if(chessboard.getGrid()[j][i]==nullcell||chessboard.getGrid()[j][i].getPiece()==nullcell.getPiece()){
//                    //int fi
//                    ChessComponent[] chessComponents=new ChessComponent[];
//                }
                if(chessboard.getGrid()[j][i]==nullcell||chessboard.getGrid()[j][i].getPiece()==nullcell.getPiece()){
                    chessboard.getGrid()[j][i].setPiece(new ChessPiece( Util.RandomPick(new String[]{"💎", "⚪", "▲", "🔶"})));
                    view.getGridComponents()[j][i].removeAll();
                    //if(isState)fallmovietry(view,view.getGridComponents()[j][i],model.getGrid()[j][i]);
                    //ChessComponent[] chessComponents=new ChessComponent[];
                    view.getGridComponents()[j][i].add(new ChessComponent(ONE_CHESS_SIZE, chessboard.getGrid()[j][i].getPiece()));
                }
            }
        }
        view.repaint();
    }




    public void readdmovie(Chessboard chessboard){
        int cun=0;
        Timer timer1=new Timer();
        ChessComponent[] chessComponents=null;
        for (int i = Constant.CHESSBOARD_COL_SIZE.getNum()-1; i >=0 ; i--) {
            chessComponents=null;
            //cun=0;
            for (int j = Constant.CHESSBOARD_ROW_SIZE.getNum()-1; j >=0 ; j--) {
                if(chessComponents==null&&(chessboard.getGrid()[j][i]==nullcell||chessboard.getGrid()[j][i].getPiece()==nullcell.getPiece())){
                    //cun++;
                    chessComponents=new ChessComponent[j+1];
                }
                if(chessboard.getGrid()[j][i]==nullcell||chessboard.getGrid()[j][i].getPiece()==nullcell.getPiece()){
                    chessboard.getGrid()[j][i].setPiece(new ChessPiece( Util.RandomPick(new String[]{"💎", "⚪", "▲", "🔶"})));
                    view.getGridComponents()[j][i].removeAll();
                    //if(isState)fallmovietry(view,view.getGridComponents()[j][i],model.getGrid()[j][i]);
                    //ChessComponent[] chessComponents=new ChessComponent[];
                    if(chessComponents!=null)chessComponents[j]=new ChessComponent(ONE_CHESS_SIZE, chessboard.getGrid()[j][i].getPiece());
                    //view.getGridComponents()[j][i].add(new ChessComponent(ONE_CHESS_SIZE, chessboard.getGrid()[j][i].getPiece()));
                }
            }
            if(chessComponents!=null) {
                ChessComponent[] finalChessComponents = chessComponents;
                int finalI = i;

                timer1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        shunxv(finalI, finalChessComponents,model);

                    }
                }, 0, 1000);
            }
        }
        if(checkBoardNull(model)){timer1.cancel();}
        view.repaint();
    }

    public void shunxv2(int x, int ychessComponent, Chessboard chessboard){
        for (int i = 0; i < ychessComponent; i++) {
            for (int j = 0; j <= i; j++) {
                if(view.getGridComponents()[j][x].getComponents().length!=0){view.getGridComponents()[j][x].removeAll();}

                view.getGridComponents()[j][x].add(new ChessComponent(ONE_CHESS_SIZE, chessboard.getGrid()[ychessComponent-1-i+j][x].getPiece()));
            }
            view.repaint();
        }
    }


    public  void readdmovie2(Chessboard chessboard){

        Timer timer1=new Timer();

        int[] ychessComponents=new int[]{-1,-1,-1,-1,-1,-1,-1,-1};

        for (int i = Constant.CHESSBOARD_COL_SIZE.getNum()-1; i >=0 ; i--) {


            for (int j = Constant.CHESSBOARD_ROW_SIZE.getNum()-1; j >=0 ; j--) {
                if(ychessComponents[i]==-1&&(chessboard.getGrid()[j][i]==nullcell||chessboard.getGrid()[j][i].getPiece()==nullcell.getPiece())){

                    ychessComponents[i]=j+1;

                }
                if(chessboard.getGrid()[j][i]==nullcell||chessboard.getGrid()[j][i].getPiece()==nullcell.getPiece()){
                    chessboard.getGrid()[j][i].setPiece(new ChessPiece( Util.RandomPick(new String[]{"💎", "⚪", "▲", "🔶"})));
                    view.getGridComponents()[j][i].removeAll();
                }
            }
        }

        COUNT_DOWN_LATCH=arrmax(ychessComponents);
        countDownLatch=new CountDownLatch(COUNT_DOWN_LATCH);

        timer1.schedule(new TimerTask() {
            int i=0;
            @Override
            public void run() {
                if(countDownLatch!=null){
                countDownLatch.countDown();}
                statemovie=true;
                for (int ii = 0; ii < ychessComponents.length; ii++) {
                    System.out.print(ychessComponents[ii]+"\t");
                }
                System.out.println("");

                for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                    if(ychessComponents[j]!=-1&&i<ychessComponents[j]){
                    for (int k = 0; k <= i; k++) {
//                            chessboard.getGrid()[0][j].setPiece(new ChessPiece( Util.RandomPick(new String[]{"💎", "⚪", "▲", "🔶"})));
//                            view.getGridComponents()[0][j].removeAll();
                            //if(isState)fallmovietry(view,view.getGridComponents()[j][i],model.getGrid()[j][i]);
                            //ChessComponent[] chessComponents=new ChessComponent[];
                        if(view.getGridComponents()[k][j].getComponents().length!=0){view.getGridComponents()[k][j].removeAll();}

                        view.getGridComponents()[k][j].add(new ChessComponent(ONE_CHESS_SIZE, chessboard.getGrid()[ychessComponents[j]-1-i+k][j].getPiece()));

                        }
                    }
                }
                view.repaint();

                i++;
                //if(i>=Constant.CHESSBOARD_ROW_SIZE.getNum()){
                    if(i>=arrmax(ychessComponents)){
                    statemovie=false;
                    timer1.cancel();
                }
            }
        },0,500);

//            if(ychessComponents[i]!=-1) {
//                //if(ychessComponent!=-1) {
//
//
//                    int finalI = i;
//                    int finalYchessComponent = ychessComponent;
//                    timer1.schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            shunxv2(finalI, finalYchessComponent,model);
//                            if(checkBoardNull(model)){timer1.cancel();}
//                        }
//                    }, 0, 1000);
//
//                //}
//            }

        //view.repaint();
    }

    private int arrmax(int[] arr){
        int out=arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>out)out=arr[i];
        }
        return out;
    }
    
    public void readdmovie3(Chessboard chessboard){
        boolean sta=true;
        int[] kong =new int[Constant.CHESSBOARD_COL_SIZE.getNum()];
        for (int i = 0; i < kong.length; i++) {
            kong[i]=-1;
        }
        for (int i = 0; i < kong.length; i++) {
            sta=true;
            for (int j = Constant.CHESSBOARD_ROW_SIZE.getNum()-1; j >=0 ; j--) {
                if(sta&&(chessboard.getGrid()[j][i]==nullcell||chessboard.getGrid()[j][i].getPiece()==nullcell.getPiece())){
                    kong[i]=j+1;
                    sta=false;
                }
            }
            
        }
        
        
        Timer timer2=new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                int it=0;
                for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
                    for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                        if(kong[j]!=-1&&i<kong[j]){
                            chessboard.getGrid()[i][j].setPiece(new ChessPiece( Util.RandomPick(new String[]{"💎", "⚪", "▲", "🔶"})));
                            view.getGridComponents()[i][j].removeAll();
                            //if(isState)fallmovietry(view,view.getGridComponents()[j][i],model.getGrid()[j][i]);
                            //ChessComponent[] chessComponents=new ChessComponent[];
                            view.getGridComponents()[i][j].add(new ChessComponent(ONE_CHESS_SIZE, chessboard.getGrid()[i][j].getPiece()));
                        }
                    }
                    view.repaint();


                }
                it++;
                if(it>=Constant.CHESSBOARD_ROW_SIZE.getNum()){timer2.cancel();}
            }
        }, 0, 1000);
    }
    




    //如果能match则true,不能match则false//如果棋盘上有null也false
    public boolean checkPossibleMatch(Chessboard chessboard){
        for (int i = Constant.CHESSBOARD_COL_SIZE.getNum()-1; i >=0 ; i--) {
            for (int j = Constant.CHESSBOARD_ROW_SIZE.getNum()-1; j >=0 ; j--) {
                if(model.getGrid()[j][i]==nullcell||model.getGrid()[j][i].getPiece()==nullcell.getPiece()) {
                    return false;
                }}}

        Cell[][] grid2=new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()+4][Constant.CHESSBOARD_COL_SIZE.getNum()+4];
//        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
//            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
//                model.setCount(i,j,0);
//            }
//        }
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum()+4; i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum()+4; j++) {
                if(i<=1||i>=Constant.CHESSBOARD_ROW_SIZE.getNum()+2||j<=1||j>=Constant.CHESSBOARD_COL_SIZE.getNum()+2){
                    grid2[i][j]=nullcell;}else{grid2[i][j]= chessboard.getGrid()[i-2][j-2];}
            }
        }

        for (int i = 2; i < Constant.CHESSBOARD_ROW_SIZE.getNum()+2; i++) {
            for (int j = 2; j < Constant.CHESSBOARD_COL_SIZE.getNum()+2; j++) {
                if(grid2[i][j].getPiece().equals(grid2[i][j+1].getPiece())){
                    if(grid2[i][j].getPiece().equals(grid2[i][j+3].getPiece())||grid2[i][j].getPiece().equals(grid2[i][j-2].getPiece())||grid2[i][j].getPiece().equals(grid2[i-1][j-1].getPiece())||grid2[i][j].getPiece().equals(grid2[i+1][j-1].getPiece())||grid2[i][j].getPiece().equals(grid2[i-1][j+2].getPiece())||grid2[i][j].getPiece().equals(grid2[i-1][j+2].getPiece())){
                      return true;
                    }
                }
                if(grid2[i][j].getPiece().equals(grid2[i+1][j].getPiece())){
                    if(grid2[i][j].getPiece().equals(grid2[i+3][j].getPiece())||grid2[i][j].getPiece().equals(grid2[i-2][j].getPiece())||grid2[i][j].getPiece().equals(grid2[i-1][j-1].getPiece())||grid2[i][j].getPiece().equals(grid2[i-1][j+1].getPiece())||grid2[i][j].getPiece().equals(grid2[i+2][j-1].getPiece())||grid2[i][j].getPiece().equals(grid2[i+2][j-1].getPiece())){
                        return true;
                    }
                }
                //|| grid2[i][j]==grid2[i-1][j]|| grid2[i][j]==grid2[i+1][j]||grid2[i][j]==grid2[i][j-1])
            }
        }
        return false;
    }

    public boolean checkSteps(int steps){
        if(steps==0){
            return false;
        }
        return true;
    }

    public void judgeResult(){
        if(view.getTotalscore()>=view.getScoregoal()){
            System.out.println("donedone");
            return;

        }else{
            System.out.println("llll");
            //next stage
        }
    }

    //如果棋盘上存在没有棋子的cell则false
    public boolean checkBoardNull(Chessboard chessboard){
        for (int i = Constant.CHESSBOARD_COL_SIZE.getNum()-1; i >=0 ; i--) {
            for (int j = Constant.CHESSBOARD_ROW_SIZE.getNum()-1; j >=0 ; j--) {
                if(chessboard.getGrid()[j][i]==nullcell||chessboard.getGrid()[j][i].getPiece()==nullcell.getPiece()) {
                    return false;
                }}}
        return true;
    }
    //如果steps小于等于零则false
    public  boolean checkStepGoneThenOver(ChessboardComponent component){

        if(component.getSteps()<=0){
            state=false;
            JFrame choicepanel= new JFrame();
            choicepanel.setLayout(null);
            choicepanel.setLocationRelativeTo(null);
            choicepanel.setSize(300,180);
            //JLabel label=new JLabel("🤡YOU LOSE🤡");
            JLabel label=new JLabel("steps used up💦💦💦");
            label.setBounds(90,0,300,70);
            Button button1=new Button("Do it again");
            button1.setBounds(20,80,130,70);
            Button button2=new Button("back to menu");
            button2.setBounds(150,80,130,70);
            button1.addActionListener((e)->{
                    choicepanel.dispose();
                    chessGameFrame.dispose();
                    ChessGameFrame mainFrame = new ChessGameFrame(1100, 810,chessGameFrame.getSTAGE());
                    GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard(0),stageSelectFrame);
                    gameController.setChessGameFrame(mainFrame);
                    mainFrame.setVisible(true);
            });
            button2.addActionListener((e)->{
                choicepanel.dispose();
                chessGameFrame.dispose();
            });
            //choicepanel.setLayout(new GridLayout(2,1));
            choicepanel.add(button1);choicepanel.add(button2);choicepanel.add(label);
            choicepanel.setVisible(true);
            return false;}
        return true;
    }
    //如果总分超过目标分数，则true
    public boolean checkScoreEnoughThenOver(int total,int score){
        if(total>=score){
            state=false;
            JFrame choicepanel= new JFrame();
            choicepanel.setLayout(null);
            choicepanel.setLocationRelativeTo(null);
            choicepanel.setSize(300,180);
            JLabel label=new JLabel("🎉Congratulations🎉");
            label.setBounds(90,0,300,70);
            Button button1=new Button("next stage");
            button1.setBounds(20,80,130,70);
            Button button2=new Button("back to menu");
            button2.setBounds(150,80,130,70);
            button1.addActionListener((e)->{
                choicepanel.dispose();
                chessGameFrame.dispose();
                ChessGameFrame mainFrame = new ChessGameFrame(1100, 810,chessGameFrame.getSTAGE()+1);
                GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard(0),stageSelectFrame);
                gameController.setChessGameFrame(mainFrame);
                mainFrame.setVisible(true);

            });
            button2.addActionListener((e)->{
                choicepanel.dispose();
                chessGameFrame.dispose();
            });
            //choicepanel.setLayout(new GridLayout(2,1));
            choicepanel.add(button1);choicepanel.add(button2);choicepanel.add(label);
            choicepanel.setVisible(true);
            if(chessGameFrame.getSTAGE()==stageSelectFrame.getOnStage()){
            stageSelectFrame.setOnStage(stageSelectFrame.getOnStage()+1);}
            stageSelectFrame.replacebutton(stageSelectFrame.label1,stageSelectFrame.getNullbuttons().get(stageSelectFrame.getOnStage()-1),stageSelectFrame.getButtons().get(stageSelectFrame.getOnStage()-1));
            return true;

        }
        return false;
    }

    public void boom(){
        if(!state){return;}
        if(statemovie){return;}
        if(!checkBoardNull(model)){JOptionPane.showMessageDialog(chessGameFrame, "Can't do that.");return;}
        if(check(model)){JOptionPane.showMessageDialog(chessGameFrame, "There are 3-matchs remain.");return;}
        if (selectedPoint==null&&selectedPoint2==null){
            return;
        }
        if(!checkBoardNull(model)){return;}
        if(!checkStepGoneThenOver(view)){return;}
        if (selectedPoint!=null&&selectedPoint2!=null){
            JOptionPane.showMessageDialog(chessGameFrame,"please choose one");
            var point1 = (ChessComponent) view.getGridComponentAt(selectedPoint).getComponent(0);
            point1.setSelected(false);
            point1.repaint();


                var point2=(ChessComponent)view.getGridComponentAt(selectedPoint2).getComponent(0);
                point2.setSelected(false);
                point2.repaint();
            selectedPoint=null;
            selectedPoint2=null;
            return;
        }
        for (int i = selectedPoint.getRow()-1; i < selectedPoint.getRow()+2; i++) {
            for (int j = selectedPoint.getCol()-1; j < selectedPoint.getCol()+2; j++) {
                if(i>=0&&i<CHESSBOARD_ROW_SIZE.getNum()) {
                    if(j>=0&&j<CHESSBOARD_COL_SIZE.getNum()) {
                        model.getGrid()[i][j].removePiece();
                        view.getGridComponents()[i][j].removeAll();
                        view.repaint();
                    }
                }
            }

        }
        view.setBooms(view.getBooms()-1);



    }

    public void boomrow(){
        if(!state){return;}
        if(statemovie){return;}
        if(!checkBoardNull(model)){JOptionPane.showMessageDialog(chessGameFrame, "Can't do that.");return;}
        if(check(model)){JOptionPane.showMessageDialog(chessGameFrame, "There are 3-matchs remain.");return;}
        if (selectedPoint==null&&selectedPoint2==null){
            return;
        }
        if(!checkBoardNull(model)){return;}
        if(!checkStepGoneThenOver(view)){return;}
        if (selectedPoint!=null&&selectedPoint2!=null){
            JOptionPane.showMessageDialog(chessGameFrame,"please choose one");
            var point1 = (ChessComponent) view.getGridComponentAt(selectedPoint).getComponent(0);
            point1.setSelected(false);
            point1.repaint();


            var point2=(ChessComponent)view.getGridComponentAt(selectedPoint2).getComponent(0);
            point2.setSelected(false);
            point2.repaint();
            selectedPoint=null;
            selectedPoint2=null;
            return;
        }
       // for (int i = selectedPoint.getRow()-1; i < selectedPoint.getRow()+2; i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                //if(i>=0&&i<CHESSBOARD_ROW_SIZE.getNum()) {
                    //if(j>=0&&j<CHESSBOARD_COL_SIZE.getNum()) {
                        model.getGrid()[selectedPoint.getRow()][j].removePiece();
                        view.getGridComponents()[selectedPoint.getRow()][j].removeAll();
                        view.repaint();
                   // }
                //}
            }
        view.setrowBooms(view.getrowBooms()-1);
        //}


    }
    public void boomcol(){
        if(!state){return;}
        if(statemovie){return;}
        if(!checkBoardNull(model)){JOptionPane.showMessageDialog(chessGameFrame, "Can't do that.");return;}
        if(check(model)){JOptionPane.showMessageDialog(chessGameFrame, "There are 3-matchs remain.");return;}
        if (selectedPoint==null&&selectedPoint2==null){
            return;
        }
        if(!checkBoardNull(model)){return;}
        if(!checkStepGoneThenOver(view)){return;}
        if (selectedPoint!=null&&selectedPoint2!=null){
            JOptionPane.showMessageDialog(chessGameFrame,"please choose one");
            var point1 = (ChessComponent) view.getGridComponentAt(selectedPoint).getComponent(0);
            point1.setSelected(false);
            point1.repaint();


            var point2=(ChessComponent)view.getGridComponentAt(selectedPoint2).getComponent(0);
            point2.setSelected(false);
            point2.repaint();
            selectedPoint=null;
            selectedPoint2=null;
            return;
        }
        // for (int i = selectedPoint.getRow()-1; i < selectedPoint.getRow()+2; i++) {
        for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
            //if(i>=0&&i<CHESSBOARD_ROW_SIZE.getNum()) {
            //if(j>=0&&j<CHESSBOARD_COL_SIZE.getNum()) {
            model.getGrid()[j][selectedPoint.getCol()].removePiece();
            view.getGridComponents()[j][selectedPoint.getCol()].removeAll();
            view.repaint();
            // }
            //}
        }
        view.setcolBooms(view.getcolBooms()-1);

        //}


    }
//    public void hints(){
//        for (int i = 0; i < ; i++) {
//            for (int j = 0; j < ; j++) {
//
//            }
//        }
//    }
}
