package reyst.gsihome.page

import android.app.Application
import android.os.Handler
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import java.util.concurrent.Executor

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(diModule))
    }
}

val MainHandler: Handler = Handler()

private val diModule = module {
    viewModel { MainViewModel(get()) }

    single(name="MainHandler") { Handler(androidContext().mainLooper) }
    single { Executor { command -> get<Handler>("MainHandler").post(command) } }
}