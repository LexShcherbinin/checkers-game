package chess.legacy;

import chess.pojo.Piece;
import chess.legacy.pieces.IPieces;
import java.util.List;

public class PiecesCreator {

  public static List<Piece> getDefaultBoard2() {
    return null;

//    return List.of(
//        new Piece(PAWN, WHITE, 1, 0),
//        new Piece(PAWN, WHITE, 1, 1),
//        new Piece(PAWN, WHITE, 1, 2),
//        new Piece(PAWN, WHITE, 1, 3),
//        new Piece(PAWN, WHITE, 1, 4),
//        new Piece(PAWN, WHITE, 1, 5),
//        new Piece(PAWN, WHITE, 1, 6),
//        new Piece(PAWN, WHITE, 1, 7),
//
//        new Piece(PAWN, BLACK, 6, 0),
//        new Piece(PAWN, BLACK, 6, 1),
//        new Piece(PAWN, BLACK, 6, 2),
//        new Piece(PAWN, BLACK, 6, 3),
//        new Piece(PAWN, BLACK, 6, 4),
//        new Piece(PAWN, BLACK, 6, 5),
//        new Piece(PAWN, BLACK, 6, 6),
//        new Piece(PAWN, BLACK, 6, 7),
//
//        new Piece(ROOK, WHITE, 0, 0),
//        new Piece(KNIGHT, WHITE, 0, 1),
//        new Piece(BISHOP, WHITE, 0, 2),
//        new Piece(QUEEN, WHITE, 0, 3),
//        new Piece(KING, WHITE, 0, 4),
//        new Piece(BISHOP, WHITE, 0, 5),
//        new Piece(KNIGHT, WHITE, 0, 6),
//        new Piece(ROOK, WHITE, 0, 7),
//
//        new Piece(ROOK, BLACK, 7, 0),
//        new Piece(KNIGHT, BLACK, 7, 1),
//        new Piece(BISHOP, BLACK, 7, 2),
//        new Piece(QUEEN, BLACK, 7, 3),
//        new Piece(KING, BLACK, 7, 4),
//        new Piece(BISHOP, BLACK, 7, 5),
//        new Piece(KNIGHT, BLACK, 7, 6),
//        new Piece(ROOK, BLACK, 7, 7)
//    );
  }

  @Deprecated
  public static List<IPieces> getDefaultBoard() {
    return null;
//    return List.of(
//        new WhitePawn(new Square(1, 0)),
//        new WhitePawn(new Square(1, 1)),
//        new WhitePawn(new Square(1, 2)),
//        new WhitePawn(new Square(1, 3)),
//        new WhitePawn(new Square(1, 4)),
//        new WhitePawn(new Square(1, 5)),
//        new WhitePawn(new Square(1, 6)),
//        new WhitePawn(new Square(1, 7)),
//
//        new Rook(WHITE, new Square(0, 0)),
//        new Knight(WHITE, new Square(0, 1)),
//        new Bishop(WHITE, new Square(0, 2)),
//        new Queen(WHITE, new Square(0, 3)),
//        new King(WHITE, new Square(0, 4)),
//        new Bishop(WHITE, new Square(0, 5)),
//        new Knight(WHITE, new Square(0, 6)),
//        new Rook(WHITE, new Square(0, 7)),
//
//        new BlackPawn(new Square(6, 0)),
//        new BlackPawn(new Square(6, 1)),
//        new BlackPawn(new Square(6, 2)),
//        new BlackPawn(new Square(6, 3)),
//        new BlackPawn(new Square(6, 4)),
//        new BlackPawn(new Square(6, 5)),
//        new BlackPawn(new Square(6, 6)),
//        new BlackPawn(new Square(6, 7)),
//
//        new Rook(BLACK, new Square(7, 0)),
//        new Knight(BLACK, new Square(7, 1)),
//        new Bishop(BLACK, new Square(7, 2)),
//        new Queen(BLACK, new Square(7, 3)),
//        new King(BLACK, new Square(7, 4)),
//        new Bishop(BLACK, new Square(7, 5)),
//        new Knight(BLACK, new Square(7, 6)),
//        new Rook(BLACK, new Square(7, 7))
//    );
  }

}
