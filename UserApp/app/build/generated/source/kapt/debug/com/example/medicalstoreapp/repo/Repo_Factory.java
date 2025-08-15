package com.example.medicalstoreapp.repo;

import com.example.medicalstoreapp.api.Api_Builder;
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
public final class Repo_Factory implements Factory<Repo> {
  private final Provider<Api_Builder> apiServicesProvider;

  public Repo_Factory(Provider<Api_Builder> apiServicesProvider) {
    this.apiServicesProvider = apiServicesProvider;
  }

  @Override
  public Repo get() {
    return newInstance(apiServicesProvider.get());
  }

  public static Repo_Factory create(Provider<Api_Builder> apiServicesProvider) {
    return new Repo_Factory(apiServicesProvider);
  }

  public static Repo newInstance(Api_Builder apiServices) {
    return new Repo(apiServices);
  }
}
