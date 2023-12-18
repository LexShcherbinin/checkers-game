package chess;

import static chess.ChessBoard.copyBoard;
import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;
import static chess.enums.Moves.PAWN_BLACK_DOWN_2;
import static chess.enums.Moves.PAWN_WHITE_UP_2;
import static chess.enums.Names.KNIGHT;
import static chess.enums.Names.PAWN;
import static chess.enums.Names.QUEEN;
import static chess.enums.Names.ROOK;

import chess.enums.Colors;
import chess.enums.Moves;
import chess.enums.Names;
import chess.pojo.Piece;
import chess.pojo.Square;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Вспомогательный класс для проверки возможности сделать ход.
 */
public final class CheckPieceMove {

  public static final int MIN_ROW_INDEX = 0;
  public static final int MAX_ROW_INDEX = 7;

  private final ChessBoard chessBoard;
  private final List<Piece> pieces;

  private final Piece before;
  private final Piece after;
  private final Moves move;

  private final int yFrom;
  private final int xFrom;
  private final int yTo;
  private final int xTo;
  private final int yShift;
  private final int xShift;

  public CheckPieceMove(ChessBoard chessBoard, Piece piece, Moves move) {
    this.chessBoard = chessBoard;
    this.pieces = chessBoard.getPieces();

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

  private boolean checkPieceNotEscape() {
    return yTo < 8 && yTo >= MIN_ROW_INDEX && xTo < 8 && xTo >= MIN_ROW_INDEX;
  }

  private boolean checkFriendlyPieceInDestination() {
    return pieces.stream()
        .anyMatch(p -> p.getSquare().equals(after.getSquare()) && p.getColor().equals(after.getColor()));
  }

  public boolean checkEnemyPieceInDestination() {
    return pieces.stream()
        .anyMatch(p -> p.getSquare().equals(after.getSquare()) && !p.getColor().equals(after.getColor()));
  }

  public Colors getFriendlyColor() {
    return chessBoard.getPriority();
  }

  public Colors getEnemyColor() {
    return chessBoard.getPriority() == Colors.WHITE ? Colors.BLACK : Colors.WHITE;
  }

  public boolean checkMoveIsPossible() {
    if (!checkPieceNotEscape() || checkFriendlyPieceInDestination() || (!before.getName().equals(KNIGHT) && !checkPathIsClear())) {
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
      if (chessBoard.containsPiece(Square.of(y, x))) {
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

    return chessBoard.containsPiece(enemyPiece)
        && chessBoard.getGameInfo().getPreviousPiece().equals(enemyPiece)
        && chessBoard.getGameInfo().getPreviousMove().equals(previousMove);
  }

  private boolean checkKingMove() {
    if (Math.abs(xShift) < 2) {
      return true;
    }

    if (before.isMoveBefore()) {
      return false;
    }

    int y = before.getColor() == WHITE ? MIN_ROW_INDEX : MAX_ROW_INDEX;
    int x = move.equals(Moves.KING_CASTLING_RIGHT) ? MAX_ROW_INDEX : MIN_ROW_INDEX;

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

    return getCapturedSquareSet().stream().noneMatch(shouldNotBeAttackedField::contains);
  }

  public void makeEnPassantIfNeeded() {
    if (xShift != 0 && isEnPassant()) {
      int dy = after.getColor() == WHITE ? -1 : 1;

      chessBoard.clearSquare(Square.of(yTo + dy, xTo));
      chessBoard.getGameInfo().upEatPiecesCount();
    }
  }

  public void makeCastlingIfNeeded() {
    Moves previousMove = chessBoard.getGameInfo().getPreviousMove();

    if (previousMove.equals(Moves.KING_CASTLING_LEFT) || previousMove.equals(Moves.KING_CASTLING_RIGHT)) {
      int y = getFriendlyColor().equals(WHITE) ? MIN_ROW_INDEX : MAX_ROW_INDEX;
      int x = previousMove.equals(Moves.KING_CASTLING_LEFT) ? MIN_ROW_INDEX : MAX_ROW_INDEX;
      Moves move = previousMove.equals(Moves.KING_CASTLING_LEFT) ? Moves.ROOK_RIGHT_2 : Moves.ROOK_LEFT_2;

      Piece rook = getPieceIfPresent(Square.of(y, x));
      move.getMove().move(rook);
    }
  }

  /**
   * По умолчанию всегда превращает пешку в ферзя. Для превращения в другую фигуру использовать метод "changePiece(Piece piece, Names name)".
   */
  public void makePawnPromotionIfNeeded() {
    if ((after.getColor().equals(WHITE) && yTo == MAX_ROW_INDEX) || (after.getColor().equals(BLACK) && yTo == MIN_ROW_INDEX)) {
      Piece queen = new Piece(QUEEN, after.getColor(), after.getSquare());

      chessBoard.removePiece(after);
      chessBoard.addPiece(queen);
    }
  }

  private Set<Square> getCapturedSquareSet() {
    ChessBoard chessBoard = copyBoard(this.chessBoard);
    chessBoard.setPriority(getEnemyColor());

    return chessBoard.getPieces()
        .stream()
        .filter(piece -> piece.getColor() == getEnemyColor())
        .map(piece -> getSquareAfterMoveSetForPrice(chessBoard, piece))
        .flatMap(Collection::stream)
        .collect(Collectors.toSet());
  }

  private Set<Square> getSquareAfterMoveSetForPrice(ChessBoard chessBoard, Piece piece) {
    return piece.getMoveList()
        .stream()
        .filter(move -> !move.equals(Moves.KING_CASTLING_LEFT) && !move.equals(Moves.KING_CASTLING_RIGHT))
        .filter(move -> new CheckPieceMove(chessBoard, piece, move).checkMoveIsPossible())
        .map(move -> move.getMove().move(new Piece(piece)).getSquare())
        .collect(Collectors.toSet());
  }

}
