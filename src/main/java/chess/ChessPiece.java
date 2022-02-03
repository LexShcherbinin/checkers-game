package chess;

public class ChessPiece {

  Pieces name;

  Coordinates coordinates;

  boolean onBoard = true;

  boolean canMove = false;

  Actions actions = null;

  Colors color;

  public Pieces getName() {
    return name;
  }

  public ChessPiece setName(Pieces name) {
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

  public Actions getActions() {
    return actions;
  }

  public ChessPiece setActions(Actions actions) {
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

  @Override
  public String toString() {
    String color = "";
    String name = "";

    switch (this.color) {
      case BLACK -> color = "B";
      case WHITE -> color = "W";
    }

    switch (this.name) {
      case KINGS -> name = "K";
      case QUEEN -> name = "Q";
      case ROOK -> name = "R";
      case BISHOP -> name = "B";
      case KNIGHT -> name = "H";
      case PAWN -> name = "P";
    }

    return color + name;
  }
}
