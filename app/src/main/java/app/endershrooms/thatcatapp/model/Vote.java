package app.endershrooms.thatcatapp.model;

import com.squareup.moshi.Json;

public class Vote {

  @Json(name = "id")
  private Integer id;
  @Json(name = "image_id")
  private String imageId;
  @Json(name = "sub_id")
  private Object subId;
  @Json(name = "created_at")
  private String createdAt;
  @Json(name = "value")
  private Integer value;
  @Json(name = "country_code")
  private String countryCode;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getImageId() {
    return imageId;
  }

  public void setImageId(String imageId) {
    this.imageId = imageId;
  }

  public Object getSubId() {
    return subId;
  }

  public void setSubId(Object subId) {
    this.subId = subId;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

}