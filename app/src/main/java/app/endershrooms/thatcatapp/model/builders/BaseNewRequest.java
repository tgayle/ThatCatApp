package app.endershrooms.thatcatapp.model.builders;

import com.squareup.moshi.Json;

public abstract class BaseNewRequest {

  @Json(name = "image_id")
  String imageId;

  @Json(name = "sub_id")
  String subId;

  BaseNewRequest(String imageId, String subId) {
    this.imageId = imageId;
    this.subId = subId;
  }

  BaseNewRequest(String imageId) {
    this.imageId = imageId;
  }

  public String getImageId() {
    return imageId;
  }

  public void setImageId(String imageId) {
    this.imageId = imageId;
  }

  public String getSubId() {
    return subId;
  }

  public void setSubId(String subId) {
    this.subId = subId;
  }
}
