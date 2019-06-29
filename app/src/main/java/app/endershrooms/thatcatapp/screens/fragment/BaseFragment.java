package app.endershrooms.thatcatapp.screens.fragment;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import app.endershrooms.thatcatapp.screens.CatViewModelFactory;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

public abstract class BaseFragment extends Fragment {

  @Inject
  protected CatViewModelFactory vmFactory;

  @Override
  public void onAttach(@NonNull Context context) {
    AndroidSupportInjection.inject(this);
    super.onAttach(context);
  }

}
