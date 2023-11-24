//package chess;
//
//import static chess.enums.Names.KING;
//
//import chess.ChessBoard.CheckPieceMove;
//import chess.enums.Moves;
//import chess.pojo.Piece;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class PieceHelper {
//
//  public static Map<Piece, List<Moves>> getMoveList(ChessBoard chessBoard) {
//    Piece enemyKing = chessBoard.getPieces()
//        .stream()
//        .filter(piece -> piece.getName().equals(KING) && !piece.getColor().equals(chessBoard.getPriority()))
//        .findAny()
//        .orElse(null);
//
//    assert enemyKing != null;
//
//    Map<Piece, List<Moves>> moveList = new HashMap<>();
//    Map<Piece, List<Moves>> kingKillersList = new HashMap<>();
//
//    chessBoard.getPieces()
//        .stream()
//        .filter(piece -> piece.getColor() == chessBoard.getPriority())
//        .forEach(piece -> {
//              List<Moves> moves = piece
//                  .getMoveList()
//                  .stream()
//                  .filter(move -> new CheckPieceMove(piece, move).checkMoveIsPossible())
//                  .collect(Collectors.toList());
//
//              List<Moves> kingKillers = moves
//                  .stream()
//                  .filter(move -> move.getMove().move(new Piece(piece)).getSquare().equals(enemyKing.getSquare()))
//                  .collect(Collectors.toList());
//
//              if (kingKillers.size() != 0) {
//                kingKillersList.put(piece, kingKillers);
//
//              } else if (moves.size() != 0) {
//                moveList.put(piece, moves);
//              }
//            }
//        );
//
//    if (kingKillersList.size() != 0) {
//      return kingKillersList;
//    }
//
//    return moveList;
//  }
//
//}
