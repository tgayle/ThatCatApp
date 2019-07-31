package app.endershrooms.thatcatapp.screens.fragment.search;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import app.endershrooms.thatcatapp.databinding.CatItemBinding;
import app.endershrooms.thatcatapp.model.Cat;
import app.endershrooms.thatcatapp.screens.fragment.search.CatSearchAdapter.SearchViewHolder;
import app.endershrooms.thatcatapp.view.OnCatClickedListener;
import com.bumptech.glide.Glide;

public class CatSearchAdapter extends ListAdapter<Cat, SearchViewHolder> {
  private OnCatClickedListener<Cat> onCatClickedListener;

  public CatSearchAdapter() {
    super(IMAGE_DIFF_UTIL);
  }

  @NonNull
  @Override
  public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    CatItemBinding binding = CatItemBinding
        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new SearchViewHolder(binding, onCatClickedListener);
  }

  @Override
  public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
    holder.bind(getItem(position));
  }

  public void setOnCatClickedListener(OnCatClickedListener<Cat> onCatClickedListener) {
    this.onCatClickedListener = onCatClickedListener;
  }

  class SearchViewHolder extends ViewHolder {

    private final CatItemBinding binding;
    private OnCatClickedListener<Cat> listener;

    SearchViewHolder(CatItemBinding binding, OnCatClickedListener<Cat> listener) {
      super(binding.getRoot());
      this.binding = binding;
      this.listener = listener;
    }

    void bind(Cat image) {
      Glide.with(binding.getRoot())
          .load(image.getUrl())
          .centerCrop()
          .into(binding.catItemImage);

      binding.getRoot().setOnClickListener(v -> {
        if (listener != null) {
          listener.onCatClicked(image, getAdapterPosition());
        }
      });
    }
  }

  static final DiffUtil.ItemCallback<Cat> IMAGE_DIFF_UTIL = new ItemCallback<Cat>() {
    @Override
    public boolean areItemsTheSame(@NonNull Cat oldItem, @NonNull Cat newItem) {
      return oldItem.getCatId().equals(newItem.getCatId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Cat oldItem,
        @NonNull Cat newItem) {
      return false;
    }
  };
}
