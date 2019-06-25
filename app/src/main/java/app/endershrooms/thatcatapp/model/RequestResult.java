package app.endershrooms.thatcatapp.model;

import com.squareup.moshi.Json;

public class RequestResult {

  @Json(name = "message")
  private String message;
  @Json(name = "id")
  private Integer id;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

}