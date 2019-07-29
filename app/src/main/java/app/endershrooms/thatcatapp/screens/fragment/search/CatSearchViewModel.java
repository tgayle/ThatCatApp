package app.endershrooms.thatcatapp.screens.fragment.search;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import app.endershrooms.thatcatapp.db.dao.CategoryDao;
import app.endershrooms.thatcatapp.model.Category;
import app.endershrooms.thatcatapp.model.ImageResponse;
import app.endershrooms.thatcatapp.model.builders.ImageSearchQuery;
import app.endershrooms.thatcatapp.model.builders.SearchQueryOrder;
import app.endershrooms.thatcatapp.net.CatService;
import app.endershrooms.thatcatapp.util.LiveDataWithInitial;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class CatSearchViewModel extends ViewModel {
  public static final int DEFAULT_IMAGE_LIMIT = 1;
  private static final int MAX_IMAGE_LIMIT = 100;

  private CatService catService;
  private CategoryDao categoryDao;

  private MutableLiveData<List<ImageResponse>> searchResults = new LiveDataWithInitial<>(new ArrayList<>());
  private MutableLiveData<Boolean> loading = new LiveDataWithInitial<>(false);
  private MutableLiveData<ImageSearchQuery> searchQuery = new LiveDataWithInitial<>(new ImageSearchQuery().setLimit(DEFAULT_IMAGE_LIMIT));
  private LiveData<List<Category>> imageCategories;

  @Inject
  public CatSearchViewModel(CatService catService, CategoryDao categoryDao) {
    this.catService = catService;
    this.categoryDao = categoryDao;

    imageCategories = categoryDao.getCategories();
  }

  void fragmentReady() {
  }

  @SuppressLint("CheckResult")
  void loadSearchResults() {
    if (!loading.getValue()) {
      loading.setValue(true);

      catService
          .getImages(searchQuery.getValue().toMap())
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

  public LiveData<List<ImageResponse>> getSearchResults() {
    return searchResults;
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
    if (newLimitString.equals(MAX_IMAGE_LIMIT + "") || newLimitString.equals(initialQuery.getLimit() + "")) {
      return;
    }

    int newLimit = getImageLimitOrMax(newLimitString);
    initialQuery.setLimit(newLimit);
    searchQuery.setValue(initialQuery);
  }

  /**
   * Returns the user entered image limit or the MAX_IMAGE_LIMIT, whichever is lowest.
   *
   * @param input A string that is a number, usually from user input.
   * @return The maximum number of images to load.
   */
  private int getImageLimitOrMax(String input) {
    int newLimit;

    try {
      newLimit = input.isEmpty() ? DEFAULT_IMAGE_LIMIT : Integer.parseInt(input);
      newLimit = Math.min(newLimit, MAX_IMAGE_LIMIT);
    } catch (NumberFormatException e) {
      newLimit = MAX_IMAGE_LIMIT;
    }
    return newLimit;
  }
}
