package chess.castling;

import chess.ChessBoard;
import chess.enums.Colors;
import chess.enums.Names;
import chess.helpers.PiecesCreator;
import chess.pojo.Piece;
import chess.pojo.Square;
import java.util.List;
import org.testng.annotations.BeforeMethod;

public class DefaultCastling {

  List<Piece> pieceList;
  ChessBoard chessBoard;

  @BeforeMethod(description = "Расстановка фигур по умолчанию")
  public void prepare() {
    pieceList = List.of(
        new Piece(Names.ROOK, Colors.WHITE, Square.of(0, 0)),
        new Piece(Names.ROOK, Colors.WHITE, Square.of(0, 7)),
        new Piece(Names.ROOK, Colors.BLACK, Square.of(7, 0)),
        new Piece(Names.ROOK, Colors.BLACK, Square.of(7, 7)),

        new Piece(Names.KING, Colors.WHITE, Square.of(0, 4)),
        new Piece(Names.KING, Colors.BLACK, Square.of(7, 4))
    );

    chessBoard = ChessBoard.createChessBoard(pieceList);
  }

}
