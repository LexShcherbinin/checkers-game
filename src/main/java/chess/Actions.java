package chess;

@FunctionalInterface
public interface Actions<R, T> {

  R apply(T t);

//  default boolean checkAction() {
//
//    return true;
//  }
//
//  default void move() {
//
//  }

}
