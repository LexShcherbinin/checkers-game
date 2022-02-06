package chess.pieces;

import chess.Colors;
import chess.Coordinates;
import chess.Names;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Knight implements IPieces {

  private IPieces piece;

  private Names name;

  private Colors color;

  private Coordinates coordinates;

  public Knight(IPieces piece) {
    this.name = piece.getName();
    this.color = piece.getColor();
    this.coordinates = piece.getCoordinates();
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
  public IPieces setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  @Override
  public List<Function<IPieces, IPieces>> getActions() {
    int vertical = getCoordinates().getVertical();
    int horizontal = getCoordinates().getHorizontal();

    Function<IPieces, IPieces> jump1 = piece -> new Knight(piece).setCoordinates(new Coordinates(vertical + 2, horizontal + 1));
    Function<IPieces, IPieces> jump2 = piece -> new Knight(piece).setCoordinates(new Coordinates(vertical + 1, horizontal + 2));
    Function<IPieces, IPieces> jump3 = piece -> new Knight(piece).setCoordinates(new Coordinates(vertical - 1, horizontal + 2));
    Function<IPieces, IPieces> jump4 = piece -> new Knight(piece).setCoordinates(new Coordinates(vertical - 2, horizontal + 1));
    Function<IPieces, IPieces> jump5 = piece -> new Knight(piece).setCoordinates(new Coordinates(vertical - 2, horizontal - 1));
    Function<IPieces, IPieces> jump6 = piece -> new Knight(piece).setCoordinates(new Coordinates(vertical - 1, horizontal - 2));
    Function<IPieces, IPieces> jump7 = piece -> new Knight(piece).setCoordinates(new Coordinates(vertical + 1, horizontal - 2));
    Function<IPieces, IPieces> jump8 = piece -> new Knight(piece).setCoordinates(new Coordinates(vertical + 2, horizontal - 1));

    List<Function<IPieces, IPieces>> actions = List.of(
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
