package app.endershrooms.thatcatapp.screens.fragment.breeds;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import app.endershrooms.thatcatapp.databinding.FragmentCatBreedsBinding;
import app.endershrooms.thatcatapp.model.BreedWithExampleCat;
import app.endershrooms.thatcatapp.screens.fragment.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CatBreedsFragment extends BaseFragment {

  private CatBreedViewModel catVm;
  private FragmentCatBreedsBinding binding;
  private CatBreedAdapter breedAdapter;

  public CatBreedsFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    binding = FragmentCatBreedsBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    breedAdapter = new CatBreedAdapter();

    binding.breedsRv.setAdapter(breedAdapter);
    binding.breedsRv.setLayoutManager(new GridLayoutManager(getContext(),2));

    catVm = ViewModelProviders.of(getActivity(), vmFactory).get(CatBreedViewModel.class);
    catVm.fragmentReady();
    catVm.getBreedsStream().observe(getViewLifecycleOwner(), breedAdapter::submitList);
    catVm.getLoading().observe(getViewLifecycleOwner(), isLoading -> binding.breedsSwipeRefresh.setRefreshing(isLoading));
    catVm.getBreedWithPreviewCat().observe(getViewLifecycleOwner(), breedWithPreviewEvent -> {
      BreedWithExampleCat breedWithPreview = breedWithPreviewEvent.getContentIfNotHandled();
      Log.d("Breeds", breedWithPreview + "");
      if (breedWithPreview == null) return;

      BreedInfoBottomSheetDialog dialog = new BreedInfoBottomSheetDialog(breedWithPreview);
      dialog.show(getFragmentManager(), "breedInfoDialog");
    });

    binding.breedsSwipeRefresh.setOnRefreshListener(() -> catVm.refresh());
    breedAdapter.setClickListener((breed, position) -> catVm.breedSelected(breed));
  }
}
