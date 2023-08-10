package chess.enums;

import chess.pieces.IPieces;
import java.util.function.Function;

/**
 * Список всех возможных ходов
 */
public enum Moves {

  //Конь
  JUMP_1(makeMove(+2, +1)),
  JUMP_2(makeMove(+1, +2)),
  JUMP_3(makeMove(-1, +2)),
  JUMP_4(makeMove(-2, +1)),
  JUMP_5(makeMove(-2, -1)),
  JUMP_6(makeMove(-1, -2)),
  JUMP_7(makeMove(+1, -2)),
  JUMP_8(makeMove(+2, -1));

  private final Function<IPieces, IPieces> move;

  Moves(Function<IPieces, IPieces> move) {
    this.move = move;
  }

  public Function<IPieces, IPieces> getMove() {
    return this.move;
  }

  private static Function<IPieces, IPieces> makeMove(int heightShift, int sideShift) {
    return piece -> piece
        .setMoveBefore(true)
        .setCoordinates(piece.getCoordinates().shift(heightShift, sideShift));
  }

}
