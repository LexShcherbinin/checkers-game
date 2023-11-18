package chess;

import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;
import static chess.enums.Names.BISHOP;
import static chess.enums.Names.KING;
import static chess.enums.Names.KNIGHT;
import static chess.enums.Names.PAWN;
import static chess.enums.Names.QUEEN;
import static chess.enums.Names.ROOK;

import java.util.List;

public final class PiecesCreator {

  public static List<Piece> getDefaultPieceList() {
    return List.of(
        new Piece(PAWN, WHITE, Square.of(1, 0)),
        new Piece(PAWN, WHITE, Square.of(1, 1)),
        new Piece(PAWN, WHITE, Square.of(1, 2)),
        new Piece(PAWN, WHITE, Square.of(1, 3)),
        new Piece(PAWN, WHITE, Square.of(1, 4)),
        new Piece(PAWN, WHITE, Square.of(1, 5)),
        new Piece(PAWN, WHITE, Square.of(1, 6)),
        new Piece(PAWN, WHITE, Square.of(1, 7)),

        new Piece(PAWN, BLACK, Square.of(6, 0)),
        new Piece(PAWN, BLACK, Square.of(6, 1)),
        new Piece(PAWN, BLACK, Square.of(6, 2)),
        new Piece(PAWN, BLACK, Square.of(6, 3)),
        new Piece(PAWN, BLACK, Square.of(6, 4)),
        new Piece(PAWN, BLACK, Square.of(6, 5)),
        new Piece(PAWN, BLACK, Square.of(6, 6)),
        new Piece(PAWN, BLACK, Square.of(6, 7)),

        new Piece(ROOK, WHITE, Square.of(0, 0)),
        new Piece(KNIGHT, WHITE, Square.of(0, 1)),
        new Piece(BISHOP, WHITE, Square.of(0, 2)),
        new Piece(QUEEN, WHITE, Square.of(0, 3)),
        new Piece(KING, WHITE, Square.of(0, 4)),
        new Piece(BISHOP, WHITE, Square.of(0, 5)),
        new Piece(KNIGHT, WHITE, Square.of(0, 6)),
        new Piece(ROOK, WHITE, Square.of(0, 7)),
        new Piece(ROOK, BLACK, Square.of(7, 0)),
        new Piece(KNIGHT, BLACK, Square.of(7, 1)),
        new Piece(BISHOP, BLACK, Square.of(7, 2)),
        new Piece(QUEEN, BLACK, Square.of(7, 3)),
        new Piece(KING, BLACK, Square.of(7, 4)),
        new Piece(BISHOP, BLACK, Square.of(7, 5)),
        new Piece(KNIGHT, BLACK, Square.of(7, 6)),
        new Piece(ROOK, BLACK, Square.of(7, 7))
    );
  }

}
