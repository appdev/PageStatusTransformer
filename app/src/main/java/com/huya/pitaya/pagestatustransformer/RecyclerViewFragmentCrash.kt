package com.huya.pitaya.pagestatustransformer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.huya.pitaya.ui.status.PageStatusTransformer
import com.huya.pitaya.ui.status.ReplacementViewStatus
import com.huya.pitaya.ui.status.SimpleStatus
import kotlinx.android.synthetic.main.fragment_recycler_view.*

class RecyclerViewFragmentCrash : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = (0..100).map { it.toString() }
        recycler_view.adapter = A(list)

        val status =
            PageStatusTransformer.newInstance(this, recycler_view) {
                "Waiting for Data" {
                    object : ReplacementViewStatus() {
                        override fun inflateView(
                            inflater: LayoutInflater,
                            parent: ViewGroup
                        ): View {
                            return ProgressBar(parent.context)
                        }
                    }
                }
                "Data arrive" {
                    SimpleStatus(contentView)
                }
            }

        status.transform("Waiting for Data")

        recycler_view.postDelayed({
            status.transform("Data arrive")
        }, 1000L)
    }

    private class A(val data: List<String>) : RecyclerView.Adapter<H>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
            val tv = TextView(parent.context)
            tv.setPadding(20, 20, 20, 20)
            tv.textSize = 20f
            return H(tv)
        }

        override fun onBindViewHolder(holder: H, position: Int) {
            holder.tv.text = data[position]
        }

        override fun getItemCount(): Int = data.size
    }

    private class H(val tv: TextView) : RecyclerView.ViewHolder(tv)

}