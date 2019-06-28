package app.endershrooms.thatcatapp.screens.fragment.vote;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import app.endershrooms.thatcatapp.R;
import app.endershrooms.thatcatapp.databinding.FragmentCatVoteBinding;
import app.endershrooms.thatcatapp.screens.fragment.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CatVoteFragment extends BaseFragment {

  private CatVoteViewModel voteVm;
  private FragmentCatVoteBinding binding;

  public CatVoteFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_cat_vote,
        container,
        false);

    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    voteVm = ViewModelProviders.of(this, vmFactory).get(CatVoteViewModel.class);
    voteVm.fragmentReady();
  }
}
