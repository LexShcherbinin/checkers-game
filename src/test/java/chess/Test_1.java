package chess;

import static org.testng.Assert.assertTrue;

import chess.enums.Colors;
import chess.enums.Names;
import chess.helpers.PiecesCreator;
import chess.pojo.Piece;
import chess.pojo.Square;
import java.util.List;
import org.testng.annotations.Test;

public class Test_1 {

  @Test
  public void test1() {
    ChessBoard chessBoard = ChessBoard.createDefaultChessBoard();
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

}
