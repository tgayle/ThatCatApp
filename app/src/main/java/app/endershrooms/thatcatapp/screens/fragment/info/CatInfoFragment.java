package app.endershrooms.thatcatapp.screens.fragment.info;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import app.endershrooms.thatcatapp.R;
import app.endershrooms.thatcatapp.databinding.FragmentCatInfoBinding;
import app.endershrooms.thatcatapp.screens.fragment.BaseFragment;
import com.bumptech.glide.Glide;


public class CatInfoFragment extends BaseFragment {
  private FragmentCatInfoBinding binding;
  private CatInfoViewModel infoVm;
  CatInfoFragmentArgs args;

  public CatInfoFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    args = CatInfoFragmentArgs.fromBundle(getArguments());

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding = FragmentCatInfoBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    infoVm = ViewModelProviders.of(this, vmFactory).get(CatInfoViewModel.class);
    infoVm.ready(args.getImageId());
    infoVm.getCurrentCat().observe(getViewLifecycleOwner(), cat -> {
      Glide.with(this)
          .load(cat.getImage().getUrl())
          .centerCrop()
          .error(R.drawable.close)
          .into(binding.catInfoImage);

      binding.catInfoFavoritedDateText.setText(cat.getCreatedAt());
    });
  }
}
