package app.endershrooms.thatcatapp.util;

import static org.junit.Assert.assertNotNull;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LiveDataWithInitialTest {

  @Rule
  public TestRule rule = new InstantTaskExecutorRule();

  @Test
  public void initial_hasValue() {
    LiveDataWithInitial<String> ld = new LiveDataWithInitial<>("test");
    assertNotNull(ld.getValue());
  }
}
