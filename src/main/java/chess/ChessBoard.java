package chess;

import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;
import static chess.enums.Names.KING;

import chess.enums.Colors;
import chess.enums.GameStatus;
import chess.enums.Moves;
import chess.enums.Names;
import chess.helpers.TextColor;
import chess.legacy.pieces.IPieces;
import chess.pojo.Piece;
import chess.pojo.Square;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
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
    this.pieces = chessBoard.getPieces();
    this.priority = chessBoard.getPriority();
    this.status = chessBoard.getStatus();
    this.gameInfo = chessBoard.getGameInfo();
  }

  private ChessBoard(List<Piece> pieces) {
    this.pieces = pieces;
    this.priority = Colors.WHITE;
    this.status = GameStatus.IN_PROGRESS;
    this.gameInfo = GameInfo.newGame();
  }

  private ChessBoard(List<Piece> pieces, Colors priority, GameStatus status) {
    this.pieces = pieces;
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
    if (containsPiece(piece.getSquare())) {
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
    //Добавить реализацию метода
    return true;
  }

  private boolean checkMoveIsPossible(Piece piece, Moves move) {
    Piece after = move.getMove().move(piece);

    if (!checkPieceNotEscape(after)) {
      return false;
    }

    if (checkFriendlyPieceInDestination(after)) {
      return false;
    }

//    return switch (after.getName()) {
//      case PAWN -> new CheckPieces().checkPawn(chessBoard, piece, action);
//      case ROOK -> new CheckPieces().checkRook(chessBoard, piece, action);
//      case BISHOP -> new CheckPieces().checkBishop(chessBoard, piece, action);
//      case QUEEN -> new CheckPieces().checkQueen(chessBoard, piece, action);
//      case KING -> new CheckPieces().checkKing(chessBoard, piece, action);
//      default -> true;
//    };
    return true;
  }

  private boolean checkCastlingIsPossible() {
    //Добавить реализацию метода
    return true;
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

      board[vertical][horizontal] = piece.toString();
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

    private boolean checkPawn(Piece piece, Moves move) {
      Piece after = move.getMove().move(piece);

      int verticalBefore = piece.getSquare().getVertical();
      int verticalAfter = after.getSquare().getVertical();

      int horizontalBefore = piece.getSquare().getHorizontal();
      int horizontalAfter = after.getSquare().getHorizontal();

      int sideShift = Math.abs(horizontalBefore - horizontalAfter);
      int heightShift = verticalAfter - verticalBefore;

      if (after.getColor() == WHITE) {
        if (checkEnemyPieceInDestination(after)) {
          return sideShift != 0;

        } else {
          if (sideShift == 1) {
            Piece enemy = getPieceIfPresent(Square.of(verticalAfter - 1, horizontalAfter));
            return enemy != null && enemy.getColor().equals(getEnemyColor()) && enemy.getPreviousMove().equals(Moves.PAWN_BLACK_DOWN_2);

          } else {
            return heightShift == 1 || !containsPiece(Square.of(verticalAfter - 1, horizontalAfter));
          }
        }

      } else {
        if (checkEnemyPieceInDestination(after)) {
          return sideShift != 0;

        } else {
          if (sideShift == 1) {
            Piece enemy = getPieceIfPresent(Square.of(verticalAfter + 1, horizontalAfter));
            return enemy != null && enemy.getColor().equals(getEnemyColor()) && enemy.getPreviousMove().equals(Moves.PAWN_BLACK_DOWN_2);

          } else {
            return heightShift == -1 || !containsPiece(Square.of(verticalAfter + 1, horizontalAfter));
          }
        }
      }
    }

    private boolean checkRook(Piece piece, Moves move) {
      Piece after = move.getMove().move(piece);

      int verticalBefore = piece.getSquare().getVertical();
      int verticalAfter = after.getSquare().getVertical();

      int horizontalBefore = piece.getSquare().getHorizontal();
      int horizontalAfter = after.getSquare().getHorizontal();

      int sideShift = Math.abs(horizontalBefore - horizontalAfter);
      int heightShift = verticalAfter - verticalBefore;

      if (heightShift > 0) {
        for (int i = 1; i < heightShift; i++) {
          if (containsPiece(Square.of(verticalBefore + i, horizontalBefore))) {
            return false;
          }
        }

      } else if (heightShift < 0) {
        for (int i = -1; i > heightShift; i--) {
          if (containsPiece(Square.of(verticalBefore + i, horizontalBefore))) {
            return false;
          }
        }
      }

      if (sideShift > 0) {
        for (int i = 1; i < sideShift; i++) {
          if (containsPiece(Square.of(verticalBefore, horizontalAfter + i))) {
            return false;
          }
        }

      } else if (sideShift < 0) {
        for (int i = -1; i > sideShift; i--) {
          if (containsPiece(Square.of(verticalBefore, horizontalAfter + i))) {
            return false;
          }
        }
      }

      return true;
    }

    private boolean checkBishop(Piece piece, Moves move) {
      Piece after = move.getMove().move(piece);

      int verticalBefore = piece.getSquare().getVertical();
      int verticalAfter = after.getSquare().getVertical();

      int horizontalBefore = piece.getSquare().getHorizontal();
      int horizontalAfter = after.getSquare().getHorizontal();

      int sideShift = Math.abs(horizontalBefore - horizontalAfter);
      int heightShift = verticalAfter - verticalBefore;

      if (heightShift > 0 && sideShift > 0) {
        for (int i = 1; i < heightShift; i++) {
          if (containsPiece(Square.of(verticalBefore + i, horizontalBefore + i))) {
            return false;
          }
        }

      } else if (heightShift < 0 && sideShift < 0) {
        for (int i = -1; i > heightShift; i--) {
          if (containsPiece(Square.of(verticalBefore + i, horizontalBefore + i))) {
            return false;
          }
        }

      } else if (heightShift > 0 && sideShift < 0) {
        for (int i = 1; i < heightShift; i++) {
          if (containsPiece(Square.of(verticalBefore + i, horizontalBefore - i))) {
            return false;
          }
        }

      } else if (heightShift < 0 && sideShift > 0) {
        for (int i = -1; i > heightShift; i--) {
          if (containsPiece(Square.of(verticalBefore + i, horizontalBefore - i))) {
            return false;
          }
        }
      }

      return true;
    }

    private boolean checkQueen(Piece piece, Moves move) {
      return checkRook(piece, move) && checkBishop(piece, move);
    }

    private boolean checkKing(chess.legacy.ChessBoard chessBoard, IPieces piece, Function<IPieces, IPieces> action) {
      IPieces pieceAfter = action.apply(piece);

      int horizontalBefore = piece.getSquare().getHorizontal();
      int horizontalAfter = pieceAfter.getSquare().getHorizontal();
      int sideShiftHorizontal = Math.abs(horizontalAfter - horizontalBefore);

//      if (chessBoard.getAttackedFields().stream().anyMatch(field -> field.equals(piece.getCoordinates()))) {
//        return false;
//      }

      if (sideShiftHorizontal < 2) {
        return true;

      } else {
        if (chessBoard.getPriority() == WHITE) {
          IPieces king = getPiece(chessBoard, KING, WHITE);
          IPieces leftRook = getPieceInSquare(chessBoard, new Square(0, 0), WHITE);
          IPieces rightRook = getPieceInSquare(chessBoard, new Square(0, 7), WHITE);

          IPieces leftKnight = getPieceInSquare(chessBoard, new Square(0, 1), WHITE);
          IPieces rightKnight = getPieceInSquare(chessBoard, new Square(0, 6), WHITE);

          IPieces leftBishop = getPieceInSquare(chessBoard, new Square(0, 2), WHITE);
          IPieces rightBishop = getPieceInSquare(chessBoard, new Square(0, 5), WHITE);

          IPieces queen = getPieceInSquare(chessBoard, new Square(0, 3), WHITE);

          Set<Square> whiteList = Set.of(
              new Square(0, 1),
              new Square(0, 2),
              new Square(0, 3),
              new Square(0, 4),
              new Square(0, 5),
              new Square(0, 6)
          );

          Set<Square> attackedFields = chessBoard.getAttackedFields()
              .stream()
              .filter(whiteList::contains)
              .collect(Collectors.toSet());

          return attackedFields.size() == 0 && !king.getMoveBefore() && leftRook != null && rightRook != null &&
              !leftRook.getMoveBefore() && !rightRook.getMoveBefore() &&
              leftKnight == null && rightKnight == null && leftBishop == null && rightBishop == null && queen == null;

        } else {
          IPieces king = getPiece(chessBoard, KING, BLACK);
          IPieces leftRook = getPieceInSquare(chessBoard, new Square(7, 0), BLACK);
          IPieces rightRook = getPieceInSquare(chessBoard, new Square(7, 7), BLACK);

          IPieces leftKnight = getPieceInSquare(chessBoard, new Square(7, 1), BLACK);
          IPieces rightKnight = getPieceInSquare(chessBoard, new Square(7, 6), BLACK);

          IPieces leftBishop = getPieceInSquare(chessBoard, new Square(7, 2), BLACK);
          IPieces rightBishop = getPieceInSquare(chessBoard, new Square(7, 5), BLACK);

          IPieces queen = getPieceInSquare(chessBoard, new Square(7, 3), BLACK);

          Set<Square> whiteList = Set.of(
              new Square(7, 1),
              new Square(7, 2),
              new Square(7, 3),
              new Square(7, 4),
              new Square(7, 5),
              new Square(7, 6)
          );

          Set<Square> attackedFields = chessBoard.getAttackedFields()
              .stream()
              .filter(whiteList::contains)
              .collect(Collectors.toSet());

          return attackedFields.size() == 0 && !king.getMoveBefore() && leftRook != null && rightRook != null &&
              !leftRook.getMoveBefore() && !rightRook.getMoveBefore() &&
              leftKnight == null && rightKnight == null && leftBishop == null && rightBishop == null && queen == null;
        }
      }
    }
  }

}
