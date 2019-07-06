package app.endershrooms.thatcatapp.screens;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;

public class BaseViewModel extends ViewModel {
  protected List<Disposable> disposables = new ArrayList<>();

  public int clearDisposables() {
    int cleared = 0;
    for (Disposable disposable : disposables) {
      disposable.dispose();
      cleared++;
    }
    return cleared;
  }

  @Override
  protected void onCleared() {
    super.onCleared();
    clearDisposables();
  }
}
