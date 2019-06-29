package app.endershrooms.thatcatapp.screens.fragment.search;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import app.endershrooms.thatcatapp.databinding.CatItemBinding;
import app.endershrooms.thatcatapp.model.ImageResponse;
import app.endershrooms.thatcatapp.screens.fragment.search.CatSearchAdapter.SearchViewHolder;
import com.bumptech.glide.Glide;

public class CatSearchAdapter extends ListAdapter<ImageResponse, SearchViewHolder> {

  public CatSearchAdapter() {
    super(IMAGE_DIFF_UTIL);
  }

  @NonNull
  @Override
  public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    CatItemBinding binding = CatItemBinding
        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new SearchViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
    holder.bind(getItem(position));
  }

  class SearchViewHolder extends ViewHolder {

    private final CatItemBinding binding;

    SearchViewHolder(CatItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    void bind(ImageResponse image) {
      Glide.with(binding.getRoot())
          .load(image.getUrl())
          .centerCrop()
          .into(binding.catItemImage);
    }
  }

  static final DiffUtil.ItemCallback<ImageResponse> IMAGE_DIFF_UTIL = new ItemCallback<ImageResponse>() {
    @Override
    public boolean areItemsTheSame(@NonNull ImageResponse oldItem, @NonNull ImageResponse newItem) {
      return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull ImageResponse oldItem,
        @NonNull ImageResponse newItem) {
      return false;
    }
  };
}
