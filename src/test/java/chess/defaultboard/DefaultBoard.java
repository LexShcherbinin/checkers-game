package chess.defaultboard;

import static chess.pojo.Piece.BISHOP_LIST;
import static chess.pojo.Piece.KING_LIST;
import static chess.pojo.Piece.KNIGHT_LIST;
import static chess.pojo.Piece.PAWN_BLACK_LIST;
import static chess.pojo.Piece.PAWN_WHITE_LIST;
import static chess.pojo.Piece.ROOK_LIST;

import chess.ChessBoard;
import chess.helpers.PiecesCreator;
import chess.pojo.Piece;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class DefaultBoard {

  List<Piece> pieceList;
  ChessBoard chessBoard;

  @DataProvider(name = "square")
  public static Object[][] square() {
    return new Object[][] {
        {0},
        {1},
        {2},
        {3},
        {4},
        {5},
        {6},
        {7},
    };
  }

  @DataProvider(name = "allMoves")
  public static Object[][] allMoves() {
    return Stream.of(PAWN_WHITE_LIST, PAWN_BLACK_LIST, ROOK_LIST, KNIGHT_LIST, KING_LIST, BISHOP_LIST)
        .flatMap(Collection::stream)
        .map(value -> Arrays.stream(new Object[] {value}).toArray())
        .toArray(Object[][]::new);
  }

  @BeforeMethod(description = "Расстановка фигур по умолчанию")
  public void prepare() {
    pieceList = PiecesCreator.getDefaultPieceList();
    chessBoard = ChessBoard.createChessBoard(pieceList);
  }

}
