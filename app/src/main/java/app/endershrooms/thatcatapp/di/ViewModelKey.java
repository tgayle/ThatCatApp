package app.endershrooms.thatcatapp.di;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// https://github.com/riggaroo/android-arch-components-date-countdown/blob/master/app/src/main/java/za/co/riggaroo/datecountdown/injection/ViewModelKey.java
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {

  Class<? extends ViewModel> value();
}