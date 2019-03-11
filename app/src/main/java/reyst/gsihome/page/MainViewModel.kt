package reyst.gsihome.page

import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList

class MainViewModel : ViewModel() {

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(10)
        .build()

    val pagedInfo = LivePagedListBuilder(
        InfoDataSourceFactory(InfoStorage()),
        config
    ).build()

    fun refreshList() {
        pagedInfo.value?.dataSource?.invalidate()
    }

}
