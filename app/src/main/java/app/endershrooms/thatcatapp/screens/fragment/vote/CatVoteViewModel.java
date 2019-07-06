package app.endershrooms.thatcatapp.screens.fragment.vote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import app.endershrooms.thatcatapp.model.ImageResponse;
import app.endershrooms.thatcatapp.model.builders.ImageSearchQuery;
import app.endershrooms.thatcatapp.model.builders.SearchQueryOrder;
import app.endershrooms.thatcatapp.model.builders.VoteRequest;
import app.endershrooms.thatcatapp.net.CatService;
import app.endershrooms.thatcatapp.screens.BaseViewModel;
import app.endershrooms.thatcatapp.util.Event;
import app.endershrooms.thatcatapp.util.LiveDataWithInitial;
import app.endershrooms.thatcatapp.util.Result;
import app.endershrooms.thatcatapp.util.Result.Type;
import app.endershrooms.thatcatapp.util.UserInfo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;

public class CatVoteViewModel extends BaseViewModel {

  private final CatService catService;
  private final UserInfo userInfo;
  private final MutableLiveData<Event<String>> snackbarMessage = new MutableLiveData<>();
  private final MutableLiveData<Boolean> loading = new LiveDataWithInitial<>(false);
  private final LiveData<Boolean> buttonsEnabled = Transformations.map(loading, currentlyLoading -> !currentlyLoading);
  private final MutableLiveData<Result<ImageResponse, String>> currentCat = new LiveDataWithInitial<>(new Result<>());
  private final ImageSearchQuery randomImageQuery = new ImageSearchQuery()
      .setLimit(1)
      .setOrder(SearchQueryOrder.RANDOM);

  @Inject
  CatVoteViewModel(CatService catService, UserInfo userInfo) {
    this.catService = catService;
    this.userInfo = userInfo;
  }

  public void fragmentReady() {
    getNewCat();
  }

  public void requestNewCat() {
    if (!loading.getValue()) getNewCat();
  }

  private void getNewCat() {
    loading.setValue(true);
    disposables.add(catService.getImages(randomImageQuery.toMap())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(images -> {
          loading.setValue(false);
          if (images.size() == 0) {
            imageLoadingFailed(new ArrayIndexOutOfBoundsException());
            return;
          }

          currentCat.setValue(currentCat.getValue().setResult(images.get(0)));
        }, err -> imageLoadingFailed(err))
    );
  }

  private void imageLoadingFailed(Throwable err) {
    currentCat.setValue(currentCat.getValue().setError("There was an issue loading a new cat!"));
  }

  public void voteOnCat(boolean likedIt) {
    if (currentCat.getValue().getType() == Type.FAILURE) {
      return;
    }
    ImageResponse currentImage = currentCat.getValue().getResult();
    int vote = likedIt ? VoteRequest.VOTE_LIKE : VoteRequest.VOTE_DISLIKE;
    VoteRequest newVote = new VoteRequest(currentImage.getId(), userInfo.getUuid(), vote);
    disposables.add(catService.createVote(newVote)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(response -> {
          snackbarMessage.setValue(new Event<>(response.getMessage()));
          requestNewCat();
        }, err -> snackbarMessage.setValue(new Event<>(err.getMessage()))));
  }

  public LiveData<Event<String>> getSnackbarMessage() {
    return snackbarMessage;
  }

  public LiveData<Boolean> getLoading() {
    return loading;
  }

  public LiveData<Result<ImageResponse, String>> getCurrentCat() {
    return currentCat;
  }

  public LiveData<Boolean> getButtonsEnabled() {
    return buttonsEnabled;
  }
}
