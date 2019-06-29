package app.endershrooms.thatcatapp.util;

import androidx.lifecycle.MutableLiveData;

public class LiveDataWithInitial<T> extends MutableLiveData<T> {

  public LiveDataWithInitial(T initial) {
    setValue(initial);
  }
}
