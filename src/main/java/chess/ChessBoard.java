package chess;

import static chess.ChessPiece.createPiece;
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

    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 0)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 1)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 2)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 3)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 4)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 5)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 6)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 7)));

    pieces.add(createPiece(WHITE, ROOK, new Coordinates(0, 0)));
    pieces.add(createPiece(WHITE, KNIGHT, new Coordinates(0, 1)));
    pieces.add(createPiece(WHITE, BISHOP, new Coordinates(0, 2)));
    pieces.add(createPiece(WHITE, QUEEN, new Coordinates(0, 3)));
    pieces.add(createPiece(WHITE, KINGS, new Coordinates(0, 4)));
    pieces.add(createPiece(WHITE, BISHOP, new Coordinates(0, 5)));
    pieces.add(createPiece(WHITE, KNIGHT, new Coordinates(0, 6)));
    pieces.add(createPiece(WHITE, ROOK, new Coordinates(0, 7)));


    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 0)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 1)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 2)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 3)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 4)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 5)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 6)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 7)));

    pieces.add(createPiece(BLACK, ROOK, new Coordinates(7, 0)));
    pieces.add(createPiece(BLACK, KNIGHT, new Coordinates(7, 1)));
    pieces.add(createPiece(BLACK, BISHOP, new Coordinates(7, 2)));
    pieces.add(createPiece(BLACK, QUEEN, new Coordinates(7, 3)));
    pieces.add(createPiece(BLACK, KINGS, new Coordinates(7, 4)));
    pieces.add(createPiece(BLACK, BISHOP, new Coordinates(7, 5)));
    pieces.add(createPiece(BLACK, KNIGHT, new Coordinates(7, 6)));
    pieces.add(createPiece(BLACK, ROOK, new Coordinates(7, 7)));

    return pieces;
  }

  /**
   * @return  +-------------------------+
   *          | BR BH BB BQ BK BB BH BR |
   *          | BP BP BP BP BP BP BP BP |
   *          |  *  *  *  *  *  *  *  * |
   *          |  *  *  *  *  *  *  *  * |
   *          |  *  *  *  *  *  *  *  * |
   *          |  *  *  *  *  *  *  *  * |
   *          | WP WP WP WP WP WP WP WP |
   *          | WR WH WB WQ WK WB WH WR |
   *          +-------------------------+
   */
  @Override
  public String toString() {

    String[][] board = new String[][]{
        {" *", " *", " *", " *", " *", " *", " *", " *"},
        {" *", " *", " *", " *", " *", " *", " *", " *"},
        {" *", " *", " *", " *", " *", " *", " *", " *"},
        {" *", " *", " *", " *", " *", " *", " *", " *"},
        {" *", " *", " *", " *", " *", " *", " *", " *"},
        {" *", " *", " *", " *", " *", " *", " *", " *"},
        {" *", " *", " *", " *", " *", " *", " *", " *"},
        {" *", " *", " *", " *", " *", " *", " *", " *"}
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
