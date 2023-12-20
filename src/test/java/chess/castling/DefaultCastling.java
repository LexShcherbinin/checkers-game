package chess.castling;

import chess.ChessBoard;
import chess.enums.Colors;
import chess.enums.Names;
import chess.pojo.Piece;
import chess.pojo.Square;
import java.util.List;
import org.testng.annotations.BeforeMethod;

public class DefaultCastling {

  List<Piece> pieceList;
  ChessBoard chessBoard;

  Piece whiteRookLeft;
  Piece whiteRookRight;
  Piece blackRookLeft;
  Piece blackRookRight;

  Piece whiteKing;
  Piece blackKing;

  @BeforeMethod(description = "Расстановка фигур по умолчанию")
  public void prepare() {
    whiteRookLeft = new Piece(Names.ROOK, Colors.WHITE, Square.of(0, 0));
    whiteRookRight = new Piece(Names.ROOK, Colors.WHITE, Square.of(0, 7));
    blackRookLeft = new Piece(Names.ROOK, Colors.BLACK, Square.of(7, 0));
    blackRookRight = new Piece(Names.ROOK, Colors.BLACK, Square.of(7, 7));
    whiteKing = new Piece(Names.KING, Colors.WHITE, Square.of(0, 4));
    blackKing = new Piece(Names.KING, Colors.BLACK, Square.of(7, 4));

    pieceList = List.of(
        whiteRookLeft,
        whiteRookRight,
        blackRookLeft,
        blackRookRight,
        whiteKing,
        blackKing
    );

    chessBoard = ChessBoard.createChessBoard(pieceList);
  }

}
