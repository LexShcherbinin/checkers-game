package chess;

import static chess.enums.Colors.BLACK;
import static chess.enums.Colors.WHITE;
import static chess.enums.Names.KING;

import chess.enums.Colors;
import chess.enums.Move;
import chess.enums.Names;
import chess.pojo.Piece;
import chess.pojo.Square;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PieceHelper {

  /**
   * Получить мапу со всеми фигурами, которыми можно пойти, и всеми их доступными ходами
   */
  public static Map<Piece, List<Move<Piece, Piece>>> getMoveList(ChessBoard chessBoard) {
    Piece enemyKing = getPiece(chessBoard, KING, getEnemyColor(chessBoard));

    Map<Piece, List<Move<Piece, Piece>>> moveList = new HashMap<>();
    Map<Piece, List<Move<Piece, Piece>>> kingKillersList = new HashMap<>();

    chessBoard.getPieces()
        .stream()
        .filter(piece -> piece.getColor() == chessBoard.getPriority())
        .forEach(piece -> {
              List<Move<Piece, Piece>> actions = piece
                  .getMovesForPiece()
                  .stream()
                  .filter(action -> checkMove(chessBoard, piece, action))
                  .collect(Collectors.toList());

              List<Move<Piece, Piece>> kingKillers = actions
                  .stream()
                  .filter(action -> action.move(piece).getSquare().equals(enemyKing.getSquare()))
                  .collect(Collectors.toList());

              if (kingKillers.size() != 0) {
                kingKillersList.put(piece, kingKillers);

              } else if (actions.size() != 0) {
                moveList.put(piece, actions);
              }
            }
        );

    if (kingKillersList.size() != 0) {
      return kingKillersList;
    }

    return moveList;
  }

  /**
   * Найти и получить фигуру на доске, если она есть
   */
  public static Piece getPiece(ChessBoard chessBoard, Names name, Colors color) {
    return chessBoard.getPieces()
        .stream()
        .filter(piece -> piece.getColor() == color && piece.getName() == name)
        .findAny()
        .orElse(null);
  }

  /**
   * Получить сет полей, которые может атаковать сторона color
   */
  public static Set<Square> getAttackedFieldList(ChessBoard chessBoard, Colors color) {
    return chessBoard.getPieces()
        .stream()
        .filter(piece -> piece.getColor() == color)
        .map(piece ->
            piece
                .getMovesForPiece()
                .stream()
                .filter(action -> checkMove(chessBoard, piece, action))
                .map(action -> action.move(piece).getSquare())
                .collect(Collectors.toSet())
        )
        .flatMap(Collection::stream)
        .collect(Collectors.toSet());
  }

  /**
   * Проверка, можно ли ТАК пойти (надо доработать)
   */
  private static boolean checkMove(ChessBoard chessBoard, Piece piece, Move<Piece, Piece> action) {
    Piece pieceAfter = action.move(piece);

    if (!checkBoardBorders(pieceAfter)) {
      return false;
    }

    if (checkMyPieceInDestination(chessBoard, pieceAfter)) {
      return false;
    }

    return switch (pieceAfter.getName()) {
//      case PAWN -> new CheckPieces().checkPawn(chessBoard, piece, action);
//      case ROOK -> new CheckPieces().checkRook(chessBoard, piece, action);
//      case BISHOP -> new CheckPieces().checkBishop(chessBoard, piece, action);
//      case QUEEN -> new CheckPieces().checkQueen(chessBoard, piece, action);
//      case KING -> new CheckPieces().checkKing(chessBoard, piece, action);
      default -> true;
    };

  }

  /**
   * Проверяет, остаётся ли фигура в пределах доски
   *
   * @param piece - фигура после того, как сделает ход
   * @return - возвращает true, если фигура остаётся на доске, false, если выходит за неё
   */
  private static boolean checkBoardBorders(Piece piece) {
    int vertical = piece.getSquare().getVertical();
    int horizontal = piece.getSquare().getHorizontal();

    return vertical < 8 && vertical >= 0 && horizontal < 8 && horizontal >= 0;
  }

  /**
   * Проверяет, есть ли какая-либо фигура в том месте, куда хочет пойти ходящая фигура
   *
   * @param piece - фигура после того, как сделает ход (ходящая фигура)
   * @return - возвращает true, если в конечной точке есть фигура, false, если фигур нет
   */
  private static boolean checkPieceInDestination(ChessBoard chessBoard, Piece piece) {
    return chessBoard.getPieces()
        .stream()
        .anyMatch(p -> p.getSquare().equals(piece.getSquare()));
  }

  /**
   * Проверяет, есть ли своя фигура в том месте, куда хочет пойти ходящая фигура
   *
   * @param piece - фигура после того, как сделает ход (ходящая фигура)
   * @return - возвращает true, если в конечной точке есть своя фигура, false - если фигур нет
   */
  private static boolean checkMyPieceInDestination(ChessBoard chessBoard, Piece piece) {
    return chessBoard.getPieces()
        .stream()
        .anyMatch(p -> p.getSquare().equals(piece.getSquare()) && p.getColor().equals(piece.getColor()));
  }

  /**
   * Проверяет, есть ли чужая фигура в том месте, куда хочет пойти ходящая фигура
   *
   * @param piece - фигура после того, как сделает ход (ходящая фигура)
   * @return - возвращает true, если в конечной точке есть чужая фигура, false - если фигур нет
   */
  private static boolean checkEnemyPieceInDestination(ChessBoard chessBoard, Piece piece) {
    return chessBoard.getPieces()
        .stream()
        .anyMatch(p -> p.getSquare().equals(piece.getSquare()) && !p.getColor().equals(piece.getColor()));
  }

  /**
   * Проверить, есть ли в месте назначения фигура противоположного цвета
   */
  public static Piece getDestinationPiece(ChessBoard chessBoard, Piece piece) {
    return chessBoard.getPieces()
        .stream()
        .filter(p -> p.getSquare().equals(piece.getSquare()) && p.getColor() != piece.getColor())
        .findFirst()
        .orElse(null);
  }

  /**
   * Проверка, есть ли на доске в поле coordinates фигура цвета color
   *
   * @param chessBoard  - доска
   * @param coordinates - координаты фигуры
   * @param color       - цвет фигуры
   * @return - возвращает true, если фигура есть, и false, если фигуры нет
   */
  public static boolean checkPieceInSquare(ChessBoard chessBoard, Square coordinates, Colors color) {
    return chessBoard.getPieces()
        .stream()
        .anyMatch(piece -> piece.getSquare().equals(coordinates) && piece.getColor().equals(color));
  }

  /**
   * Возвращает фигуру цвета color, стоящую на поле coordinates
   *
   * @param chessBoard  - доска
   * @param coordinates - координаты фигуры
   * @param color       - цвет фигуры
   * @return - возвращает фигуру, если она там есть, и null, если в поле её нет
   */
  public static Piece getPieceInSquare(ChessBoard chessBoard, Square coordinates, Colors color) {
    return chessBoard.getPieces()
        .stream()
        .filter(piece -> piece.getSquare().equals(coordinates) && piece.getColor().equals(color))
        .findAny()
        .orElse(null);
  }

  /**
   * Проверка, присутствует ли на доске вражеский король
   */
  public static boolean checkEnemyKingOnBoard(ChessBoard chessBoard) {
    return chessBoard.getPieces()
        .stream()
        .anyMatch(piece -> piece.getColor() != chessBoard.getPriority() && piece.getName() == KING);
  }

  private Colors getMyColor(ChessBoard chessBoard) {
    return chessBoard.getPriority();
  }

  public static Colors getEnemyColor(ChessBoard chessBoard) {
    Colors color = chessBoard.getPriority();

    if (color == WHITE) {
      return BLACK;

    } else {
      return WHITE;
    }
  }

}
