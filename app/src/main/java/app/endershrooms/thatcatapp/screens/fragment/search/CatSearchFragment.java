package app.endershrooms.thatcatapp.screens.fragment.search;


import static app.endershrooms.thatcatapp.model.builders.SearchQueryOrder.ASC;
import static app.endershrooms.thatcatapp.model.builders.SearchQueryOrder.DESC;
import static app.endershrooms.thatcatapp.model.builders.SearchQueryOrder.RANDOM;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import app.endershrooms.thatcatapp.model.builders.SearchQueryOrder;
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
    searchVm = ViewModelProviders.of(getActivity(), vmFactory).get(CatSearchViewModel.class);
    CatSearchAdapter adapter = new CatSearchAdapter();
    GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
    binding.searchRv.setAdapter(adapter);
    binding.searchRv.setLayoutManager(layoutManager);

    searchVm.getSearchResults().observe(getViewLifecycleOwner(), adapter::submitList);

    binding.setLifecycleOwner(getViewLifecycleOwner());

    setupInputListeners();

    searchVm.getSearchQuery().observe(getViewLifecycleOwner(), imageSearchQuery -> {
      int radioId = -1;
      switch (imageSearchQuery.getOrder()) {
        case RANDOM:
          radioId = R.id.radio_random;
          break;
        case ASC:
          radioId = R.id.radio_asc;
          break;
        case DESC:
          radioId = R.id.radio_desc;
          break;
      }

      binding.searchBottomSheetLayout.searchBottomSheetSortGroup.check(radioId);
      binding.searchBottomSheetLayout.searchBottomSheetLimitInput
          .setText(imageSearchQuery.getLimit() + "");
      binding.searchBottomSheetLayout.searchBottomSheetLimitInput
          .setSelection((imageSearchQuery.getLimit() + "").length());
      Log.d("Search", imageSearchQuery.getOrder() + " " + imageSearchQuery.getLimit());
    });
  }

  private void setupInputListeners() {
    binding.searchBottomSheetLayout.searchBottomSheetSortGroup.setOnCheckedChangeListener(
        (group, checkedId) -> {
          SearchQueryOrder order;

          switch (checkedId) {
            case R.id.radio_random:
              order = RANDOM;
              break;
            case R.id.radio_asc:
              order = ASC;
              break;
            case R.id.radio_desc:
              order = DESC;
              break;
            default:
              order = ASC;
              break;
          }
          searchVm.sortButtonChecked(order);
        });

    binding.searchBottomSheetLayout.searchBottomSheetLimitInput.addTextChangedListener(
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {
          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
            searchVm.limitTextChanged(s.toString());
          }

          @Override
          public void afterTextChanged(Editable s) {
          }
        });
  }
}
