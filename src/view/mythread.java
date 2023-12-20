package view;

import controller.GameController;

public class mythread extends Thread{
    GameController gameController;
    public mythread(GameController gameController) {
        this.gameController=gameController;
    }

    @Override
    public void run() {
        gameController.onPlayerNextStep();

    }

    public boolean st(){
        if(!gameController.check(gameController.getModel())&&!gameController.isStatemovie()&&gameController.checkBoardNull(gameController.getModel())){return false;}
        return true;
    }

}
