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
   * Последний сделанный ход.
   */
  private String lastStep;

  /**
   * Предыдущий ход.
   */
  private Moves previousMove;

  /**
   * Предыдущая фигура, сделавшая ход.
   */
  private Piece previousPiece;

  private Square from;
  private Square to;

  public static GameInfo newGame() {
    return new GameInfo()
        .setEatPiecesCount(0)
        .setStepCount(0)
        .setLastStep(null)
        .setPreviousMove(null)
        .setPreviousPiece(null);
  }

  public void upEatPiecesCount() {
    this.eatPiecesCount++;
  }

  public void upStepCount() {
    this.stepCount++;
  }

  @Override
  public String toString() {
    return "=".repeat(80) + "\n" +
        "Ход #%s \t/// %s ///\tКоличество съеденных фигур: %s\t\n".formatted(stepCount, lastStep, eatPiecesCount) +
        "=".repeat(80) + "\n";
  }

}
