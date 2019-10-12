package jp.abekoh.log.sink;

public interface PostClient {

    void post(String body);

    void dryPost(String body);

    default void pushToStack(String body) {
    }
}
