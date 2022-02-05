package chess;

import java.util.List;

public class ChessPiece {

  Names name;

  Coordinates coordinates;

  boolean onBoard = true;

  boolean canMove = false;

  List<Actions> actions = null;

  Colors color;

  public Names getName() {
    return name;
  }

  public ChessPiece setName(Names name) {
    this.name = name;
    return this;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public ChessPiece setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  public boolean isOnBoard() {
    return onBoard;
  }

  public ChessPiece setOnBoard(boolean onBoard) {
    this.onBoard = onBoard;
    return this;
  }

  public boolean isCanMove() {
    return canMove;
  }

  public ChessPiece setCanMove(boolean canMove) {
    this.canMove = canMove;
    return this;
  }

  public List<Actions> getActions() {
    return actions;
  }

  public ChessPiece setActions(List<Actions> actions) {
    this.actions = actions;
    return this;
  }

  public Colors getColor() {
    return color;
  }

  public ChessPiece setColor(Colors color) {
    this.color = color;
    return this;
  }

  public static ChessPiece createPiece(Colors color, Names piece, Coordinates coordinate) {
    return new ChessPiece()
        .setName(piece)
        .setCoordinates(coordinate)
        .setColor(color);
  }

  @Override
  public String toString() {
    String color = this.color.toString();
    String name = this.name.toString();

    return color + name;
  }

  public void move() {

  }
}
