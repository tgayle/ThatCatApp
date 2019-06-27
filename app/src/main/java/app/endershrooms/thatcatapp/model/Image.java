package app.endershrooms.thatcatapp.model;

import androidx.room.ColumnInfo;
import com.squareup.moshi.Json;

public class Image {

  @Json(name = "id")
  @ColumnInfo(name = "img_id")
  private String id;
  @Json(name = "url")
  private String url;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}