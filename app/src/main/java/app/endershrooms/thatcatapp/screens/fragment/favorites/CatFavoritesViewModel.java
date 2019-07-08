package app.endershrooms.thatcatapp.screens.fragment.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import app.endershrooms.thatcatapp.db.dao.FavoriteDao;
import app.endershrooms.thatcatapp.model.Favorite;
import app.endershrooms.thatcatapp.net.CatService;
import app.endershrooms.thatcatapp.screens.BaseViewModel;
import app.endershrooms.thatcatapp.util.Event;
import app.endershrooms.thatcatapp.util.UserInfo;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class CatFavoritesViewModel extends BaseViewModel {

  private final FavoriteDao favoriteDao;
  private final CatService catService;
  private final UserInfo userInfo;
  private final LiveData<List<Favorite>> favoriteImages;
  private final MutableLiveData<Event<String>> snackbarMessage = new MutableLiveData<>();

  private int favoritesLimit = 500;
  private int page = 0;

  @Inject
  CatFavoritesViewModel(CatService catService, FavoriteDao favoriteDao, UserInfo userInfo) {
    this.catService = catService;
    this.favoriteDao = favoriteDao;
    this.userInfo = userInfo;
    this.favoriteImages = favoriteDao.getFavorites();
  }

  public void ready() {
    disposables.add(
        catService.getFavorites(userInfo.getUuid(), favoritesLimit, page)
          .observeOn(Schedulers.io())
          .subscribe(favoriteDao::insertFavorites,
              err -> snackbarMessage.postValue(new Event<>(err.getMessage()))));
  }

  public LiveData<List<Favorite>> getFavoriteImages() {
    return favoriteImages;
  }

  public LiveData<Event<String>> getSnackbarMessage() {
    return snackbarMessage;
  }
}
