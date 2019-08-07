package app.endershrooms.thatcatapp;

import androidx.lifecycle.Observer;
import org.mockito.Mockito;

public class TestUtilities {

  public static <T> Observer<T> getMockObserver() {
    return Mockito.mock(Observer.class);
  }

}
