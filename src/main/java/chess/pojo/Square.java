package chess.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Класс для хранения данных позиции фигур на доске.
 */
@Getter()
@Setter()
@Accessors(chain = true)
@EqualsAndHashCode
public final class Square {

  private int vertical;
  private int horizontal;

  public static Square of(int vertical, int horizontal) {
    return new Square(vertical, horizontal);
  }

  //Сделать приватным
  public Square(int vertical, int horizontal) {
    this.vertical = vertical;
    this.horizontal = horizontal;
  }

  /**
   * Смещает фигуру.
   */
  public Square shift(int heightShift, int sideShift) {
    this.vertical = this.vertical + heightShift;
    this.horizontal = this.horizontal + sideShift;
    return this;
  }

//  @Override
//  public boolean equals(Object obj) {
//    if (obj instanceof Square) {
//      boolean v = ((Square) obj).getVertical() == this.getVertical();
//      boolean h = ((Square) obj).getHorizontal() == this.getHorizontal();
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
