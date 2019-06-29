package app.endershrooms.thatcatapp.screens.fragment.search;

import android.annotation.SuppressLint;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import app.endershrooms.thatcatapp.model.ImageResponse;
import app.endershrooms.thatcatapp.model.builders.ImageSearchQuery;
import app.endershrooms.thatcatapp.model.builders.ImageSize;
import app.endershrooms.thatcatapp.net.CatService;
import app.endershrooms.thatcatapp.util.LiveDataWithInitial;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.ArrayList;
import java.util.List;

public class CatSearchViewModel extends ViewModel {

  private MutableLiveData<List<ImageResponse>> searchResults = new LiveDataWithInitial<>(
      new ArrayList<>());
  private CatService catService;
  private MutableLiveData<Boolean> loading = new LiveDataWithInitial<>(false);

  public CatSearchViewModel(CatService catService) {
    this.catService = catService;
  }

  void fragmentReady() {
  }

  @SuppressLint("CheckResult")
  void loadSearchResults() {
    if (!loading.getValue()) {
      loading.setValue(true);
      ImageSearchQuery query = new ImageSearchQuery();
      query.setLimit(30);
      query.setSize(ImageSize.MED);

      catService
          .getImages(query.toMap())
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
}
