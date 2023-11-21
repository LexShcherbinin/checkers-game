package chess;

import static chess.enums.Colors.WHITE;
import static chess.enums.Moves.KING_CASTLING_RIGHT;
import static chess.enums.Moves.PAWN_BLACK_DOWN_1;
import static chess.enums.Moves.PAWN_BLACK_DOWN_2;
import static chess.enums.Moves.PAWN_BLACK_DOWN_LEFT;
import static chess.enums.Moves.PAWN_BLACK_DOWN_RIGHT;
import static chess.enums.Moves.PAWN_WHITE_UP_1;
import static chess.enums.Moves.PAWN_WHITE_UP_2;
import static chess.enums.Moves.PAWN_WHITE_UP_LEFT;
import static chess.enums.Moves.PAWN_WHITE_UP_RIGHT;
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

  public boolean containsPiece(Piece piece) {
    return pieces.contains(piece);
  }

  public boolean containsPiece(Names name, Colors color) {
    return pieces.stream().anyMatch(piece -> piece.getColor().equals(color) && piece.getName().equals(name));
  }

  public boolean containsPiece(Square square) {
    return pieces.stream().anyMatch(piece -> piece.getSquare().equals(square));
  }

  public boolean haveBothKingOnBoard() {
    return pieces.stream().filter(piece -> piece.getName().equals(Names.KING)).count() == 2;
  }

  public void giveUp() {
    status = GameStatus.CHECKMATE;
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
        .filter(piece -> piece.getColor().equals(color) && piece.getName().equals(name))
        .findAny()
        .orElse(null);
  }

  private Piece getPieceIfPresent(Square square) {
    return pieces
        .stream()
        .filter(piece -> piece.getSquare().equals(square))
        .findAny()
        .orElse(null);
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

  public boolean makeMove(Piece piece, Moves move) {
    if (checkMoveIsPossible(piece, move)) {
      Piece after = new Piece(piece);
      move.getMove().move(after);

      if (checkEnemyPieceInDestination(after)) {
        clearSquare(after.getSquare());
      }

      removePiece(piece);
      addPiece(after);

      updateGameInfo(piece, after);
      changePriority();
    }

    System.out.println("Нельзя пойти ходом " + piece + " /// " + move);

    return false;
  }

  private boolean checkMoveIsPossible(Piece piece, Moves move) {
    Piece after = new Piece(piece);
    move.getMove().move(after);

    if (!checkPieceNotEscape(after)) {
      return false;
    }

    if (checkFriendlyPieceInDestination(after)) {
      return false;
    }

    CheckPieceMove checkPieceMove = new CheckPieceMove();

    return switch (after.getName()) {
      case KNIGHT -> true;
      case PAWN -> checkPieceMove.checkPawn(piece, move);
      case KING -> checkPieceMove.checkKing(piece, move);
      case ROOK, QUEEN, BISHOP -> checkPieceMove.checkPathClear(piece.getSquare(), after.getSquare());
    };
  }

  private boolean checkPieceNotEscape(Piece piece) {
    int vertical = piece.getSquare().getVertical();
    int horizontal = piece.getSquare().getHorizontal();

    return vertical < 8 && vertical >= 0 && horizontal < 8 && horizontal >= 0;
  }

  private boolean checkFriendlyPieceInDestination(Piece afterMove) {
    return pieces.stream().anyMatch(p -> p.getSquare().equals(afterMove.getSquare()) && p.getColor().equals(afterMove.getColor()));
  }

  private boolean checkEnemyPieceInDestination(Piece afterMove) {
    return pieces.stream().anyMatch(p -> p.getSquare().equals(afterMove.getSquare()) && !p.getColor().equals(afterMove.getColor()));
  }

  /**
   * Обновить информацию об игре.
   */
  private void updateGameInfo(Piece before, Piece after) {
    String lastStep = String.format("%s : %s -> %s", before, before.getSquare(), after.getSquare());
    int stepCount = gameInfo.getStepCount();
    int eatPiecesCount = gameInfo.getEatPiecesCount();

    gameInfo
        .setLastStep(lastStep)
        .setStepCount(++stepCount)
        .setEatPiecesCount(++eatPiecesCount);
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
      int vertical = piece.getSquare().getVertical();
      int horizontal = piece.getSquare().getHorizontal();

      if (piece.getColor().equals(WHITE)) {
        board[vertical][horizontal] = TextColor.WHITE_BRIGHT + piece + TextColor.RESET;

      } else {
        board[vertical][horizontal] = TextColor.BLACK + piece + TextColor.RESET;
      }

//      board[vertical][horizontal] = piece.toString();
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

  private class CheckPieceMove {

//    private boolean checkPathIsFree(Square from, Square to) {
//      int verticalBefore = from.getVertical();
//      int verticalAfter = to.getVertical();
//
//      int horizontalBefore = from.getHorizontal();
//      int horizontalAfter = to.getHorizontal();
//
//      int sideShift = horizontalBefore - horizontalAfter;
//      int heightShift = verticalAfter - verticalBefore;
//
//      Function<Integer, Integer> functionVertical = heightShift == 0 ? i -> i : (heightShift > 0 ? i -> ++i : i -> --i);
//      Function<Integer, Integer> functionHorizontal = sideShift == 0 ? i -> i : (sideShift > 0 ? i -> ++i : i -> --i);
//
//      for (int i = 0; i < Math.max(Math.abs(heightShift), Math.abs(sideShift)); i++) {
//        int a = verticalBefore + functionVertical.apply(i);
//        int b = horizontalBefore + functionHorizontal.apply(i);
//
//        Square square = Square.of(a, b);
//
//        if (containsPiece(square)) {
//          return false;
//        }
//      }
//
//      return true;
//    }

    private boolean checkPathClear(Square from, Square to) {
      int yFrom = from.getVertical();
      int yTo = to.getVertical();

      int xFrom = from.getHorizontal();
      int xTo = to.getHorizontal();

      int dx = Integer.compare(xTo, xFrom);
      int dy = Integer.compare(yTo, yFrom);

      for (int x = xFrom + dx, y = yFrom + dy; x != xTo || y != yTo; x += dx, y += dy) {
        if (containsPiece(Square.of(x, y))) {
          return false;
        }
      }

      return true;
    }

    private boolean checkPawn(Piece piece, Moves move) {
      Piece after = new Piece(piece);
      move.getMove().move(after);

      int verticalBefore = piece.getSquare().getVertical();
      int verticalAfter = after.getSquare().getVertical();

      int horizontalBefore = piece.getSquare().getHorizontal();
      int horizontalAfter = after.getSquare().getHorizontal();

      int sideShift = Math.abs(horizontalBefore - horizontalAfter);
      int heightShift = verticalAfter - verticalBefore;

      if (!checkPathClear(piece.getSquare(), after.getSquare())) {
        return false;
      }

      if (after.getColor() == WHITE) {
        if (move.equals(PAWN_BLACK_DOWN_1) || move.equals(PAWN_BLACK_DOWN_2) || move.equals(PAWN_BLACK_DOWN_LEFT) || move.equals(PAWN_BLACK_DOWN_RIGHT)) {
          return false;
        }

        if (checkEnemyPieceInDestination(after)) {
          return sideShift != 0;

        } else {
          if (sideShift == 1) {
            Piece enemy = getPieceIfPresent(Square.of(verticalAfter - 1, horizontalAfter));
            return enemy != null && enemy.getColor().equals(getEnemyColor()) && enemy.getPreviousMove().equals(PAWN_BLACK_DOWN_2);

          } else {
            return heightShift == 1 || verticalAfter == 3;
          }
        }

      } else {
        if (move.equals(PAWN_WHITE_UP_1) || move.equals(PAWN_WHITE_UP_2) || move.equals(PAWN_WHITE_UP_LEFT) || move.equals(PAWN_WHITE_UP_RIGHT)) {
          return false;
        }

        if (checkEnemyPieceInDestination(after)) {
          return sideShift != 0;

        } else {
          if (sideShift == 1) {
            Piece enemy = getPieceIfPresent(Square.of(verticalAfter + 1, horizontalAfter));
            return enemy != null && enemy.getColor().equals(getEnemyColor()) && enemy.getPreviousMove().equals(PAWN_BLACK_DOWN_2);

          } else {
            return heightShift == -1 || verticalAfter == 4;
          }
        }
      }
    }

    private boolean checkKing(Piece piece, Moves move) {
      Piece after = new Piece(piece);
      move.getMove().move(after);

      int horizontalBefore = piece.getSquare().getHorizontal();
      int horizontalAfter = after.getSquare().getHorizontal();
      int sideShift = Math.abs(horizontalAfter - horizontalBefore);

      if (checkFriendlyPieceInDestination(after)) {
        return false;
      }

      if (sideShift < 2) {
        return true;
      }

      if (piece.isMoveBefore() || !checkPathClear(piece.getSquare(), after.getSquare())) {
        return false;
      }

      Set<Square> attackedFields = getAttackedFieldList();
      Set<Square> shouldNotBeAttackedField;
      int vertical;
      int horizontal;

      if (getPriority() == WHITE) {

        if (move.equals(KING_CASTLING_RIGHT)) {
          vertical = 0;
          horizontal = 7;
          shouldNotBeAttackedField = Set.of(
              Square.of(vertical, 4),
              Square.of(vertical, 5),
              Square.of(vertical, 6)
          );

        } else {
          vertical = 0;
          horizontal = 0;
          shouldNotBeAttackedField = Set.of(
              Square.of(vertical, 4),
              Square.of(vertical, 3),
              Square.of(vertical, 2),
              Square.of(vertical, 1)
          );
        }

      } else {
        if (move.equals(KING_CASTLING_RIGHT)) {
          vertical = 7;
          horizontal = 7;
          shouldNotBeAttackedField = Set.of(
              Square.of(vertical, 4),
              Square.of(vertical, 5),
              Square.of(vertical, 6)
          );

        } else {
          vertical = 7;
          horizontal = 0;
          shouldNotBeAttackedField = Set.of(
              Square.of(vertical, 4),
              Square.of(vertical, 3),
              Square.of(vertical, 2),
              Square.of(vertical, 1)
          );
        }
      }

      boolean existRook = pieces.stream()
          .anyMatch(p -> p.getName().equals(ROOK) &&
              p.getColor().equals(getFriendlyColor()) &&
              p.getSquare().equals(Square.of(vertical, horizontal)) &&
              !p.isMoveBefore()
          );

      return existRook && attackedFields.stream().noneMatch(shouldNotBeAttackedField::contains);

    }

    private Set<Square> getAttackedFieldList() {
      return pieces
          .stream()
          .filter(piece -> piece.getColor() == getEnemyColor())
          .map(piece ->
              piece
                  .getMoveList()
                  .stream()
                  .filter(move -> checkMoveIsPossible(piece, move))
                  .map(move -> move.getMove().move(piece).getSquare())
                  .collect(Collectors.toSet())
          )
          .flatMap(Collection::stream)
          .collect(Collectors.toSet());
    }
  }

}
