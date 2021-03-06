package com.example.myapplication.ui.features.chat.view

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.data.model.MessageBO
import com.example.myapplication.data.model.MicrochatBO
import com.example.myapplication.databinding.ActivityChatBinding
import com.example.myapplication.ui.base.activity.BaseMvpActivity
import com.example.myapplication.ui.features.chat.adapter.MessageAdapter
import com.example.myapplication.ui.features.chat.data.MemberData
import com.example.myapplication.ui.features.chat.data.Message
import com.example.myapplication.ui.features.chat.di.ChatActivityModule
import com.example.myapplication.ui.features.chat.di.DaggerChatActivityComponent
import com.example.myapplication.ui.features.chat.presenter.IChatActivityPresenter
import java.io.IOException

import java.util.*


class ChatActivity : BaseMvpActivity<ActivityChatBinding, IChatView, IChatActivityPresenter>(), IChatView{

    private val channelID = "ioncKdqaLM7SSfrA"
    private val roomName = "observable-room"

    var microcahtBO: MicrochatBO? = null

    private var messageAdapter: MessageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        messageAdapter = MessageAdapter(this)
        binding.messagesView.adapter = messageAdapter

      //  testInitTestMessage()

        microcahtBO = (intent.getSerializableExtra("microchat")) as MicrochatBO?

        title = microcahtBO?.title ?: "Микрочаты"

        presenter.getMessages(microcahtBO?.id ?: "")
    }

    override fun showChats(list: List<MessageBO>) {
        for (i in list) {
            val message = Message(
                i,
                null,
                i.text,
                MemberData(i.user?.firstName, i.user?.lastName, getRandomColor()),
                isBelongsToCurrentUser = i.isMy ?: false,
                isChat = i.isParent!!
            )
            message.fireCount = i.hot.toInt()
            message.peoplesCount = i.peopleCount
            message.forkCount = i.microchatCount

            runOnUiThread {
                messageAdapter?.add(message)
                binding.messagesView.setSelection(binding.messagesView.count - 1)
            }
        }
    }

    fun sendMessage(view: View?) {
        val messageText: String = binding.editText.text.toString()
        if (messageText.isNotEmpty()) {



            val message = Message(
                null,
                microcahtBO,
                messageText,
                MemberData(getRandomName(), getRandomSurname(), getRandomColor()),
                isBelongsToCurrentUser = true,
                isChat = false
            )
            presenter.sendMessage(message)
            binding.editText.text.clear()
        }
    }

    override fun showMessage(messageBO: MessageBO) {
        val message = Message(
            messageBO,
            microcahtBO,
            messageBO.text,
            MemberData(getRandomName(), getRandomSurname(), getRandomColor()),
            isBelongsToCurrentUser = messageBO.isMy ?: false,
            isChat = false
        )
        messageAdapter?.add(message)
        binding.messagesView.setSelection(binding.messagesView.count - 1)
    }

    private fun getRandomSurname(): String? {
        val nouns = arrayOf(
            "waterfall",
            "river",
            "breeze",
            "moon",
            "rain",
            "wind",
            "sea",
            "morning",
            "snow",
            "lake",
            "sunset",
            "pine",
            "shadow",
            "leaf",
            "dawn",
            "glitter",
            "forest",
            "hill",
            "cloud",
            "meadow",
            "sun",
            "glade",
            "bird",
            "brook",
            "butterfly",
            "bush",
            "dew",
            "dust",
            "field",
            "fire",
            "flower",
            "firefly",
            "feather",
            "grass",
            "haze",
            "mountain",
            "night",
            "pond",
            "darkness",
            "snowflake",
            "silence",
            "sound",
            "sky",
            "shape",
            "surf",
            "thunder",
            "violet",
            "water",
            "wildflower",
            "wave",
            "water",
            "resonance",
            "sun",
            "wood",
            "dream",
            "cherry",
            "tree",
            "fog",
            "frost",
            "voice",
            "paper",
            "frog",
            "smoke",
            "star"
        )
        return nouns[Math.floor(Math.random() * nouns.size).toInt()]
    }

    private fun getRandomName(): String? {
        val adjs = arrayOf(
            "autumn",
            "hidden",
            "bitter",
            "misty",
            "silent",
            "empty",
            "dry",
            "dark",
            "summer",
            "icy",
            "delicate",
            "quiet",
            "white",
            "cool",
            "spring",
            "winter",
            "patient",
            "twilight",
            "dawn",
            "crimson",
            "wispy",
            "weathered",
            "blue",
            "billowing",
            "broken",
            "cold",
            "damp",
            "falling",
            "frosty",
            "green",
            "long",
            "late",
            "lingering",
            "bold",
            "little",
            "morning",
            "muddy",
            "old",
            "red",
            "rough",
            "still",
            "small",
            "sparkling",
            "throbbing",
            "shy",
            "wandering",
            "withered",
            "wild",
            "black",
            "young",
            "holy",
            "solitary",
            "fragrant",
            "aged",
            "snowy",
            "proud",
            "floral",
            "restless",
            "divine",
            "polished",
            "ancient",
            "purple",
            "lively",
            "nameless"
        )
        return adjs[Math.floor(Math.random() * adjs.size).toInt()]
    }

    private fun getRandomColor(): String? {
        val r = Random()
        val sb = StringBuffer("#")
        while (sb.length < 7) {
            sb.append(Integer.toHexString(r.nextInt()))
        }
        return sb.toString().substring(0, 7)
    }

    override val layoutResId: Int
        get() = R.layout.activity_chat

    override fun injectDependencies() {
        DaggerChatActivityComponent.builder()
            .applicationComponent(applicationComponent)
            .chatActivityModule(ChatActivityModule())
            .build().inject(this)
    }

    override fun hideProgress() {

    }

    override fun showProgress() {

    }

    fun onOpen() {
        println("Conneted to room")
    }

    fun onOpenFailure(ex: java.lang.Exception?) {
        System.err.println(ex)
    }

}