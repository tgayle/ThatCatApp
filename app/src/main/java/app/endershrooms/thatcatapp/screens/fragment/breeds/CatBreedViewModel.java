package app.endershrooms.thatcatapp.screens.fragment.breeds;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import app.endershrooms.thatcatapp.db.dao.BreedDao;
import app.endershrooms.thatcatapp.model.Breed;
import app.endershrooms.thatcatapp.model.BreedWithExampleCat;
import app.endershrooms.thatcatapp.net.CatService;
import app.endershrooms.thatcatapp.screens.BaseViewModel;
import app.endershrooms.thatcatapp.util.Event;
import app.endershrooms.thatcatapp.util.LiveDataWithInitial;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class CatBreedViewModel extends BaseViewModel {

  private BreedDao breedDao;
  private final LiveData<List<Breed>> breedsStream;
  private final CatService catService;

  private final MutableLiveData<Breed> currentBreed = new MutableLiveData<>();
  private final LiveData<Event<BreedWithExampleCat>> breedWithPreviewCat =
      Transformations.switchMap(currentBreed, breed -> {
        return Transformations.map(breedDao.getBreedWithPreviewCat(breed.getBreedId()), Event::new);
      });

  private final MutableLiveData<Boolean> loading = new LiveDataWithInitial<>(false);
  private boolean fragmentFirstLoaded = false;

  @Inject
  public CatBreedViewModel(BreedDao breedDao, CatService catService) {
    this.breedDao = breedDao;
    breedsStream = breedDao.getBreeds();
    this.catService = catService;
  }

  public void fragmentReady() {
    if (!fragmentFirstLoaded) {
      loadBreeds();
      fragmentFirstLoaded = true;
    }
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

  public void breedSelected(Breed breed) {
    currentBreed.setValue(breed);
  }

  public LiveData<Event<BreedWithExampleCat>> getBreedWithPreviewCat() {
    return breedWithPreviewCat;
  }
}
