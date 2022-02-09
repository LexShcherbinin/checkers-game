import chess.ChessBoard;

public class Test {

  public static void main(String[] args) {
    ChessBoard chessBoard = new ChessBoard();
    System.out.println(chessBoard);

    for (int i = 0; i <= 100; i++) {
      chessBoard.makeMove();
      System.out.println(chessBoard);
    }
  }

}
