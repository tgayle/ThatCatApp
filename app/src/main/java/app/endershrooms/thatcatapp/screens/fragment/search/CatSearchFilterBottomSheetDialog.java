package app.endershrooms.thatcatapp.screens.fragment.search;

import static app.endershrooms.thatcatapp.model.builders.SearchQueryOrder.ASC;
import static app.endershrooms.thatcatapp.model.builders.SearchQueryOrder.DESC;
import static app.endershrooms.thatcatapp.model.builders.SearchQueryOrder.RANDOM;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import app.endershrooms.thatcatapp.R;
import app.endershrooms.thatcatapp.databinding.SearchBottomSheetBinding;
import app.endershrooms.thatcatapp.model.builders.SearchQueryOrder;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CatSearchFilterBottomSheetDialog extends BottomSheetDialogFragment {

  private final CatSearchViewModel searchVm;
  private SearchBottomSheetBinding binding;

  public CatSearchFilterBottomSheetDialog(CatSearchViewModel searchVm) {
    this.searchVm = searchVm;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = SearchBottomSheetBinding.inflate(inflater, container, false);
    setupInputs();
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    binding.searchBottomSheetLimitInput.setText(searchVm.getSearchQuery().getValue().getLimit() + "");

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

      binding.searchBottomSheetSortGroup.check(radioId);
      Log.d("Search", imageSearchQuery.getOrder() + " " + imageSearchQuery.getLimit());
    });
  }

  void setupInputs() {
    binding.searchBottomSheetSortGroup.setOnCheckedChangeListener(
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

    binding.searchBottomSheetLimitInput.setFilters(new InputFilter[]{new LengthFilter(3)});
    binding.searchBottomSheetLimitInput.addTextChangedListener(
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {
          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
          }

          @Override
          public void afterTextChanged(Editable s) {
            searchVm.limitTextChanged(s.toString());
          }
        });
  }
}
