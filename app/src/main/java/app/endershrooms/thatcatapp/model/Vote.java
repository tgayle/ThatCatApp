package app.endershrooms.thatcatapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.squareup.moshi.Json;

@Entity
public class Vote {

  @Json(name = "id")
  @PrimaryKey
  private Integer id;

  @Json(name = "image_id")
  private String imageId;
  @Json(name = "sub_id")
  private String subId;
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

  public String getSubId() {
    return subId;
  }

  public void setSubId(String subId) {
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