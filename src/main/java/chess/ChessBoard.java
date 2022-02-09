package chess;

import static chess.Colors.BLACK;
import static chess.Colors.WHITE;
import static chess.Names.BISHOP;
import static chess.Names.KING;
import static chess.Names.KNIGHT;
import static chess.Names.PAWN;
import static chess.Names.QUEEN;
import static chess.Names.ROOK;

import chess.pieces.Bishop;
import chess.pieces.BlackPawn;
import chess.pieces.IPieces;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Queen;
import chess.pieces.Rook;
import chess.pieces.WhitePawn;
import java.util.ArrayList;
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

  public IPieces createPiece(Colors color, Names name, Coordinates coordinate) {
    switch (name) {
      case KING:
        return new King(name, color, coordinate);

      case QUEEN:
        return new Queen(name, color, coordinate);

      case ROOK:
        return new Rook(name, color, coordinate);

      case BISHOP:
        return new Bishop(name, color, coordinate);

      case KNIGHT:
        return new Knight(name, color, coordinate);

      case PAWN: {
        if (color == WHITE) {
          return new WhitePawn(name, color, coordinate);

        } else {
          return new BlackPawn(name, color, coordinate);
        }
      }

      default:
        throw new RuntimeException("Что-то не так с фигурами");
    }
  }

  public List<IPieces> getDefaultBoard() {
    List<IPieces> pieces = new ArrayList<>();

    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 0)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 1)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 2)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 3)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 4)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 5)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 6)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 7)));

    pieces.add(createPiece(WHITE, ROOK, new Coordinates(0, 0)));
    pieces.add(createPiece(WHITE, KNIGHT, new Coordinates(0, 1)));
    pieces.add(createPiece(WHITE, BISHOP, new Coordinates(0, 2)));
    pieces.add(createPiece(WHITE, QUEEN, new Coordinates(0, 3)));
    pieces.add(createPiece(WHITE, KING, new Coordinates(0, 4)));
    pieces.add(createPiece(WHITE, BISHOP, new Coordinates(0, 5)));
    pieces.add(createPiece(WHITE, KNIGHT, new Coordinates(0, 6)));
    pieces.add(createPiece(WHITE, ROOK, new Coordinates(0, 7)));

    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 0)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 1)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 2)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 3)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 4)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 5)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 6)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 7)));

    pieces.add(createPiece(BLACK, ROOK, new Coordinates(7, 0)));
    pieces.add(createPiece(BLACK, KNIGHT, new Coordinates(7, 1)));
    pieces.add(createPiece(BLACK, BISHOP, new Coordinates(7, 2)));
    pieces.add(createPiece(BLACK, QUEEN, new Coordinates(7, 3)));
    pieces.add(createPiece(BLACK, KING, new Coordinates(7, 4)));
    pieces.add(createPiece(BLACK, BISHOP, new Coordinates(7, 5)));
    pieces.add(createPiece(BLACK, KNIGHT, new Coordinates(7, 6)));
    pieces.add(createPiece(BLACK, ROOK, new Coordinates(7, 7)));

    return pieces;
  }

  /**
   * Сделать какой-нибудь ход какой-нибудь фигурой
   */
  public void makeMove() {
    Random random = new Random();

    List<IPieces> pieceList = pieces
        .stream()
        .filter(p -> p.getColor() == priority)
        .filter(p -> p.getActions().stream().anyMatch(a -> checkAction(p, a)))
        .collect(Collectors.toList());

    //Выбрать фигуру
    IPieces pieceBefore = pieceList.get(random.nextInt(pieceList.size()));

    List<Function<IPieces, IPieces>> actionList = pieceBefore
        .getActions()
        .stream()
        .filter(a -> checkAction(pieceBefore, a))
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
    List<IPieces> kings =  pieces.stream()
        .filter(p -> p.getName() == KING)
        .collect(Collectors.toList());

    if (kings.size() < 2) {
      Colors color = kings.get(0).getColor();

      if (color == WHITE) {
        System.out.println("WHITE is win");

      } else {
        System.out.println("BLACK is win");
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
  public boolean checkAction(IPieces piece, Function<IPieces, IPieces> action) {
    IPieces pieceAfter = action.apply(piece);

    int vertical = pieceAfter.getCoordinates().getVertical();
    int horizontal = pieceAfter.getCoordinates().getHorizontal();


    if (vertical > 7 || vertical < 0) {
      return false;
    }

    if (horizontal > 7 || horizontal < 0) {
      return false;
    }

    IPieces destinationPiece = this.pieces.stream()
        .filter(p -> (p.getCoordinates().getVertical() == vertical) && (p.getCoordinates().getHorizontal() == horizontal))
        .findAny()
        .orElse(null);

    if (destinationPiece == null) {
      return true;
    }

    if (pieceAfter.getColor() == destinationPiece.getColor()) {
      return false;
    }

    return true;
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
