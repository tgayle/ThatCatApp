package app.endershrooms.thatcatapp.screens.fragment.breeds;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import app.endershrooms.thatcatapp.databinding.BreedSimpleItemBinding;
import app.endershrooms.thatcatapp.model.Breed;
import app.endershrooms.thatcatapp.screens.fragment.breeds.CatBreedAdapter.BreedViewHolder;
import app.endershrooms.thatcatapp.view.OnCatClickedListener;

public class CatBreedAdapter extends ListAdapter<Breed, BreedViewHolder> {
  public CatBreedAdapter() {
    super(BREED_DIFF);
  }
  public OnCatClickedListener<Breed> clickListener;

  @NonNull
  @Override
  public BreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new BreedViewHolder(
        BreedSimpleItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull BreedViewHolder holder, int position) {
    holder.bind(getItem(position), clickListener);
  }

  public void setClickListener(OnCatClickedListener<Breed> clickListener) {
    this.clickListener = clickListener;
  }

  class BreedViewHolder extends ViewHolder {
    private final BreedSimpleItemBinding binding;

    public BreedViewHolder(@NonNull BreedSimpleItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    public void bind(Breed breed, OnCatClickedListener<Breed> onClicked) {
      binding.setBreed(breed);
      binding.executePendingBindings();
      if (onClicked != null) {
        binding.getRoot().setOnClickListener(v -> onClicked.onCatClicked(breed, getAdapterPosition()));
      }
    }
  }

  private static final DiffUtil.ItemCallback<Breed> BREED_DIFF = new ItemCallback<Breed>() {
    @Override
    public boolean areItemsTheSame(@NonNull Breed oldItem, @NonNull Breed newItem) {
      return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Breed oldItem, @NonNull Breed newItem) {
      return oldItem.getDescription().equals(newItem.getDescription());
    }
  };
}
