package chess.legacy.pieces;

import chess.enums.Colors;
import chess.Square;
import chess.enums.Names;
import java.util.List;
import java.util.function.Function;

public class Rook implements IPieces {

  private final Colors color;

  private Square coordinates;

  private boolean moveBefore = false;

  public Rook(IPieces piece) {
    this.color = piece.getColor();
    this.coordinates = piece.getCoordinates();
  }

  public Rook(Colors color, Square coordinates) {
    this.color = color;
    this.coordinates = coordinates;
  }

  @Override
  public Names getName() {
    return Names.ROOK;
  }

  @Override
  public Colors getColor() {
    return color;
  }

  @Override
  public Square getCoordinates() {
    return coordinates;
  }

  @Override
  public IPieces setCoordinates(Square coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  @Override
  public List<Function<IPieces, IPieces>> getActions() {
    int vertical = getCoordinates().getVertical();
    int horizontal = getCoordinates().getHorizontal();

    Function<IPieces, IPieces> up1 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical + 1, horizontal));
    Function<IPieces, IPieces> up2 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical + 2, horizontal));
    Function<IPieces, IPieces> up3 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical + 3, horizontal));
    Function<IPieces, IPieces> up4 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical + 4, horizontal));
    Function<IPieces, IPieces> up5 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical + 5, horizontal));
    Function<IPieces, IPieces> up6 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical + 6, horizontal));
    Function<IPieces, IPieces> up7 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical + 7, horizontal));

    Function<IPieces, IPieces> down1 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical - 1, horizontal));
    Function<IPieces, IPieces> down2 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical - 2, horizontal));
    Function<IPieces, IPieces> down3 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical - 3, horizontal));
    Function<IPieces, IPieces> down4 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical - 4, horizontal));
    Function<IPieces, IPieces> down5 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical - 5, horizontal));
    Function<IPieces, IPieces> down6 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical - 6, horizontal));
    Function<IPieces, IPieces> down7 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical - 7, horizontal));

    Function<IPieces, IPieces> right1 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal + 1));
    Function<IPieces, IPieces> right2 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal + 2));
    Function<IPieces, IPieces> right3 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal + 3));
    Function<IPieces, IPieces> right4 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal + 4));
    Function<IPieces, IPieces> right5 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal + 5));
    Function<IPieces, IPieces> right6 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal + 6));
    Function<IPieces, IPieces> right7 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal + 7));

    Function<IPieces, IPieces> left1 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal - 1));
    Function<IPieces, IPieces> left2 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal - 2));
    Function<IPieces, IPieces> left3 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal - 3));
    Function<IPieces, IPieces> left4 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal - 4));
    Function<IPieces, IPieces> left5 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal - 5));
    Function<IPieces, IPieces> left6 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal - 6));
    Function<IPieces, IPieces> left7 =
        piece -> new Rook(piece).setMoveBefore(true).setCoordinates(new Square(vertical, horizontal - 7));

    List<Function<IPieces, IPieces>> actions = List.of(
        up1,
        up2,
        up3,
        up4,
        up5,
        up6,
        up7,
        down1,
        down2,
        down3,
        down4,
        down5,
        down6,
        down7,
        right1,
        right2,
        right3,
        right4,
        right5,
        right6,
        right7,
        left1,
        left2,
        left3,
        left4,
        left5,
        left6,
        left7
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