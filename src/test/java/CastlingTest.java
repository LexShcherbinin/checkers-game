import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;

import chess.legacy.ChessBoard;
import chess.Square;
import chess.legacy.pieces.IPieces;
import chess.legacy.pieces.King;
import chess.legacy.pieces.Rook;
import java.util.List;

public class CastlingTest {

  public static void main(String[] args) {
    List<IPieces> pieceList = List.of(
        new King(WHITE, new Square(0, 4)),
        new Rook(WHITE, new Square(0, 0)),
        new Rook(WHITE, new Square(0, 7)),

        new King(BLACK, new Square(7, 4)),
        new Rook(BLACK, new Square(7, 0)),
        new Rook(BLACK, new Square(7, 7))
    );

    ChessBoard chessBoard = new ChessBoard(pieceList);
    System.out.println(chessBoard);

    for (int i = 0; i <= 1; i++) {
      chessBoard.makeMove();
      System.out.println(chessBoard);
    }
  }

}
