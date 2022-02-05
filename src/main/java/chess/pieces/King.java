package chess.pieces;

import chess.Colors;
import chess.Coordinates;
import chess.Names;
import java.util.List;
import java.util.function.Consumer;

public class King implements IPieces {

  private IPieces piece;

  private Names name;

  private Colors color;

  private Coordinates coordinates;

  public King(IPieces piece) {
    this.piece = piece;
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
  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public List<Consumer<IPieces>> getActions() {
    int vertical = getCoordinates().getVertical();
    int horizontal = getCoordinates().getHorizontal();

    Consumer<IPieces> up1 = piece -> new King(piece).setCoordinates(new Coordinates(vertical + 1, horizontal));
    Consumer<IPieces> down1 = piece -> new King(piece).setCoordinates(new Coordinates(vertical - 1, horizontal));
    Consumer<IPieces> right1 = piece -> new King(piece).setCoordinates(new Coordinates(vertical, horizontal + 1));
    Consumer<IPieces> left1 = piece -> new King(piece).setCoordinates(new Coordinates(vertical, horizontal - 1));

    List<Consumer<IPieces>> actions = List.of(
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
