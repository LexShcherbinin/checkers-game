package chess.enums;

public enum Colors {
  WHITE,
  BLACK;

  @Override
  public String toString() {
    return this.equals(BLACK) ? "B" : "W";
  }
}
