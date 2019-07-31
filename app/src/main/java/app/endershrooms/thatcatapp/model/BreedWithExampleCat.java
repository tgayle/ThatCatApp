package app.endershrooms.thatcatapp.model;

import androidx.room.Embedded;

public class BreedWithExampleCat {
  @Embedded
  private Breed breed;
  @Embedded
  private Cat cat;

  public BreedWithExampleCat(Breed breed, Cat cat) {
    this.breed = breed;
    this.cat = cat;
  }

  public Breed getBreed() {
    return breed;
  }

  public void setBreed(Breed breed) {
    this.breed = breed;
  }

  public Cat getCat() {
    return cat;
  }

  public void setCat(Cat cat) {
    this.cat = cat;
  }
}
