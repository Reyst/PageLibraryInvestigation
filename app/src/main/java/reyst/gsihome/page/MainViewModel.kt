package reyst.gsihome.page

import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import java.util.concurrent.Executor

class MainViewModel(mainExecutor: Executor) : ViewModel() {

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(10)
        .build()

    val pagedInfo = LivePagedListBuilder(
        InfoDataSourceFactory(InfoStorage()),
        config
    ).build()

    //    private val pageList = PagedList.Builder<Int, Info>(InfoDataSource(InfoStorage()), config)
    //        .setNotifyExecutor(mainExecutor)
    //        .setFetchExecutor(Executors.newSingleThreadExecutor())
    //        .build()
}
