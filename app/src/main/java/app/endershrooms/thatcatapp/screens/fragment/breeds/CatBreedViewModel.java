package app.endershrooms.thatcatapp.screens.fragment.breeds;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import app.endershrooms.thatcatapp.db.dao.BreedDao;
import app.endershrooms.thatcatapp.model.Breed;
import app.endershrooms.thatcatapp.net.CatService;
import app.endershrooms.thatcatapp.screens.BaseViewModel;
import app.endershrooms.thatcatapp.util.LiveDataWithInitial;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class CatBreedViewModel extends BaseViewModel {

  private final BreedDao breedDao;
  private final LiveData<List<Breed>> breedsStream;
  private final CatService catService;
  private final MutableLiveData<Boolean> loading = new LiveDataWithInitial<>(false);

  @Inject
  public CatBreedViewModel(BreedDao breedDao, CatService catService) {
    this.breedDao = breedDao;
    breedsStream = breedDao.getBreeds();
    this.catService = catService;
  }

  public void fragmentReady() {
    loadBreeds();
  }

  public void refresh() {
    loadBreeds();
  }

  private void loadBreeds() {
    if (!loading.getValue()) {
      disposables.add(catService.getBreeds()
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe(ignored -> loading.postValue(true))
          .subscribeOn(Schedulers.io())
          .observeOn(Schedulers.io())
          .subscribe(breeds -> {
            breedDao.insertBreeds(breeds);
            loading.postValue(false);
          }));
    }
  }

  public LiveData<List<Breed>> getBreedsStream() {
    return breedsStream;
  }

  public LiveData<Boolean> getLoading() {
    return loading;
  }
}
