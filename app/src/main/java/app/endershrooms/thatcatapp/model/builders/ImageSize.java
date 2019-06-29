package app.endershrooms.thatcatapp.model.builders;

public enum ImageSize {
  FULL("full"),
  MED("med"),
  SMALL("small"),
  THUMBNAIL("thumb");

  public final String size;

  ImageSize(String size) {
    this.size = size;
  }
}
