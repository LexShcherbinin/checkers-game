package chess;

import static chess.Colors.WHITE;

import chess.pieces.BlackPawn;
import chess.pieces.IPieces;
import chess.pieces.Rook;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PieceHelper {

  /**
   * Выбрать фигуру, которой можно пойти
   */
  public static IPieces getPrices(ChessBoard chessBoard) {
    Random random = new Random();

    // Получить список всех фигур, которыми можно пойти
    List<IPieces> pieceList = chessBoard.getPieces()
        .stream()
        .filter(p -> p.getColor() == chessBoard.getPriority())
        .filter(p -> p
            .getActions()
            .stream()
            .anyMatch(a -> checkMove(chessBoard, p, a))
        )
        .collect(Collectors.toList());

    // Выбрать фигуру
    return pieceList.get(random.nextInt(pieceList.size()));
  }

  /**
   * Выбрать ход, которым можно пойти фигурой
   */
  public static Function<IPieces, IPieces> getAction(ChessBoard chessBoard, IPieces piece) {
    Random random = new Random();

    // Получить список всех ходов, которыми можно пойти
    List<Function<IPieces, IPieces>> actionList = piece
        .getActions()
        .stream()
        .filter(a -> checkMove(chessBoard, piece, a))
        .collect(Collectors.toList());

    // Выбрать ход
    return actionList.get(random.nextInt(actionList.size()));
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
  private static boolean checkOppositePieceInDestination(ChessBoard chessBoard, IPieces piece) {
    return chessBoard.getPieces()
        .stream()
        .anyMatch(p -> p.getCoordinates().equals(piece.getCoordinates()) && !p.getColor().equals(piece.getColor()));
  }

  /**
   * Проверить, есть ли в месте назначения фигура противоположного цвета
   */
  private static IPieces getDestinationPiece(ChessBoard chessBoard, IPieces piece) {
    return chessBoard.getPieces()
        .stream()
        .filter(p -> p.getCoordinates().equals(piece.getCoordinates()) && p.getColor() != piece.getColor())
        .findFirst()
        .orElse(null);
  }

  private static class CheckPieces {

    private boolean checkPawn(ChessBoard chessBoard, IPieces piece, Function<IPieces, IPieces> action) {
      IPieces pieceAfter = action.apply(piece);

      int verticalBefore = piece.getCoordinates().getVertical();
      int verticalAfter = pieceAfter.getCoordinates().getVertical();

      int horizontalBefore = piece.getCoordinates().getHorizontal();
      int horizontalAfter = pieceAfter.getCoordinates().getHorizontal();

      int sideShiftHorizontal = Math.abs(horizontalBefore - horizontalAfter);

      if (pieceAfter.getColor() == WHITE) {
        if (checkOppositePieceInDestination(chessBoard, pieceAfter)) {
          if (sideShiftHorizontal == 0) {
            return false;

          } else {
            return true;
          }

        } else {
          if (sideShiftHorizontal == 1) {

            IPieces www = new BlackPawn(new Coordinates(
                pieceAfter.getCoordinates().getVertical() - 1,
                pieceAfter.getCoordinates().getHorizontal()
            ));

            www.setMoveBefore(false);

            IPieces pieceDestination = getDestinationPiece(chessBoard, www);

            if (pieceDestination != null && !pieceDestination.getMoveBefore()) {
              return true;

            } else {
              return false;
            }

          } else {
            if (verticalAfter - verticalBefore == 1) {
              return true;

            } else if (verticalBefore == 1) {
              return true;

            } else {
              return false;
            }
          }
        }

      } else {
        if (checkOppositePieceInDestination(chessBoard, pieceAfter)) {
          if (sideShiftHorizontal == 0) {
            return false;

          } else {
            return true;
          }

        } else {
          if (sideShiftHorizontal == 1) {
            return false;

          } else {
            if (verticalAfter - verticalBefore == -1) {
              return true;

            } else if (verticalBefore == 6) {
              return true;

            } else {
              return false;
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

  }

}
