package chess;

import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;
import static chess.enums.Moves.PAWN_BLACK_DOWN_2;
import static chess.enums.Moves.PAWN_WHITE_UP_2;
import static chess.enums.Names.KING;
import static chess.enums.Names.PAWN;
import static chess.enums.Names.QUEEN;
import static chess.enums.Names.ROOK;

import chess.enums.Colors;
import chess.enums.GameStatus;
import chess.enums.Moves;
import chess.enums.Names;
import chess.helpers.TextColor;
import chess.pojo.Piece;
import chess.pojo.Square;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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

  private Colors getFriendlyColor() {
    return priority;
  }

  private Colors getEnemyColor() {
    return priority == Colors.WHITE ? Colors.BLACK : Colors.WHITE;
  }

  private void changePriority() {
    priority = getEnemyColor();
  }

  //TODO: Доработать данный метод
  public boolean makeMove(Piece piece, Moves move) {
    CheckPieceMove checkPieceMove = new CheckPieceMove(piece, move);

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
    }

    return false;
  }

  private Piece getPieceIfPresent(Piece piece) {
    return pieces
        .stream()
        .filter(p -> p.equals(piece))
        .findAny()
        .orElse(null);
  }

  private Piece getPieceIfPresent(Names name, Colors color) {
    return pieces
        .stream()
        .filter(p -> p.getColor().equals(color) && p.getName().equals(name))
        .findAny()
        .orElse(null);
  }

  private Piece getPieceIfPresent(Square square) {
    return pieces
        .stream()
        .filter(p -> p.getSquare().equals(square))
        .findAny()
        .orElse(null);
  }

  /**
   * Обновить информацию об игре.
   */
  private void updateGameInfo(Piece from, Piece to) {
    String lastStep = String.format("%s(%s -> %s)", from, from.getSquare(), to.getSquare());
    gameInfo.upStepCount();
    gameInfo.setLastStep(lastStep);
  }

  @Override
  public String toString() {
    String[][] board = new String[8][8];

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
          board[i][j] = TextColor.BLACK + "֎\t" + TextColor.RESET;

        } else {
          board[i][j] = TextColor.WHITE_BRIGHT + "֎\t" + TextColor.RESET;
        }

      }
    }

    for (Piece piece : pieces) {
      int y = piece.getSquare().getVertical();
      int x = piece.getSquare().getHorizontal();

      if (piece.getColor().equals(WHITE)) {
        board[y][x] = TextColor.WHITE_BRIGHT + piece + TextColor.RESET;

      } else {
        board[y][x] = TextColor.BLACK + piece + TextColor.RESET;
      }
    }

    String boardColor = TextColor.WHITE;
    StringBuilder result = new StringBuilder(boardColor + " \t+---------------------------------+\n" + TextColor.RESET);

    for (int i = 7; i >= 0; i--) {
      StringBuilder row = new StringBuilder();

      for (int j = 0; j < 8; j++) {
        row.append(board[i][j]).append("\t");
      }

      result
          .append(boardColor)
          .append(i + 1)
          .append("\t| ")
          .append(TextColor.RESET)
          .append(row)
          .append(boardColor)
          .append("|\n")
          .append(TextColor.RESET);
    }

    return result
        .append(boardColor)
        .append(" \t+---------------------------------+" + "\n")
        .append(" \t\tA\t\tB\t\tC\t\tD\t\tE\t\tF\t\tG\t\tH  \n")
        .append(TextColor.RESET)
        .append(gameInfo)
        .toString();
  }

  /**
   * Вспомогательный класс для проверки возможности сделать ход.
   */
  private class CheckPieceMove {

    private final Piece before;
    private final Piece after;
    private final Moves move;

    private final int yFrom;
    private final int xFrom;
    private final int yTo;
    private final int xTo;
    private final int yShift;
    private final int xShift;

    public CheckPieceMove(Piece piece, Moves move) {
      this.before = piece;
      this.after = move.getMove().move(new Piece(piece));
      this.move = move;

      this.yFrom = piece.getSquare().getVertical();
      this.xFrom = piece.getSquare().getHorizontal();
      this.yTo = this.after.getSquare().getVertical();
      this.xTo = this.after.getSquare().getHorizontal();

      this.yShift = this.yTo - this.yFrom;
      this.xShift = this.xTo - this.xFrom;
    }

    private boolean checkPieceNotEscape() {
      return yTo < 8 && yTo >= 0 && xTo < 8 && xTo >= 0;
    }

    private boolean checkFriendlyPieceInDestination() {
      return pieces.stream()
          .anyMatch(p -> p.getSquare().equals(after.getSquare()) && p.getColor().equals(after.getColor()));
    }

    private boolean checkEnemyPieceInDestination() {
      return pieces.stream()
          .anyMatch(p -> p.getSquare().equals(after.getSquare()) && !p.getColor().equals(after.getColor()));
    }


    private boolean checkMoveIsPossible() {
      if (!checkPieceNotEscape() || checkFriendlyPieceInDestination() || !checkPathIsClear()) {
        return false;
      }

      return switch (before.getName()) {
        case KNIGHT -> true;
        case PAWN -> checkPawnMove();
        case KING -> checkKingMove();
        case ROOK, QUEEN, BISHOP -> checkPathIsClear();
      };
    }

    private boolean checkPathIsClear() {
      int dx = Integer.compare(xTo, xFrom);
      int dy = Integer.compare(yTo, yFrom);

      for (int x = xFrom + dx, y = yFrom + dy; x != xTo || y != yTo; x += dx, y += dy) {
        if (containsPiece(Square.of(y, x))) {
          return false;
        }
      }

      return true;
    }

    private boolean checkPawnMove() {
      if (checkEnemyPieceInDestination()) {
        return xShift != 0;
      }

      if (xShift != 0) {
        return isEnPassant();

      } else {
        return Math.abs(yShift) == 1 || yTo == (before.getColor() == WHITE ? 3 : 4);
      }
    }

    /**
     * Проверяет, было ли взятие на проходе.
     */
    private boolean isEnPassant() {
      boolean isWhite = after.getColor() == WHITE;
      int dy = isWhite ? -1 : 1;
      Moves previousMove = isWhite ? PAWN_BLACK_DOWN_2 : PAWN_WHITE_UP_2;

      Piece enemyPiece = new Piece(PAWN, getEnemyColor(), Square.of(yTo + dy, xTo))
          .setMoveBefore(true);

      return containsPiece(enemyPiece) && gameInfo.getPreviousPiece().equals(enemyPiece) && gameInfo.getPreviousMove().equals(previousMove);
    }

    private boolean checkKingMove() {
      if (Math.abs(xShift) < 2) {
        return true;
      }

      if (before.isMoveBefore() || !checkPathIsClear()) {
        return false;
      }

      int y = before.getColor() == WHITE ? 0 : 7;
      int x = move.equals(Moves.KING_CASTLING_RIGHT) ? 7 : 0;

      Piece rook = getPieceIfPresent(new Piece(ROOK, getFriendlyColor(), Square.of(y, x)));

      if (rook == null || rook.isMoveBefore()) {
        return false;
      }

      Set<Square> shouldNotBeAttackedField;

      if (move.equals(Moves.KING_CASTLING_RIGHT)) {
        shouldNotBeAttackedField = Set.of(
            Square.of(y, 4),
            Square.of(y, 5),
            Square.of(y, 6)
        );

      } else {
        shouldNotBeAttackedField = Set.of(
            Square.of(y, 4),
            Square.of(y, 3),
            Square.of(y, 2),
            Square.of(y, 1)
        );
      }

      return getAttackedFieldList().stream().noneMatch(shouldNotBeAttackedField::contains);
    }

    //TODO: Перенести эти три метода в основной класс

    private void makeEnPassantIfNeeded() {
      if (xShift != 0) {
        if (isEnPassant()) {
          int dy = after.getColor() == WHITE ? -1 : 1;

          clearSquare(Square.of(yTo + dy, xTo));
          gameInfo.upEatPiecesCount();
        }
      }
    }

    private void makeCastlingIfNeeded() {
      Moves previousMove = gameInfo.getPreviousMove();

      if (previousMove.equals(Moves.KING_CASTLING_LEFT) || previousMove.equals(Moves.KING_CASTLING_RIGHT)) {
        int y = getFriendlyColor().equals(WHITE) ? 0 : 7;
        int x = previousMove.equals(Moves.KING_CASTLING_LEFT) ? 0 : 7;
        Moves move = previousMove.equals(Moves.KING_CASTLING_LEFT) ? Moves.ROOK_RIGHT_2 : Moves.ROOK_LEFT_2;

        Piece rook = getPieceIfPresent(Square.of(y, x));
        move.getMove().move(rook);
      }
    }

    /**
     * По умолчанию всегда превращает пешку в ферзя. Для превращения в другую фигуру использовать метод "changePiece(Piece piece, Names name)".
     */
    private void makePawnPromotionIfNeeded() {
      if ((after.getColor().equals(WHITE) && yTo == 7) || (after.getColor().equals(BLACK) && yTo == 0)) {
        removePiece(after);
        Piece queen = new Piece(QUEEN, after.getColor(), after.getSquare());
        addPiece(queen);
      }
    }

    //TODO: Не происходит смена приоритета. Некорректно работают методы getFriendlyColor() и getEnemyColor()
    private Set<Square> getAttackedFieldList() {
      ChessBoard chessBoard = createChessBoard(pieces);
      chessBoard.setPriority(getEnemyColor());

      return chessBoard.getPieces()
          .stream()
          .filter(piece -> piece.getColor() == getEnemyColor())
          .map(piece ->
              piece
                  .getMoveList()
                  .stream()
                  .filter(move -> !move.equals(Moves.KING_CASTLING_LEFT) && !move.equals(Moves.KING_CASTLING_RIGHT))
                  .filter(move -> new CheckPieceMove(piece, move).checkMoveIsPossible())
                  .map(move -> move.getMove().move(new Piece(piece)).getSquare())
                  .collect(Collectors.toSet())
          )
          .flatMap(Collection::stream)
          .collect(Collectors.toSet());
    }
  }

}
