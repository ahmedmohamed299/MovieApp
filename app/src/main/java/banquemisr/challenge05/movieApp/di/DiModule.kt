package banquemisr.challenge05.movieApp.di

import banquemisr.challenge05.movieApp.common.Constants
import banquemisr.challenge05.movieApp.data.ApiService
import banquemisr.challenge05.movieApp.data.repoImpl.MovieDetailsRepositoryImpl
import banquemisr.challenge05.movieApp.data.repoImpl.MovieRepositoryImpl
import banquemisr.challenge05.movieApp.domain.repository.MovieDetailsRepository
import banquemisr.challenge05.movieApp.domain.useCase.MovieDetailsUseCase
import banquemisr.challenge05.movieApp.domain.repository.MovieRepository
import banquemisr.challenge05.movieApp.domain.useCase.NowPlayingUseCase
import banquemisr.challenge05.movieApp.presentation.movieDetails.MovieDetailViewModel
import banquemisr.challenge05.movieApp.presentation.nowPlaying.MovieViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    fun retrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY


        }
        val interceptorHeader= Interceptor { chain ->
            val request = chain.request().newBuilder()
            chain.proceed(request.build())
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .addInterceptor(interceptorHeader)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)

        }.build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())

            .baseUrl(Constants.BASIC_URL)
            .client(client)
            .build()
    }


    fun provideAPIService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    single<Retrofit>() { retrofit() }
    single<ApiService> { provideAPIService(get())  }

}

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single<MovieDetailsRepository> { MovieDetailsRepositoryImpl(get()) }

}
val useCaseModule = module {
    single<NowPlayingUseCase> { NowPlayingUseCase(get()) }
    single<MovieDetailsUseCase> { MovieDetailsUseCase(get()) }

}

val viewModelModule = module {
    viewModel {
        MovieViewModel(get())

    }
    viewModel {
        MovieDetailViewModel(get())

    }
}