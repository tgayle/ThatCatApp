package app.endershrooms.thatcatapp.screens.fragment.breeds;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import app.endershrooms.thatcatapp.databinding.FragmentBreedInfoBottomsheetBinding;
import app.endershrooms.thatcatapp.model.BreedWithExampleCat;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BreedInfoBottomSheetDialog extends BottomSheetDialogFragment {

  private final BreedWithExampleCat breedAndExampleCat;
  private FragmentBreedInfoBottomsheetBinding binding;

  public BreedInfoBottomSheetDialog(BreedWithExampleCat breedAndExampleCat) {
    this.breedAndExampleCat = breedAndExampleCat;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentBreedInfoBottomsheetBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    binding.setBreed(breedAndExampleCat.getBreed());
    if (breedAndExampleCat.getCat() != null) {
      ImageView catImg = binding.breedItemPreview;
      Glide.with(catImg)
          .load(breedAndExampleCat.getCat().getUrl())
          .into(catImg);
    }

  }
}
