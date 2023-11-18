package chess.legacy.pieces;

import chess.enums.Colors;
import chess.Square;
import chess.enums.Names;
import java.util.List;
import java.util.function.Function;

public class BlackPawn implements IPieces {

  private Square coordinates;

  private boolean moveBefore = false;

  public BlackPawn(IPieces piece) {
    this.coordinates = piece.getCoordinates();
  }

  public BlackPawn(Square coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public Names getName() {
    return Names.PAWN;
  }

  @Override
  public Colors getColor() {
    return Colors.BLACK;
  }

  @Override
  public Square getCoordinates() {
    return coordinates;
  }

  @Override
  public IPieces setCoordinates(Square coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  @Override
  public List<Function<IPieces, IPieces>> getActions() {
    int vertical = getCoordinates().getVertical();
    int horizontal = getCoordinates().getHorizontal();

    Function<IPieces, IPieces> down1 =
        piece -> new BlackPawn(piece).setMoveBefore(true).setCoordinates(new Square(vertical - 1, horizontal));
    Function<IPieces, IPieces> down2 =
        piece -> new BlackPawn(piece).setMoveBefore(false).setCoordinates(new Square(vertical - 2, horizontal));
    Function<IPieces, IPieces> downAndRight =
        piece -> new BlackPawn(piece).setMoveBefore(true).setCoordinates(new Square(vertical - 1, horizontal + 1));
    Function<IPieces, IPieces> downAndLeft =
        piece -> new BlackPawn(piece).setMoveBefore(true).setCoordinates(new Square(vertical - 1, horizontal - 1));

    List<Function<IPieces, IPieces>> actions = List.of(
        down1,
        down2,
        downAndRight,
        downAndLeft
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