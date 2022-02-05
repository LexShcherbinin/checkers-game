package chess.pieces;

import chess.Colors;
import chess.Coordinates;
import chess.Names;
import java.util.List;
import java.util.function.Consumer;

public interface IPieces {

  Names getName();

  Colors getColor();

  Coordinates getCoordinates();

  void setCoordinates(Coordinates coordinates);

  List<Consumer<IPieces>> getActions();

}
