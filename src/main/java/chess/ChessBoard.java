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
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChessBoard {

  List<IPieces> pieces;

  Colors priority = WHITE;

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

  public void makeMove() {
    Random random = new Random();

    List<IPieces> pieceList = pieces
        .stream()
        .filter(p -> p.getColor() == priority)
        .filter(p -> p.getActions().stream().anyMatch(a -> checkAction(p, a)))
        .collect(Collectors.toList());

    IPieces accessiblePiece = pieceList.get(random.nextInt(pieceList.size()));

    IPieces accessiblePiece2 = accessiblePiece;

    List<Function<IPieces, IPieces>> actionList = accessiblePiece
        .getActions()
        .stream()
        .filter(a -> checkAction(accessiblePiece2, a))
        .collect(Collectors.toList());

    Function<IPieces, IPieces> action = actionList.get(random.nextInt(actionList.size()));

    pieces.remove(accessiblePiece);

    accessiblePiece = action.apply(accessiblePiece);

    pieces.add(accessiblePiece);
    changePriority();
  }

  private void changePriority() {
    if (priority == WHITE) {
      priority = BLACK;

    } else {
      priority = WHITE;
    }
  }

//  public List<Function<IPieces, IPieces>> checkPiece(IPieces piece) {
//    List<Function<IPieces, IPieces>> actionList = new ArrayList<>();
//
//    for (Function<IPieces, IPieces> action : piece.getActions()) {
//      if (checkAction(piece, action)) {
//        actionList.add(action);
//      }
//    }
//
//    return actionList;
//  }

  public boolean checkAction(IPieces piece, Function<IPieces, IPieces> action) {
    IPieces piece2 = action.apply(piece);

    int vertical = piece2.getCoordinates().getVertical();
    int horizontal = piece2.getCoordinates().getHorizontal();


    if (vertical > 7 || vertical < 0) {
      return false;
    }

    if (horizontal > 7 || horizontal < 0) {
      return false;
    }

    IPieces therePiece = this.pieces.stream()
        .filter(p -> p.getCoordinates() == piece2.getCoordinates())
        .findAny()
        .orElse(null);

    if (therePiece == null || piece2.getColor() != therePiece.getColor()) {
      return true;
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

    String result = "+-------------------------+\n| ";

    for (int i = 7; i >= 0; i--) {
      for (int j = 0; j < 8; j++) {
        result = result + board[i][j] + " ";
      }

      if (i == 0) {
        result = result + "|\n";

      } else {
        result = result + "|\n| ";
      }
    }

    return result + "+-------------------------+";
  }
}
