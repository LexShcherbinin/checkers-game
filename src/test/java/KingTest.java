import static chess.Colors.BLACK;
import static chess.Colors.WHITE;

import chess.ChessBoard;
import chess.Coordinates;
import chess.pieces.BlackPawn;
import chess.pieces.IPieces;
import chess.pieces.King;
import chess.pieces.WhitePawn;
import java.util.List;

public class KingTest {

  public static void main(String[] args) {
    List<IPieces> pieceList = List.of(
        new King(WHITE, new Coordinates(4, 4)),
        new King(BLACK, new Coordinates(4, 3))
    );

    ChessBoard chessBoard = new ChessBoard(pieceList);
    System.out.println(chessBoard);

    for (int i = 0; i <= 1000; i++) {
      chessBoard.makeMove();
      System.out.println(chessBoard);
    }
  }

}
