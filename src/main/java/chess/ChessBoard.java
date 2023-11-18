package chess;

import static chess.helpers.TextColor.BLACK;

import chess.enums.Colors;
import chess.enums.GameStatus;
import chess.enums.Moves;
import chess.helpers.PiecesCreator;
import chess.helpers.TextColor;
import chess.pojo.Piece;
import chess.pojo.Square;
import java.util.ArrayList;
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

  private ChessBoard(ChessBoard chessBoard) {
    this.pieces = chessBoard.getPieces();
    this.priority = chessBoard.getPriority();
    this.status = chessBoard.getStatus();
    this.eatPiecesCount = chessBoard.getEatPiecesCount();
    this.stepCount = chessBoard.getStepCount();
    this.lastStep = chessBoard.getLastStep();
  }

  private ChessBoard(List<Piece> pieces) {
    this.pieces = pieces;
    this.priority = Colors.WHITE;
    this.status = GameStatus.IN_PROGRESS;
    this.eatPiecesCount = 0;
    this.stepCount = 0;
    this.lastStep = "";
  }

  private ChessBoard(List<Piece> pieces, Colors priority, GameStatus status) {
    this.pieces = pieces;
    this.priority = priority;
    this.status = status;
    this.eatPiecesCount = 0;
    this.stepCount = 0;
    this.lastStep = "";
  }

  private ChessBoard(List<Piece> pieces, Colors priority, GameStatus status, int eatPiecesCount, int stepCount, String lastStep) {
    this.pieces = pieces;
    this.priority = priority;
    this.status = status;
    this.eatPiecesCount = eatPiecesCount;
    this.stepCount = stepCount;
    this.lastStep = lastStep;
  }

  public static ChessBoard createChessBoard(List<Piece> pieces) {
    return new ChessBoard(pieces);
  }

  public static ChessBoard createChessBoard(List<Piece> pieces, Colors priority, GameStatus status) {
    return new ChessBoard(pieces, priority, status);
  }

  public static ChessBoard createDefaultChessBoard() {
    return new ChessBoard(PiecesCreator.getDefaultPieceList());
  }

  public static ChessBoard createEmptyChessBoard() {
    return new ChessBoard(new ArrayList<>());
  }

  public boolean makeMove(Piece piece, Moves move) {
    return true;
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

  private boolean checkMoveIsPossible(Piece piece, Moves move) {
    return true;
  }

  private boolean checkCastlingIsPossible() {
    return true;
  }

  private void changePriority() {
    stepCount++;

    if (priority == Colors.WHITE) {
      priority = Colors.BLACK;

    } else {
      priority = Colors.WHITE;
    }
  }

  private void giveUp() {
    status = GameStatus.CHECKMATE;
  }

  private void saveLastStep(Piece pieceBefore, Piece pieceAfter) {
    lastStep = String.format(
        "%s[%s -> %s] (Killing pieces = %s, step = %s)",
        pieceBefore, pieceBefore.getSquare(), pieceAfter.getSquare(), eatPiecesCount, stepCount
    );
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
