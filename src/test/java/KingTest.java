import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;

import chess.legacy.ChessBoard;
import chess.Square;
import chess.legacy.pieces.IPieces;
import chess.legacy.pieces.King;
import chess.legacy.pieces.Queen;
import java.util.List;

public class KingTest {

  public static void main(String[] args) {
    List<IPieces> pieceList = List.of(
        new King(WHITE, new Square(3, 3)),
        new Queen(BLACK, new Square(4, 3)),

//        new King(WHITE, new Coordinates(5, 5)),
        new King(BLACK, new Square(5, 4))
    );

    ChessBoard chessBoard = new ChessBoard(pieceList);
    System.out.println(chessBoard);

    for (int i = 0; i <= 1000; i++) {
      chessBoard.makeMove();
      System.out.println(chessBoard);
    }
  }

}
