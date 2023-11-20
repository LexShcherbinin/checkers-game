package chess;

import chess.enums.Colors;
import chess.enums.Moves;
import chess.enums.Names;
import chess.helpers.PiecesCreator;
import chess.pojo.Piece;
import chess.pojo.Square;
import java.util.List;
import org.testng.annotations.Test;

public class Test_1 {

  @Test
  public void test1() {
    List<Piece> pieceList = PiecesCreator.getDefaultPieceList();
    ChessBoard chessBoard = ChessBoard.createChessBoard(pieceList);
    System.out.println(chessBoard);
  }

  @Test
  public void test2() {
    List<Piece> pieceList = PiecesCreator.getDefaultPieceList();
    System.out.println(pieceList);
  }

  @Test
  public void test3() {
    List<Piece> pieceList = PiecesCreator.getDefaultPieceList();
    System.out.println(pieceList);

    Piece piece = new Piece(Names.KING, Colors.BLACK, Square.of(5, 5));
    pieceList.add(piece);
    System.out.println(pieceList);

    pieceList.remove(piece);
    System.out.println(pieceList);
  }

  @Test
  public void test4() {
    List<Piece> pieceList = PiecesCreator.getDefaultPieceList();
    ChessBoard chessBoard = ChessBoard.createChessBoard(pieceList);
    System.out.println(chessBoard);

    chessBoard.makeMove(pieceList.get(0), Moves.PAWN_WHITE_UP_1);
    System.out.println(chessBoard);
  }

  @Test
  public void test5() {
    Piece piece1 = new Piece(Names.KING, Colors.WHITE, Square.of(5, 5));
    Piece piece2 = new Piece(piece1);

    System.out.println(piece1 == piece2);

    Moves.KING_DOWN_LEFT.getMove().move(piece2);
    System.out.println(piece1.getSquare() == piece2.getSquare());

    ChessBoard chessBoard = ChessBoard.createChessBoard(List.of(
        piece1,
        piece2
    ));
    System.out.println(chessBoard);
  }

  @Test
  public void test6() {
    List<Piece> pieceList = List.of(
        new Piece(Names.PAWN, Colors.WHITE, Square.of(4, 5)),
        new Piece(Names.PAWN, Colors.WHITE, Square.of(4, 4)),
        new Piece(Names.PAWN, Colors.BLACK, Square.of(4, 3))
    );
    ChessBoard chessBoard = ChessBoard.createChessBoard(pieceList);
    System.out.println(chessBoard);

    chessBoard.makeMove(pieceList.get(0), Moves.PAWN_WHITE_UP_1);
    System.out.println(chessBoard);
  }

}
