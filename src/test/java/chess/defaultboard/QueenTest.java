package chess.defaultboard;

import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;
import static chess.enums.Names.QUEEN;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import chess.enums.Moves;
import chess.pojo.Piece;
import chess.pojo.Square;
import org.testng.annotations.Test;

public class QueenTest extends DefaultBoard {

  @Test(description = "Белый ферзь не может сделать ход", dataProvider = "allMoves")
  public void queenTest1(Moves move) {
    Piece piece = new Piece(QUEEN, WHITE, Square.of(0, 3));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, move);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрный ферзь не может сделать ход", dataProvider = "allMoves")
  public void queenTest3(Moves move) {
    Piece piece = new Piece(QUEEN, BLACK, Square.of(7, 3));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, move);
    assertFalse(isPossible);
  }

}
