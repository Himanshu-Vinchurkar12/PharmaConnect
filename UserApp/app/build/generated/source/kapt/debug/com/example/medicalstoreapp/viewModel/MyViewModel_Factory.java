package com.example.medicalstoreapp.viewModel;

import com.example.medicalstoreapp.prefData.MyPreference;
import com.example.medicalstoreapp.repo.Repo;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
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
public final class MyViewModel_Factory implements Factory<MyViewModel> {
  private final Provider<Repo> repoProvider;

  private final Provider<MyPreference> prefsProvider;

  public MyViewModel_Factory(Provider<Repo> repoProvider, Provider<MyPreference> prefsProvider) {
    this.repoProvider = repoProvider;
    this.prefsProvider = prefsProvider;
  }

  @Override
  public MyViewModel get() {
    return newInstance(repoProvider.get(), prefsProvider.get());
  }

  public static MyViewModel_Factory create(Provider<Repo> repoProvider,
      Provider<MyPreference> prefsProvider) {
    return new MyViewModel_Factory(repoProvider, prefsProvider);
  }

  public static MyViewModel newInstance(Repo repo, MyPreference prefs) {
    return new MyViewModel(repo, prefs);
  }
}
