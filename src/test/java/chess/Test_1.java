package chess;

import static org.testng.Assert.assertTrue;

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

}
