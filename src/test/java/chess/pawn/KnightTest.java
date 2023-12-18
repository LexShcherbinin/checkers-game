package chess.pawn;

import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;
import static chess.enums.Names.KNIGHT;
import static org.testng.Assert.assertTrue;

import chess.ChessBoard;
import chess.enums.Moves;
import chess.helpers.PiecesCreator;
import chess.pojo.Piece;
import chess.pojo.Square;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class KnightTest {

  List<Piece> pieceList;
  ChessBoard chessBoard;

  @BeforeMethod(description = "Расстановка фигур по умолчанию")
  public void prepare() {
    pieceList = PiecesCreator.getDefaultPieceList();
    chessBoard = ChessBoard.createChessBoard(pieceList);
  }

  @Test(description = "Белый левый конь может сделать ход 1")
  public void knightTest1() {
    Piece piece = new Piece(KNIGHT, WHITE, Square.of(0, 1));

    boolean containsOnBoard = chessBoard.containsPiece(piece);
    boolean isPossible = chessBoard.makeMove(piece, Moves.KNIGHT_1);
    System.out.println();

    assertTrue(containsOnBoard && isPossible);
  }

  @Test(description = "Белый левый конь может сделать ход 8")
  public void knightTest2() {
    Piece piece = new Piece(KNIGHT, WHITE, Square.of(0, 1));

    boolean containsOnBoard = chessBoard.containsPiece(piece);
    boolean isPossible = chessBoard.makeMove(piece, Moves.KNIGHT_8);
    System.out.println();

    assertTrue(containsOnBoard && isPossible);
  }

  @Test(description = "Белый левый конь не может сделать ход")
  public void knightTest3() {
    List<Moves> movesList = Stream.of(
            Moves.KNIGHT_2,
            Moves.KNIGHT_3,
            Moves.KNIGHT_4,
            Moves.KNIGHT_5,
            Moves.KNIGHT_6,
            Moves.KNIGHT_7
        )
        .collect(Collectors.toList());

    SoftAssert softAssert = new SoftAssert();
    Piece piece = new Piece(KNIGHT, WHITE, Square.of(0, 1));

    for (Moves move : movesList) {
      boolean containsOnBoard = chessBoard.containsPiece(piece);
      boolean isPossible = chessBoard.makeMove(piece, move);

      softAssert.assertTrue(containsOnBoard && !isPossible);
    }

    softAssert.assertAll();
  }

  @Test(description = "Белый правый конь может сделать ход 1")
  public void knightTest11() {
    Piece piece = new Piece(KNIGHT, WHITE, Square.of(0, 6));

    boolean containsOnBoard = chessBoard.containsPiece(piece);
    boolean isPossible = chessBoard.makeMove(piece, Moves.KNIGHT_1);
    System.out.println();

    assertTrue(containsOnBoard && isPossible);
  }

  @Test(description = "Белый правый конь может сделать ход 8")
  public void knightTest12() {
    Piece piece = new Piece(KNIGHT, WHITE, Square.of(0, 6));

    boolean containsOnBoard = chessBoard.containsPiece(piece);
    boolean isPossible = chessBoard.makeMove(piece, Moves.KNIGHT_8);
    System.out.println();

    assertTrue(containsOnBoard && isPossible);
  }

  @Test(description = "Белый правый конь не может сделать ход")
  public void knightTest13() {
    List<Moves> movesList = Stream.of(
            Moves.KNIGHT_2,
            Moves.KNIGHT_3,
            Moves.KNIGHT_4,
            Moves.KNIGHT_5,
            Moves.KNIGHT_6,
            Moves.KNIGHT_7
        )
        .collect(Collectors.toList());

    SoftAssert softAssert = new SoftAssert();
    Piece piece = new Piece(KNIGHT, WHITE, Square.of(0, 6));

    for (Moves move : movesList) {
      boolean containsOnBoard = chessBoard.containsPiece(piece);
      boolean isPossible = chessBoard.makeMove(piece, move);

      softAssert.assertTrue(containsOnBoard && !isPossible);
    }

    softAssert.assertAll();
  }

  @Test(description = "Чёрный левый конь может сделать ход 4")
  public void knightTest21() {
    Piece piece = new Piece(KNIGHT, BLACK, Square.of(7, 1));

    boolean containsOnBoard = chessBoard.containsPiece(piece);
    boolean isPossible = chessBoard.makeMove(piece, Moves.KNIGHT_4);
    System.out.println();

    assertTrue(containsOnBoard && isPossible);
  }

  @Test(description = "Чёрный левый конь может сделать ход 5")
  public void knightTest22() {
    Piece piece = new Piece(KNIGHT, BLACK, Square.of(7, 1));

    boolean containsOnBoard = chessBoard.containsPiece(piece);
    boolean isPossible = chessBoard.makeMove(piece, Moves.KNIGHT_5);
    System.out.println();

    assertTrue(containsOnBoard && isPossible);
  }

  @Test(description = "Чёрный левый конь не может сделать ход")
  public void knightTest23() {
    List<Moves> movesList = Stream.of(
            Moves.KNIGHT_1,
            Moves.KNIGHT_2,
            Moves.KNIGHT_3,
            Moves.KNIGHT_6,
            Moves.KNIGHT_7,
            Moves.KNIGHT_8
        )
        .collect(Collectors.toList());

    SoftAssert softAssert = new SoftAssert();
    Piece piece = new Piece(KNIGHT, BLACK, Square.of(7, 1));

    for (Moves move : movesList) {
      boolean containsOnBoard = chessBoard.containsPiece(piece);
      boolean isPossible = chessBoard.makeMove(piece, move);

      softAssert.assertTrue(containsOnBoard && !isPossible);
    }

    softAssert.assertAll();
  }

  @Test(description = "Чёрный правый конь может сделать ход 4")
  public void knightTest31() {
    Piece piece = new Piece(KNIGHT, BLACK, Square.of(7, 6));

    boolean containsOnBoard = chessBoard.containsPiece(piece);
    boolean isPossible = chessBoard.makeMove(piece, Moves.KNIGHT_4);
    System.out.println();

    assertTrue(containsOnBoard && isPossible);
  }

  @Test(description = "Чёрный правый конь может сделать ход 5")
  public void knightTest32() {
    Piece piece = new Piece(KNIGHT, BLACK, Square.of(7, 6));

    boolean containsOnBoard = chessBoard.containsPiece(piece);
    boolean isPossible = chessBoard.makeMove(piece, Moves.KNIGHT_5);
    System.out.println();

    assertTrue(containsOnBoard && isPossible);
  }

  @Test(description = "Чёрный правый конь не может сделать ход")
  public void knightTest33() {
    List<Moves> movesList = Stream.of(
            Moves.KNIGHT_1,
            Moves.KNIGHT_2,
            Moves.KNIGHT_3,
            Moves.KNIGHT_6,
            Moves.KNIGHT_7,
            Moves.KNIGHT_8
        )
        .collect(Collectors.toList());

    SoftAssert softAssert = new SoftAssert();
    Piece piece = new Piece(KNIGHT, BLACK, Square.of(7, 6));

    for (Moves move : movesList) {
      boolean containsOnBoard = chessBoard.containsPiece(piece);
      boolean isPossible = chessBoard.makeMove(piece, move);

      softAssert.assertTrue(containsOnBoard && !isPossible);
    }

    softAssert.assertAll();
  }


}
