package chess.pieces;

import chess.enums.Colors;
import chess.Coordinates;
import chess.enums.Names;
import java.util.List;
import java.util.function.Function;

public interface IPieces {

  Names getName();

  Colors getColor();

  Coordinates getCoordinates();

  IPieces setCoordinates(Coordinates coordinates);

  List<Function<IPieces, IPieces>> getActions();

  boolean getMoveBefore();

  IPieces setMoveBefore(boolean moveBefore);

}
