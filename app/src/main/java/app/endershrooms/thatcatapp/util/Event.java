package app.endershrooms.thatcatapp.util;

// https://gist.github.com/JoseAlcerreca/5b661f1800e1e654f07cc54fe87441af/raw/d1d9ad561c16f4d04367424ac5f5b305ba691852/Event.kt
public class Event <T> {
  private T content;
  private boolean hasBeenHandled = false;

  public Event(T content) {
    this.content = content;
  }

  public T getContentIfNotHandled() {
    if (hasBeenHandled) {
      return null;
    } else {
      hasBeenHandled = true;
      return content;
    }
  }

  public T peek() {
    return content;
  }

  public boolean hasBeenHandled() {
    return hasBeenHandled;
  }
}
