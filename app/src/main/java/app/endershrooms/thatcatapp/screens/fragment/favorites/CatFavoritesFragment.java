package app.endershrooms.thatcatapp.screens.fragment.favorites;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import app.endershrooms.thatcatapp.R;
import app.endershrooms.thatcatapp.databinding.FragmentCatFavoritesBinding;
import app.endershrooms.thatcatapp.screens.fragment.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CatFavoritesFragment extends BaseFragment {

  private FragmentCatFavoritesBinding binding;
  private CatFavoritesViewModel favoritesVm;

  public CatFavoritesFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cat_favorites, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    favoritesVm = ViewModelProviders.of(this, vmFactory).get(CatFavoritesViewModel.class);
    CatFavoritesAdapter favoriteAdapter = new CatFavoritesAdapter();
    binding.favoritesRv.setAdapter(favoriteAdapter);
    binding.favoritesRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
    favoritesVm.ready();

    favoritesVm.getFavoriteImages().observe(getViewLifecycleOwner(), favoriteAdapter::submitList);

    favoriteAdapter.setOnFavoriteClicked((cat, position) -> {
      Navigation
          .findNavController(view)
          .navigate(CatFavoritesFragmentDirections.actionCatFavoritesToCatInfoFragment(cat.getImageId()));
    });

  }
}
