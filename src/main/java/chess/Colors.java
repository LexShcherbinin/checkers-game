package chess;

public enum Colors {
  WHITE,
  BLACK;

  @Override
  public String toString() {
    switch (this) {
      case BLACK -> {
        return "B";
      }

      case WHITE -> {
        return "W";
      }

      default -> {
        return "?";
      }
    }
  }
}
