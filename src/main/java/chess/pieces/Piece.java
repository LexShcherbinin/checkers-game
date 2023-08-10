package chess.pieces;

import chess.enums.Colors;
import chess.Coordinates;
import chess.enums.Moves;
import chess.enums.Names;
import chess.enums.Position;
import java.util.List;

public class Piece {

  private Names name;
  private Colors color;
  private Coordinates coordinates;
  private boolean moveBefore = false;
  private List<Moves> moveList;

  public Piece(Names name, Colors color, Coordinates coordinates) {
    this.name = name;
    this.color = color;
    this.coordinates = coordinates;
  }

  public Piece(Names name, Colors color, Position.Horizontal horizontal, Position.Vertical vertical) {
    this.name = name;
    this.color = color;
    this.coordinates = new Coordinates(vertical.getValue(), horizontal.getValue());
  }

  public Names getName() {
    return name;
  }

  public Piece setName(Names name) {
    this.name = name;
    return this;
  }

  public Colors getColor() {
    return color;
  }

  public Piece setColor(Colors color) {
    this.color = color;
    return this;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public Piece setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  public boolean isMoveBefore() {
    return moveBefore;
  }

  public Piece setMoveBefore(boolean moveBefore) {
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
