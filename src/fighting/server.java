package fighting;

import controller.GameController;
import model.Chessboard;

public class server {
    public static void main(String[] args) {
        serverboard serverboard0=new serverboard(2000,810);
        GameController gameController1 = new GameController(serverboard0.getChessboardComponent1(), new Chessboard(0),null);
        //gameController1.setChessGameFrame(serverboard0);
        GameController gameController2 = new GameController(serverboard0.getChessboardComponent2(), new Chessboard(0),null);
        //gameController2.setChessGameFrame(serverboard0);
    }
}
