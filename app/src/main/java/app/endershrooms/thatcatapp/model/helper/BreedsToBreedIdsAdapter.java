package app.endershrooms.thatcatapp.model.helper;

import app.endershrooms.thatcatapp.model.Breed;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BreedsToBreedIdsAdapter {
  @FromJson
  @BreedsToBreedId
  List<String> fromJson(List<Breed> breeds) {
    List<String> breedIds = new ArrayList<>();
    for (Breed breed: breeds) {
      breedIds.add(breed.getId());
    }
    return breedIds;
  }

  @ToJson
  /*
  Return an empty list here as it's not very easy to convert the ids
  back to the breeds without a database query.
   */
  List<Breed> toJson(@BreedsToBreedId List<String> breedIds) {
    return Collections.emptyList();
  }
}
