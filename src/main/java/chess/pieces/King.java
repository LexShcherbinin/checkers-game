package chess.pieces;

import chess.Colors;
import chess.Coordinates;
import chess.Names;
import java.util.List;
import java.util.function.Function;

public class King implements IPieces {

  private final Colors color;

  private Coordinates coordinates;

  private boolean moveBefore = false;

  public King(IPieces piece) {
    this.color = piece.getColor();
    this.coordinates = piece.getCoordinates();
  }

  public King(Colors color, Coordinates coordinates) {
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
  public Coordinates getCoordinates() {
    return coordinates;
  }

  @Override
  public IPieces setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  @Override
  public List<Function<IPieces, IPieces>> getActions() {
    int vertical = getCoordinates().getVertical();
    int horizontal = getCoordinates().getHorizontal();

    Function<IPieces, IPieces> up1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Coordinates(vertical + 1, horizontal));
    Function<IPieces, IPieces> down1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Coordinates(vertical - 1, horizontal));
    Function<IPieces, IPieces> right1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Coordinates(vertical, horizontal + 1));
    Function<IPieces, IPieces> left1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Coordinates(vertical, horizontal - 1));

    Function<IPieces, IPieces> upAndRight1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Coordinates(vertical + 1, horizontal + 1));
    Function<IPieces, IPieces> upAndLeft1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Coordinates(vertical + 1, horizontal - 1));
    Function<IPieces, IPieces> downAndRight1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Coordinates(vertical - 1, horizontal + 1));
    Function<IPieces, IPieces> downAndLeft1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Coordinates(vertical - 1, horizontal - 1));

    Function<IPieces, IPieces> castling1 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Coordinates(vertical, horizontal + 2));
    Function<IPieces, IPieces> castling2 =
        piece -> new King(piece).setMoveBefore(true).setCoordinates(new Coordinates(vertical, horizontal - 2));

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
