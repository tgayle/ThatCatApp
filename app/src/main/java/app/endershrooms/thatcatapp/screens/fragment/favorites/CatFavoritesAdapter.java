package app.endershrooms.thatcatapp.screens.fragment.favorites;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import app.endershrooms.thatcatapp.databinding.CatItemBinding;
import app.endershrooms.thatcatapp.model.Favorite;
import app.endershrooms.thatcatapp.screens.fragment.favorites.CatFavoritesAdapter.FavoriteViewHolder;
import app.endershrooms.thatcatapp.view.OnCatClickedListener;
import com.bumptech.glide.Glide;

public class CatFavoritesAdapter extends ListAdapter<Favorite, FavoriteViewHolder> {
  private OnCatClickedListener<Favorite> onFavoriteClicked;

  public CatFavoritesAdapter() {
    super(FAVORITE_DIFF_CALLBACK);
  }

  public void setOnFavoriteClicked(OnCatClickedListener<Favorite> onFavoriteClicked) {
    this.onFavoriteClicked = onFavoriteClicked;
  }

  @NonNull
  @Override
  public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    CatItemBinding binding = CatItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new FavoriteViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
    holder.bind(getItem(position), onFavoriteClicked);
  }

  public class FavoriteViewHolder extends ViewHolder {
    private final CatItemBinding binding;

    public FavoriteViewHolder(@NonNull CatItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    public void bind(Favorite favorite, OnCatClickedListener<Favorite> listener) {
      Glide.with(binding.getRoot())
          .load(favorite.getImage().getUrl())
          .centerCrop()
          .into(binding.catItemImage);

      binding.getRoot().setOnClickListener(v -> {
        if (listener != null) {
          listener.onCatClicked(favorite, getAdapterPosition());
        }
      });
    }
  }

  static final DiffUtil.ItemCallback<Favorite> FAVORITE_DIFF_CALLBACK = new ItemCallback<Favorite>() {
    @Override
    public boolean areItemsTheSame(@NonNull Favorite oldItem, @NonNull Favorite newItem) {
      return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Favorite oldItem, @NonNull Favorite newItem) {
      return false; // always redraw
    }
  };
}
