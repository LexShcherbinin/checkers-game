package chess;

import static chess.enums.Moves.BISHOP_DOWN_LEFT_1;
import static chess.enums.Moves.BISHOP_DOWN_LEFT_2;
import static chess.enums.Moves.BISHOP_DOWN_LEFT_3;
import static chess.enums.Moves.BISHOP_DOWN_LEFT_4;
import static chess.enums.Moves.BISHOP_DOWN_LEFT_5;
import static chess.enums.Moves.BISHOP_DOWN_LEFT_6;
import static chess.enums.Moves.BISHOP_DOWN_LEFT_7;
import static chess.enums.Moves.BISHOP_DOWN_RIGHT_1;
import static chess.enums.Moves.BISHOP_DOWN_RIGHT_2;
import static chess.enums.Moves.BISHOP_DOWN_RIGHT_3;
import static chess.enums.Moves.BISHOP_DOWN_RIGHT_4;
import static chess.enums.Moves.BISHOP_DOWN_RIGHT_5;
import static chess.enums.Moves.BISHOP_DOWN_RIGHT_6;
import static chess.enums.Moves.BISHOP_DOWN_RIGHT_7;
import static chess.enums.Moves.BISHOP_UP_LEFT_1;
import static chess.enums.Moves.BISHOP_UP_LEFT_2;
import static chess.enums.Moves.BISHOP_UP_LEFT_3;
import static chess.enums.Moves.BISHOP_UP_LEFT_4;
import static chess.enums.Moves.BISHOP_UP_LEFT_5;
import static chess.enums.Moves.BISHOP_UP_LEFT_6;
import static chess.enums.Moves.BISHOP_UP_LEFT_7;
import static chess.enums.Moves.BISHOP_UP_RIGHT_1;
import static chess.enums.Moves.BISHOP_UP_RIGHT_2;
import static chess.enums.Moves.BISHOP_UP_RIGHT_3;
import static chess.enums.Moves.BISHOP_UP_RIGHT_4;
import static chess.enums.Moves.BISHOP_UP_RIGHT_5;
import static chess.enums.Moves.BISHOP_UP_RIGHT_6;
import static chess.enums.Moves.BISHOP_UP_RIGHT_7;
import static chess.enums.Moves.KING_CASTLING_LEFT;
import static chess.enums.Moves.KING_CASTLING_RIGHT;
import static chess.enums.Moves.KING_DOWN;
import static chess.enums.Moves.KING_DOWN_LEFT;
import static chess.enums.Moves.KING_DOWN_RIGHT;
import static chess.enums.Moves.KING_LEFT;
import static chess.enums.Moves.KING_RIGHT;
import static chess.enums.Moves.KING_UP;
import static chess.enums.Moves.KING_UP_LEFT;
import static chess.enums.Moves.KING_UP_RIGHT;
import static chess.enums.Moves.KNIGHT_1;
import static chess.enums.Moves.KNIGHT_2;
import static chess.enums.Moves.KNIGHT_3;
import static chess.enums.Moves.KNIGHT_4;
import static chess.enums.Moves.KNIGHT_5;
import static chess.enums.Moves.KNIGHT_6;
import static chess.enums.Moves.KNIGHT_7;
import static chess.enums.Moves.KNIGHT_8;
import static chess.enums.Moves.PAWN_BLACK_DOWN_1;
import static chess.enums.Moves.PAWN_BLACK_DOWN_2;
import static chess.enums.Moves.PAWN_BLACK_DOWN_LEFT;
import static chess.enums.Moves.PAWN_BLACK_DOWN_RIGHT;
import static chess.enums.Moves.PAWN_WHITE_UP_1;
import static chess.enums.Moves.PAWN_WHITE_UP_2;
import static chess.enums.Moves.PAWN_WHITE_UP_LEFT;
import static chess.enums.Moves.PAWN_WHITE_UP_RIGHT;
import static chess.enums.Moves.ROOK_DOWN_1;
import static chess.enums.Moves.ROOK_DOWN_2;
import static chess.enums.Moves.ROOK_DOWN_3;
import static chess.enums.Moves.ROOK_DOWN_4;
import static chess.enums.Moves.ROOK_DOWN_5;
import static chess.enums.Moves.ROOK_DOWN_6;
import static chess.enums.Moves.ROOK_DOWN_7;
import static chess.enums.Moves.ROOK_LEFT_1;
import static chess.enums.Moves.ROOK_LEFT_2;
import static chess.enums.Moves.ROOK_LEFT_3;
import static chess.enums.Moves.ROOK_LEFT_4;
import static chess.enums.Moves.ROOK_LEFT_5;
import static chess.enums.Moves.ROOK_LEFT_6;
import static chess.enums.Moves.ROOK_LEFT_7;
import static chess.enums.Moves.ROOK_RIGHT_1;
import static chess.enums.Moves.ROOK_RIGHT_2;
import static chess.enums.Moves.ROOK_RIGHT_3;
import static chess.enums.Moves.ROOK_RIGHT_4;
import static chess.enums.Moves.ROOK_RIGHT_5;
import static chess.enums.Moves.ROOK_RIGHT_6;
import static chess.enums.Moves.ROOK_RIGHT_7;
import static chess.enums.Moves.ROOK_UP_1;
import static chess.enums.Moves.ROOK_UP_2;
import static chess.enums.Moves.ROOK_UP_3;
import static chess.enums.Moves.ROOK_UP_4;
import static chess.enums.Moves.ROOK_UP_5;
import static chess.enums.Moves.ROOK_UP_6;
import static chess.enums.Moves.ROOK_UP_7;

import chess.enums.Colors;
import chess.enums.Moves;
import chess.enums.Names;
import java.util.List;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter()
@Setter()
@Accessors(chain = true)
@EqualsAndHashCode
public final class Piece {

  private Names name;
  private Colors color;
  private Square square;
  private boolean moveBefore;
  private List<Moves> moveList;

  public Piece(Names name, Colors color, Square square) {
    this.name = name;
    this.color = color;
    this.square = square;
    this.moveBefore = false;
  }

//  public Piece(Piece piece) {
//    this.name = piece.getName();
//    this.color = piece.getColor();
//    this.square = piece.getSquare();
//    this.moveBefore = piece.isMoveBefore();
//    this.moveList = piece.getMoveList();
//  }

  /**
   * Получение списка всех ходов фигуры.
   */
  public List<Move<Piece, Piece>> getMovesForPiece() {
    return getMovesForPiece(this);
  }

  /**
   * Получение списка всех ходов фигуры piece.
   */
  public List<Move<Piece, Piece>> getMovesForPiece(Piece piece) {
    switch (piece.getName()) {
      case PAWN -> {
        if (color.equals(Colors.WHITE)) {
          moveList = List.of(
              PAWN_WHITE_UP_1,
              PAWN_WHITE_UP_2,
              PAWN_WHITE_UP_RIGHT,
              PAWN_WHITE_UP_LEFT
          );

        } else {
          moveList = List.of(
              PAWN_BLACK_DOWN_1,
              PAWN_BLACK_DOWN_2,
              PAWN_BLACK_DOWN_RIGHT,
              PAWN_BLACK_DOWN_LEFT
          );
        }
      }

      case KNIGHT -> moveList = List.of(
          KNIGHT_1,
          KNIGHT_2,
          KNIGHT_3,
          KNIGHT_4,
          KNIGHT_5,
          KNIGHT_6,
          KNIGHT_7,
          KNIGHT_8
      );

      case KING -> moveList = List.of(
          KING_UP,
          KING_DOWN,
          KING_RIGHT,
          KING_LEFT,
          KING_UP_RIGHT,
          KING_UP_LEFT,
          KING_DOWN_RIGHT,
          KING_DOWN_LEFT,
          KING_CASTLING_RIGHT,
          KING_CASTLING_LEFT
      );

      case ROOK -> moveList = List.of(
          ROOK_UP_1,
          ROOK_UP_2,
          ROOK_UP_3,
          ROOK_UP_4,
          ROOK_UP_5,
          ROOK_UP_6,
          ROOK_UP_7,
          ROOK_DOWN_1,
          ROOK_DOWN_2,
          ROOK_DOWN_3,
          ROOK_DOWN_4,
          ROOK_DOWN_5,
          ROOK_DOWN_6,
          ROOK_DOWN_7,
          ROOK_RIGHT_1,
          ROOK_RIGHT_2,
          ROOK_RIGHT_3,
          ROOK_RIGHT_4,
          ROOK_RIGHT_5,
          ROOK_RIGHT_6,
          ROOK_RIGHT_7,
          ROOK_LEFT_1,
          ROOK_LEFT_2,
          ROOK_LEFT_3,
          ROOK_LEFT_4,
          ROOK_LEFT_5,
          ROOK_LEFT_6,
          ROOK_LEFT_7
      );

      case BISHOP -> moveList = List.of(
          BISHOP_UP_RIGHT_1,
          BISHOP_UP_RIGHT_2,
          BISHOP_UP_RIGHT_3,
          BISHOP_UP_RIGHT_4,
          BISHOP_UP_RIGHT_5,
          BISHOP_UP_RIGHT_6,
          BISHOP_UP_RIGHT_7,
          BISHOP_UP_LEFT_1,
          BISHOP_UP_LEFT_2,
          BISHOP_UP_LEFT_3,
          BISHOP_UP_LEFT_4,
          BISHOP_UP_LEFT_5,
          BISHOP_UP_LEFT_6,
          BISHOP_UP_LEFT_7,
          BISHOP_DOWN_RIGHT_1,
          BISHOP_DOWN_RIGHT_2,
          BISHOP_DOWN_RIGHT_3,
          BISHOP_DOWN_RIGHT_4,
          BISHOP_DOWN_RIGHT_5,
          BISHOP_DOWN_RIGHT_6,
          BISHOP_DOWN_RIGHT_7,
          BISHOP_DOWN_LEFT_1,
          BISHOP_DOWN_LEFT_2,
          BISHOP_DOWN_LEFT_3,
          BISHOP_DOWN_LEFT_4,
          BISHOP_DOWN_LEFT_5,
          BISHOP_DOWN_LEFT_6,
          BISHOP_DOWN_LEFT_7
      );

      case QUEEN -> moveList = List.of(
          ROOK_UP_1,
          ROOK_UP_2,
          ROOK_UP_3,
          ROOK_UP_4,
          ROOK_UP_5,
          ROOK_UP_6,
          ROOK_UP_7,
          ROOK_DOWN_1,
          ROOK_DOWN_2,
          ROOK_DOWN_3,
          ROOK_DOWN_4,
          ROOK_DOWN_5,
          ROOK_DOWN_6,
          ROOK_DOWN_7,
          ROOK_RIGHT_1,
          ROOK_RIGHT_2,
          ROOK_RIGHT_3,
          ROOK_RIGHT_4,
          ROOK_RIGHT_5,
          ROOK_RIGHT_6,
          ROOK_RIGHT_7,
          ROOK_LEFT_1,
          ROOK_LEFT_2,
          ROOK_LEFT_3,
          ROOK_LEFT_4,
          ROOK_LEFT_5,
          ROOK_LEFT_6,
          ROOK_LEFT_7,
          BISHOP_UP_RIGHT_1,
          BISHOP_UP_RIGHT_2,
          BISHOP_UP_RIGHT_3,
          BISHOP_UP_RIGHT_4,
          BISHOP_UP_RIGHT_5,
          BISHOP_UP_RIGHT_6,
          BISHOP_UP_RIGHT_7,
          BISHOP_UP_LEFT_1,
          BISHOP_UP_LEFT_2,
          BISHOP_UP_LEFT_3,
          BISHOP_UP_LEFT_4,
          BISHOP_UP_LEFT_5,
          BISHOP_UP_LEFT_6,
          BISHOP_UP_LEFT_7,
          BISHOP_DOWN_RIGHT_1,
          BISHOP_DOWN_RIGHT_2,
          BISHOP_DOWN_RIGHT_3,
          BISHOP_DOWN_RIGHT_4,
          BISHOP_DOWN_RIGHT_5,
          BISHOP_DOWN_RIGHT_6,
          BISHOP_DOWN_RIGHT_7,
          BISHOP_DOWN_LEFT_1,
          BISHOP_DOWN_LEFT_2,
          BISHOP_DOWN_LEFT_3,
          BISHOP_DOWN_LEFT_4,
          BISHOP_DOWN_LEFT_5,
          BISHOP_DOWN_LEFT_6,
          BISHOP_DOWN_LEFT_7
      );

    }

    return moveList.stream()
        .map(Moves::getMove)
        .collect(Collectors.toList());
  }

  //TODO: Разобраться, что не так с одним единственным символом белого коня ♘
  @Override
  public String toString() {
    if (this.color.equals(Colors.WHITE)) {
      return switch (this.name) {
        case KING -> TextColor.WHITE_BRIGHT + "♚" + TextColor.RESET;
        case QUEEN -> TextColor.WHITE_BRIGHT + "♛" + TextColor.RESET;
        case ROOK -> TextColor.WHITE_BRIGHT + "♜" + TextColor.RESET;
        case BISHOP -> TextColor.WHITE_BRIGHT + "♝" + TextColor.RESET;
        case KNIGHT -> TextColor.WHITE_BRIGHT + "♞" + TextColor.RESET;
        case PAWN -> TextColor.WHITE_BRIGHT + "♟" + TextColor.RESET;
      };

    } else {
      return switch (this.name) {
        case KING -> TextColor.BLACK + "♚" + TextColor.RESET;
        case QUEEN -> TextColor.BLACK + "♛" + TextColor.RESET;
        case ROOK -> TextColor.BLACK + "♜" + TextColor.RESET;
        case BISHOP -> TextColor.BLACK + "♝" + TextColor.RESET;
        case KNIGHT -> TextColor.BLACK + "♞" + TextColor.RESET;
        case PAWN -> TextColor.BLACK + "♟" + TextColor.RESET;
      };
    }
  }
}
