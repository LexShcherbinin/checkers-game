package chess.enums;

@FunctionalInterface
public interface Move<T, R> {

  R move(T t);

}
