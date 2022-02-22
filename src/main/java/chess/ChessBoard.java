package chess;

import static chess.Colors.BLACK;
import static chess.Colors.WHITE;
import static chess.Names.KING;
import static chess.Names.PAWN;
import static chess.PieceHelper.getAction;
import static chess.PieceHelper.getPrices;
import static chess.PiecesCreator.getDefaultBoard;

import chess.pieces.BlackPawn;
import chess.pieces.IPieces;
import chess.pieces.Queen;
import chess.pieces.WhitePawn;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChessBoard {

  /**
   * Список фигур на доске
   */
  private List<IPieces> pieces;

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

  public ChessBoard() {
    this.pieces = getDefaultBoard();
  }

  public ChessBoard(List<IPieces> pieceList) {
    this.pieces = pieceList;
  }

  public List<IPieces> getPieces() {
    return pieces;
  }

  public Colors getPriority() {
    return priority;
  }

  /**
   * Сделать какой-нибудь ход какой-нибудь фигурой
   */
  public void makeMove() {
    IPieces pieceBefore = getPrices(this);
    Function<IPieces, IPieces> action = getAction(this, pieceBefore);

    IPieces pieceAfter = action.apply(pieceBefore);

    // Проверить, есть ли в месте назначения фигура противоположного цвета
    IPieces eatenPrice = getEatenPiece(pieceAfter);

    // Если есть, удалить её с доски
    if (eatenPrice != null) {
      pieces.remove(eatenPrice);
      eatPiecesCount++;

    } else if (pieceAfter.getName() == PAWN && action == pieceAfter.getActions().get(1)) {
      if (pieceAfter.getColor() == WHITE) {
        IPieces www = new BlackPawn(new Coordinates(
            pieceAfter.getCoordinates().getVertical() - 1,
            pieceAfter.getCoordinates().getHorizontal()
        ));

        System.out.println("en passant");

        pieces.remove(getEatenPiece(www));
        eatPiecesCount++;

      } else {
        IPieces www = new WhitePawn(new Coordinates(
            pieceAfter.getCoordinates().getVertical() + 1,
            pieceAfter.getCoordinates().getHorizontal()
        ));

        System.out.println("en passant");

        pieces.remove(getEatenPiece(www));
        eatPiecesCount++;
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

    saveLastStep(pieceBefore, pieceAfter);

    // Проверить, не короля ли убили
    if (eatenPrice != null) {
      checkKing();
    }

    // Передать ход другой стороне
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
   *
   * @param piece - фигура после того, как сделает ход
   * @return - возвращает фигуру, которую надо съесть, если она там есть, или null, если её там нет
   */
  private IPieces getEatenPiece(IPieces piece) {
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

}
