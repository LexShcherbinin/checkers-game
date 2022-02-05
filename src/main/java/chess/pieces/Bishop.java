package chess.pieces;

import chess.Colors;
import chess.Coordinates;
import chess.Names;
import java.util.List;
import java.util.function.Consumer;

public class Bishop implements IPieces {

  private IPieces piece;

  private Names name;

  private Colors color;

  private Coordinates coordinates;

  public Bishop(IPieces piece) {
    this.piece = piece;
  }

  public Bishop(Names name, Colors color, Coordinates coordinates) {
    this.name = name;
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
  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public List<Consumer<IPieces>> getActions() {
    int vertical = getCoordinates().getVertical();
    int horizontal = getCoordinates().getHorizontal();

    Consumer<IPieces> upAndRight1 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 1, horizontal + 1));
    Consumer<IPieces> upAndRight2 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 2, horizontal + 2));
    Consumer<IPieces> upAndRight3 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 3, horizontal + 3));
    Consumer<IPieces> upAndRight4 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 4, horizontal + 4));
    Consumer<IPieces> upAndRight5 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 5, horizontal + 5));
    Consumer<IPieces> upAndRight6 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 6, horizontal + 6));
    Consumer<IPieces> upAndRight7 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 7, horizontal + 7));

    Consumer<IPieces> upAndLeft1 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 1, horizontal - 1));
    Consumer<IPieces> upAndLeft2 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 2, horizontal - 2));
    Consumer<IPieces> upAndLeft3 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 3, horizontal - 3));
    Consumer<IPieces> upAndLeft4 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 4, horizontal - 4));
    Consumer<IPieces> upAndLeft5 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 5, horizontal - 5));
    Consumer<IPieces> upAndLeft6 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 6, horizontal - 6));
    Consumer<IPieces> upAndLeft7 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical + 7, horizontal - 7));

    Consumer<IPieces> downAndRight1 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 1, horizontal + 1));
    Consumer<IPieces> downAndRight2 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 2, horizontal + 2));
    Consumer<IPieces> downAndRight3 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 3, horizontal + 3));
    Consumer<IPieces> downAndRight4 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 4, horizontal + 4));
    Consumer<IPieces> downAndRight5 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 5, horizontal + 5));
    Consumer<IPieces> downAndRight6 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 6, horizontal + 6));
    Consumer<IPieces> downAndRight7 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 7, horizontal + 7));

    Consumer<IPieces> downAndLeft1 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 1, horizontal - 1));
    Consumer<IPieces> downAndLeft2 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 2, horizontal - 2));
    Consumer<IPieces> downAndLeft3 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 3, horizontal - 3));
    Consumer<IPieces> downAndLeft4 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 4, horizontal - 4));
    Consumer<IPieces> downAndLeft5 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 5, horizontal - 5));
    Consumer<IPieces> downAndLeft6 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 6, horizontal - 6));
    Consumer<IPieces> downAndLeft7 = piece -> new Bishop(piece).setCoordinates(new Coordinates(vertical - 7, horizontal - 7));

    List<Consumer<IPieces>> actions = List.of(
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
  public String toString() {
    String color = this.getColor().toString();
    String name = this.getName().toString();

    return color + name;
  }

}
