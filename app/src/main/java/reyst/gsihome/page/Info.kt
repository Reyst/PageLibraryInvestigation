package reyst.gsihome.page

import android.arch.paging.DataSource
import android.arch.paging.PositionalDataSource
import android.support.v7.util.DiffUtil
import android.util.Log

data class Info(val id: Int, val name: String) {
    override fun toString() = "$id - $name"
}

class InfoDiffer: DiffUtil.ItemCallback<Info>() {

    override fun areItemsTheSame(oldItem: Info?, newItem: Info?): Boolean = oldItem?.id == newItem?.id

    override fun areContentsTheSame(oldItem: Info?, newItem: Info?) = oldItem == newItem

}

class InfoDataSourceFactory(private val storage: InfoStorage): DataSource.Factory<Int, Info>() {
    override fun create() = InfoDataSource(storage)
}

class InfoDataSource(private val storage: InfoStorage): PositionalDataSource<Info>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Info>) {
        Log.d("INSPECT", "loadInitial, requestedStartPosition = ${params.requestedStartPosition}, requestedLoadSize = ${params.requestedLoadSize}")
        Thread.sleep(3_000)
        val result = storage.getInitialData(params.requestedLoadSize)
        callback.onResult(result, 0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Info>) {
        Log.d("INSPECT", "loadRange, startPosition = ${params.startPosition}, loadSize = ${params.loadSize}")
        Thread.sleep(1_000)
        val result = storage.getData(params.startPosition, params.loadSize)
        callback.onResult(result)
    }
}

class InfoStorage {

    fun getInitialData(loadSize: Int): MutableList<Info> {
        return getData(0, loadSize)
    }

    fun getData(startPosition: Int, loadSize: Int): MutableList<Info> {
        val endPos = minOf(startPosition + loadSize - 1, 99)
        return (startPosition..endPos).map { Info(it + 1, "Info num ${it + 1}") } as MutableList<Info>
    }
}