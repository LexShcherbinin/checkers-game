package chess.newversion;

import static chess.enums.Moves.KNIGHT_1;
import static chess.enums.Moves.KNIGHT_2;

import chess.Coordinates;
import chess.enums.Colors;
import chess.enums.Moves;
import chess.enums.Names;
import chess.enums.Position;
import chess.pieces.IPieces;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

  public List<Moves> getMoveList() {
    return moveList;
  }

  public Piece setMoveList(List<Moves> moveList) {
    this.moveList = moveList;
    return this;
  }

  public List<Function<IPieces, IPieces>> getActions() {
    switch (getName()) {
      case KNIGHT -> moveList = List.of(
          KNIGHT_1
      );

      case KING -> moveList = List.of(
          KNIGHT_2
      );

    }

    return moveList.stream().map(Moves::getMove).collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return this.color.toString() + this.name.toString();
  }
}
