package chess.pawn;

import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;
import static chess.enums.Names.PAWN;
import static chess.pojo.Piece.BISHOP_LIST;
import static chess.pojo.Piece.KING_LIST;
import static chess.pojo.Piece.KNIGHT_LIST;
import static chess.pojo.Piece.ROOK_LIST;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import chess.ChessBoard;
import chess.enums.Moves;
import chess.helpers.PiecesCreator;
import chess.pojo.Piece;
import chess.pojo.Square;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PawnTest {

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

  @BeforeMethod(description = "Расстановка фигур по умолчанию")
  public void prepare() {
    pieceList = PiecesCreator.getDefaultPieceList();
    chessBoard = ChessBoard.createChessBoard(pieceList);
  }

  @Test(description = "Белая пешка ходит на одну клетку вперёд", dataProvider = "square")
  public void pawnTest1(int square) {
    Piece piece = new Piece(PAWN, WHITE, Square.of(1, square));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, Moves.PAWN_WHITE_UP_1);
    assertTrue(isPossible);
    assertFalse(chessBoard.containsPiece(Square.of(1, square)));
    assertTrue(chessBoard.containsPiece(Square.of(2, square)));
    assertEquals(chessBoard.getPieces().size(), pieceList.size());
  }

  @Test(description = "Белая пешка ходит на две клетки вперёд", dataProvider = "square")
  public void pawnTest2(int square) {
    Piece piece = new Piece(PAWN, WHITE, Square.of(1, square));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, Moves.PAWN_WHITE_UP_2);
    assertTrue(isPossible);
    assertFalse(chessBoard.containsPiece(Square.of(1, square)));
    assertTrue(chessBoard.containsPiece(Square.of(3, square)));
    assertEquals(chessBoard.getPieces().size(), pieceList.size());
  }

  @Test(description = "Чёрная пешка ходит на одну клетку вперёд", dataProvider = "square")
  public void pawnTest3(int square) {
    Piece piece = new Piece(PAWN, BLACK, Square.of(6, square));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, Moves.PAWN_BLACK_DOWN_1);
    assertTrue(isPossible);
    assertFalse(chessBoard.containsPiece(Square.of(6, square)));
    assertTrue(chessBoard.containsPiece(Square.of(5, square)));
    assertEquals(chessBoard.getPieces().size(), pieceList.size());
  }

  @Test(description = "Чёрная пешка ходит на две клетки вперёд", dataProvider = "square")
  public void pawnTest4(int square) {
    Piece piece = new Piece(PAWN, BLACK, Square.of(6, square));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, Moves.PAWN_BLACK_DOWN_2);
    assertTrue(isPossible);
    assertFalse(chessBoard.containsPiece(Square.of(6, square)));
    assertTrue(chessBoard.containsPiece(Square.of(4, square)));
    assertEquals(chessBoard.getPieces().size(), pieceList.size());
  }

  @Test(description = "Белая пешка ходит на одну клетку назад", dataProvider = "square")
  public void pawnTest5(int square) {
    Piece piece = new Piece(PAWN, WHITE, Square.of(1, square));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, Moves.PAWN_BLACK_DOWN_1);
    assertFalse(isPossible);
  }

  @Test(description = "Белая пешка ходит на две клетки назад", dataProvider = "square")
  public void pawnTest6(int square) {
    Piece piece = new Piece(PAWN, WHITE, Square.of(1, square));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, Moves.PAWN_BLACK_DOWN_2);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрная пешка ходит на одну клетку назад", dataProvider = "square")
  public void pawnTest7(int square) {
    Piece piece = new Piece(PAWN, BLACK, Square.of(6, square));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, Moves.PAWN_WHITE_UP_1);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрная пешка ходит на две клетки назад", dataProvider = "square")
  public void pawnTest8(int square) {
    Piece piece = new Piece(PAWN, BLACK, Square.of(6, square));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, Moves.PAWN_WHITE_UP_2);
    assertFalse(isPossible);
  }

  @Test(description = "Белая пешка ходит на одну клетку вперёд и вправо", dataProvider = "square")
  public void pawnTest11(int square) {
    Piece piece = new Piece(PAWN, WHITE, Square.of(1, square));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, Moves.PAWN_WHITE_UP_RIGHT);
    assertFalse(isPossible);
  }

  @Test(description = "Белая пешка ходит на одну клетку вперёд и влево", dataProvider = "square")
  public void pawnTest12(int square) {
    Piece piece = new Piece(PAWN, WHITE, Square.of(1, square));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, Moves.PAWN_WHITE_UP_LEFT);
    assertFalse(isPossible);
  }

  @Test(description = "Белая пешка ходит на одну клетку назад и вправо", dataProvider = "square")
  public void pawnTest13(int square) {
    Piece piece = new Piece(PAWN, WHITE, Square.of(1, square));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, Moves.PAWN_BLACK_DOWN_RIGHT);
    assertFalse(isPossible);
  }

  @Test(description = "Белая пешка ходит на одну клетку назад и влево", dataProvider = "square")
  public void pawnTest14(int square) {
    Piece piece = new Piece(PAWN, WHITE, Square.of(1, square));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, Moves.PAWN_BLACK_DOWN_LEFT);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрная пешка ходит на одну клетку вперёд и вправо", dataProvider = "square")
  public void pawnTest15(int square) {
    Piece piece = new Piece(PAWN, BLACK, Square.of(6, square));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, Moves.PAWN_BLACK_DOWN_RIGHT);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрная пешка ходит на одну клетку вперёд и влево", dataProvider = "square")
  public void pawnTest16(int square) {
    Piece piece = new Piece(PAWN, BLACK, Square.of(6, square));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, Moves.PAWN_BLACK_DOWN_LEFT);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрная пешка ходит на одну клетку назад и вправо", dataProvider = "square")
  public void pawnTest17(int square) {
    Piece piece = new Piece(PAWN, BLACK, Square.of(6, square));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, Moves.PAWN_WHITE_UP_RIGHT);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрная пешка ходит на одну клетку назад и влево", dataProvider = "square")
  public void pawnTest18(int square) {
    Piece piece = new Piece(PAWN, BLACK, Square.of(6, square));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, Moves.PAWN_WHITE_UP_LEFT);
    assertFalse(isPossible);
  }

  @Test(description = "Белая пешка не может пойти как другая фигура", dataProvider = "square")
  public void pawnTest21(int square) {
    List<Moves> movesList = Stream.of(KNIGHT_LIST, KING_LIST, ROOK_LIST, BISHOP_LIST)
        .flatMap(Collection::stream)
        .collect(Collectors.toList());

    SoftAssert softAssert = new SoftAssert();
    Piece piece = new Piece(PAWN, WHITE, Square.of(1, square));

    for (Moves move : movesList) {
      boolean containsOnBoard = chessBoard.containsPiece(piece);
      boolean isPossible = chessBoard.makeMove(piece, move);

      softAssert.assertTrue(containsOnBoard && !isPossible);
    }

    softAssert.assertAll();
  }

  @Test(description = "Чёрная пешка не может пойти как другая фигура", dataProvider = "square")
  public void pawnTest22(int square) {
    List<Moves> movesList = Stream.of(KNIGHT_LIST, KING_LIST, ROOK_LIST, BISHOP_LIST)
        .flatMap(Collection::stream)
        .collect(Collectors.toList());

    SoftAssert softAssert = new SoftAssert();
    Piece piece = new Piece(PAWN, BLACK, Square.of(6, square));

    for (Moves move : movesList) {
      boolean containsOnBoard = chessBoard.containsPiece(piece);
      boolean isPossible = chessBoard.makeMove(piece, move);

      softAssert.assertTrue(containsOnBoard && !isPossible);
    }

    softAssert.assertAll();
  }

}
