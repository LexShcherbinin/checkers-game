import static chess.Colors.BLACK;
import static chess.Colors.WHITE;

import chess.ChessBoard;
import chess.Coordinates;
import chess.PieceHelper;
import chess.pieces.Bishop;
import chess.pieces.BlackPawn;
import chess.pieces.IPieces;
import chess.pieces.King;
import chess.pieces.Queen;
import chess.pieces.Rook;
import chess.pieces.WhitePawn;
import java.util.List;

public class FieldsTest {

  public static void main(String[] args) {
    List<IPieces> pieceList = List.of(
        new Rook(BLACK, new Coordinates(0, 0)),
        new Rook(BLACK, new Coordinates(0, 7)),
        new Rook(BLACK, new Coordinates(7, 0)),
        new Rook(BLACK, new Coordinates(7, 7))
    );

    ChessBoard chessBoard = new ChessBoard(pieceList);
    System.out.println(chessBoard);
    System.out.println(PieceHelper.getAttackedFieldList(chessBoard, BLACK));
  }

}
