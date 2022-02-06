package chess.pieces;

import chess.Colors;
import chess.Coordinates;
import chess.Names;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public interface IPieces {

  Names getName();

  Colors getColor();

  Coordinates getCoordinates();

  IPieces setCoordinates(Coordinates coordinates);

  List<Function<IPieces, IPieces>> getActions();

}
