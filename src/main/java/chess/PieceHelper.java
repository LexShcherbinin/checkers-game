package chess;

import static chess.Colors.BLACK;
import static chess.Colors.WHITE;
import static chess.Names.KING;
import static chess.Names.ROOK;

import chess.pieces.IPieces;
import chess.pieces.Rook;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PieceHelper {

  /**
   * Получить мапу со всеми фигурами, которыми можно пойти, и всеми их доступными ходами
   */
  public static Map<IPieces, List<Function<IPieces, IPieces>>> getMoveList(ChessBoard chessBoard) {
//    IPieces enemyKing = chessBoard.getPieces()
//        .stream()
//        .filter(piece -> piece.getColor() != chessBoard.getPriority() && piece.getName() == KING)
//        .findAny()
//        .orElse(null);

    IPieces enemyKing = getPiece(chessBoard, KING, getRivalColor(chessBoard));

    Map<IPieces, List<Function<IPieces, IPieces>>> moveList = new HashMap<>();
    Map<IPieces, List<Function<IPieces, IPieces>>> kingKillersList = new HashMap<>();

    chessBoard.getPieces()
        .stream()
        .filter(piece -> piece.getColor() == chessBoard.getPriority())
        .forEach(piece -> {
              List<Function<IPieces, IPieces>> actions = piece
                  .getActions()
                  .stream()
                  .filter(action -> checkMove(chessBoard, piece, action))
                  .collect(Collectors.toList());

              List<Function<IPieces, IPieces>> kingKillers = actions
                  .stream()
                  .filter(action -> action.apply(piece).getCoordinates().equals(enemyKing.getCoordinates()))
                  .collect(Collectors.toList());

              if (kingKillers.size() != 0) {
                kingKillersList.put(piece, kingKillers);

              } else if (actions.size() != 0) {
                moveList.put(piece, actions);
              }
            }
        );

    if (kingKillersList.size() !=0) {
      return kingKillersList;
    }

    return moveList;
  }

  /**
   * Найти и получить фигуру на доске, если она есть
   */
  private static IPieces getPiece(ChessBoard chessBoard, Names name, Colors color) {
    return chessBoard.getPieces()
        .stream()
        .filter(piece -> piece.getColor() == color && piece.getName() == name)
        .findAny()
        .orElse(null);
  }

  /**
   * Получить сет полей, которые может атаковать сторона color. Просто дополнительный метод
   */
  @Deprecated
  public static Set<Coordinates> getAttackedFieldList(ChessBoard chessBoard, Colors color) {
    return chessBoard.getPieces()
        .stream()
        .filter(piece -> piece.getColor() == color)
        .map(piece ->
            piece
                .getActions()
                .stream()
                .filter(action -> checkMove(chessBoard, piece, action))
                .map(action -> action.apply(piece).getCoordinates())
                .collect(Collectors.toSet())
        )
        .flatMap(Collection::stream)
        .collect(Collectors.toSet());
  }

  /**
   * Проверка, можно ли ТАК пойти (надо доработать)
   */
  private static boolean checkMove(ChessBoard chessBoard, IPieces piece, Function<IPieces, IPieces> action) {
    IPieces pieceAfter = action.apply(piece);

    if (!checkBoardBorders(pieceAfter)) {
      return false;
    }

    if (checkMyPieceInDestination(chessBoard, pieceAfter)) {
      return false;
    }

    return switch (pieceAfter.getName()) {
      case PAWN -> new CheckPieces().checkPawn(chessBoard, piece, action);
      case ROOK -> new CheckPieces().checkRook(chessBoard, piece, action);
      case BISHOP -> new CheckPieces().checkBishop(chessBoard, piece, action);
      case QUEEN -> new CheckPieces().checkQueen(chessBoard, piece, action);
      case KING -> new CheckPieces().checkKing(chessBoard, piece, action);
      default -> true;
    };

  }

  /**
   * Проверяет, остаётся ли фигура в пределах доски
   *
   * @param piece - фигура после того, как сделает ход
   * @return - возвращает true, если фигура остаётся на доске, false, если выходит за неё
   */
  private static boolean checkBoardBorders(IPieces piece) {
    int vertical = piece.getCoordinates().getVertical();
    int horizontal = piece.getCoordinates().getHorizontal();

    return vertical < 8 && vertical >= 0 && horizontal < 8 && horizontal >= 0;
  }

  /**
   * Проверяет, есть ли какая-либо фигура в том месте, куда хочет пойти ходящая фигура
   *
   * @param piece - фигура после того, как сделает ход (ходящая фигура)
   * @return - возвращает true, если в конечной точке есть фигура, false, если фигур нет
   */
  private static boolean checkPieceInDestination(ChessBoard chessBoard, IPieces piece) {
    return chessBoard.getPieces()
        .stream()
        .anyMatch(p -> p.getCoordinates().equals(piece.getCoordinates()));
  }

  /**
   * Проверяет, есть ли своя фигура в том месте, куда хочет пойти ходящая фигура
   *
   * @param piece - фигура после того, как сделает ход (ходящая фигура)
   * @return - возвращает true, если в конечной точке есть своя фигура, false - если фигур нет
   */
  private static boolean checkMyPieceInDestination(ChessBoard chessBoard, IPieces piece) {
    return chessBoard.getPieces()
        .stream()
        .anyMatch(p -> p.getCoordinates().equals(piece.getCoordinates()) && p.getColor().equals(piece.getColor()));
  }

  /**
   * Проверяет, есть ли чужая фигура в том месте, куда хочет пойти ходящая фигура
   *
   * @param piece - фигура после того, как сделает ход (ходящая фигура)
   * @return - возвращает true, если в конечной точке есть чужая фигура, false - если фигур нет
   */
  private static boolean checkEnemyPieceInDestination(ChessBoard chessBoard, IPieces piece) {
    return chessBoard.getPieces()
        .stream()
        .anyMatch(p -> p.getCoordinates().equals(piece.getCoordinates()) && !p.getColor().equals(piece.getColor()));
  }

  /**
   * Проверить, есть ли в месте назначения фигура противоположного цвета
   */
  public static IPieces getDestinationPiece(ChessBoard chessBoard, IPieces piece) {
    return chessBoard.getPieces()
        .stream()
        .filter(p -> p.getCoordinates().equals(piece.getCoordinates()) && p.getColor() != piece.getColor())
        .findFirst()
        .orElse(null);
  }

  /**
   * Проверка, есть ли на доске в поле coordinates фигура цвета color
   *
   * @param chessBoard  - доска
   * @param coordinates - координаты фигуры
   * @param color       - цвет фигуры
   * @return - возвращает true, если фигура есть, и false, если фигуры нет
   */
  public static boolean checkPieceInSquare(ChessBoard chessBoard, Coordinates coordinates, Colors color) {
    return chessBoard.getPieces()
        .stream()
        .anyMatch(piece -> piece.getCoordinates().equals(coordinates) && piece.getColor().equals(color));
  }

  /**
   * Возвращает фигуру цвета color, стоящую на поле coordinates
   *
   * @param chessBoard  - доска
   * @param coordinates - координаты фигуры
   * @param color       - цвет фигуры
   * @return - возвращает фигуру, если она там есть, и null, если в поле её нет
   */
  public static IPieces getPieceInSquare(ChessBoard chessBoard, Coordinates coordinates, Colors color) {
    return chessBoard.getPieces()
        .stream()
        .filter(piece -> piece.getCoordinates().equals(coordinates) && piece.getColor().equals(color))
        .findAny()
        .orElse(null);
  }

  /**
   * Проверка, присутствует ли на доске вражеский король
   */
  public static boolean checkEnemyKingOnBoard(ChessBoard chessBoard) {
    return chessBoard.getPieces()
        .stream()
        .anyMatch(piece -> piece.getColor() != chessBoard.getPriority() && piece.getName() == KING);
  }

  private Colors getMyColor(ChessBoard chessBoard) {
    return chessBoard.getPriority();
  }

  public static Colors getRivalColor(ChessBoard chessBoard) {
    Colors color = chessBoard.getPriority();

    if (color == WHITE) {
      return BLACK;

    } else {
      return WHITE;
    }
  }

  private static class CheckPieces {

    private boolean checkPawn(ChessBoard chessBoard, IPieces piece, Function<IPieces, IPieces> action) {
      IPieces pieceAfter = action.apply(piece);

      int verticalBefore = piece.getCoordinates().getVertical();
      int verticalAfter = pieceAfter.getCoordinates().getVertical();

      int horizontalBefore = piece.getCoordinates().getHorizontal();
      int horizontalAfter = pieceAfter.getCoordinates().getHorizontal();

      int sideShiftHorizontal = Math.abs(horizontalBefore - horizontalAfter);
      int sideShiftVertical = verticalAfter - verticalBefore;

      if (pieceAfter.getColor() == WHITE) {
        if (checkPieceInSquare(chessBoard, pieceAfter.getCoordinates(), BLACK)) {
          if (sideShiftHorizontal == 0) {
            return false;

          } else {
            return true;
          }

        } else {
          if (sideShiftHorizontal == 1) {

            Coordinates coordinates = new Coordinates(
                pieceAfter.getCoordinates().getVertical() - 1,
                pieceAfter.getCoordinates().getHorizontal()
            );

            IPieces enemyPiece = getPieceInSquare(chessBoard, coordinates, BLACK);

            if (checkPieceInSquare(chessBoard, coordinates, BLACK) && !enemyPiece.getMoveBefore()) {
              return true;

            } else {
              return false;
            }

          } else {
            if (sideShiftVertical == 1) {
              return true;

            } else {
              Coordinates coordinates = new Coordinates(
                  pieceAfter.getCoordinates().getVertical() - 1,
                  pieceAfter.getCoordinates().getHorizontal()
              );

              if (checkPieceInSquare(chessBoard, coordinates, BLACK) || checkPieceInSquare(chessBoard, coordinates, WHITE)) {
                return false;

              } else {
                return true;
              }
            }
          }
        }

      } else {
        if (checkPieceInSquare(chessBoard, pieceAfter.getCoordinates(), WHITE)) {
          if (sideShiftHorizontal == 0) {
            return false;

          } else {
            return true;
          }

        } else {
          if (sideShiftHorizontal == 1) {

            Coordinates coordinates = new Coordinates(
                pieceAfter.getCoordinates().getVertical() + 1,
                pieceAfter.getCoordinates().getHorizontal()
            );

            IPieces enemyPiece = getPieceInSquare(chessBoard, coordinates, WHITE);

            if (checkPieceInSquare(chessBoard, coordinates, WHITE) && !enemyPiece.getMoveBefore()) {
              return true;

            } else {
              return false;
            }

          } else {
            if (sideShiftVertical == -1) {
              return true;

            } else {
              Coordinates coordinates = new Coordinates(
                  pieceAfter.getCoordinates().getVertical() + 1,
                  pieceAfter.getCoordinates().getHorizontal()
              );

              if (checkPieceInSquare(chessBoard, coordinates, BLACK) || checkPieceInSquare(chessBoard, coordinates, WHITE)) {
                return false;

              } else {
                return true;
              }
            }
          }
        }
      }
    }

    private boolean checkRook(ChessBoard chessBoard, IPieces piece, Function<IPieces, IPieces> action) {
      IPieces pieceAfter = action.apply(piece);
      boolean result = true;

      int verticalBefore = piece.getCoordinates().getVertical();
      int verticalAfter = pieceAfter.getCoordinates().getVertical();
      int sideShiftVertical = verticalAfter - verticalBefore;

      int horizontalBefore = piece.getCoordinates().getHorizontal();
      int horizontalAfter = pieceAfter.getCoordinates().getHorizontal();
      int sideShiftHorizontal = horizontalAfter - horizontalBefore;

      if (sideShiftVertical > 0 && sideShiftHorizontal == 0) {
        for (int i = 1; i < sideShiftVertical; i++) {

          IPieces tempPrice = new Rook(piece).setCoordinates(
              new Coordinates(
                  piece.getCoordinates().getVertical() + i,
                  horizontalAfter
              )
          );

          if (checkPieceInDestination(chessBoard, tempPrice)) {
            result = false;
            break;
          }
        }

      } else if (sideShiftVertical < 0 && sideShiftHorizontal == 0) {
        for (int i = -1; i > sideShiftVertical; i--) {

          IPieces tempPrice = new Rook(piece).setCoordinates(
              new Coordinates(
                  piece.getCoordinates().getVertical() + i,
                  horizontalAfter
              )
          );

          if (checkPieceInDestination(chessBoard, tempPrice)) {
            result = false;
            break;
          }
        }

      } else if (sideShiftHorizontal > 0 && sideShiftVertical == 0) {
        for (int i = 1; i < sideShiftHorizontal; i++) {

          IPieces tempPrice = new Rook(piece).setCoordinates(
              new Coordinates(
                  verticalAfter,
                  piece.getCoordinates().getHorizontal() + i
              )
          );

          if (checkPieceInDestination(chessBoard, tempPrice)) {
            result = false;
            break;
          }
        }

      } else if (sideShiftHorizontal < 0 && sideShiftVertical == 0) {
        for (int i = -1; i > sideShiftHorizontal; i--) {

          IPieces tempPrice = new Rook(piece).setCoordinates(
              new Coordinates(
                  verticalAfter,
                  piece.getCoordinates().getHorizontal() + i
              )
          );

          if (checkPieceInDestination(chessBoard, tempPrice)) {
            result = false;
            break;
          }
        }

      }

      return result;
    }

    private boolean checkBishop(ChessBoard chessBoard, IPieces piece, Function<IPieces, IPieces> action) {
      IPieces pieceAfter = action.apply(piece);
      boolean result = true;

      int verticalBefore = piece.getCoordinates().getVertical();
      int verticalAfter = pieceAfter.getCoordinates().getVertical();
      int sideShiftVertical = verticalAfter - verticalBefore;

      int horizontalBefore = piece.getCoordinates().getHorizontal();
      int horizontalAfter = pieceAfter.getCoordinates().getHorizontal();
      int sideShiftHorizontal = horizontalAfter - horizontalBefore;

      if (sideShiftVertical > 0 && sideShiftHorizontal > 0) {
        for (int i = 1; i < sideShiftVertical; i++) {

          IPieces tempPrice = new Rook(piece).setCoordinates(
              new Coordinates(
                  piece.getCoordinates().getVertical() + i,
                  piece.getCoordinates().getHorizontal() + i
              )
          );

          if (checkPieceInDestination(chessBoard, tempPrice)) {
            result = false;
            break;
          }
        }

      } else if (sideShiftVertical < 0 && sideShiftHorizontal < 0) {
        for (int i = -1; i > sideShiftVertical; i--) {

          IPieces tempPrice = new Rook(piece).setCoordinates(
              new Coordinates(
                  piece.getCoordinates().getVertical() + i,
                  piece.getCoordinates().getHorizontal() + i
              )
          );

          if (checkPieceInDestination(chessBoard, tempPrice)) {
            result = false;
            break;
          }
        }

      } else if (sideShiftVertical > 0 && sideShiftHorizontal < 0) {
        for (int i = 1; i < sideShiftVertical; i++) {

          IPieces tempPrice = new Rook(piece).setCoordinates(
              new Coordinates(
                  piece.getCoordinates().getVertical() + i,
                  piece.getCoordinates().getHorizontal() - i
              )
          );

          if (checkPieceInDestination(chessBoard, tempPrice)) {
            result = false;
            break;
          }
        }

      } else if (sideShiftVertical < 0 && sideShiftHorizontal > 0) {
        for (int i = -1; i > sideShiftVertical; i--) {

          IPieces tempPrice = new Rook(piece).setCoordinates(
              new Coordinates(
                  piece.getCoordinates().getVertical() + i,
                  piece.getCoordinates().getHorizontal() - i
              )
          );

          if (checkPieceInDestination(chessBoard, tempPrice)) {
            result = false;
            break;
          }
        }

      }

      return result;
    }

    private boolean checkQueen(ChessBoard chessBoard, IPieces piece, Function<IPieces, IPieces> action) {
      return checkRook(chessBoard, piece, action) && checkBishop(chessBoard, piece, action);
    }

    private boolean checkKing(ChessBoard chessBoard, IPieces piece, Function<IPieces, IPieces> action) {
      if (chessBoard.getPriority() == WHITE) {
        IPieces king = getPiece(chessBoard, KING, WHITE);
        IPieces leftRook = getPieceInSquare(chessBoard, new Coordinates(0, 0), WHITE);
        IPieces rightRook = getPieceInSquare(chessBoard, new Coordinates(0, 7), WHITE);

        IPieces leftKnight = getPieceInSquare(chessBoard, new Coordinates(0, 1), WHITE);
        IPieces rightKnight = getPieceInSquare(chessBoard, new Coordinates(0, 6), WHITE);

        IPieces leftBishop = getPieceInSquare(chessBoard, new Coordinates(0, 2), WHITE);
        IPieces rightBishop = getPieceInSquare(chessBoard, new Coordinates(0, 5), WHITE);

        IPieces queen = getPieceInSquare(chessBoard, new Coordinates(0, 3), WHITE);

        Set<Coordinates> whiteList = Set.of(
            new Coordinates(0, 1),
            new Coordinates(0, 2),
            new Coordinates(0, 3),
            new Coordinates(0, 4),
            new Coordinates(0, 5),
            new Coordinates(0, 6)
        );

//        Set<Coordinates> attackedFields = getAttackedFieldList(chessBoard, BLACK)
//            .stream()
//            .filter(whiteList::contains)
//            .collect(Collectors.toSet());
//
//        return attackedFields.size() == 0 && !king.getMoveBefore() && leftRook != null && rightRook!= null &&
//            !leftRook.getMoveBefore() && !rightRook.getMoveBefore() &&
//            leftKnight == null && rightKnight == null && leftBishop == null && rightBishop == null && queen == null;

        return !king.getMoveBefore() && leftRook != null && rightRook!= null &&
            !leftRook.getMoveBefore() && !rightRook.getMoveBefore() &&
            leftKnight == null && rightKnight == null && leftBishop == null && rightBishop == null && queen == null;

      } else {
        IPieces king = getPiece(chessBoard, KING, BLACK);
        IPieces leftRook = getPieceInSquare(chessBoard, new Coordinates(7, 0), BLACK);
        IPieces rightRook = getPieceInSquare(chessBoard, new Coordinates(7, 7), BLACK);

        IPieces leftKnight = getPieceInSquare(chessBoard, new Coordinates(7, 1), BLACK);
        IPieces rightKnight = getPieceInSquare(chessBoard, new Coordinates(7, 6), BLACK);

        IPieces leftBishop = getPieceInSquare(chessBoard, new Coordinates(7, 2), BLACK);
        IPieces rightBishop = getPieceInSquare(chessBoard, new Coordinates(7, 5), BLACK);

        IPieces queen = getPieceInSquare(chessBoard, new Coordinates(7, 3), BLACK);

        Set<Coordinates> whiteList = Set.of(
            new Coordinates(7, 1),
            new Coordinates(7, 2),
            new Coordinates(7, 3),
            new Coordinates(7, 4),
            new Coordinates(7, 5),
            new Coordinates(7, 6)
        );

//        Set<Coordinates> attackedFields = getAttackedFieldList(chessBoard, WHITE)
//            .stream()
//            .filter(whiteList::contains)
//            .collect(Collectors.toSet());
//
//        return attackedFields.size() == 0 && !king.getMoveBefore() && leftRook != null && rightRook!= null &&
//            !leftRook.getMoveBefore() && !rightRook.getMoveBefore() &&
//            leftKnight == null && rightKnight == null && leftBishop == null && rightBishop == null && queen == null;

        return !king.getMoveBefore() && leftRook != null && rightRook!= null &&
            !leftRook.getMoveBefore() && !rightRook.getMoveBefore() &&
            leftKnight == null && rightKnight == null && leftBishop == null && rightBishop == null && queen == null;
      }
    }

  }

}
