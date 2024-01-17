package com.naufalnibros.kagura

import com.naufalnibros.kagura.viewmodels.SkinViewModel
import io.ktor.client.engine.android.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun platformModule() = module {

    single {
        Android.create()
    }

    viewModel {
        SkinViewModel(get())
    }
}