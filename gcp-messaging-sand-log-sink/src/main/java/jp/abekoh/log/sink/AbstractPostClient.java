package jp.abekoh.log.sink;

public abstract class AbstractPostClient implements PostClient {
    @Override
    public abstract void post(String body);

    @Override
    public void dryPost(String body) {
        System.out.println(body);
    }
}
