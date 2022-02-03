package chess;

import static chess.Colors.*;
import static chess.Pieces.*;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

  List<ChessPiece> pieces;

  public ChessBoard() {
    this.pieces = getDefaultBoard();
  }

  public List<ChessPiece> getDefaultBoard() {
    List<ChessPiece> pieces = new ArrayList<>();

    ChessPiece whiteRook = new ChessPiece()
        .setName(ROOK)
        .setCoordinates(new Coordinates(0, 0))
        .setOnBoard(true)
        .setCanMove(false)
        .setActions(new Actions())
        .setColor(WHITE);

    pieces.add(whiteRook);
    return pieces;
  }

  /**
   * @return  +-------------------------+
   *          | ** ** ** ** ** ** ** ** |
   *          | ** ** ** ** ** ** ** ** |
   *          | ** ** ** ** ** ** ** ** |
   *          | ** ** ** ** ** ** ** ** |
   *          | ** ** ** ** ** ** ** ** |
   *          | ** ** ** ** ** ** ** ** |
   *          | ** ** ** ** ** ** ** ** |
   *          | WR ** ** ** ** ** ** ** |
   *          +-------------------------+
   */
  @Override
  public String toString() {

    String[][] board = new String[][]{
        {"**", "**", "**", "**", "**", "**", "**", "**"},
        {"**", "**", "**", "**", "**", "**", "**", "**"},
        {"**", "**", "**", "**", "**", "**", "**", "**"},
        {"**", "**", "**", "**", "**", "**", "**", "**"},
        {"**", "**", "**", "**", "**", "**", "**", "**"},
        {"**", "**", "**", "**", "**", "**", "**", "**"},
        {"**", "**", "**", "**", "**", "**", "**", "**"},
        {"**", "**", "**", "**", "**", "**", "**", "**"}
    };

    for (ChessPiece piece : pieces) {
      if (piece.onBoard) {
        int vertical = piece.coordinates.vertical;
        int horizontal = piece.coordinates.horizontal;

        board[vertical][horizontal] = piece.toString();
      }
    }

    String result = "+-------------------------+\n| ";

    for (int i = 7; i >= 0; i--) {
      for (int j = 0; j < 8; j++) {
        result = result + board[i][j] + " ";
      }

      if (i == 0) {
        result = result + "|\n";

      } else {
        result = result + "|\n| ";
      }
    }

    return result + "+-------------------------+";
  }
}
