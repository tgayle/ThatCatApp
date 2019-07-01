package app.endershrooms.thatcatapp.screens.fragment.search;

import android.annotation.SuppressLint;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
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

  private MutableLiveData<List<ImageResponse>> searchResults = new LiveDataWithInitial<>(
      new ArrayList<>());
  private CatService catService;
  private MutableLiveData<Boolean> loading = new LiveDataWithInitial<>(false);
  private MutableLiveData<ImageSearchQuery> searchQuery = new LiveDataWithInitial<>(
      new ImageSearchQuery());

  @Inject
  public CatSearchViewModel(CatService catService) {
    this.catService = catService;
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

    int newLimit = newLimitString.isEmpty() ? 0 : Integer.parseInt(newLimitString);

    if (initialQuery.getLimit() == newLimit) {
      return;
    }

    initialQuery.setLimit(newLimit);
    searchQuery.setValue(initialQuery);
  }
}
