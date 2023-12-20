package chess.castling;

import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;
import static chess.enums.Names.KING;
import static chess.enums.Names.ROOK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import chess.enums.Moves;
import chess.pojo.Piece;
import chess.pojo.Square;
import org.testng.annotations.Test;

public class CastlingTest extends DefaultCastling {

  @Test
  public void castlingTest1() {
    Piece piece = new Piece(KING, WHITE, Square.of(0, 4));
    boolean isPossible = chessBoard.makeMove(piece, Moves.KING_CASTLING_RIGHT);

    assertTrue(isPossible);
    assertFalse(chessBoard.containsPiece(Square.of(0, 4)));
    assertFalse(chessBoard.containsPiece(Square.of(0, 7)));
    assertTrue(chessBoard.containsPiece(new Piece(KING, WHITE, Square.of(0, 6)).setMoveBefore(true)));
    assertTrue(chessBoard.containsPiece(new Piece(ROOK, WHITE, Square.of(0, 5)).setMoveBefore(true)));
    assertEquals(chessBoard.getPieces().size(), pieceList.size());
  }

  @Test
  public void castlingTest2() {
    Piece piece = new Piece(KING, WHITE, Square.of(0, 4));
    boolean isPossible = chessBoard.makeMove(piece, Moves.KING_CASTLING_LEFT);

    assertTrue(isPossible);
    assertFalse(chessBoard.containsPiece(Square.of(0, 4)));
    assertFalse(chessBoard.containsPiece(Square.of(0, 0)));
    assertTrue(chessBoard.containsPiece(new Piece(KING, WHITE, Square.of(0, 1)).setMoveBefore(true)));
    assertTrue(chessBoard.containsPiece(new Piece(ROOK, WHITE, Square.of(0, 2)).setMoveBefore(true)));
    assertEquals(chessBoard.getPieces().size(), pieceList.size());
  }

  @Test
  public void castlingTest3() {
    Piece piece = new Piece(KING, BLACK, Square.of(7, 4));
    chessBoard.changePriority();
    boolean isPossible = chessBoard.makeMove(piece, Moves.KING_CASTLING_RIGHT);

    assertTrue(isPossible);
    assertFalse(chessBoard.containsPiece(Square.of(7, 4)));
    assertFalse(chessBoard.containsPiece(Square.of(7, 7)));
    assertTrue(chessBoard.containsPiece(new Piece(KING, BLACK, Square.of(7, 6)).setMoveBefore(true)));
    assertTrue(chessBoard.containsPiece(new Piece(ROOK, BLACK, Square.of(7, 5)).setMoveBefore(true)));
    assertEquals(chessBoard.getPieces().size(), pieceList.size());
  }

  @Test
  public void castlingTest4() {
    Piece piece = new Piece(KING, BLACK, Square.of(7, 4));
    chessBoard.changePriority();
    boolean isPossible = chessBoard.makeMove(piece, Moves.KING_CASTLING_LEFT);

    assertTrue(isPossible);
    assertFalse(chessBoard.containsPiece(Square.of(7, 4)));
    assertFalse(chessBoard.containsPiece(Square.of(7, 0)));
    assertTrue(chessBoard.containsPiece(new Piece(KING, BLACK, Square.of(7, 1)).setMoveBefore(true)));
    assertTrue(chessBoard.containsPiece(new Piece(ROOK, BLACK, Square.of(7, 2)).setMoveBefore(true)));
    assertEquals(chessBoard.getPieces().size(), pieceList.size());
  }

}
