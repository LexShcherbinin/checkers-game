package chess.enums;

import chess.pojo.Piece;

/**
 * Список всех возможных ходов фигур.
 */
public enum Moves {

  //Пешка
  PAWN_WHITE_UP_1(move(+1, +0)),
  PAWN_WHITE_UP_2(move(+2, +0)),
  PAWN_WHITE_UP_RIGHT(move(+1, +1)),
  PAWN_WHITE_UP_LEFT(move(+1, -1)),

  PAWN_BLACK_DOWN_1(move(-1, +0)),
  PAWN_BLACK_DOWN_2(move(-2, +0)),
  PAWN_BLACK_DOWN_RIGHT(move(-1, +1)),
  PAWN_BLACK_DOWN_LEFT(move(-1, -1)),

  //Конь
  KNIGHT_1(move(+2, +1)),
  KNIGHT_2(move(+1, +2)),
  KNIGHT_3(move(-1, +2)),
  KNIGHT_4(move(-2, +1)),
  KNIGHT_5(move(-2, -1)),
  KNIGHT_6(move(-1, -2)),
  KNIGHT_7(move(+1, -2)),
  KNIGHT_8(move(+2, -1)),

  //Король
  KING_UP(move(+1, +0)),
  KING_DOWN(move(-1, +0)),
  KING_RIGHT(move(+0, +1)),
  KING_LEFT(move(+0, -1)),
  KING_UP_RIGHT(move(+1, +1)),
  KING_UP_LEFT(move(+1, -1)),
  KING_DOWN_RIGHT(move(-1, +1)),
  KING_DOWN_LEFT(move(-1, -1)),
  KING_CASTLING_RIGHT(move(+0, +2)),
  KING_CASTLING_LEFT(move(+0, -3)),

  //Ладья
  ROOK_UP_1(move(+1, +0)),
  ROOK_UP_2(move(+2, +0)),
  ROOK_UP_3(move(+3, +0)),
  ROOK_UP_4(move(+4, +0)),
  ROOK_UP_5(move(+5, +0)),
  ROOK_UP_6(move(+6, +0)),
  ROOK_UP_7(move(+7, +0)),

  ROOK_DOWN_1(move(-1, +0)),
  ROOK_DOWN_2(move(-2, +0)),
  ROOK_DOWN_3(move(-3, +0)),
  ROOK_DOWN_4(move(-4, +0)),
  ROOK_DOWN_5(move(-5, +0)),
  ROOK_DOWN_6(move(-6, +0)),
  ROOK_DOWN_7(move(-7, +0)),

  ROOK_RIGHT_1(move(+0, +1)),
  ROOK_RIGHT_2(move(+0, +2)),
  ROOK_RIGHT_3(move(+0, +3)),
  ROOK_RIGHT_4(move(+0, +4)),
  ROOK_RIGHT_5(move(+0, +5)),
  ROOK_RIGHT_6(move(+0, +6)),
  ROOK_RIGHT_7(move(+0, +7)),

  ROOK_LEFT_1(move(+0, -1)),
  ROOK_LEFT_2(move(+0, -2)),
  ROOK_LEFT_3(move(+0, -3)),
  ROOK_LEFT_4(move(+0, -4)),
  ROOK_LEFT_5(move(+0, -5)),
  ROOK_LEFT_6(move(+0, -6)),
  ROOK_LEFT_7(move(+0, -7)),

  //Слон
  BISHOP_UP_RIGHT_1(move(+1, +1)),
  BISHOP_UP_RIGHT_2(move(+2, +2)),
  BISHOP_UP_RIGHT_3(move(+3, +3)),
  BISHOP_UP_RIGHT_4(move(+4, +4)),
  BISHOP_UP_RIGHT_5(move(+5, +5)),
  BISHOP_UP_RIGHT_6(move(+6, +6)),
  BISHOP_UP_RIGHT_7(move(+7, +7)),

  BISHOP_UP_LEFT_1(move(+1, -1)),
  BISHOP_UP_LEFT_2(move(+2, -2)),
  BISHOP_UP_LEFT_3(move(+3, -3)),
  BISHOP_UP_LEFT_4(move(+4, -4)),
  BISHOP_UP_LEFT_5(move(+5, -5)),
  BISHOP_UP_LEFT_6(move(+6, -6)),
  BISHOP_UP_LEFT_7(move(+7, -7)),

  BISHOP_DOWN_RIGHT_1(move(-1, +1)),
  BISHOP_DOWN_RIGHT_2(move(-2, +2)),
  BISHOP_DOWN_RIGHT_3(move(-3, +3)),
  BISHOP_DOWN_RIGHT_4(move(-4, +4)),
  BISHOP_DOWN_RIGHT_5(move(-5, +5)),
  BISHOP_DOWN_RIGHT_6(move(-6, +6)),
  BISHOP_DOWN_RIGHT_7(move(-7, +7)),

  BISHOP_DOWN_LEFT_1(move(-1, -1)),
  BISHOP_DOWN_LEFT_2(move(-2, -2)),
  BISHOP_DOWN_LEFT_3(move(-3, -3)),
  BISHOP_DOWN_LEFT_4(move(-4, -4)),
  BISHOP_DOWN_LEFT_5(move(-5, -5)),
  BISHOP_DOWN_LEFT_6(move(-6, -6)),
  BISHOP_DOWN_LEFT_7(move(-7, -7));

  private final Move<Piece, Piece> move;

  Moves(Move<Piece, Piece> move) {
    this.move = move;
  }

  public Move<Piece, Piece> getMove() {
    return this.move;
  }

  public static Move<Piece, Piece> move(int heightShift, int sideShift) {
    return piece -> piece
        .setMoveBefore(true)
        .setSquare(piece.getSquare().shift(heightShift, sideShift));
  }

}
