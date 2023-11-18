package chess;

import static chess.TextColor.BLACK;
import static chess.TextColor.GREEN;
import static chess.TextColor.RESET;

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

  private ChessBoard(List<Piece> pieces, Colors priority) {
    this.pieces = pieces;
    this.priority = priority;
    this.status = GameStatus.IN_PROGRESS;
    this.eatPiecesCount = 0;
    this.stepCount = 0;
    this.lastStep = "";
  }

  public static ChessBoard createChessBoard(List<Piece> pieces, Colors priority) {
    return new ChessBoard(pieces, priority);
  }

  public static ChessBoard createDefaultChessBoard() {
    List<Piece> pieces = PiecesCreator.getDefaultPieceList();
    Colors priority = Colors.WHITE;

    return new ChessBoard(pieces, priority);
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
   * @return
   * +-------------------------+
   * | BR BH BB BQ BK BB BH BR |
   * | BP BP BP BP BP BP BP BP |
   * |  *  *  *  *  *  *  *  * |
   * |  *  *  *  *  *  *  *  * |
   * |  *  *  *  *  *  *  *  * |
   * |  *  *  *  *  *  *  *  * |
   * | WP WP WP WP WP WP WP WP |
   * | WR WH WB WQ WK WB WH WR |
   * +-------------------------+
   */
  @Override
  public String toString() {

    String[][] board = new String[8][8];

    for (int i = 0; i < board.length; i ++) {
      for (int j = 0; j < board.length; j ++) {
        if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
          board[i][j] = TextColor.BLACK + "■" + TextColor.RESET;

        } else {
          board[i][j] = TextColor.WHITE + "■" + TextColor.RESET;
        }

      }
    }

    for (Piece piece : pieces) {
      int vertical = piece.getSquare().getVertical();
      int horizontal = piece.getSquare().getHorizontal();

      board[vertical][horizontal] = piece.toString();
    }

    StringBuilder result = new StringBuilder(" \t+-------------------------+\n");

    for (int i = 7; i >= 0; i--) {
      StringBuilder row = new StringBuilder();

      for (int j = 0; j < 8; j++) {
        row.append(board[i][j]).append(" ");
      }

      switch (i) {
//        case 7 -> result += "8\t| " + row + "|\n";
        case 7 -> result.append("8\t| ").append(row).append("| ").append(lastStep).append("\n");
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
        " \t+-------------------------+" + "\n" +
        " \t   A  B  C  D  E  F  G  H  \n";
  }

  public static void main(String[] args) {
    List<Piece> pieceList = PiecesCreator.getDefaultPieceList();
    String[][] board = fillBoard(pieceList);

    printMatrix(board);
  }

  private static String[][] fillBoard(List<Piece> pieceList) {
    String[][] board = new String[8][8];

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
          board[i][j] = TextColor.WHITE + "■\t" + TextColor.RESET;

        } else {
          board[i][j] = BLACK + "■\t" + TextColor.RESET;
        }

      }
    }

    for (Piece piece : pieceList) {
      int vertical = piece.getSquare().getVertical();
      int horizontal = piece.getSquare().getHorizontal();

      board[vertical][horizontal] = piece.toString();
    }

    return board;
  }

  private static void printMatrix(String[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        System.out.print(matrix[i][j] + "\t");
      }
      System.out.println();
    }
  }


}
