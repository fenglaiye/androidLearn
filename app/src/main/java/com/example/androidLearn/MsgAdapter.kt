package com.example.androidLearn

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MsgAdapter(private val msgList: List<Msg>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    inner class LeftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val leftMsg: TextView = view.findViewById(R.id.chatLeftMsg)
//    }
//
//    inner class RightViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val rightMsg: TextView = view.findViewById(R.id.chatRightMsg)
//    }

    override fun getItemViewType(position: Int): Int {
        val msg = msgList[position]
        return msg.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == Msg.TYPE_RECEIVED) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item, parent, false)
            LeftViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item, parent, false)
            RightViewHolder(view)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when (holder) {
            is LeftViewHolder -> holder.leftMsg.text = msg.content
            is RightViewHolder -> holder.rightMsg.text = msg.content
        }
    }

    override fun getItemCount() = msgList.size
}