package app.endershrooms.thatcatapp.screens.fragment.search;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import app.endershrooms.thatcatapp.R;
import app.endershrooms.thatcatapp.databinding.FragmentCatSearchBinding;
import app.endershrooms.thatcatapp.screens.fragment.BaseFragment;
import com.google.android.material.snackbar.Snackbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class CatSearchFragment extends BaseFragment {

  private FragmentCatSearchBinding binding;
  private CatSearchViewModel searchVm;

  public CatSearchFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cat_search, container, false);

    binding.searchToolbar.inflateMenu(R.menu.search_menu);

    binding.searchToolbar.setOnMenuItemClickListener(item -> {
      if (item.getItemId() == R.id.search_btn) {
        searchVm.loadSearchResults();
        Snackbar.make(getView(), "Test Text", Snackbar.LENGTH_SHORT).show();
        return true;
      }
      return false;
    });

    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    searchVm = ViewModelProviders.of(this, vmFactory).get(CatSearchViewModel.class);
    CatSearchAdapter adapter = new CatSearchAdapter();
    GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
    binding.searchRv.setAdapter(adapter);
    binding.searchRv.setLayoutManager(layoutManager);

    searchVm.getSearchResults().observe(getViewLifecycleOwner(), adapter::submitList);
  }
}
