package chess;

import static chess.TextColor.BLACK;

import chess.enums.Colors;
import chess.enums.GameStatus;
import chess.enums.Moves;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter()
@Setter()
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ChessBoard {

  /**
   * Список фигур на доске.
   */
  private List<Piece> pieces;

  /**
   * Чья очередь ходить.
   */
  private Colors priority;

  /**
   * Статус игры.
   */
  private GameStatus status;

  /**
   * Количество съеденных фигур.
   */
  private int eatPiecesCount;

  /**
   * Количество сделанных шагов.
   */
  private int stepCount;

  /**
   * Последний сделанный ход.
   */
  private String lastStep;

  private ChessBoard(List<Piece> pieces) {
    this.pieces = pieces;
    this.priority = Colors.WHITE;
    this.status = GameStatus.IN_PROGRESS;
    this.eatPiecesCount = 0;
    this.stepCount = 0;
    this.lastStep = "";
  }

  public static ChessBoard createChessBoard(List<Piece> pieces) {
    return new ChessBoard(pieces);
  }

  public static ChessBoard createDefaultChessBoard() {
    return new ChessBoard(PiecesCreator.getDefaultPieceList());
  }

  public void makeMove() {

  }

  public void makeMove(Piece piece, Moves move) {

  }

  public void makeRandomMove() {

  }

  public boolean addPiece(Piece piece) {
    return true;
  }

  public boolean removePiece(Piece piece) {
    return true;
  }

  public boolean clearSquare(Square square) {
    return true;
  }

  public boolean isSquareEmpty(Square square) {
    return true;
  }

  public boolean isPieceOnBoard(Piece piece) {
    return true;
  }

  public boolean isBothKingOnBoard() {
    return true;
  }

  /**
   *  	+---------------------------------+
   * 8	| ♜	♞	♝	♛	♚	♝	♞	♜	|
   * 7	| ♟	♟	♟	♟	♟	♟	♟	♟	|
   * 6	| *		*		*		*		*		*		*		*		|
   * 5	| *		*		*		*		*		*		*		*		|
   * 4	| *		*		*		*		*		*		*		*		|
   * 3	| *		*		*		*		*		*		*		*		|
   * 2	| ♟	♟	♟	♟	♟	♟	♟	♟	|
   * 1	| ♜	♞	♝	♛	♚	♝	♞	♜	|
   *  	+---------------------------------+
   *  		A		B		C		D		E		F		G		H
   */
  @Override
  public String toString() {

    String[][] board = new String[8][8];

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
          board[i][j] = TextColor.WHITE_BRIGHT + "*\t" + TextColor.RESET;

        } else {
          board[i][j] = BLACK + "*\t" + TextColor.RESET;
        }

      }
    }

    for (Piece piece : pieces) {
      int vertical = piece.getSquare().getVertical();
      int horizontal = piece.getSquare().getHorizontal();

      board[vertical][horizontal] = piece.toString();
    }

    StringBuilder result = new StringBuilder(" \t+---------------------------------+\n");

    for (int i = 7; i >= 0; i--) {
      StringBuilder row = new StringBuilder();

      for (int j = 0; j < 8; j++) {
        row.append(board[i][j]).append("\t");
      }

      switch (i) {
        case 7 -> result.append("8\t| ").append(row).append("|\n");
        case 6 -> result.append("7\t| ").append(row).append("|\n");
        case 5 -> result.append("6\t| ").append(row).append("|\n");
        case 4 -> result.append("5\t| ").append(row).append("|\n");
        case 3 -> result.append("4\t| ").append(row).append("|\n");
        case 2 -> result.append("3\t| ").append(row).append("|\n");
        case 1 -> result.append("2\t| ").append(row).append("|\n");
        case 0 -> result.append("1\t| ").append(row).append("|\n");
      }
    }

    return result +
        " \t+---------------------------------+" + "\n" +
        " \t\tA\t\tB\t\tC\t\tD\t\tE\t\tF\t\tG\t\tH  \n";
  }

}
