package chess.pojo;

import chess.enums.Moves;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

// TODO: 19.12.2023 Доработать данный класс добавив больше информации
@Getter()
@Setter()
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GameInfo {

  /**
   * Количество съеденных фигур.
   */
  private int eatPiecesCount;

  /**
   * Количество сделанных шагов.
   */
  private int stepCount;

  /**
   * Предыдущий ход.
   */
  private Moves previousMove;

  /**
   * Фигура, сделавшая предыдущий ход.
   */
  private Piece previousPiece;

  /**
   * Клетка "Откуда".
   */
  private Square from;

  /**
   * Клетка "Куда".
   */
  private Square to;

  public static GameInfo newGame() {
    return new GameInfo()
        .setEatPiecesCount(0)
        .setStepCount(0)
        .setPreviousMove(null)
        .setPreviousPiece(null)
        .setFrom(null)
        .setTo(null);
  }

//  public void upEatPiecesCount() {
//    this.eatPiecesCount++;
//  }
//
//  public void upStepCount() {
//    this.stepCount++;
//  }

  @Override
  public String toString() {
    String borderLine = "=".repeat(80);
    return "%s\nХод #%s \t/// %s(%s -> %s) ///\tКоличество съеденных фигур: %s\t\n%s\n"
        .formatted(borderLine, stepCount, previousPiece, from, to, eatPiecesCount, borderLine);
  }

}
