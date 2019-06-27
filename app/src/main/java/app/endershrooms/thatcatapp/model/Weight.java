package app.endershrooms.thatcatapp.model;

import com.squareup.moshi.Json;

public class Weight {

  @Json(name = "imperial")
  private String imperial;
  @Json(name = "metric")
  private String metric;

  /**
   * No args constructor for use in serialization
   */
  public Weight() {
  }

  /**
   *
   */
  public Weight(String imperial, String metric) {
    super();
    this.imperial = imperial;
    this.metric = metric;
  }

  public String getImperial() {
    return imperial;
  }

  public void setImperial(String imperial) {
    this.imperial = imperial;
  }

  public String getMetric() {
    return metric;
  }

  public void setMetric(String metric) {
    this.metric = metric;
  }
}