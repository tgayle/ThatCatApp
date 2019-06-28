package app.endershrooms.thatcatapp.screens.fragment.breeds;

import androidx.lifecycle.ViewModel;
import app.endershrooms.thatcatapp.db.dao.BreedDao;

public class CatBreedViewModel extends ViewModel {

  private BreedDao breedDao;

  public CatBreedViewModel(BreedDao breedDao) {
    this.breedDao = breedDao;
  }
}
