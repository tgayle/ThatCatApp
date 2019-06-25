package app.endershrooms.thatcatapp.model;

import com.squareup.moshi.Json;
import java.util.List;

public class ImageResponse {

  @Json(name = "breeds")
  private List<Breed> breeds;
  @Json(name = "height")
  private Integer height;
  @Json(name = "id")
  private String id;
  @Json(name = "url")
  private String url;
  @Json(name = "width")
  private Integer width;

  public List<Breed> getBreeds() {
    return breeds;
  }

  public void setBreeds(List<Breed> breeds) {
    this.breeds = breeds;
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