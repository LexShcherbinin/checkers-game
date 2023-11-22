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

  public void incrementEatPiecesCount() {
    this.eatPiecesCount++;
  }

  @Override
  public String toString() {
    return TextColor.YELLOW +
        "=".repeat(100) + "\n" +
        String.format("Ход: %s\t///\tКоличество съеденных фигур: %s\t///\tКоличество сделанных ходов: %s\n", lastStep, eatPiecesCount, stepCount) +
        "=".repeat(100) + "\n" +
        TextColor.RESET;
  }

}
