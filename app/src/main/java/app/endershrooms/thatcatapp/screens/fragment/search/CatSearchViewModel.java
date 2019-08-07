package app.endershrooms.thatcatapp.screens.fragment.search;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import app.endershrooms.thatcatapp.db.dao.CatDao;
import app.endershrooms.thatcatapp.model.Cat;
import app.endershrooms.thatcatapp.model.builders.ImageSearchQuery;
import app.endershrooms.thatcatapp.model.builders.SearchQueryOrder;
import app.endershrooms.thatcatapp.net.CatService;
import app.endershrooms.thatcatapp.util.LiveDataWithInitial;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class CatSearchViewModel extends ViewModel {
  public static final int DEFAULT_IMAGE_LIMIT = 1;
  public static final int MAX_IMAGE_LIMIT = 100;
  private final CatDao catDao;

  private CatService catService;

  private MutableLiveData<List<Cat>> searchResults = new LiveDataWithInitial<>(new ArrayList<>());
  private MutableLiveData<Boolean> loading = new LiveDataWithInitial<>(false);
  private MutableLiveData<ImageSearchQuery> searchQuery = new LiveDataWithInitial<>(new ImageSearchQuery()
      .setLimit(DEFAULT_IMAGE_LIMIT)
      .setOrder(SearchQueryOrder.RANDOM));

  @Inject
  public CatSearchViewModel(CatService catService, CatDao catDao) {
    this.catService = catService;
    this.catDao = catDao;
  }

  void fragmentReady() {
  }

  @SuppressLint("CheckResult")
  public void loadSearchResults() {
    if (!loading.getValue()) {
      loading.setValue(true);

      catService
          .getImages(searchQuery.getValue().toMap())
          .flatMap(cats -> {
            catDao.insert(cats.toArray(new Cat[0]));
            return Single.just(cats);
          })
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(images -> {
            searchResults.setValue(images);
            loading.setValue(false);
          },
            err -> {
              loading.setValue(false);
              Log.e("Search", "Error loading search results", err);
            });
    }
  }

  public LiveData<List<Cat>> getSearchResults() {
    return searchResults;
  }

  public LiveData<Boolean> getLoading() {
    return loading;
  }

  public LiveData<ImageSearchQuery> getSearchQuery() {
    return searchQuery;
  }

  public void sortButtonChecked(SearchQueryOrder order) {
    ImageSearchQuery initialQuery = searchQuery.getValue();

    if (initialQuery.getOrder().equals(order)) {
      return;
    }

    initialQuery.setOrder(order);
    searchQuery.setValue(initialQuery);
  }

  public void limitTextChanged(String newLimitString) {
    ImageSearchQuery initialQuery = searchQuery.getValue();
    if (!newLimitString.equals(initialQuery.getLimit() + "")) {
      int newLimit = 0;

      try {
        newLimit = Math.min(Integer.parseInt(newLimitString), MAX_IMAGE_LIMIT);
      } catch (NumberFormatException ignored) {
      }

      initialQuery.setLimit(newLimit);
      searchQuery.setValue(initialQuery);
    }
  }
}
