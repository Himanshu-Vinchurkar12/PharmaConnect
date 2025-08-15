package com.example.medicalstoreapp.di;

import android.content.Context;
import com.example.medicalstoreapp.prefData.MyPreference;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class HiltModule_ProvidePrefsFactory implements Factory<MyPreference> {
  private final Provider<Context> contextProvider;

  public HiltModule_ProvidePrefsFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public MyPreference get() {
    return providePrefs(contextProvider.get());
  }

  public static HiltModule_ProvidePrefsFactory create(Provider<Context> contextProvider) {
    return new HiltModule_ProvidePrefsFactory(contextProvider);
  }

  public static MyPreference providePrefs(Context context) {
    return Preconditions.checkNotNullFromProvides(HiltModule.INSTANCE.providePrefs(context));
  }
}
