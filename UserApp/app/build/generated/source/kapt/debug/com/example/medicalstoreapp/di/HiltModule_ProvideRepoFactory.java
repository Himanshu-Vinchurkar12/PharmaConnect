package com.example.medicalstoreapp.di;

import com.example.medicalstoreapp.api.Api_Builder;
import com.example.medicalstoreapp.repo.Repo;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class HiltModule_ProvideRepoFactory implements Factory<Repo> {
  private final Provider<Api_Builder> apiServiceProvider;

  public HiltModule_ProvideRepoFactory(Provider<Api_Builder> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public Repo get() {
    return provideRepo(apiServiceProvider.get());
  }

  public static HiltModule_ProvideRepoFactory create(Provider<Api_Builder> apiServiceProvider) {
    return new HiltModule_ProvideRepoFactory(apiServiceProvider);
  }

  public static Repo provideRepo(Api_Builder apiService) {
    return Preconditions.checkNotNullFromProvides(HiltModule.INSTANCE.provideRepo(apiService));
  }
}
