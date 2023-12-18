package chess;

import static chess.common.TextColor.colorize;
import static chess.enums.Colors.WHITE;
import static chess.enums.Names.KING;
import static chess.enums.Names.PAWN;

import chess.enums.Colors;
import chess.enums.GameStatus;
import chess.enums.Moves;
import chess.enums.Names;
import chess.common.TextColor;
import chess.pojo.GameInfo;
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
   * Информация о партии и состоянии игры.
   */
  private GameInfo gameInfo;

  private ChessBoard(ChessBoard chessBoard) {
    this.pieces = new ArrayList<>(chessBoard.getPieces());
    this.priority = chessBoard.getPriority();
    this.status = chessBoard.getStatus();
    this.gameInfo = chessBoard.getGameInfo();
  }

  private ChessBoard(List<Piece> pieces) {
    this.pieces = new ArrayList<>(pieces);
    this.priority = Colors.WHITE;
    this.status = GameStatus.IN_PROGRESS;
    this.gameInfo = GameInfo.newGame();
  }

  private ChessBoard(List<Piece> pieces, Colors priority, GameStatus status) {
    this.pieces = new ArrayList<>(pieces);
    this.priority = priority;
    this.status = status;
    this.gameInfo = GameInfo.newGame();
  }

  public static ChessBoard createChessBoard() {
    return new ChessBoard(new ArrayList<>());
  }

  public static ChessBoard createChessBoard(List<Piece> pieces) {
    return new ChessBoard(pieces);
  }

  public static ChessBoard createChessBoard(List<Piece> pieces, Colors priority, GameStatus status) {
    return new ChessBoard(pieces, priority, status);
  }

  public static ChessBoard copyBoard(ChessBoard chessBoard) {
    return new ChessBoard(chessBoard);
  }

  public boolean addPiece(Piece piece) {
    if (!containsPiece(piece.getSquare())) {
      pieces.add(piece);
      return true;
    }

    return false;
  }

  public boolean removePiece(Piece piece) {
    if (pieces.contains(piece)) {
      pieces.remove(piece);
      return true;
    }

    return false;
  }

  public boolean clearSquare(Square square) {
    Piece piece = pieces.stream()
        .filter(p -> p.getSquare().equals(square))
        .findAny()
        .orElse(null);

    if (piece != null) {
      pieces.remove(piece);
      return true;
    }

    return false;
  }

  public boolean changePiece(Piece piece, Names name) {
    if (containsPiece(piece)) {
      removePiece(piece);
      Piece newPiece = new Piece(name, piece.getColor(), piece.getSquare());
      addPiece(newPiece);

      return true;
    }

    return false;
  }

  public boolean containsPiece(Piece piece) {
    return pieces.contains(piece);
  }

  public boolean containsPiece(Names name, Colors color) {
    return pieces.stream().anyMatch(p -> p.getColor().equals(color) && p.getName().equals(name));
  }

  public boolean containsPiece(Square square) {
    return pieces.stream().anyMatch(p -> p.getSquare().equals(square));
  }

  public boolean haveBothKingOnBoard() {
    return pieces.stream().filter(p -> p.getName().equals(Names.KING)).count() == 2;
  }

  public void giveUp() {
    status = GameStatus.CHECKMATE;
  }

  public void changePriority() {
    priority = priority == Colors.WHITE ? Colors.BLACK : Colors.WHITE;
  }

  //TODO: Доработать данный метод
  public boolean makeMove(Piece piece, Moves move) {
    if (!containsPiece(piece) || !piece.getMoveList().contains(move)) {
      return false;
    }

    CheckPieceMove checkPieceMove = new CheckPieceMove(this, piece, move);

    if (checkPieceMove.checkMoveIsPossible()) {
      Piece after = new Piece(piece);
      move.getMove().move(after);

      if (checkPieceMove.checkEnemyPieceInDestination()) {
        clearSquare(after.getSquare());
        gameInfo.upEatPiecesCount();
      }

      if (piece.getName().equals(PAWN)) {
        checkPieceMove.makeEnPassantIfNeeded();
        checkPieceMove.makePawnPromotionIfNeeded();
      }

      gameInfo.setPreviousPiece(after);
      gameInfo.setPreviousMove(move);

      if (piece.getName().equals(KING)) {
        checkPieceMove.makeCastlingIfNeeded();
      }

      removePiece(piece);
      addPiece(after);

      updateGameInfo(piece, after);
      changePriority();

      return true;
    }

    return false;
  }

  /**
   * Обновить информацию об игре.
   */
  private void updateGameInfo(Piece from, Piece to) {
    String lastStep = String.format("%s(%s -> %s)", from, from.getSquare(), to.getSquare());
    gameInfo.upStepCount();
    gameInfo.setLastStep(lastStep);
    gameInfo.setFrom(from.getSquare());
    gameInfo.setTo(to.getSquare());
  }

  @Override
  public String toString() {
    String[][] board = new String[8][8];
    String squareChar = "֎\t";

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        String color = ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) ? TextColor.BLACK : TextColor.WHITE_BRIGHT;
        board[i][j] = colorize(squareChar, color);
      }
    }

    for (Piece piece : pieces) {
      int y = piece.getSquare().getVertical();
      int x = piece.getSquare().getHorizontal();

      String color = piece.getColor().equals(WHITE) ? TextColor.WHITE_BRIGHT : TextColor.BLACK;
      board[y][x] = colorize(piece, color);
    }

    if (gameInfo.getLastStep() != null) {
      board[gameInfo.getFrom().getVertical()][gameInfo.getFrom().getHorizontal()] = colorize(squareChar, TextColor.YELLOW_BRIGHT);
      board[gameInfo.getTo().getVertical()][gameInfo.getTo().getHorizontal()] = colorize(gameInfo.getPreviousPiece(), TextColor.YELLOW_BRIGHT);
    }

    String boardColor = TextColor.WHITE;
    StringBuilder result = new StringBuilder(colorize(" \t+---------------------------------+\n", boardColor));

    for (int i = 7; i >= 0; i--) {
      StringBuilder row = new StringBuilder();

      for (int j = 0; j < 8; j++) {
        row.append(board[i][j]).append("\t");
      }

      result
          .append(colorize(i + 1 + "\t| ", boardColor))
          .append(row)
          .append(colorize("|\n", boardColor));
    }

    return result
        .append(colorize(" \t+---------------------------------+\n \t\tA\t\tB\t\tC\t\tD\t\tE\t\tF\t\tG\t\tH\n", boardColor))
        .append(colorize(gameInfo, TextColor.YELLOW))
        .toString();
  }

}
