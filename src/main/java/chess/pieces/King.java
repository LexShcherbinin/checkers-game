package chess.pieces;

import chess.Colors;
import chess.Coordinates;
import chess.Names;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class King implements IPieces {

  private IPieces piece;

  private Names name;

  private Colors color;

  private Coordinates coordinates;

  public King(IPieces piece) {
    this.name = piece.getName();
    this.color = piece.getColor();
    this.coordinates = piece.getCoordinates();
  }

  public King(Names name, Colors color, Coordinates coordinates) {
    this.name = name;
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

    Function<IPieces, IPieces> up1 = piece -> new King(piece).setCoordinates(new Coordinates(vertical + 1, horizontal));
    Function<IPieces, IPieces> down1 = piece -> new King(piece).setCoordinates(new Coordinates(vertical - 1, horizontal));
    Function<IPieces, IPieces> right1 = piece -> new King(piece).setCoordinates(new Coordinates(vertical, horizontal + 1));
    Function<IPieces, IPieces> left1 = piece -> new King(piece).setCoordinates(new Coordinates(vertical, horizontal - 1));

    List<Function<IPieces, IPieces>> actions = List.of(
        up1,
        down1,
        right1,
        left1
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
