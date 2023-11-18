package chess.enums;

import chess.Move;
import chess.Piece;

/**
 * Список всех возможных ходов
 */
public enum Moves {

  //Пешка
  PAWN_WHITE_UP_1(makeMove(+1, +0)),
  PAWN_WHITE_UP_2(makeMove(+2, +0)),
  PAWN_WHITE_UP_RIGHT(makeMove(+1, +1)),
  PAWN_WHITE_UP_LEFT(makeMove(+1, -1)),

  PAWN_BLACK_DOWN_1(makeMove(-1, +0)),
  PAWN_BLACK_DOWN_2(makeMove(-2, +0)),
  PAWN_BLACK_DOWN_RIGHT(makeMove(-1, +1)),
  PAWN_BLACK_DOWN_LEFT(makeMove(-1, -1)),

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
  KING_UP(makeMove(+1, +0)),
  KING_DOWN(makeMove(-1, +0)),
  KING_RIGHT(makeMove(+0, +1)),
  KING_LEFT(makeMove(+0, -1)),
  KING_UP_RIGHT(makeMove(+1, +1)),
  KING_UP_LEFT(makeMove(+1, -1)),
  KING_DOWN_RIGHT(makeMove(-1, +1)),
  KING_DOWN_LEFT(makeMove(-1, -1)),
  KING_CASTLING_RIGHT(makeMove(+0, +2)),
  KING_CASTLING_LEFT(makeMove(+0, -2)),

  //Ладья
  ROOK_UP_1(makeMove(+1, +0)),
  ROOK_UP_2(makeMove(+2, +0)),
  ROOK_UP_3(makeMove(+3, +0)),
  ROOK_UP_4(makeMove(+4, +0)),
  ROOK_UP_5(makeMove(+5, +0)),
  ROOK_UP_6(makeMove(+6, +0)),
  ROOK_UP_7(makeMove(+7, +0)),

  ROOK_DOWN_1(makeMove(-1, +0)),
  ROOK_DOWN_2(makeMove(-2, +0)),
  ROOK_DOWN_3(makeMove(-3, +0)),
  ROOK_DOWN_4(makeMove(-4, +0)),
  ROOK_DOWN_5(makeMove(-5, +0)),
  ROOK_DOWN_6(makeMove(-6, +0)),
  ROOK_DOWN_7(makeMove(-7, +0)),

  ROOK_RIGHT_1(makeMove(+0, +1)),
  ROOK_RIGHT_2(makeMove(+0, +2)),
  ROOK_RIGHT_3(makeMove(+0, +3)),
  ROOK_RIGHT_4(makeMove(+0, +4)),
  ROOK_RIGHT_5(makeMove(+0, +5)),
  ROOK_RIGHT_6(makeMove(+0, +6)),
  ROOK_RIGHT_7(makeMove(+0, +7)),

  ROOK_LEFT_1(makeMove(+0, -1)),
  ROOK_LEFT_2(makeMove(+0, -2)),
  ROOK_LEFT_3(makeMove(+0, -3)),
  ROOK_LEFT_4(makeMove(+0, -4)),
  ROOK_LEFT_5(makeMove(+0, -5)),
  ROOK_LEFT_6(makeMove(+0, -6)),
  ROOK_LEFT_7(makeMove(+0, -7)),

  //Слон
  BISHOP_UP_RIGHT_1(makeMove(+1, +1)),
  BISHOP_UP_RIGHT_2(makeMove(+2, +2)),
  BISHOP_UP_RIGHT_3(makeMove(+3, +3)),
  BISHOP_UP_RIGHT_4(makeMove(+4, +4)),
  BISHOP_UP_RIGHT_5(makeMove(+5, +5)),
  BISHOP_UP_RIGHT_6(makeMove(+6, +6)),
  BISHOP_UP_RIGHT_7(makeMove(+7, +7)),

  BISHOP_UP_LEFT_1(makeMove(+1, -1)),
  BISHOP_UP_LEFT_2(makeMove(+2, -2)),
  BISHOP_UP_LEFT_3(makeMove(+3, -3)),
  BISHOP_UP_LEFT_4(makeMove(+4, -4)),
  BISHOP_UP_LEFT_5(makeMove(+5, -5)),
  BISHOP_UP_LEFT_6(makeMove(+6, -6)),
  BISHOP_UP_LEFT_7(makeMove(+7, -7)),

  BISHOP_DOWN_RIGHT_1(makeMove(-1, +1)),
  BISHOP_DOWN_RIGHT_2(makeMove(-2, +2)),
  BISHOP_DOWN_RIGHT_3(makeMove(-3, +3)),
  BISHOP_DOWN_RIGHT_4(makeMove(-4, +4)),
  BISHOP_DOWN_RIGHT_5(makeMove(-5, +5)),
  BISHOP_DOWN_RIGHT_6(makeMove(-6, +6)),
  BISHOP_DOWN_RIGHT_7(makeMove(-7, +7)),

  BISHOP_DOWN_LEFT_1(makeMove(-1, -1)),
  BISHOP_DOWN_LEFT_2(makeMove(-2, -2)),
  BISHOP_DOWN_LEFT_3(makeMove(-3, -3)),
  BISHOP_DOWN_LEFT_4(makeMove(-4, -4)),
  BISHOP_DOWN_LEFT_5(makeMove(-5, -5)),
  BISHOP_DOWN_LEFT_6(makeMove(-6, -6)),
  BISHOP_DOWN_LEFT_7(makeMove(-7, -7));

  private final Move<Piece, Piece> move;

  Moves(Move<Piece, Piece> move) {
    this.move = move;
  }

  public Move<Piece, Piece> getMove() {
    return this.move;
  }

  private static Move<Piece, Piece> makeMove(int heightShift, int sideShift) {
    return piece -> piece
        .setMoveBefore(true)
        .setCoordinates(piece.getCoordinates().shift(heightShift, sideShift));
  }

}
