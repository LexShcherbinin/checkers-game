import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;

import chess.ChessBoard;
import chess.Coordinates;
import chess.pieces.IPieces;
import chess.pieces.King;
import chess.pieces.Rook;
import java.util.List;

public class CastlingTest {

  public static void main(String[] args) {
    List<IPieces> pieceList = List.of(
        new King(WHITE, new Coordinates(0, 4)),
        new Rook(WHITE, new Coordinates(0, 0)),
        new Rook(WHITE, new Coordinates(0, 7)),

        new King(BLACK, new Coordinates(7, 4)),
        new Rook(BLACK, new Coordinates(7, 0)),
        new Rook(BLACK, new Coordinates(7, 7))
    );

    ChessBoard chessBoard = new ChessBoard(pieceList);
    System.out.println(chessBoard);

    for (int i = 0; i <= 1; i++) {
      chessBoard.makeMove();
      System.out.println(chessBoard);
    }
  }

}
