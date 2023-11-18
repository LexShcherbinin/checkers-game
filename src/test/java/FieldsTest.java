import static chess.enums.Colors.BLACK;

import chess.legacy.ChessBoard;
import chess.Square;
import chess.legacy.PieceHelper;
import chess.legacy.pieces.IPieces;
import chess.legacy.pieces.Rook;
import java.util.List;

public class FieldsTest {

  public static void main(String[] args) {
    List<IPieces> pieceList = List.of(
        new Rook(BLACK, new Square(0, 0)),
        new Rook(BLACK, new Square(0, 7)),
        new Rook(BLACK, new Square(7, 0)),
        new Rook(BLACK, new Square(7, 7))
    );

    ChessBoard chessBoard = new ChessBoard(pieceList);
    System.out.println(chessBoard);
    System.out.println(PieceHelper.getAttackedFieldList(chessBoard, BLACK));
  }

}
