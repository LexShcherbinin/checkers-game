package chess.pieces;

import chess.Colors;
import chess.Coordinates;
import chess.Names;
import java.util.List;
import java.util.function.Consumer;

public class WhitePawn implements IPieces {

  private IPieces piece;

  private Names name;

  private Colors color;

  private Coordinates coordinates;

  public WhitePawn(IPieces piece) {
    this.piece = piece;
  }

  public WhitePawn(Names name, Colors color, Coordinates coordinates) {
    this.name = name;
    this.color = color;
    this.coordinates = coordinates;
  }

  @Override
  public Names getName() {
    return Names.PAWN;
  }

  @Override
  public Colors getColor() {
    return Colors.WHITE;
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

    Consumer<IPieces> up1 = piece -> new WhitePawn(piece).setCoordinates(new Coordinates(vertical + 1, horizontal));
    Consumer<IPieces> up2 = piece -> new WhitePawn(piece).setCoordinates(new Coordinates(vertical + 2, horizontal));
    Consumer<IPieces> upAndRight = piece -> new WhitePawn(piece).setCoordinates(new Coordinates(vertical + 1, horizontal + 1));
    Consumer<IPieces> upAndLeft = piece -> new WhitePawn(piece).setCoordinates(new Coordinates(vertical + 1, horizontal - 1));

    List<Consumer<IPieces>> actions = List.of(
        up1,
        up2,
        upAndRight,
        upAndLeft
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
