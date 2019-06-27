package app.endershrooms.thatcatapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.squareup.moshi.Json;

@Entity()
public class Favorite {

  @Json(name = "id")
  @PrimaryKey
  @ColumnInfo(name = "fav_id")
  private Integer id;
  @Json(name = "user_id")
  private String userId;
  @Json(name = "image_id")
  private String imageId;
  @Json(name = "sub_id")
  private String subId;
  @Json(name = "created_at")
  private String createdAt;

  @Json(name = "image")
  @Embedded
  private Image image;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

}