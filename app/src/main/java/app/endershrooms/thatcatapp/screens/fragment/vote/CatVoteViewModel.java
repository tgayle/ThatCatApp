package app.endershrooms.thatcatapp.screens.fragment.vote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import javax.inject.Inject;

public class CatVoteViewModel extends ViewModel {

  @Inject
  CatVoteViewModel() {

  }

  private MutableLiveData<String> btn = new MutableLiveData<>();

  void fragmentReady() {
    btn.setValue("hewoo");
  }

  public LiveData<String> getBtn() {
    return btn;
  }
}
