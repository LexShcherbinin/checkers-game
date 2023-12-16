package chess.pawn;

import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;
import static chess.enums.Names.PAWN;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import chess.ChessBoard;
import chess.enums.Moves;
import chess.helpers.PiecesCreator;
import chess.pojo.Piece;
import chess.pojo.Square;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

  @BeforeMethod
  public void prepare() {
    pieceList = PiecesCreator.getDefaultPieceList();
    chessBoard = ChessBoard.createChessBoard(pieceList);
  }

  @Test(description = "Белая пешка ходит на одну клетку вперёд", dataProvider = "square")
  public void pawnTest1(int square) {
    boolean isPossible = chessBoard.makeMove(new Piece(PAWN, WHITE, Square.of(1, square)), Moves.PAWN_WHITE_UP_1);

    assertTrue(isPossible);
    assertFalse(chessBoard.containsPiece(Square.of(1, square)));
    assertTrue(chessBoard.containsPiece(Square.of(2, square)));
    assertEquals(chessBoard.getPieces().size(), pieceList.size());
  }

  @Test(description = "Белая пешка ходит на две клетки вперёд", dataProvider = "square")
  public void pawnTest2(int square) {
    boolean isPossible = chessBoard.makeMove(new Piece(PAWN, WHITE, Square.of(1, square)), Moves.PAWN_WHITE_UP_2);

    assertTrue(isPossible);
    assertFalse(chessBoard.containsPiece(Square.of(1, square)));
    assertTrue(chessBoard.containsPiece(Square.of(3, square)));
    assertEquals(chessBoard.getPieces().size(), pieceList.size());
  }

  @Test(description = "Чёрная пешка ходит на одну клетку вперёд", dataProvider = "square")
  public void pawnTest3(int square) {
    boolean isPossible = chessBoard.makeMove(new Piece(PAWN, BLACK, Square.of(6, square)), Moves.PAWN_BLACK_DOWN_1);

    assertTrue(isPossible);
    assertFalse(chessBoard.containsPiece(Square.of(6, square)));
    assertTrue(chessBoard.containsPiece(Square.of(5, square)));
    assertEquals(chessBoard.getPieces().size(), pieceList.size());
  }

  @Test(description = "Чёрная пешка ходит на две клетки вперёд", dataProvider = "square")
  public void pawnTest4(int square) {
    boolean isPossible = chessBoard.makeMove(new Piece(PAWN, BLACK, Square.of(6, square)), Moves.PAWN_BLACK_DOWN_2);

    assertTrue(isPossible);
    assertFalse(chessBoard.containsPiece(Square.of(6, square)));
    assertTrue(chessBoard.containsPiece(Square.of(4, square)));
    assertEquals(chessBoard.getPieces().size(), pieceList.size());
  }

  @Test(description = "Белая пешка ходит на одну клетку назад", dataProvider = "square")
  public void pawnTest5(int square) {
    boolean isPossible = chessBoard.makeMove(new Piece(PAWN, WHITE, Square.of(1, square)), Moves.PAWN_BLACK_DOWN_1);

    assertFalse(isPossible);
  }

  @Test(description = "Белая пешка ходит на две клетки назад", dataProvider = "square")
  public void pawnTest6(int square) {
    boolean isPossible = chessBoard.makeMove(new Piece(PAWN, WHITE, Square.of(1, square)), Moves.PAWN_BLACK_DOWN_2);

    assertFalse(isPossible);
  }

  @Test(description = "Чёрная пешка ходит на одну клетку назад", dataProvider = "square")
  public void pawnTest7(int square) {
    boolean isPossible = chessBoard.makeMove(new Piece(PAWN, BLACK, Square.of(6, square)), Moves.PAWN_WHITE_UP_1);

    assertFalse(isPossible);
  }

  @Test(description = "Чёрная пешка ходит на две клетки назад", dataProvider = "square")
  public void pawnTest8(int square) {
    boolean isPossible = chessBoard.makeMove(new Piece(PAWN, BLACK, Square.of(6, square)), Moves.PAWN_WHITE_UP_2);

    assertFalse(isPossible);
  }

}
