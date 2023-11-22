package chess;

import chess.enums.Moves;
import chess.helpers.TextColor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

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
   * Последний сделанный ход.
   */
  private String lastStep;

  /**
   * Предыдущий ход.
   */
  private Moves previousMove;

  public static GameInfo newGame() {
    return new GameInfo()
        .setEatPiecesCount(0)
        .setStepCount(0)
        .setLastStep("")
        .setPreviousMove(null);
  }

  public void upEatPiecesCount() {
    this.eatPiecesCount++;
  }

  public void upStepCount() {
    this.stepCount++;
  }

  @Override
  public String toString() {
    return TextColor.YELLOW +
        "=".repeat(80) + "\n" +
        String.format("Ход #%s \t/// %s ///\tКоличество съеденных фигур: %s\t\n", stepCount, lastStep, eatPiecesCount) +
        "=".repeat(80) + "\n" +
        TextColor.RESET;
  }

}
