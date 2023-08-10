package chess.pieces;

import static chess.enums.Moves.KNIGHT_1;
import static chess.enums.Moves.KNIGHT_2;

import chess.enums.Colors;
import chess.Coordinates;
import chess.enums.Moves;
import chess.enums.Names;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface IPieces {

  Names getName();

  Colors getColor();

  Coordinates getCoordinates();

  IPieces setCoordinates(Coordinates coordinates);

  List<Function<IPieces, IPieces>> getActions();

  boolean getMoveBefore();

  IPieces setMoveBefore(boolean moveBefore);

  default List<Function<IPieces, IPieces>> getActions2() {
    List<Moves> result = new ArrayList<>();

    switch (getName()) {
      case KNIGHT -> result = List.of(
          KNIGHT_1
      );

      case KING -> result = List.of(
          KNIGHT_2
      );

    }

    return result.stream().map(Moves::getMove).collect(Collectors.toList());
  }

}
