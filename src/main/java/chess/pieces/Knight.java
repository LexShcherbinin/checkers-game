package chess.pieces;

import chess.Colors;
import chess.Coordinates;
import chess.Names;
import java.util.List;
import java.util.function.Consumer;

public class Knight implements IPieces {

  private IPieces piece;

  private Names name;

  private Colors color;

  private Coordinates coordinates;

  public Knight(IPieces piece) {
    this.piece = piece;
  }

  public Knight(Names name, Colors color, Coordinates coordinates) {
    this.name = name;
    this.color = color;
    this.coordinates = coordinates;
  }

  @Override
  public Names getName() {
    return Names.KNIGHT;
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

    Consumer<IPieces> jump1 = piece -> new Knight(piece).setCoordinates(new Coordinates(vertical + 2, horizontal + 1));
    Consumer<IPieces> jump2 = piece -> new Knight(piece).setCoordinates(new Coordinates(vertical + 1, horizontal + 2));
    Consumer<IPieces> jump3 = piece -> new Knight(piece).setCoordinates(new Coordinates(vertical - 1, horizontal + 2));
    Consumer<IPieces> jump4 = piece -> new Knight(piece).setCoordinates(new Coordinates(vertical - 2, horizontal + 1));
    Consumer<IPieces> jump5 = piece -> new Knight(piece).setCoordinates(new Coordinates(vertical - 2, horizontal - 1));
    Consumer<IPieces> jump6 = piece -> new Knight(piece).setCoordinates(new Coordinates(vertical - 1, horizontal - 2));
    Consumer<IPieces> jump7 = piece -> new Knight(piece).setCoordinates(new Coordinates(vertical + 1, horizontal - 2));
    Consumer<IPieces> jump8 = piece -> new Knight(piece).setCoordinates(new Coordinates(vertical + 2, horizontal - 1));

    List<Consumer<IPieces>> actions = List.of(
        jump1,
        jump2,
        jump3,
        jump4,
        jump5,
        jump6,
        jump7,
        jump8
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
