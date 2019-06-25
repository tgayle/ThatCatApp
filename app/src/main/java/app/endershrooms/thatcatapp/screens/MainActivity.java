package app.endershrooms.thatcatapp.screens;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import app.endershrooms.thatcatapp.R;
import app.endershrooms.thatcatapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
    NavigationUI.setupWithNavController(binding.navView, navController);
  }

}
