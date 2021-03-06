package reyst.gsihome.page

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(diModule))
    }
}

private val diModule = module {
    viewModel { MainViewModel() }
}