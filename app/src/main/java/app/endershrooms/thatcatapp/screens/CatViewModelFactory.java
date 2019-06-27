package app.endershrooms.thatcatapp.screens;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class CatViewModelFactory implements ViewModelProvider.Factory {

  private Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

  @Inject
  public CatViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
    this.creators = creators;
  }

  public <T extends ViewModel> T create(Class<T> modelClass) {
    Provider<ViewModel> creator = creators.get(modelClass);

    if (creator == null) {
      for (Entry<Class<? extends ViewModel>, Provider<ViewModel>> provider : creators.entrySet()) {
        if (modelClass.isAssignableFrom(provider.getKey())) {
          creator = provider.getValue();
          break;
        }
      }
    }

    try {
      return (T) creator.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}