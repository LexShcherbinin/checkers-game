package chess.castling;

import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;
import static chess.enums.Names.KING;
import static chess.enums.Names.KNIGHT;
import static chess.enums.Names.QUEEN;
import static chess.enums.Names.ROOK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import chess.enums.Moves;
import chess.pojo.Piece;
import chess.pojo.Square;
import org.testng.annotations.Test;

public class CastlingTest extends DefaultCastling {

  @Test(description = "Белый король, рокировка вправо возможна")
  public void castlingTest1() {
    boolean isPossible = chessBoard.makeMove(whiteKing, Moves.KING_CASTLING_RIGHT);

    assertTrue(isPossible);
    assertFalse(chessBoard.containsPiece(Square.of(0, 4)));
    assertFalse(chessBoard.containsPiece(Square.of(0, 7)));
    assertTrue(chessBoard.containsPiece(new Piece(KING, WHITE, Square.of(0, 6)).setMoveBefore(true)));
    assertTrue(chessBoard.containsPiece(new Piece(ROOK, WHITE, Square.of(0, 5)).setMoveBefore(true)));
    assertEquals(chessBoard.getPieces().size(), pieceList.size());
  }

  @Test(description = "Белый король, рокировка влево возможна")
  public void castlingTest2() {
    boolean isPossible = chessBoard.makeMove(whiteKing, Moves.KING_CASTLING_LEFT);

    assertTrue(isPossible);
    assertFalse(chessBoard.containsPiece(Square.of(0, 4)));
    assertFalse(chessBoard.containsPiece(Square.of(0, 0)));
    assertTrue(chessBoard.containsPiece(new Piece(KING, WHITE, Square.of(0, 1)).setMoveBefore(true)));
    assertTrue(chessBoard.containsPiece(new Piece(ROOK, WHITE, Square.of(0, 2)).setMoveBefore(true)));
    assertEquals(chessBoard.getPieces().size(), pieceList.size());
  }

  @Test(description = "Чёрный король, рокировка вправо возможна")
  public void castlingTest3() {
    chessBoard.setPriority(BLACK);
    boolean isPossible = chessBoard.makeMove(blackKing, Moves.KING_CASTLING_RIGHT);

    assertTrue(isPossible);
    assertFalse(chessBoard.containsPiece(Square.of(7, 4)));
    assertFalse(chessBoard.containsPiece(Square.of(7, 7)));
    assertTrue(chessBoard.containsPiece(new Piece(KING, BLACK, Square.of(7, 6)).setMoveBefore(true)));
    assertTrue(chessBoard.containsPiece(new Piece(ROOK, BLACK, Square.of(7, 5)).setMoveBefore(true)));
    assertEquals(chessBoard.getPieces().size(), pieceList.size());
  }

  @Test(description = "Чёрный король, рокировка влево возможна")
  public void castlingTest4() {
    chessBoard.setPriority(BLACK);
    boolean isPossible = chessBoard.makeMove(blackKing, Moves.KING_CASTLING_LEFT);

    assertTrue(isPossible);
    assertFalse(chessBoard.containsPiece(Square.of(7, 4)));
    assertFalse(chessBoard.containsPiece(Square.of(7, 0)));
    assertTrue(chessBoard.containsPiece(new Piece(KING, BLACK, Square.of(7, 1)).setMoveBefore(true)));
    assertTrue(chessBoard.containsPiece(new Piece(ROOK, BLACK, Square.of(7, 2)).setMoveBefore(true)));
    assertEquals(chessBoard.getPieces().size(), pieceList.size());
  }

  @Test(description = "Белый король, рокировка вправо не возможна, если король уже ходил")
  public void castlingTest11() {
    boolean isPossible = chessBoard.makeMove(whiteKing.setMoveBefore(true), Moves.KING_CASTLING_RIGHT);
    assertFalse(isPossible);
  }

  @Test(description = "Белый король, рокировка влево не возможна, если король уже ходил")
  public void castlingTest12() {
    boolean isPossible = chessBoard.makeMove(whiteKing.setMoveBefore(true), Moves.KING_CASTLING_LEFT);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрный король, рокировка вправо не возможна, если король уже ходил")
  public void castlingTest13() {
    chessBoard.setPriority(BLACK);
    boolean isPossible = chessBoard.makeMove(blackKing.setMoveBefore(true), Moves.KING_CASTLING_RIGHT);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрный король, рокировка влево не возможна, если король уже ходил")
  public void castlingTest14() {
    chessBoard.setPriority(BLACK);
    boolean isPossible = chessBoard.makeMove(blackKing.setMoveBefore(true), Moves.KING_CASTLING_LEFT);
    assertFalse(isPossible);
  }

  @Test(description = "Белый король, рокировка вправо не возможна, если правая ладья уже ходила")
  public void castlingTest21() {
    whiteRookRight.setMoveBefore(true);
    boolean isPossible = chessBoard.makeMove(whiteKing, Moves.KING_CASTLING_RIGHT);
    assertFalse(isPossible);
  }

  @Test(description = "Белый король, рокировка вправо возможна, если левая ладья уже ходила, а правая нет")
  public void castlingTest22() {
    whiteRookLeft.setMoveBefore(true);
    boolean isPossible = chessBoard.makeMove(whiteKing, Moves.KING_CASTLING_RIGHT);
    assertTrue(isPossible);
  }

  @Test(description = "Белый король, рокировка влево не возможна, если левая ладья уже ходила")
  public void castlingTest23() {
    whiteRookLeft.setMoveBefore(true);
    boolean isPossible = chessBoard.makeMove(whiteKing, Moves.KING_CASTLING_LEFT);
    assertFalse(isPossible);
  }

  @Test(description = "Белый король, рокировка влево возможна, если правая ладья уже ходила, а левая нет")
  public void castlingTest24() {
    whiteRookRight.setMoveBefore(true);
    boolean isPossible = chessBoard.makeMove(whiteKing, Moves.KING_CASTLING_LEFT);
    assertTrue(isPossible);
  }

  @Test(description = "Чёрный король, рокировка вправо не возможна, если правая ладья уже ходила")
  public void castlingTest25() {
    blackRookRight.setMoveBefore(true);
    chessBoard.setPriority(BLACK);
    boolean isPossible = chessBoard.makeMove(blackKing, Moves.KING_CASTLING_RIGHT);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрный король, рокировка вправо возможна, если левая ладья уже ходила, а правая нет")
  public void castlingTest26() {
    blackRookLeft.setMoveBefore(true);
    chessBoard.setPriority(BLACK);
    boolean isPossible = chessBoard.makeMove(blackKing, Moves.KING_CASTLING_RIGHT);
    assertTrue(isPossible);
  }

  @Test(description = "Чёрный король, рокировка влево не возможна, если левая ладья уже ходила")
  public void castlingTest27() {
    blackRookLeft.setMoveBefore(true);
    chessBoard.setPriority(BLACK);
    boolean isPossible = chessBoard.makeMove(blackKing, Moves.KING_CASTLING_LEFT);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрный король, рокировка влево возможна, если правая ладья уже ходила, а левая нет")
  public void castlingTest28() {
    blackRookRight.setMoveBefore(true);
    chessBoard.setPriority(BLACK);
    boolean isPossible = chessBoard.makeMove(blackKing, Moves.KING_CASTLING_LEFT);
    assertTrue(isPossible);
  }

  @Test(description = "Белый король, рокировка вправо не возможна, если путь не свободен")
  public void castlingTest31() {
    chessBoard.addPiece(new Piece(QUEEN, WHITE, Square.of(0, 5)));

    boolean isPossible = chessBoard.makeMove(whiteKing, Moves.KING_CASTLING_RIGHT);
    assertFalse(isPossible);
  }

  @Test(description = "Белый король, рокировка влево не возможна, если путь не свободен")
  public void castlingTest32() {
    chessBoard.addPiece(new Piece(KNIGHT, WHITE, Square.of(0, 3)));

    boolean isPossible = chessBoard.makeMove(whiteKing, Moves.KING_CASTLING_LEFT);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрный король, рокировка вправо не возможна, если путь не свободен")
  public void castlingTest33() {
    chessBoard.addPiece(new Piece(QUEEN, BLACK, Square.of(7, 5)));
    chessBoard.setPriority(BLACK);

    boolean isPossible = chessBoard.makeMove(blackKing, Moves.KING_CASTLING_RIGHT);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрный король, рокировка влево не возможна, если путь не свободен")
  public void castlingTest34() {
    chessBoard.addPiece(new Piece(KNIGHT, WHITE, Square.of(7, 3)));
    chessBoard.setPriority(BLACK);

    boolean isPossible = chessBoard.makeMove(blackKing, Moves.KING_CASTLING_LEFT);
    assertFalse(isPossible);
  }

}
