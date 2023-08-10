package chess.enums;

public enum Colors {
  WHITE,
  BLACK;

  @Override
  public String toString() {
    return switch (this) {
      case WHITE -> "W";
      case BLACK -> "B";
    };
  }
}
