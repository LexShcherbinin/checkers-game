package chess.legacy.pieces;

import chess.enums.Colors;
import chess.Square;
import chess.enums.Names;
import java.util.List;
import java.util.function.Function;

public class WhitePawn implements IPieces {

  private Square coordinates;

  private boolean moveBefore = false;

  public WhitePawn(IPieces piece) {
    this.coordinates = piece.getSquare();
  }

  public WhitePawn(Square coordinates) {
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
  public Square getSquare() {
    return coordinates;
  }

  @Override
  public IPieces setCoordinates(Square coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  @Override
  public List<Function<IPieces, IPieces>> getActions() {
    int vertical = getSquare().getVertical();
    int horizontal = getSquare().getHorizontal();

    Function<IPieces, IPieces> up1 =
        piece -> new WhitePawn(piece).setMoveBefore(true).setCoordinates(new Square(vertical + 1, horizontal));
    Function<IPieces, IPieces> up2 =
        piece -> new WhitePawn(piece).setMoveBefore(false).setCoordinates(new Square(vertical + 2, horizontal));
    Function<IPieces, IPieces> upAndRight =
        piece -> new WhitePawn(piece).setMoveBefore(true).setCoordinates(new Square(vertical + 1, horizontal + 1));
    Function<IPieces, IPieces> upAndLeft =
        piece -> new WhitePawn(piece).setMoveBefore(true).setCoordinates(new Square(vertical + 1, horizontal - 1));

    List<Function<IPieces, IPieces>> actions = List.of(
        up1,
        up2,
        upAndRight,
        upAndLeft
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
