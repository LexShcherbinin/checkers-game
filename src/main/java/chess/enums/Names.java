package chess.enums;

public enum Names {
  KING,     //король
  QUEEN,    //ферзь
  ROOK,     //ладья, тура
  BISHOP,   //слон, офицер
  KNIGHT,   //конь
  PAWN;     //пешка

  @Override
  public String toString() {
    switch (this) {
      case KING -> {
        return "K";
      }

      case QUEEN -> {
        return "Q";
      }

      case ROOK -> {
        return "R";
      }

      case BISHOP -> {
        return "B";
      }

      case KNIGHT -> {
        return "H";
      }

      case PAWN -> {
        return "P";
      }

      default -> {
        return "?";
      }
    }
  }
}
