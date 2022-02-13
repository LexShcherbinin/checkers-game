package chess.pieces;

import chess.Colors;
import chess.Coordinates;
import chess.Names;
import java.util.List;
import java.util.function.Function;

public class BlackPawn implements IPieces {

  private IPieces piece;

  private final Names name;

  private final Colors color;

  private Coordinates coordinates;

  private boolean moveBefore = false;

  public BlackPawn(IPieces piece) {
    this.name = piece.getName();
    this.color = piece.getColor();
    this.coordinates = piece.getCoordinates();
  }

  public BlackPawn(Names name, Colors color, Coordinates coordinates) {
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
    return Colors.BLACK;
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

    Function<IPieces, IPieces> down1 = piece -> new BlackPawn(piece)
        .setMoveBefore(true)
        .setCoordinates(new Coordinates(vertical - 1, horizontal));

    Function<IPieces, IPieces> down2 = piece -> new BlackPawn(piece)
        .setMoveBefore(false)
        .setCoordinates(new Coordinates(vertical - 2, horizontal));

    Function<IPieces, IPieces> downAndRight = piece -> new BlackPawn(piece)
        .setMoveBefore(true)
        .setCoordinates(new Coordinates(vertical - 1, horizontal + 1));

    Function<IPieces, IPieces> downAndLeft = piece -> new BlackPawn(piece)
        .setMoveBefore(true)
        .setCoordinates(new Coordinates(vertical - 1, horizontal - 1));

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
