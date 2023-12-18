package chess.pawn;

import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;
import static chess.enums.Names.BISHOP;
import static chess.pojo.Piece.BISHOP_LIST;
import static chess.pojo.Piece.KING_LIST;
import static chess.pojo.Piece.KNIGHT_LIST;
import static chess.pojo.Piece.PAWN_BLACK_LIST;
import static chess.pojo.Piece.PAWN_WHITE_LIST;
import static chess.pojo.Piece.ROOK_LIST;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import chess.ChessBoard;
import chess.enums.Moves;
import chess.helpers.PiecesCreator;
import chess.pojo.Piece;
import chess.pojo.Square;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BishopTest {

  List<Piece> pieceList;
  ChessBoard chessBoard;

  @DataProvider(name = "square")
  public static Object[][] square() {
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

  @Test(description = "Белый левый слон не может сделать ход", dataProvider = "square")
  public void bishopTest1(Moves move) {
    Piece piece = new Piece(BISHOP, WHITE, Square.of(0, 2));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, move);
    assertFalse(isPossible);
  }

  @Test(description = "Белый правый слон не может сделать ход", dataProvider = "square")
  public void bishopTest2(Moves move) {
    Piece piece = new Piece(BISHOP, WHITE, Square.of(0, 5));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, move);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрный левый слон не может сделать ход", dataProvider = "square")
  public void bishopTest3(Moves move) {
    Piece piece = new Piece(BISHOP, BLACK, Square.of(7, 2));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, move);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрный правый слон не может сделать ход", dataProvider = "square")
  public void bishopTest4(Moves move) {
    Piece piece = new Piece(BISHOP, BLACK, Square.of(7, 5));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, move);
    assertFalse(isPossible);
  }

}
