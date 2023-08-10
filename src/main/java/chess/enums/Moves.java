package chess.enums;

import chess.pieces.IPieces;
import java.util.function.Function;

/**
 * Список всех возможных ходов
 */
public enum Moves {

  //Конь
  KNIGHT_1(makeMove(+2, +1)),
  KNIGHT_2(makeMove(+1, +2)),
  KNIGHT_3(makeMove(-1, +2)),
  KNIGHT_4(makeMove(-2, +1)),
  KNIGHT_5(makeMove(-2, -1)),
  KNIGHT_6(makeMove(-1, -2)),
  KNIGHT_7(makeMove(+1, -2)),
  KNIGHT_8(makeMove(+2, -1)),

  //Король
  KING_1(makeMove(+1, +0)),
  KING_2(makeMove(-1, +0)),
  KING_3(makeMove(+0, +1)),
  KING_4(makeMove(+0, -1)),
  KING_5(makeMove(+1, +1)),
  KING_6(makeMove(+1, -1)),
  KING_7(makeMove(-1, +1)),
  KING_8(makeMove(-1, -1)),
  KING_9(makeMove(+0, +2)),
  KING_10(makeMove(+0, -2));

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
