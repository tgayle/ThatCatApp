package app.endershrooms.thatcatapp.model;

import app.endershrooms.thatcatapp.model.helper.BreedsToBreedId;
import com.squareup.moshi.Json;
import java.util.List;

public class Cat {

  @Json(name = "breeds")
  @BreedsToBreedId
  private List<String> breedIds;

  @Json(name = "height")
  private Integer height;

  @Json(name = "id")
  private String id;

  @Json(name = "url")
  private String url;

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

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }
}