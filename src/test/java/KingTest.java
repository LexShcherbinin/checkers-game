import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;

import chess.ChessBoard;
import chess.Coordinates;
import chess.pieces.IPieces;
import chess.pieces.King;
import chess.pieces.Queen;
import java.util.List;

public class KingTest {

  public static void main(String[] args) {
    List<IPieces> pieceList = List.of(
        new King(WHITE, new Coordinates(3, 3)),
        new Queen(BLACK, new Coordinates(4, 3)),

//        new King(WHITE, new Coordinates(5, 5)),
        new King(BLACK, new Coordinates(5, 4))
    );

    ChessBoard chessBoard = new ChessBoard(pieceList);
    System.out.println(chessBoard);

    for (int i = 0; i <= 1000; i++) {
      chessBoard.makeMove();
      System.out.println(chessBoard);
    }
  }

}
