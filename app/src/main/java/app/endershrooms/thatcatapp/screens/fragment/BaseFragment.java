package app.endershrooms.thatcatapp.screens.fragment;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment extends Fragment {

  @Override
  public void onAttach(@NonNull Context context) {
    AndroidSupportInjection.inject(this);
    super.onAttach(context);
  }
}
