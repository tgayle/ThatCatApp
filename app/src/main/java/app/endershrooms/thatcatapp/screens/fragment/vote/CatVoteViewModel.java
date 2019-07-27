package app.endershrooms.thatcatapp.screens.fragment.vote;

import android.annotation.SuppressLint;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import app.endershrooms.thatcatapp.db.dao.CatDao;
import app.endershrooms.thatcatapp.model.ImageResponse;
import app.endershrooms.thatcatapp.model.builders.FavoriteRequest;
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
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
  private final CatDao catDao;

  @Inject
  CatVoteViewModel(CatService catService, UserInfo userInfo, CatDao catDao) {
    this.catService = catService;
    this.userInfo = userInfo;
    this.catDao = catDao;
  }

  public void fragmentReady() {
    nextCatClicked();
  }

  @SuppressLint("CheckResult")
  private Single<ImageResponse> getNewCat() {
    return catService.getImages(randomImageQuery.toMap())
        .flatMap(images -> {
          if (images.size() == 0) {
            throw new ArrayIndexOutOfBoundsException();
          }
          return Single.just(images.get(0));
        });
  }

  private void imageLoadingFailed(Throwable err) {
    currentCat.setValue(currentCat.getValue().setError("There was an issue loading a new cat!\n" + err.getMessage()));
    loading.setValue(false);
  }

  public void catVoteClicked(boolean likedIt) {
    if (currentCat.getValue().getType() == Type.FAILURE) return;

    ImageResponse currentImage = currentCat.getValue().getResult();
    int vote = VoteRequest.fromBoolean(likedIt);
    VoteRequest newVote = new VoteRequest(currentImage.getId(), userInfo.getUuid(), vote);

    loading.setValue(true);
    disposables.add(catService.createVote(newVote)
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap(
            voteResult -> {
              snackbarMessage.setValue(new Event<>(voteResult.getMessage()));
              return getNewCat();
            })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(imageResponse -> {
          currentCat.setValue(currentCat.getValue().setResult(imageResponse));
          loading.setValue(false);
        }, this::imageLoadingFailed));
  }

  public void catFavoriteClicked() {
    if (currentCat.getValue().getType() == Type.FAILURE) return;

    ImageResponse currentImage = currentCat.getValue().getResult();
    int vote = VoteRequest.fromBoolean(true);
    VoteRequest newVote = new VoteRequest(currentImage.getId(), userInfo.getUuid(), vote);
    FavoriteRequest newFav = new FavoriteRequest(currentImage.getId(), userInfo.getUuid());

    loading.setValue(true);
    Disposable disposable = Single.zip(catService.createVote(newVote), catService.createFavorite(newFav), (voteResult, favResult) -> {
      boolean successfulAction = favResult.getMessage().equals("SUCCESS");
      String snackText = successfulAction ? "Liked! Find this cat in your favorites. 🎉" : favResult.getMessage();
      snackbarMessage.postValue(new Event<>(snackText));
      return true;
    })
        .flatMap(unused -> getNewCat())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(newCat -> {
          currentCat.setValue(currentCat.getValue().setResult(newCat));
          loading.setValue(false);
        }, this::imageLoadingFailed);

    disposables.add(disposable);
  }

  public void nextCatClicked() {
    if (currentCat.getValue().getType() == Type.FAILURE) return;
    loading.setValue(true);
    disposables.add(getNewCat()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(newCat -> {
          currentCat.setValue(currentCat.getValue().setResult(newCat));
          loading.setValue(false);
    }));
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
