package chess;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class Test_1 {

  @Test
  public void test1() {
    ChessBoard chessBoard = ChessBoard.createDefaultChessBoard();
    System.out.println(chessBoard);
  }

}
