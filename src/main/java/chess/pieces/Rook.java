package chess.pieces;

import chess.Colors;
import chess.Coordinates;
import chess.Names;
import java.util.List;
import java.util.function.Consumer;

public class Rook implements IPieces {

  private IPieces piece;

  private Names name;

  private Colors color;

  private Coordinates coordinates;

  public Rook(IPieces piece) {
    this.piece = piece;
  }

  public Rook(Names name, Colors color, Coordinates coordinates) {
    this.name = name;
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

    Consumer<IPieces> up1 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical + 1, horizontal));
    Consumer<IPieces> up2 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical + 2, horizontal));
    Consumer<IPieces> up3 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical + 3, horizontal));
    Consumer<IPieces> up4 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical + 4, horizontal));
    Consumer<IPieces> up5 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical + 5, horizontal));
    Consumer<IPieces> up6 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical + 6, horizontal));
    Consumer<IPieces> up7 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical + 7, horizontal));

    Consumer<IPieces> down1 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical - 1, horizontal));
    Consumer<IPieces> down2 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical - 2, horizontal));
    Consumer<IPieces> down3 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical - 3, horizontal));
    Consumer<IPieces> down4 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical - 4, horizontal));
    Consumer<IPieces> down5 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical - 5, horizontal));
    Consumer<IPieces> down6 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical - 6, horizontal));
    Consumer<IPieces> down7 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical - 7, horizontal));

    Consumer<IPieces> right1 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical, horizontal + 1));
    Consumer<IPieces> right2 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical, horizontal + 2));
    Consumer<IPieces> right3 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical, horizontal + 3));
    Consumer<IPieces> right4 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical, horizontal + 4));
    Consumer<IPieces> right5 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical, horizontal + 5));
    Consumer<IPieces> right6 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical, horizontal + 6));
    Consumer<IPieces> right7 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical, horizontal + 7));

    Consumer<IPieces> left1 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical, horizontal - 1));
    Consumer<IPieces> left2 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical, horizontal - 2));
    Consumer<IPieces> left3 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical, horizontal - 3));
    Consumer<IPieces> left4 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical, horizontal - 4));
    Consumer<IPieces> left5 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical, horizontal - 5));
    Consumer<IPieces> left6 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical, horizontal - 6));
    Consumer<IPieces> left7 = piece -> new Rook(piece).setCoordinates(new Coordinates(vertical, horizontal - 7));

    List<Consumer<IPieces>> actions = List.of(
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
  public String toString() {
    String color = this.getColor().toString();
    String name = this.getName().toString();

    return color + name;
  }

}
