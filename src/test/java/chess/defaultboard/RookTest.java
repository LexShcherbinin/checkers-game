package chess.defaultboard;

import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;
import static chess.enums.Names.ROOK;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import chess.enums.Moves;
import chess.pojo.Piece;
import chess.pojo.Square;
import org.testng.annotations.Test;

public class RookTest extends DefaultBoard {

  @Test(description = "Белая левая ладья не может сделать ход", dataProvider = "allMoves")
  public void rookTest1(Moves move) {
    Piece piece = new Piece(ROOK, WHITE, Square.of(0, 0));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, move);
    assertFalse(isPossible);
  }

  @Test(description = "Белая правая ладья не может сделать ход", dataProvider = "allMoves")
  public void rookTest2(Moves move) {
    Piece piece = new Piece(ROOK, WHITE, Square.of(0, 7));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, move);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрная левая ладья не может сделать ход", dataProvider = "allMoves")
  public void rookTest3(Moves move) {
    Piece piece = new Piece(ROOK, BLACK, Square.of(7, 0));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, move);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрная правая ладья не может сделать ход", dataProvider = "allMoves")
  public void rookTest4(Moves move) {
    Piece piece = new Piece(ROOK, BLACK, Square.of(7, 7));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, move);
    assertFalse(isPossible);
  }

}
