package fastcampus.aop.part5.chapter02.di

import fastcampus.aop.part5.chapter02.data.network.buildOkHttpClient
import fastcampus.aop.part5.chapter02.data.network.provideGsonConverterFactory
import fastcampus.aop.part5.chapter02.data.network.provideProductApiService
import fastcampus.aop.part5.chapter02.data.network.provideProductRetrofit
import fastcampus.aop.part5.chapter02.data.repository.DefaultProductRepository
import fastcampus.aop.part5.chapter02.data.repository.ProductRepository
import fastcampus.aop.part5.chapter02.domain.GetProductItemUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val appModule = module {

    // Coroutines Dispatcher
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    // factory
    factory { GetProductItemUseCase(get()) }

    // Repository
    single<ProductRepository> { DefaultProductRepository(get(), get(), get()) }

    single { provideGsonConverterFactory() }

    single { buildOkHttpClient() }

    single { provideProductRetrofit(get(), get()) }

    single { provideProductApiService(get()) }

}