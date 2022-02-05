package chess;

public class Coordinates {

  int vertical;

  int horizontal;

  public Coordinates(int vertical, int horizontal) {
    this.vertical = vertical;
    this.horizontal = horizontal;
  }

  public int getVertical() {
    return vertical;
  }

  public Coordinates setVertical(int vertical) {
    this.vertical = vertical;
    return this;
  }

  public int getHorizontal() {
    return horizontal;
  }

  public Coordinates setHorizontal(int horizontal) {
    this.horizontal = horizontal;
    return this;
  }
}
