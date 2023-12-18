package chess.defaultboard;

import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;
import static chess.enums.Names.KING;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import chess.enums.Moves;
import chess.pojo.Piece;
import chess.pojo.Square;
import org.testng.annotations.Test;

public class KingTest extends DefaultBoard {

  @Test(description = "Белый король не может сделать ход", dataProvider = "allMoves")
  public void queenTest1(Moves move) {
    Piece piece = new Piece(KING, WHITE, Square.of(0, 4));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, move);
    assertFalse(isPossible);
  }

  @Test(description = "Чёрный король не может сделать ход", dataProvider = "allMoves")
  public void queenTest3(Moves move) {
    Piece piece = new Piece(KING, BLACK, Square.of(7, 4));
    assertTrue(chessBoard.containsPiece(piece));

    boolean isPossible = chessBoard.makeMove(piece, move);
    assertFalse(isPossible);
  }

}
