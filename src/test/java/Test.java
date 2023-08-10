import chess.ChessBoard;
import chess.pieces.IPieces;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Test {

  public static void main(String[] args) {
    ChessBoard chessBoard = new ChessBoard();
    System.out.println(chessBoard);

//    for (int i = 0; i <= 1000; i++) {
//      chessBoard.makeMove();
//      System.out.println(chessBoard);
//    }

//    while (true) {
//      chessBoard.makeMove();
//      System.out.println(chessBoard);
//    }

    for (int i = 0; i <= 3; i++) {
      Map<IPieces, List<Function<IPieces, IPieces>>> moveMap = chessBoard.getMoveMap();
      ChessBoard newChessBoard = new ChessBoard();

      for (IPieces piece : moveMap.keySet()) {
        for (Function<IPieces, IPieces> action : moveMap.get(piece)) {
          newChessBoard = new ChessBoard();

          newChessBoard.makeMove(piece, action);
          System.out.println(newChessBoard);
          System.out.println("<====================================================================================================>");
        }
      }

      chessBoard = newChessBoard;
    }

  }

}
