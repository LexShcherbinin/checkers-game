package chess;

import static chess.Colors.BLACK;
import static chess.Colors.WHITE;
import static chess.Names.KING;
import static chess.PiecesCreator.getDefaultBoard;

import chess.pieces.IPieces;
import chess.pieces.Rook;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChessBoard {

  List<IPieces> pieces;

  Colors priority = WHITE;

  String lastStep = "";
  int eatPiecesCount = 0;
  int stepCount = 1;

  public ChessBoard() {
    this.pieces = getDefaultBoard();
  }

  /**
   * Сделать какой-нибудь ход какой-нибудь фигурой
   */
  public void makeMove() {
    Random random = new Random();

    //Получить список всех фигур, которыми можно пойти
    List<IPieces> pieceList = pieces
        .stream()
        .filter(p -> p.getColor() == priority)
        .filter(p -> p
            .getActions()
            .stream()
            .anyMatch(a -> checkMove(p, a))
        )
        .collect(Collectors.toList());

    //Выбрать фигуру
    IPieces pieceBefore = pieceList.get(random.nextInt(pieceList.size()));

    //Получить список всех ходов, которыми можно пойти
    List<Function<IPieces, IPieces>> actionList = pieceBefore
        .getActions()
        .stream()
        .filter(a -> checkMove(pieceBefore, a))
        .collect(Collectors.toList());

    //Выбрать ход
    Function<IPieces, IPieces> action = actionList.get(random.nextInt(actionList.size()));

    IPieces pieceAfter = action.apply(pieceBefore);

    //Проверить, есть ли в месте назначения фигура противоположного цвета
    IPieces pieceDestination = getDestinationPiece(pieceAfter);

    //Если есть, удалить её с доски
    if (pieceDestination != null) {
      pieces.remove(pieceDestination);
      eatPiecesCount++;
    }

    pieces.remove(pieceBefore);
    pieces.add(pieceAfter);

    saveLastStep(pieceBefore, pieceAfter);

    //Проверить, не короля ли убили
    if (pieceDestination != null) {
      checkKing();
    }

    //Передать ход другой стороне
    changePriority();
  }

  /**
   * Запомнить последний ход
   */
  private void saveLastStep(IPieces pieceBefore, IPieces pieceAfter) {
    lastStep = String.format(
        "%s[%s -> %s] (Killing pieces = %s, step = %s)",
        pieceBefore, pieceBefore.getCoordinates(), pieceAfter.getCoordinates(), eatPiecesCount, stepCount
    );
  }

  /**
   * Проверить, есть ли в месте назначения фигура противоположного цвета
   */
  private IPieces getDestinationPiece(IPieces piece) {
    return pieces
        .stream()
        .filter(p -> p.getCoordinates().equals(piece.getCoordinates()) && p.getColor() != piece.getColor())
        .findFirst()
        .orElse(null);
  }

  /**
   * Проверить, не убили ли одного из королей
   */
  private void checkKing() {
    List<IPieces> kings = pieces.stream()
        .filter(p -> p.getName() == KING)
        .collect(Collectors.toList());

    if (kings.size() < 2) {
      Colors color = kings.get(0).getColor();

      if (color == WHITE) {
        System.out.println("WHITE win");

      } else {
        System.out.println("BLACK win");
      }

      System.out.println(this);
      System.exit(0);
    }
  }

  /**
   * Передать ход другой стороне
   */
  private void changePriority() {
    stepCount++;

    if (priority == WHITE) {
      priority = BLACK;

    } else {
      priority = WHITE;
    }
  }

  /**
   * Проверка, можно ли ТАК пойти (надо доработать)
   */
  public boolean checkMove(IPieces piece, Function<IPieces, IPieces> action) {
    IPieces pieceAfter = action.apply(piece);

    if (!checkBoardBorders(pieceAfter)) {
      return false;
    }

    if (checkMyPieceInDestination(pieceAfter)) {
      return false;
    }

    switch (pieceAfter.getName()) {
      case PAWN: return new checkPieces().checkPawn(piece, action);
      case ROOK: return new checkPieces().checkRook(piece, action);
      case BISHOP: return new checkPieces().checkBishop(piece, action);
      case QUEEN: return new checkPieces().checkQueen(piece, action);
    }

    return true;
  }

  /**
   * Проверяет, не выходит ли фигура за пределы доски
   *
   * @param piece - фигура после того, как сделает ход
   * @return - возвращает true, если фигура не выходит за рамки доски, false - если выходит
   */
  private boolean checkBoardBorders(IPieces piece) {
    int vertical = piece.getCoordinates().getVertical();
    int horizontal = piece.getCoordinates().getHorizontal();

    return vertical < 8 && vertical >= 0 && horizontal < 8 && horizontal >= 0;
  }

  /**
   * Проверяет, есть ли своя фигура в том месте, куда хочет пойти ходящая фигура
   *
   * @param piece - фигура после того, как сделает ход (ходящая фигура)
   * @return - возвращает true, если в конечной точке есть своя фигура, false - если фигур нет
   */
  private boolean checkPieceInDestination(IPieces piece) {
    return this.pieces
        .stream()
        .anyMatch(p -> p.getCoordinates().equals(piece.getCoordinates()));
  }

  /**
   * Проверяет, есть ли своя фигура в том месте, куда хочет пойти ходящая фигура
   *
   * @param piece - фигура после того, как сделает ход (ходящая фигура)
   * @return - возвращает true, если в конечной точке есть своя фигура, false - если фигур нет
   */
  private boolean checkMyPieceInDestination(IPieces piece) {
    return this.pieces
        .stream()
        .anyMatch(p -> p.getCoordinates().equals(piece.getCoordinates()) && p.getColor().equals(piece.getColor()));
  }

  /**
   * Проверяет, есть ли чужая фигура в том месте, куда хочет пойти ходящая фигура
   *
   * @param piece - фигура после того, как сделает ход (ходящая фигура)
   * @return - возвращает true, если в конечной точке есть чужая фигура, false - если фигур нет
   */
  private boolean checkOppositePieceInDestination(IPieces piece) {
    return this.pieces
        .stream()
        .anyMatch(p -> p.getCoordinates().equals(piece.getCoordinates()) && !p.getColor().equals(piece.getColor()));
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

    String[][] board = new String[][] {
        {" *", " *", " *", " *", " *", " *", " *", " *"},
        {" *", " *", " *", " *", " *", " *", " *", " *"},
        {" *", " *", " *", " *", " *", " *", " *", " *"},
        {" *", " *", " *", " *", " *", " *", " *", " *"},
        {" *", " *", " *", " *", " *", " *", " *", " *"},
        {" *", " *", " *", " *", " *", " *", " *", " *"},
        {" *", " *", " *", " *", " *", " *", " *", " *"},
        {" *", " *", " *", " *", " *", " *", " *", " *"}
    };

    for (IPieces piece : pieces) {
      int vertical = piece.getCoordinates().getVertical();
      int horizontal = piece.getCoordinates().getHorizontal();

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
        " \t   a  b  c  d  e  f  g  h  \n";
  }

  private class checkPieces {

    private boolean checkPawn(IPieces piece, Function<IPieces, IPieces> action) {
      IPieces pieceAfter = action.apply(piece);

      int verticalBefore = piece.getCoordinates().getVertical();
      int verticalAfter = pieceAfter.getCoordinates().getVertical();

      int horizontalBefore = piece.getCoordinates().getHorizontal();
      int horizontalAfter = pieceAfter.getCoordinates().getHorizontal();

      int sideShift = Math.abs(horizontalBefore - horizontalAfter);

      if (pieceAfter.getColor() == WHITE) {
        if (checkOppositePieceInDestination(pieceAfter)) {
          if (sideShift == 0) {
            return false;

          } else {
            return true;
          }

        } else {
          if (sideShift == 1) {
            return false;

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
        if (checkOppositePieceInDestination(pieceAfter)) {
          if (sideShift == 0) {
            return false;

          } else {
            return true;
          }

        } else {
          if (sideShift == 1) {
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

    private boolean checkRook(IPieces piece, Function<IPieces, IPieces> action) {
      IPieces pieceAfter = action.apply(piece);
      boolean result = true;

      int verticalBefore = piece.getCoordinates().getVertical();
      int verticalAfter = pieceAfter.getCoordinates().getVertical();
      int sideShiftVertical = verticalAfter - verticalBefore;

      int horizontalBefore = piece.getCoordinates().getHorizontal();
      int horizontalAfter = pieceAfter.getCoordinates().getHorizontal();
      int sideShiftHorizontal = horizontalAfter - horizontalBefore;

      if (sideShiftVertical > 0) {
        for (int i = 1; i < sideShiftVertical; i++) {

          IPieces tempPrice = new Rook(piece).setCoordinates(
              new Coordinates(
                  piece.getCoordinates().getVertical() + i,
                  horizontalAfter
              )
          );

          if (checkPieceInDestination(tempPrice)) {
            result = false;
            break;
          }
        }

      } else if (sideShiftVertical < 0){
        for (int i = -1; i > sideShiftVertical; i--) {

          IPieces tempPrice = new Rook(piece).setCoordinates(
              new Coordinates(
                  piece.getCoordinates().getVertical() + i,
                  horizontalAfter
              )
          );

          if (checkPieceInDestination(tempPrice)) {
            result = false;
            break;
          }
        }

      } else if (sideShiftHorizontal > 0){
        for (int i = 1; i < sideShiftHorizontal; i++) {

          IPieces tempPrice = new Rook(piece).setCoordinates(
              new Coordinates(
                  verticalAfter,
                  piece.getCoordinates().getHorizontal() + i
              )
          );

          if (checkPieceInDestination(tempPrice)) {
            result = false;
            break;
          }
        }

      } else if (sideShiftHorizontal < 0){
        for (int i = -1; i > sideShiftHorizontal; i--) {

          IPieces tempPrice = new Rook(piece).setCoordinates(
              new Coordinates(
                  verticalAfter,
                  piece.getCoordinates().getHorizontal() + i
              )
          );

          if (checkPieceInDestination(tempPrice)) {
            result = false;
            break;
          }
        }

      }

      return result;
    }

    private boolean checkBishop(IPieces piece, Function<IPieces, IPieces> action) {
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

          if (checkPieceInDestination(tempPrice)) {
            result = false;
            break;
          }
        }

      } else if (sideShiftVertical < 0 && sideShiftHorizontal < 0){
        for (int i = -1; i > sideShiftVertical; i--) {

          IPieces tempPrice = new Rook(piece).setCoordinates(
              new Coordinates(
                  piece.getCoordinates().getVertical() + i,
                  piece.getCoordinates().getHorizontal() + i
              )
          );

          if (checkPieceInDestination(tempPrice)) {
            result = false;
            break;
          }
        }

      } else if (sideShiftVertical > 0 && sideShiftHorizontal < 0){
        for (int i = 1; i < sideShiftVertical; i++) {

          IPieces tempPrice = new Rook(piece).setCoordinates(
              new Coordinates(
                  piece.getCoordinates().getVertical() + i,
                  piece.getCoordinates().getHorizontal() - i
              )
          );

          if (checkPieceInDestination(tempPrice)) {
            result = false;
            break;
          }
        }

      } else if (sideShiftVertical < 0 && sideShiftHorizontal > 0){
        for (int i = -1; i > sideShiftVertical; i--) {

          IPieces tempPrice = new Rook(piece).setCoordinates(
              new Coordinates(
                  piece.getCoordinates().getVertical() + i,
                  piece.getCoordinates().getHorizontal() - i
              )
          );

          if (checkPieceInDestination(tempPrice)) {
            result = false;
            break;
          }
        }

      }

      return result;
    }

    private boolean checkQueen(IPieces piece, Function<IPieces, IPieces> action) {
      IPieces pieceAfter = action.apply(piece);

      int verticalBefore = piece.getCoordinates().getVertical();
      int verticalAfter = pieceAfter.getCoordinates().getVertical();

      int horizontalBefore = piece.getCoordinates().getHorizontal();
      int horizontalAfter = pieceAfter.getCoordinates().getHorizontal();

      return true;
    }

  }
}
