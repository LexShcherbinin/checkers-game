package chess;

@FunctionalInterface
public interface Move<T, R> {

  R move(T t);

}
