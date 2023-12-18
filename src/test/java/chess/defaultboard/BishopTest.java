package chess.defaultboard;

import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;
import static chess.enums.Names.BISHOP;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import chess.enums.Moves;
import chess.pojo.Piece;
import chess.pojo.Square;
import org.testng.annotations.Test;

public class BishopTest extends DefaultBoard {

  @Test(description = "Белый левый слон не может сделать ход", dataProvider = "allMoves")
  public void bishopTest1(Moves move) {
    Piece piece = new Piece(BISHOP, WHITE, Square.of(0, 2));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, move);
    assertFalse(isPossible);
  }

  @Test(description = "Белый правый слон не может сделать ход", dataProvider = "allMoves")
  public void bishopTest2(Moves move) {
    Piece piece = new Piece(BISHOP, WHITE, Square.of(0, 5));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, move);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрный левый слон не может сделать ход", dataProvider = "allMoves")
  public void bishopTest3(Moves move) {
    Piece piece = new Piece(BISHOP, BLACK, Square.of(7, 2));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, move);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрный правый слон не может сделать ход", dataProvider = "allMoves")
  public void bishopTest4(Moves move) {
    Piece piece = new Piece(BISHOP, BLACK, Square.of(7, 5));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, move);
    assertFalse(isPossible);
  }

}
