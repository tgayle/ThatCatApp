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
import javax.inject.Inject;

public class CatVoteViewModel extends BaseViewModel {

  private final CatService catService;
  private final UserInfo userInfo;

  private final MutableLiveData<CatVoteState> state = new LiveDataWithInitial<>(CatVoteState.LOADING);
  private final MutableLiveData<Event<String>> snackbarMessage = new MutableLiveData<>();
  private final LiveData<Boolean> buttonsEnabled = Transformations.map(state, currentState -> !currentState.equals(CatVoteState.LOADING));
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
    state.setValue(CatVoteState.VOTING);
  }

  public void catVoteClicked(boolean likedIt) {
    if (currentCat.getValue().getType() == Type.FAILURE) return;

    ImageResponse currentImage = currentCat.getValue().getResult();
    int vote = VoteRequest.fromBoolean(likedIt);
    VoteRequest newVote = new VoteRequest(currentImage.getId(), userInfo.getUuid(), vote);

    state.setValue(CatVoteState.LOADING);

    disposables.add(Single.zip(catService.createVote(newVote), getNewCat(), (voteResult, newCat) -> {
      snackbarMessage.postValue(new Event<>(voteResult.getMessage()));
      currentCat.postValue(currentCat.getValue().setResult(newCat));
      state.postValue(CatVoteState.VOTING);
      return true;
    })
      .observeOn(AndroidSchedulers.mainThread())
      .doOnError(this::imageLoadingFailed)
      .subscribe());
  }

  public void catFavoriteClicked() {
    if (currentCat.getValue().getType() == Type.FAILURE) return;

    ImageResponse currentImage = currentCat.getValue().getResult();
    int vote = VoteRequest.fromBoolean(true);
    VoteRequest newVote = new VoteRequest(currentImage.getId(), userInfo.getUuid(), vote);
    FavoriteRequest newFav = new FavoriteRequest(currentImage.getId(), userInfo.getUuid());

    state.setValue(CatVoteState.LOADING);

    disposables.add(Single.zip(catService.createVote(newVote), catService.createFavorite(newFav), (voteResult, favResult) -> {
      boolean successfulAction = favResult.getMessage().equals("SUCCESS");
      String snackText = successfulAction ? "Liked! Find this cat in your favorites. 🎉" : favResult.getMessage();

      snackbarMessage.postValue(new Event<>(snackText));
      return true;
    })
        .flatMap(ignored -> getNewCat())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(newCat -> {
          currentCat.setValue(currentCat.getValue().setResult(newCat));
          state.setValue(CatVoteState.VOTING);
        }, this::imageLoadingFailed));
  }

  public void nextCatClicked() {
    if (currentCat.getValue().getType() == Type.FAILURE) return;
    state.setValue(CatVoteState.LOADING);
    disposables.add(getNewCat()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(newCat -> {
          currentCat.setValue(currentCat.getValue().setResult(newCat));
          state.setValue(CatVoteState.VOTING);
    }));
  }


  public LiveData<Event<String>> getSnackbarMessage() {
    return snackbarMessage;
  }

  public LiveData<CatVoteState> getState() {
    return state;
  }

  public LiveData<Result<ImageResponse, String>> getCurrentCat() {
    return currentCat;
  }

  public LiveData<Boolean> getButtonsEnabled() {
    return buttonsEnabled;
  }
}
