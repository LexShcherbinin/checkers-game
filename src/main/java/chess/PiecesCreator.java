package chess;

import static chess.Colors.BLACK;
import static chess.Colors.WHITE;
import static chess.Names.BISHOP;
import static chess.Names.KING;
import static chess.Names.KNIGHT;
import static chess.Names.PAWN;
import static chess.Names.QUEEN;
import static chess.Names.ROOK;

import chess.pieces.Bishop;
import chess.pieces.BlackPawn;
import chess.pieces.IPieces;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Queen;
import chess.pieces.Rook;
import chess.pieces.WhitePawn;
import java.util.ArrayList;
import java.util.List;

public class PiecesCreator {

  public static IPieces createPiece(Colors color, Names name, Coordinates coordinate) {
    switch (name) {
      case KING:
        return new King(name, color, coordinate);

      case QUEEN:
        return new Queen(name, color, coordinate);

      case ROOK:
        return new Rook(name, color, coordinate);

      case BISHOP:
        return new Bishop(name, color, coordinate);

      case KNIGHT:
        return new Knight(name, color, coordinate);

      case PAWN: {
        if (color == WHITE) {
          return new WhitePawn(name, color, coordinate);

        } else {
          return new BlackPawn(name, color, coordinate);
        }
      }

      default:
        throw new RuntimeException("Что-то не так с фигурами");
    }
  }

  public static List<IPieces> getDefaultBoard() {
    List<IPieces> pieces = new ArrayList<>();

    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 0)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 1)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 2)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 3)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 4)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 5)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 6)));
    pieces.add(createPiece(WHITE, PAWN, new Coordinates(1, 7)));

    pieces.add(createPiece(WHITE, ROOK, new Coordinates(0, 0)));
    pieces.add(createPiece(WHITE, KNIGHT, new Coordinates(0, 1)));
    pieces.add(createPiece(WHITE, BISHOP, new Coordinates(0, 2)));
    pieces.add(createPiece(WHITE, QUEEN, new Coordinates(0, 3)));
    pieces.add(createPiece(WHITE, KING, new Coordinates(0, 4)));
    pieces.add(createPiece(WHITE, BISHOP, new Coordinates(0, 5)));
    pieces.add(createPiece(WHITE, KNIGHT, new Coordinates(0, 6)));
    pieces.add(createPiece(WHITE, ROOK, new Coordinates(0, 7)));

    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 0)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 1)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 2)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 3)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 4)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 5)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 6)));
    pieces.add(createPiece(BLACK, PAWN, new Coordinates(6, 7)));

    pieces.add(createPiece(BLACK, ROOK, new Coordinates(7, 0)));
    pieces.add(createPiece(BLACK, KNIGHT, new Coordinates(7, 1)));
    pieces.add(createPiece(BLACK, BISHOP, new Coordinates(7, 2)));
    pieces.add(createPiece(BLACK, QUEEN, new Coordinates(7, 3)));
    pieces.add(createPiece(BLACK, KING, new Coordinates(7, 4)));
    pieces.add(createPiece(BLACK, BISHOP, new Coordinates(7, 5)));
    pieces.add(createPiece(BLACK, KNIGHT, new Coordinates(7, 6)));
    pieces.add(createPiece(BLACK, ROOK, new Coordinates(7, 7)));

    return pieces;
  }

}