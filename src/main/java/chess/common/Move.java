package chess.common;

//Подумать над возможностью замены данного интерфейса на Function
@FunctionalInterface
public interface Move<T, R> {

  R move(T t);

}
