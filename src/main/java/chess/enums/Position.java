package chess.enums;

public class Position {

  public enum Horizontal {
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H;

//    private int value;
//
//    Horizontal(int value) {
//      this.value = value;
//    }

    public int getValue() {
      int result;

      switch (this) {
        case A -> result = 0;
        case B -> result = 1;
        case C -> result = 2;
        case D -> result = 3;
        case E -> result = 4;
        case F -> result = 5;
        case G -> result = 6;
        case H -> result = 7;
        default -> result = 9;
      }

      return result;
    }
  }

  public enum Vertical {
    $1,
    $2,
    $3,
    $4,
    $5,
    $6,
    $7,
    $8;

    public int getValue() {
      int result;

      switch (this) {
        case $1 -> result = 0;
        case $2 -> result = 1;
        case $3 -> result = 2;
        case $4 -> result = 3;
        case $5 -> result = 4;
        case $6 -> result = 5;
        case $7 -> result = 6;
        case $8 -> result = 7;
        default -> result = 9;
      }

      return result;
    }
  }
}
