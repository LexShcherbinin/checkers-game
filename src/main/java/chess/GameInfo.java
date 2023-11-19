package chess;

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
public class GameInfo {

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

  public static GameInfo newGame() {
    return new GameInfo()
        .setEatPiecesCount(0)
        .setStepCount(0)
        .setLastStep("");
  }

  @Override
  public String toString() {
    return TextColor.YELLOW +
        "=".repeat(80) + "\n" +
        String.format("Ход: %s\t\t\tКоличество съеденных фигур: %s\t\t\tКоличество сделанных ходов: %s\n", lastStep, eatPiecesCount, stepCount) +
        "=".repeat(80) + "\n" +
        TextColor.RESET;
  }

}
