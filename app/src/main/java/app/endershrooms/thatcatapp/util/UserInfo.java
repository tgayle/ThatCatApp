package app.endershrooms.thatcatapp.util;

import static app.endershrooms.thatcatapp.util.Constants.SHAREDPREFS_USERID;

import android.content.SharedPreferences;
import android.util.Log;
import java.util.UUID;
import javax.inject.Inject;

public class UserInfo {
  private SharedPreferences sharedPreferences;
  private String uuid;

  @Inject
  public UserInfo(SharedPreferences sharedPrefs) {
    this.sharedPreferences = sharedPrefs;
  }

  public String getUuid() {
    if (uuid == null) {
      uuid = sharedPreferences.getString(SHAREDPREFS_USERID, UUID.randomUUID().toString());
      sharedPreferences.edit().putString(SHAREDPREFS_USERID, uuid).apply();
      Log.d("UserInfo", "User didn't have a UUID, setting to " + uuid);
    }

    return uuid;
  }
}
