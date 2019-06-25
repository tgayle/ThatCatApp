package app.endershrooms.thatcatapp.screens.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import app.endershrooms.thatcatapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CatVote extends Fragment {


  public CatVote() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_cat_vote, container, false);
  }

}
