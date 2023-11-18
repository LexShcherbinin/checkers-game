package chess;

import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;
import static chess.enums.Names.BISHOP;
import static chess.enums.Names.KING;
import static chess.enums.Names.KNIGHT;
import static chess.enums.Names.PAWN;
import static chess.enums.Names.QUEEN;
import static chess.enums.Names.ROOK;

import java.util.ArrayList;
import java.util.List;

/**
 * Вспомогательный класс для создания шахматной доски.
 */
public final class PiecesCreator {

  public static List<Piece> getDefaultPieceList() {
    List<Piece> pieceList = new ArrayList<>();
    pieceList.add(new Piece(PAWN, WHITE, Square.of(1, 0)));
    pieceList.add(new Piece(PAWN, WHITE, Square.of(1, 1)));
    pieceList.add(new Piece(PAWN, WHITE, Square.of(1, 2)));
    pieceList.add(new Piece(PAWN, WHITE, Square.of(1, 3)));
    pieceList.add(new Piece(PAWN, WHITE, Square.of(1, 4)));
    pieceList.add(new Piece(PAWN, WHITE, Square.of(1, 5)));
    pieceList.add(new Piece(PAWN, WHITE, Square.of(1, 6)));
    pieceList.add(new Piece(PAWN, WHITE, Square.of(1, 7)));

    pieceList.add(new Piece(PAWN, BLACK, Square.of(6, 0)));
    pieceList.add(new Piece(PAWN, BLACK, Square.of(6, 1)));
    pieceList.add(new Piece(PAWN, BLACK, Square.of(6, 2)));
    pieceList.add(new Piece(PAWN, BLACK, Square.of(6, 3)));
    pieceList.add(new Piece(PAWN, BLACK, Square.of(6, 4)));
    pieceList.add(new Piece(PAWN, BLACK, Square.of(6, 5)));
    pieceList.add(new Piece(PAWN, BLACK, Square.of(6, 6)));
    pieceList.add(new Piece(PAWN, BLACK, Square.of(6, 7)));

    pieceList.add(new Piece(ROOK, WHITE, Square.of(0, 0)));
    pieceList.add(new Piece(KNIGHT, WHITE, Square.of(0, 1)));
    pieceList.add(new Piece(BISHOP, WHITE, Square.of(0, 2)));
    pieceList.add(new Piece(QUEEN, WHITE, Square.of(0, 3)));
    pieceList.add(new Piece(KING, WHITE, Square.of(0, 4)));
    pieceList.add(new Piece(BISHOP, WHITE, Square.of(0, 5)));
    pieceList.add(new Piece(KNIGHT, WHITE, Square.of(0, 6)));
    pieceList.add(new Piece(ROOK, WHITE, Square.of(0, 7)));

    pieceList.add(new Piece(ROOK, BLACK, Square.of(7, 0)));
    pieceList.add(new Piece(KNIGHT, BLACK, Square.of(7, 1)));
    pieceList.add(new Piece(BISHOP, BLACK, Square.of(7, 2)));
    pieceList.add(new Piece(QUEEN, BLACK, Square.of(7, 3)));
    pieceList.add(new Piece(KING, BLACK, Square.of(7, 4)));
    pieceList.add(new Piece(BISHOP, BLACK, Square.of(7, 5)));
    pieceList.add(new Piece(KNIGHT, BLACK, Square.of(7, 6)));
    pieceList.add(new Piece(ROOK, BLACK, Square.of(7, 7)));

    return pieceList;
  }

}
