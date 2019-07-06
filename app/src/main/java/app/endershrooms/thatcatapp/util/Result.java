package app.endershrooms.thatcatapp.util;

public class Result<S, F> {
  private S result;
  private F error;
  private Type type;

  public enum Type {
    SUCCESS,
    FAILURE
  }

  public S getResult() {
    return result;
  }

  public Result<S, F> setResult(S result) {
    this.result = result;
    type = Type.SUCCESS;
    return this;
  }

  public F getError() {
    return error;
  }

  public Result<S, F> setError(F error) {
    this.error = error;
    type = Type.FAILURE;
    return this;
  }

  public Type getType() {
    return type;
  }

  public Result<S, F> setType(Type type) {
    this.type = type;
    return this;
  }
}
