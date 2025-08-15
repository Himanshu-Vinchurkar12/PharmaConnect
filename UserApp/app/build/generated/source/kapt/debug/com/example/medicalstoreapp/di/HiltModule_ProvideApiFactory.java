package com.example.medicalstoreapp.di;

import com.example.medicalstoreapp.api.Api_Builder;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class HiltModule_ProvideApiFactory implements Factory<Api_Builder> {
  @Override
  public Api_Builder get() {
    return provideApi();
  }

  public static HiltModule_ProvideApiFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Api_Builder provideApi() {
    return Preconditions.checkNotNullFromProvides(HiltModule.INSTANCE.provideApi());
  }

  private static final class InstanceHolder {
    private static final HiltModule_ProvideApiFactory INSTANCE = new HiltModule_ProvideApiFactory();
  }
}
