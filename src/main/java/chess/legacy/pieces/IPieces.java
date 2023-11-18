package chess.legacy.pieces;

import chess.enums.Colors;
import chess.pojo.Square;
import chess.enums.Names;
import java.util.List;
import java.util.function.Function;

@Deprecated
public interface IPieces {

  Names getName();

  Colors getColor();

  Square getSquare();

  IPieces setCoordinates(Square coordinates);

  List<Function<IPieces, IPieces>> getActions();

  boolean getMoveBefore();

  IPieces setMoveBefore(boolean moveBefore);

//  default List<Function<IPieces, IPieces>> getActions2() {
//    List<Moves> result = new ArrayList<>();
//
//    switch (getName()) {
//      case KNIGHT -> result = List.of(
//          KNIGHT_1
//      );
//
//      case KING -> result = List.of(
//          KNIGHT_2
//      );
//
//    }
//
//    return result.stream().map(Moves::getMove).collect(Collectors.toList());
//  }

}
