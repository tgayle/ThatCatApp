package app.endershrooms.thatcatapp.model.helper;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

public class IntToBooleanAdapter {
  @FromJson
  @IntToBoolean
  boolean fromJson(int value) {
    return value != 0;
  }

  @ToJson
  int toJson(@IntToBoolean boolean value) {
    return value ? 1 : 0;
  }
}