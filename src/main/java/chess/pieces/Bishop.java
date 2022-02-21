package chess.pieces;

import chess.Colors;
import chess.Coordinates;
import chess.Names;
import java.util.List;
import java.util.function.Function;

public class Bishop implements IPieces {

  private final Colors color;

  private Coordinates coordinates;

  private boolean moveBefore = false;

  public Bishop(IPieces piece) {
    this.color = piece.getColor();
    this.coordinates = piece.getCoordinates();
  }

  public Bishop(Colors color, Coordinates coordinates) {
    this.color = color;
    this.coordinates = coordinates;
  }

  @Override
  public Names getName() {
    return Names.BISHOP;
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

    Function<IPieces, IPieces> upAndRight1 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 1, horizontal + 1));
    Function<IPieces, IPieces> upAndRight2 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 2, horizontal + 2));
    Function<IPieces, IPieces> upAndRight3 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 3, horizontal + 3));
    Function<IPieces, IPieces> upAndRight4 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 4, horizontal + 4));
    Function<IPieces, IPieces> upAndRight5 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 5, horizontal + 5));
    Function<IPieces, IPieces> upAndRight6 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 6, horizontal + 6));
    Function<IPieces, IPieces> upAndRight7 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 7, horizontal + 7));

    Function<IPieces, IPieces> upAndLeft1 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 1, horizontal - 1));
    Function<IPieces, IPieces> upAndLeft2 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 2, horizontal - 2));
    Function<IPieces, IPieces> upAndLeft3 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 3, horizontal - 3));
    Function<IPieces, IPieces> upAndLeft4 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 4, horizontal - 4));
    Function<IPieces, IPieces> upAndLeft5 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 5, horizontal - 5));
    Function<IPieces, IPieces> upAndLeft6 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 6, horizontal - 6));
    Function<IPieces, IPieces> upAndLeft7 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 7, horizontal - 7));

    Function<IPieces, IPieces> downAndRight1 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 1, horizontal + 1));
    Function<IPieces, IPieces> downAndRight2 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 2, horizontal + 2));
    Function<IPieces, IPieces> downAndRight3 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 3, horizontal + 3));
    Function<IPieces, IPieces> downAndRight4 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 4, horizontal + 4));
    Function<IPieces, IPieces> downAndRight5 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 5, horizontal + 5));
    Function<IPieces, IPieces> downAndRight6 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 6, horizontal + 6));
    Function<IPieces, IPieces> downAndRight7 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 7, horizontal + 7));

    Function<IPieces, IPieces> downAndLeft1 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 1, horizontal - 1));
    Function<IPieces, IPieces> downAndLeft2 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 2, horizontal - 2));
    Function<IPieces, IPieces> downAndLeft3 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 3, horizontal - 3));
    Function<IPieces, IPieces> downAndLeft4 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 4, horizontal - 4));
    Function<IPieces, IPieces> downAndLeft5 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 5, horizontal - 5));
    Function<IPieces, IPieces> downAndLeft6 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 6, horizontal - 6));
    Function<IPieces, IPieces> downAndLeft7 =
        piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 7, horizontal - 7));

    List<Function<IPieces, IPieces>> actions = List.of(
        upAndRight1,
        upAndRight2,
        upAndRight3,
        upAndRight4,
        upAndRight5,
        upAndRight6,
        upAndRight7,
        upAndLeft1,
        upAndLeft2,
        upAndLeft3,
        upAndLeft4,
        upAndLeft5,
        upAndLeft6,
        upAndLeft7,
        downAndRight1,
        downAndRight2,
        downAndRight3,
        downAndRight4,
        downAndRight5,
        downAndRight6,
        downAndRight7,
        downAndLeft1,
        downAndLeft2,
        downAndLeft3,
        downAndLeft4,
        downAndLeft5,
        downAndLeft6,
        downAndLeft7
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
