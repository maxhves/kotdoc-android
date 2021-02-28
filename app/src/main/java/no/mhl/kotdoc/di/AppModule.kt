package no.mhl.kotdoc.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import no.mhl.kotdoc.data.remote.DocService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // region Base URL
    @Provides
    fun provideBaseUrl(): String = ""
    // endregion

    // region OkHttp
    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()
    // endregion

    // region Retrofit
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()
    // endregion

    // region Documentation Service
    @Singleton
    @Provides
    fun provideDocService(retrofit: Retrofit): DocService = retrofit.create(DocService::class.java)
    // endregion

}