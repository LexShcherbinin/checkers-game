import static chess.Colors.BLACK;
import static chess.Colors.WHITE;

import chess.ChessBoard;
import chess.Coordinates;
import chess.pieces.BlackPawn;
import chess.pieces.IPieces;
import chess.pieces.King;
import chess.pieces.WhitePawn;
import java.util.List;

public class PawnTest {

  public static void main(String[] args) {
    IPieces whitePawn = new WhitePawn(new Coordinates(3, 4)).setMoveBefore(false);
    IPieces blackPawn = new BlackPawn(new Coordinates(3, 5)).setMoveBefore(true);

    List<IPieces> pieceList = List.of(
//        new WhitePawn(new Coordinates(1, 0)),
//        new WhitePawn(new Coordinates(1, 1)),
//        new WhitePawn(new Coordinates(1, 2)),
//        new WhitePawn(new Coordinates(1, 3)),
//        new WhitePawn(new Coordinates(1, 4)),
//        new WhitePawn(new Coordinates(1, 5)),
//        new WhitePawn(new Coordinates(1, 6)),
//        new WhitePawn(new Coordinates(1, 7)),
//
//        new BlackPawn(new Coordinates(6, 0)),
//        new BlackPawn(new Coordinates(6, 1)),
//        new BlackPawn(new Coordinates(6, 2)),
//        new BlackPawn(new Coordinates(6, 3)),
//        new BlackPawn(new Coordinates(6, 4)),
//        new BlackPawn(new Coordinates(6, 5)),
//        new BlackPawn(new Coordinates(6, 6)),
//        new BlackPawn(new Coordinates(6, 7)),

//        new WhitePawn(new Coordinates(4, 4)),
//        new BlackPawn(new Coordinates(3, 5)),

        whitePawn,
        blackPawn,

        new King(WHITE, new Coordinates(0, 4)),
        new King(BLACK, new Coordinates(7, 4))
    );

    ChessBoard chessBoard = new ChessBoard(pieceList);
    System.out.println(chessBoard);

    for (int i = 0; i <= 1000; i++) {
      chessBoard.makeMove();
      System.out.println(chessBoard);
    }
  }

}
