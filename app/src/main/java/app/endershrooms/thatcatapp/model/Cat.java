package app.endershrooms.thatcatapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import app.endershrooms.thatcatapp.model.helper.BreedsToBreedId;
import com.squareup.moshi.Json;
import java.util.List;

@Entity
public class Cat {

  @NonNull
  @PrimaryKey
  @Json(name = "id")
  private String catId;

  @Json(name = "breeds")
  @BreedsToBreedId
  private List<String> breedIds;

  @Json(name = "url")
  private String url;

  @Json(name = "height")
  private Integer height;

  @Json(name = "width")
  private Integer width;

  public List<String> getBreedIds() {
    return breedIds;
  }

  public void setBreedIds(List<String> breedIds) {
    this.breedIds = breedIds;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public String getCatId() {
    return catId;
  }

  public void setCatId(String catId) {
    this.catId = catId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }
}