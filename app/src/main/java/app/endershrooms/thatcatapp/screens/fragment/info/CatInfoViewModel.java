package app.endershrooms.thatcatapp.screens.fragment.info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import app.endershrooms.thatcatapp.db.dao.FavoriteDao;
import app.endershrooms.thatcatapp.model.Favorite;
import app.endershrooms.thatcatapp.net.CatService;
import app.endershrooms.thatcatapp.screens.BaseViewModel;
import javax.inject.Inject;

public class CatInfoViewModel extends BaseViewModel {

  private final FavoriteDao favoriteDao;
  private final CatService catService;
  private final MutableLiveData<String> liveCatId = new MutableLiveData<>();
  private final LiveData<Favorite> currentCat;

  @Inject
  public CatInfoViewModel(CatService catService, FavoriteDao favoriteDao) {
    this.catService = catService;
    this.favoriteDao = favoriteDao;
    this.currentCat = Transformations.switchMap(liveCatId, favoriteDao::getFavoriteByImageId);
  }

  public void ready(String catId) {
    liveCatId.setValue(catId);
  }

  public LiveData<Favorite> getCurrentCat() {
    return currentCat;
  }
}
