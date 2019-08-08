package app.endershrooms.thatcatapp.screens.fragment.vote;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import app.endershrooms.thatcatapp.databinding.FragmentCatVoteBinding;
import app.endershrooms.thatcatapp.model.Cat;
import app.endershrooms.thatcatapp.screens.fragment.BaseFragment;
import app.endershrooms.thatcatapp.util.Result.Type;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.snackbar.Snackbar;


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
    binding = FragmentCatVoteBinding.inflate(inflater, container, false);

    voteVm = ViewModelProviders.of(getActivity(), vmFactory).get(CatVoteViewModel.class);
    voteVm.fragmentReady();
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    binding.voteLikeCatBtn.setOnClickListener(v -> voteVm.catVoteClicked(true));
    binding.voteDislikeCatBtn.setOnClickListener(v -> voteVm.catVoteClicked(false));
    binding.voteNextCatBtn.setOnClickListener(v -> voteVm.nextCatClicked());
    binding.voteFavoriteCatBtn.setOnClickListener(v -> voteVm.catFavoriteClicked());

    voteVm.getButtonsEnabled().observe(getViewLifecycleOwner(), enabled -> {
      binding.voteLikeCatBtn.setEnabled(enabled);
      binding.voteNextCatBtn.setEnabled(enabled);
      binding.voteDislikeCatBtn.setEnabled(enabled);
      binding.voteFavoriteCatBtn.setEnabled(enabled);
    });

    voteVm.getSnackbarMessage().observe(getViewLifecycleOwner(), msgEvent -> {
      String msg = msgEvent.getContentIfNotHandled();

      if (msg != null) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
      }
    });

    voteVm.getCurrentCat().observe(getViewLifecycleOwner(), catResult -> {
      if (catResult.getResult() == null) return;
      if (catResult.getType() == Type.FAILURE) {
        Snackbar.make(getView(), catResult.getError(), Snackbar.LENGTH_SHORT).show();
        return;
      }

      Cat catInfo = catResult.getResult();

      Glide.with(this)
          .load(catInfo.getUrl())
          .transform(new CenterCrop(), new RoundedCorners(15))
          .transition(DrawableTransitionOptions.withCrossFade())
          .into(binding.voteCatImg);
    });
  }
}
