package com.example.bestkotlinprojectgithub.di

import android.util.Log
import com.example.bestkotlinprojectgithub.data.GitHubServiceApi
import com.example.bestkotlinprojectgithub.data.ListUserRepositoryUseCase
import com.example.bestkotlinprojectgithub.data.Repository
import com.example.bestkotlinprojectgithub.data.RepositoryImpl
import com.example.bestkotlinprojectgithub.utils.NetWorkUtils
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object DataModule {

    private const val OK_HTTP = "OkHttp"

    fun load() {
        loadKoinModules(networkModules() + repositoriesModule() + useCaseModule())
    }

    private fun networkModules(): Module {
        return module {
            single {

                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .cache(NetWorkUtils.settingCache(androidContext()))
                    .addInterceptor{chain ->
                        var request = chain.request()

                        request = if (NetWorkUtils.isNetworkAvailable(androidContext()))
                            request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build()
                        else
                            request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                        chain.proceed(request)
                    }.build()

            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<GitHubServiceApi>(get(), get())
            }
        }
    }

    private fun repositoriesModule(): Module {
        return module {
            single<Repository> { RepositoryImpl(get()) }
        }


    }
    private fun useCaseModule(): Module {
        return module {
            factory { ListUserRepositoryUseCase(get()) }
        }
    }

    private inline fun <reified T> createService(client: OkHttpClient, factory: GsonConverterFactory): T {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(factory)
            .build().create(T::class.java)
    }
}