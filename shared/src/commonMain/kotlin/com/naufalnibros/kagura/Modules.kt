package com.naufalnibros.kagura

import com.naufalnibros.kagura.data.mapper.SkinMapper
import com.naufalnibros.kagura.data.respository.SkinRepository
import com.naufalnibros.kagura.data.source.SkinRemote
import com.naufalnibros.kagura.domain.interactor.SkinInteractor
import com.naufalnibros.kagura.domain.interactor.SkinInteractorImpl
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    modules(
        appModule(),
        platformModule()
    )
}

fun initKoin() = initKoin {

}

fun appModule() = module {
    single {
        SkinMapper()
    }

    single {
        SkinRemote(get())
    }

    single {
        SkinRepository(get(), get())
    }

    single<SkinInteractor> {
        SkinInteractorImpl(get())
    }


    single {
        Json { isLenient = true; ignoreUnknownKeys = true }
    }


    single {
        HttpClient(get()) {
            install(ContentNegotiation) {
                json(get())
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }
}