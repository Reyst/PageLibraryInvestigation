package reyst.gsihome.page

import android.arch.lifecycle.Observer
import android.arch.paging.PagedListAdapter
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val vmMain: MainViewModel by viewModel()

    private val srlMain: SwipeRefreshLayout by lazy { findViewById<SwipeRefreshLayout>(R.id.srl_main) }
    private val rvMain: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rv_main) }

    private val adapter = InfoAdapter(InfoDiffer())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        srlMain.setOnRefreshListener { vmMain.refreshList() }

        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = adapter
        vmMain.pagedInfo.observe(this, Observer {
            adapter.submitList(it)
            srlMain.isRefreshing = false
        })
    }
}

class InfoAdapter(itemDiffer: DiffUtil.ItemCallback<Info>) : PagedListAdapter<Info, InfoVH>(itemDiffer) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return InfoVH(view)
    }

    override fun onBindViewHolder(holder: InfoVH, position: Int) {
        getItem(position)?.also { holder.bind(it) }
    }

}

class InfoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Info) {
        (itemView as TextView).text = item.toString()
    }
}
