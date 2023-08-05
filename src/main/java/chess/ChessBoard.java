package chess;

import static chess.Colors.BLACK;
import static chess.Colors.WHITE;
import static chess.Names.KING;
import static chess.Names.PAWN;
import static chess.PieceHelper.checkEnemyKingOnBoard;
import static chess.PieceHelper.getAttackedFieldList;
import static chess.PieceHelper.getMoveList;
import static chess.PieceHelper.getPieceInSquare;
import static chess.PieceHelper.getRivalColor;
import static chess.PiecesCreator.getDefaultBoard;

import chess.pieces.IPieces;
import chess.pieces.Queen;
import chess.pieces.Rook;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class ChessBoard {

  ChessBoard chessBoard;

  /**
   * Список фигур на доске
   */
  private final List<IPieces> pieces;

  /**
   * Приоритет хода
   */
  private Colors priority = WHITE;

  /**
   * Последний сделанный ход
   */
  private String lastStep = "";

  /**
   * Количество съеденных фигур
   */
  private int eatPiecesCount = 0;

  /**
   * Количество сделанных шагов
   */
  private int stepCount = 1;

  /**
   * Список атакуемых полей
   */
  private Set<Coordinates> attackedFields = new HashSet<>();

  public ChessBoard() {
    this.pieces = new ArrayList<>(getDefaultBoard());
  }

  public ChessBoard(List<IPieces> pieceList) {
    this.pieces = new ArrayList<>(pieceList);
  }

  public ChessBoard(ChessBoard chessBoard) {
    this.chessBoard = chessBoard;
    this.pieces = chessBoard.getPieces();
  }

  public List<IPieces> getPieces() {
    return pieces;
  }

  public Colors getPriority() {
    return priority;
  }

  public Set<Coordinates> getAttackedFields() {
    return attackedFields;
  }

  public String getLastStep() {
    return lastStep;
  }

  public Map<IPieces, List<Function<IPieces, IPieces>>> getMoveMap() {
    return getMoveList(this);
  }

  /**
   * Сделать какой-нибудь ход какой-нибудь фигурой
   */
  public void makeMove() {
    //Получить полный список всех возможных ходов. Убийцы королей в приоритете
    Map<IPieces, List<Function<IPieces, IPieces>>> moveList = getMoveMap();

    IPieces pieceBefore = getPrices(moveList);
    Function<IPieces, IPieces> action = getAction(moveList, pieceBefore);

    IPieces pieceAfter = action.apply(pieceBefore);

    // Проверить, есть ли в месте назначения фигура противоположного цвета
    IPieces enemyPrice = getPieceInSquare(this, pieceAfter.getCoordinates(), getRivalColor(this));

    // Если есть, удалить её с доски
    if (enemyPrice != null) {
      pieces.remove(enemyPrice);
      eatPiecesCount++;

    } else if (pieceAfter.getName() == PAWN) {
      int horizontalBefore = pieceBefore.getCoordinates().getHorizontal();
      int horizontalAfter = pieceAfter.getCoordinates().getHorizontal();
      int sideShiftHorizontal = Math.abs(horizontalBefore - horizontalAfter);

      if (sideShiftHorizontal == 1 && pieceAfter.getColor() == WHITE) {

        Coordinates coordinates = new Coordinates(
            pieceAfter.getCoordinates().getVertical() - 1,
            pieceAfter.getCoordinates().getHorizontal()
        );

        IPieces enemyPiece = getPieceInSquare(this, coordinates, BLACK);

        if (enemyPiece != null && !enemyPiece.getMoveBefore()) {
          pieces.remove(enemyPiece);
          eatPiecesCount++;
        }

      } else if (sideShiftHorizontal == 1 && pieceAfter.getColor() == BLACK) {
        Coordinates coordinates = new Coordinates(
            pieceAfter.getCoordinates().getVertical() + 1,
            pieceAfter.getCoordinates().getHorizontal()
        );

        IPieces enemyPiece = getPieceInSquare(this, coordinates, WHITE);

        if (enemyPiece != null && !enemyPiece.getMoveBefore()) {
          pieces.remove(enemyPiece);
          eatPiecesCount++;
        }
      }
    }

    pieces.remove(pieceBefore);

    // Если пешка добралась до противоположной стороны, превратить её в ферзя
    if (pieceAfter.getName() == PAWN) {
      if (pieceAfter.getColor() == WHITE && pieceAfter.getCoordinates().getVertical() == 7) {
        pieceAfter = new Queen(WHITE, pieceAfter.getCoordinates());

      } else if (pieceAfter.getColor() == BLACK && pieceAfter.getCoordinates().getVertical() == 0) {
        pieceAfter = new Queen(BLACK, pieceAfter.getCoordinates());
      }
    }

    pieces.add(pieceAfter);

    //Проверка на рокировку
    if (pieceAfter.getName() == KING) {
      int horizontalBefore = pieceBefore.getCoordinates().getHorizontal();
      int horizontalAfter = pieceAfter.getCoordinates().getHorizontal();
      int sideShiftHorizontal = horizontalAfter - horizontalBefore;

      if (sideShiftHorizontal == 2) {
        if (pieceBefore.getColor() == WHITE) {
          IPieces rightRook = getPieceInSquare(this, new Coordinates(0, 7), WHITE);

          pieces.remove(rightRook);
          pieces.add(new Rook(WHITE, new Coordinates(0, 5)));

        } else {
          IPieces rightRook = getPieceInSquare(this, new Coordinates(7, 7), BLACK);

          pieces.remove(rightRook);
          pieces.add(new Rook(BLACK, new Coordinates(7, 5)));
        }

      } else if (sideShiftHorizontal == -2) {
        if (pieceBefore.getColor() == WHITE) {
          IPieces leftRook = getPieceInSquare(this, new Coordinates(0, 0), WHITE);

          pieces.remove(leftRook);
          pieces.add(new Rook(WHITE, new Coordinates(0, 3)));

        } else {
          IPieces leftRook = getPieceInSquare(this, new Coordinates(7, 0), BLACK);

          pieces.remove(leftRook);
          pieces.add(new Rook(BLACK, new Coordinates(7, 3)));
        }
      }
    }

    saveLastStep(pieceBefore, pieceAfter);

    // Проверить, не короля ли убили
    if (enemyPrice != null) {
      checkKing();
    }

    //Запомнить поля, которые находятся под ударом
    attackedFields = getAttackedFieldList(this, priority);

    // Передать ход другой стороне
    changePriority();
  }

  public void makeMove(IPieces piece, Function<IPieces, IPieces> action) {
    IPieces pieceBefore = piece;
    IPieces pieceAfter = action.apply(pieceBefore);

    // Проверить, есть ли в месте назначения фигура противоположного цвета
    IPieces enemyPrice = getPieceInSquare(this, pieceAfter.getCoordinates(), getRivalColor(this));

    // Если есть, удалить её с доски
    if (enemyPrice != null) {
      pieces.remove(enemyPrice);
      eatPiecesCount++;

    } else if (pieceAfter.getName() == PAWN) {
      int horizontalBefore = pieceBefore.getCoordinates().getHorizontal();
      int horizontalAfter = pieceAfter.getCoordinates().getHorizontal();
      int sideShiftHorizontal = Math.abs(horizontalBefore - horizontalAfter);

      if (sideShiftHorizontal == 1 && pieceAfter.getColor() == WHITE) {

        Coordinates coordinates = new Coordinates(
            pieceAfter.getCoordinates().getVertical() - 1,
            pieceAfter.getCoordinates().getHorizontal()
        );

        IPieces enemyPiece = getPieceInSquare(this, coordinates, BLACK);

        if (enemyPiece != null && !enemyPiece.getMoveBefore()) {
          pieces.remove(enemyPiece);
          eatPiecesCount++;
        }

      } else if (sideShiftHorizontal == 1 && pieceAfter.getColor() == BLACK) {
        Coordinates coordinates = new Coordinates(
            pieceAfter.getCoordinates().getVertical() + 1,
            pieceAfter.getCoordinates().getHorizontal()
        );

        IPieces enemyPiece = getPieceInSquare(this, coordinates, WHITE);

        if (enemyPiece != null && !enemyPiece.getMoveBefore()) {
          pieces.remove(enemyPiece);
          eatPiecesCount++;
        }
      }
    }

//    pieces.remove(pieceBefore);
    pieces.remove(PieceHelper.getPieceInSquare(this, pieceBefore.getCoordinates(), pieceBefore.getColor()));

    // Если пешка добралась до противоположной стороны, превратить её в ферзя
    if (pieceAfter.getName() == PAWN) {
      if (pieceAfter.getColor() == WHITE && pieceAfter.getCoordinates().getVertical() == 7) {
        pieceAfter = new Queen(WHITE, pieceAfter.getCoordinates());

      } else if (pieceAfter.getColor() == BLACK && pieceAfter.getCoordinates().getVertical() == 0) {
        pieceAfter = new Queen(BLACK, pieceAfter.getCoordinates());
      }
    }

    pieces.add(pieceAfter);

    //Проверка на рокировку
    if (pieceAfter.getName() == KING) {
      int horizontalBefore = pieceBefore.getCoordinates().getHorizontal();
      int horizontalAfter = pieceAfter.getCoordinates().getHorizontal();
      int sideShiftHorizontal = horizontalAfter - horizontalBefore;

      if (sideShiftHorizontal == 2) {
        if (pieceBefore.getColor() == WHITE) {
          IPieces rightRook = getPieceInSquare(this, new Coordinates(0, 7), WHITE);

          pieces.remove(rightRook);
          pieces.add(new Rook(WHITE, new Coordinates(0, 5)));

        } else {
          IPieces rightRook = getPieceInSquare(this, new Coordinates(7, 7), BLACK);

          pieces.remove(rightRook);
          pieces.add(new Rook(BLACK, new Coordinates(7, 5)));
        }

      } else if (sideShiftHorizontal == -2) {
        if (pieceBefore.getColor() == WHITE) {
          IPieces leftRook = getPieceInSquare(this, new Coordinates(0, 0), WHITE);

          pieces.remove(leftRook);
          pieces.add(new Rook(WHITE, new Coordinates(0, 3)));

        } else {
          IPieces leftRook = getPieceInSquare(this, new Coordinates(7, 0), BLACK);

          pieces.remove(leftRook);
          pieces.add(new Rook(BLACK, new Coordinates(7, 3)));
        }
      }
    }

    saveLastStep(pieceBefore, pieceAfter);

    // Проверить, не короля ли убили
    if (enemyPrice != null) {
      checkKing();
    }

    //Запомнить поля, которые находятся под ударом
    attackedFields = getAttackedFieldList(this, priority);

    // Передать ход другой стороне
    changePriority();
  }

  /**
   * Выбрать фигуру, которой можно пойти
   */
  public IPieces getPrices(Map<IPieces, List<Function<IPieces, IPieces>>> moveList) {
    List<IPieces> pieceList = new ArrayList<>(moveList.keySet());

//    if (pieceList.size() == 0) {
//      System.out.println("Move list is null");
//
//      if (this.getPriority() == WHITE) {
//        System.out.println("BLACK win");
//
//      } else {
//        System.out.println("WHITE win");
//      }
//
//      System.exit(0);
//    }

    return pieceList.get(new Random().nextInt(pieceList.size()));
  }

  /**
   * Выбрать ход, которым можно пойти фигурой
   */
  public Function<IPieces, IPieces> getAction(Map<IPieces, List<Function<IPieces, IPieces>>> moveList, IPieces piece) {
    List<Function<IPieces, IPieces>> actionList = moveList.get(piece);
    return actionList.get(new Random().nextInt(actionList.size()));
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
   * Проверить, не убили ли одного из королей
   */
  private void checkKing() {
    if (!checkEnemyKingOnBoard(this)) {
      if (this.getPriority() == WHITE) {
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
        " \t   A  B  C  D  E  F  G  H  \n";
  }

}
