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
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    searchVm = ViewModelProviders.of(getActivity(), vmFactory).get(CatSearchViewModel.class);
    CatSearchAdapter adapter = new CatSearchAdapter();
    GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
    binding.searchRv.setAdapter(adapter);
    binding.searchRv.setLayoutManager(layoutManager);

    searchVm.getSearchResults().observe(getViewLifecycleOwner(), adapter::submitList);
    binding.setLifecycleOwner(getViewLifecycleOwner());

    searchVm.getLoading().observe(getViewLifecycleOwner(), loading -> binding.searchSwipeRefresh.setRefreshing(loading));
    binding.searchSwipeRefresh.setOnRefreshListener(() -> searchVm.loadSearchResults());
    binding.searchFab.setOnClickListener(v -> new CatSearchFilterBottomSheetDialog(searchVm).show(getFragmentManager(), "SearchBottomSheet"));

  }
}
