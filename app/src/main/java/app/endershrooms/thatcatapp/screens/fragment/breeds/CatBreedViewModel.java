package app.endershrooms.thatcatapp.screens.fragment.breeds;

import androidx.lifecycle.ViewModel;
import app.endershrooms.thatcatapp.db.dao.BreedDao;
import javax.inject.Inject;

public class CatBreedViewModel extends ViewModel {

  private BreedDao breedDao;

  @Inject
  public CatBreedViewModel(BreedDao breedDao) {
    this.breedDao = breedDao;
  }
}
