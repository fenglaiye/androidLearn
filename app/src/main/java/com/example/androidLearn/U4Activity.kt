package com.example.androidLearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidLearn.databinding.U4LayoutBinding

private lateinit var binding: U4LayoutBinding

class U4Activity : AppCompatActivity(), View.OnClickListener {
    private val msgList = ArrayList<Msg>()
    private lateinit var adapter: MsgAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = U4LayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initMsg()
        val layoutManager = LinearLayoutManager(this)
        binding.chatRecyclerView.layoutManager = layoutManager
        adapter = MsgAdapter(msgList)
        binding.chatRecyclerView.adapter = adapter
        binding.chatBtnSend.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.chatBtnSend -> {
                val content = binding.chatEditText.text.toString()
                if (content.isNotEmpty()) {
                    val msg = Msg(content, Msg.TYPE_SENT)
                    msgList.add(msg)
                    adapter.notifyItemInserted(msgList.size - 1)
                    binding.chatRecyclerView.scrollToPosition(msgList.size - 1)
                    binding.chatEditText.setText("")
                }
            }
        }
    }

    private fun initMsg() {
        val msg1 = Msg("Hello guy.", Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 = Msg("Hello. Who is that?", Msg.TYPE_SENT)
        msgList.add(msg2)
        val msg3 = Msg("This is Liam. Nice talking to you.", Msg.TYPE_RECEIVED)
        msgList.add(msg3)
    }
}