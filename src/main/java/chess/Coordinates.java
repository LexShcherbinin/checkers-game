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

//  @Override
//  public boolean equals(Object obj) {
//    if (obj instanceof Coordinates) {
//      boolean v = ((Coordinates) obj).getVertical() == this.getVertical();
//      boolean h = ((Coordinates) obj).getHorizontal() == this.getHorizontal();
//
//      return v && h;
//
//    } else {
//      return false;
//    }
//  }
//
//  @Override
//  public int hashCode() {
//    return Integer.hashCode(this.getVertical()) + Integer.hashCode(this.getHorizontal());
//  }

  @Override
  public String toString() {
    String letter;

    switch (horizontal) {
      case 0 -> letter = "A";
      case 1 -> letter = "B";
      case 2 -> letter = "C";
      case 3 -> letter = "D";
      case 4 -> letter = "E";
      case 5 -> letter = "F";
      case 6 -> letter = "G";
      case 7 -> letter = "H";
      default -> letter = "";
    }

    return letter + (vertical + 1);
  }
}
