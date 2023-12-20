package GameTool;

import model.ChessPiece;
import model.Chessboard;
import model.Constant;
import model.Util;
import view.ChessComponent;
import view.ChessboardComponent;

public class Reset {
    public void RestChessBoard(int chesssize, Chessboard chessboard, ChessboardComponent view){
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                //chessboard.getGrid()[i][i].removePiece();
                chessboard.getGrid()[i][j].setPiece(new ChessPiece( Util.RandomPick(new String[]{"💎", "⚪", "▲", "🔶"})));
                view.getGridComponents()[i][j].removeAll();
                view.getGridComponents()[i][j].add(new ChessComponent(chesssize, chessboard.getGrid()[i][j].getPiece()));

            }
        }
    }
}
