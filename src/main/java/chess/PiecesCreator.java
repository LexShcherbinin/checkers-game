package chess;

import static chess.Colors.BLACK;
import static chess.Colors.WHITE;

import chess.pieces.Bishop;
import chess.pieces.BlackPawn;
import chess.pieces.IPieces;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Queen;
import chess.pieces.Rook;
import chess.pieces.WhitePawn;
import java.util.List;

public class PiecesCreator {

  public static List<IPieces> getDefaultBoard() {
    return List.of(
        new WhitePawn(new Coordinates(1, 0)),
        new WhitePawn(new Coordinates(1, 1)),
        new WhitePawn(new Coordinates(1, 2)),
        new WhitePawn(new Coordinates(1, 3)),
        new WhitePawn(new Coordinates(1, 4)),
        new WhitePawn(new Coordinates(1, 5)),
        new WhitePawn(new Coordinates(1, 6)),
        new WhitePawn(new Coordinates(1, 7)),

        new Rook(WHITE, new Coordinates(0, 0)),
        new Knight(WHITE, new Coordinates(0, 1)),
        new Bishop(WHITE, new Coordinates(0, 2)),
        new Queen(WHITE, new Coordinates(0, 3)),
        new King(WHITE, new Coordinates(0, 4)),
        new Bishop(WHITE, new Coordinates(0, 5)),
        new Knight(WHITE, new Coordinates(0, 6)),
        new Rook(WHITE, new Coordinates(0, 7)),

        new BlackPawn(new Coordinates(6, 0)),
        new BlackPawn(new Coordinates(6, 1)),
        new BlackPawn(new Coordinates(6, 2)),
        new BlackPawn(new Coordinates(6, 3)),
        new BlackPawn(new Coordinates(6, 4)),
        new BlackPawn(new Coordinates(6, 5)),
        new BlackPawn(new Coordinates(6, 6)),
        new BlackPawn(new Coordinates(6, 7)),

        new Rook(BLACK, new Coordinates(7, 0)),
        new Knight(BLACK, new Coordinates(7, 1)),
        new Bishop(BLACK, new Coordinates(7, 2)),
        new Queen(BLACK, new Coordinates(7, 3)),
        new King(BLACK, new Coordinates(7, 4)),
        new Bishop(BLACK, new Coordinates(7, 5)),
        new Knight(BLACK, new Coordinates(7, 6)),
        new Rook(BLACK, new Coordinates(7, 7))
    );
  }

}
