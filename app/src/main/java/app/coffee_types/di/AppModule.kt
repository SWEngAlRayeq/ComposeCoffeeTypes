package app.coffee_types.di

import app.coffee_types.data.api.CoffeeApi
import app.coffee_types.data.repo_impl.CoffeeRepositoryImpl
import app.coffee_types.domain.repo.CoffeeRepository
import app.coffee_types.domain.usecase.GetCoffeesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()

    @Provides
    fun provideCoffeeApi(client: OkHttpClient): CoffeeApi =
        Retrofit.Builder()
            .baseUrl("https://api.sampleapis.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoffeeApi::class.java)

    @Provides
    fun provideCoffeeRepository(api: CoffeeApi): CoffeeRepository =
        CoffeeRepositoryImpl(api)

    @Provides
    fun provideUseCase(repo: CoffeeRepository): GetCoffeesUseCase =
        GetCoffeesUseCase(repo)


}