package chess.legacy.pieces;

import chess.enums.Colors;
import chess.pojo.Square;
import chess.enums.Names;
import java.util.List;
import java.util.function.Function;

public class King implements IPieces {

  private final Colors color;

  private Square coordinates;

  private boolean moveBefore = false;

  public King(IPieces piece) {
    this.color = piece.getColor();
    this.coordinates = piece.getSquare();
  }

  public King(Colors color, Square coordinates) {
    this.color = color;
    this.coordinates = coordinates;
  }

  @Override
  public Names getName() {
    return Names.KING;
  }

  @Override
  public Colors getColor() {
    return color;
  }

  @Override
  public Square getSquare() {
    return coordinates;
  }

  @Override
  public IPieces setCoordinates(Square coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  @Override
  public List<Function<IPieces, IPieces>> getActions() {
    int vertical = getSquare().getVertical();
    int horizontal = getSquare().getHorizontal();

    Function<IPieces, IPieces> up1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Square(vertical + 1, horizontal));
    Function<IPieces, IPieces> down1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Square(vertical - 1, horizontal));
    Function<IPieces, IPieces> right1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal + 1));
    Function<IPieces, IPieces> left1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal - 1));

    Function<IPieces, IPieces> upAndRight1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Square(vertical + 1, horizontal + 1));
    Function<IPieces, IPieces> upAndLeft1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Square(vertical + 1, horizontal - 1));
    Function<IPieces, IPieces> downAndRight1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Square(vertical - 1, horizontal + 1));
    Function<IPieces, IPieces> downAndLeft1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Square(vertical - 1, horizontal - 1));

    Function<IPieces, IPieces> castling1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal + 2));
    Function<IPieces, IPieces> castling2 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal - 2));

    List<Function<IPieces, IPieces>> actions = List.of(
        up1,
        down1,
        right1,
        left1,
        upAndRight1,
        upAndLeft1,
        downAndRight1,
        downAndLeft1,
        castling1,
        castling2
    );

    return actions;
  }

  @Override
  public boolean getMoveBefore() {
    return moveBefore;
  }

  @Override
  public IPieces setMoveBefore(boolean moveBefore) {
    this.moveBefore = moveBefore;
    return this;
  }

  @Override
  public String toString() {
    String color = this.getColor().toString();
    String name = this.getName().toString();

    return color + name;
  }

}
