package app.endershrooms.thatcatapp.model.builders;

import com.squareup.moshi.Json;

public class VoteRequest extends BaseNewRequest {

  @Json(name = "value")
  private int value;

  public VoteRequest(String imageId, int value) {
    super(imageId);
    this.value = value;
  }

  public VoteRequest(String imageId, String subId, int value) {
    super(imageId, subId);
    this.value = value;
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

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
