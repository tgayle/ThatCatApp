package app.endershrooms.thatcatapp.ui;

import static app.endershrooms.thatcatapp.TestUtilities.getMockObserver;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import app.endershrooms.thatcatapp.TrampolineSchedulerRule;
import app.endershrooms.thatcatapp.db.dao.CatDao;
import app.endershrooms.thatcatapp.model.Cat;
import app.endershrooms.thatcatapp.model.builders.SearchQueryOrder;
import app.endershrooms.thatcatapp.net.CatService;
import app.endershrooms.thatcatapp.screens.fragment.search.CatSearchViewModel;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

@RunWith(JUnit4.class)
public class SearchViewModelTest {
  @Rule
  public TestRule instantExecutorRule = new InstantTaskExecutorRule();

  @Rule
  public TestRule trampolineExecutionRule = new TrampolineSchedulerRule();

  CatService catService = Mockito.mock(CatService.class);

  CatDao catDao = Mockito.mock(CatDao.class);

  CatSearchViewModel searchVm;

  @Test
  public void onGetNewCatSuccess() {
    searchVm = new CatSearchViewModel(catService, catDao);

    Cat aCat = new Cat();
    List<Cat> cats = new ArrayList<>();
    cats.add(aCat);

    Mockito.when(catService.getImages(Mockito.anyMap())).thenReturn(Single.just(cats));

    searchVm.loadSearchResults();
    searchVm.getSearchResults().observeForever(getMockObserver());

    Assert.assertEquals(cats, searchVm.getSearchResults().getValue());
  }

  @Test
  public void sortButtonChanged() {
    searchVm = new CatSearchViewModel(catService, catDao);

    searchVm.getSearchQuery().observeForever(getMockObserver());
    searchVm.sortButtonChecked(SearchQueryOrder.ASC);
    Assert.assertEquals(SearchQueryOrder.ASC, searchVm.getSearchQuery().getValue().getOrder());
  }

  @Test
  public void searchLimitChangedInBounds() {
    searchVm = new CatSearchViewModel(catService, catDao);

    searchVm.getSearchQuery().observeForever(getMockObserver());
    searchVm.limitTextChanged("50");
    Assert.assertEquals(searchVm.getSearchQuery().getValue().getLimit(), 50);
  }

  @Test
  public void searchLimitChangedOutOfBounds() {
    searchVm = new CatSearchViewModel(catService, catDao);

    searchVm.getSearchQuery().observeForever(getMockObserver());
    searchVm.limitTextChanged("999");
    Assert.assertEquals(searchVm.getSearchQuery().getValue().getLimit(), CatSearchViewModel.MAX_IMAGE_LIMIT);
  }
}
