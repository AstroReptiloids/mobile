package com.example.myapplication.ui.features.chat.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.R
import com.example.myapplication.data.model.MicrochatBO
import com.example.myapplication.databinding.ActivityChatListBinding
import com.example.myapplication.ui.base.activity.BaseMvpActivity
import com.example.myapplication.ui.features.chat.adapter.ChatAdapter
import com.example.myapplication.ui.features.chat.data.MemberData
import com.example.myapplication.ui.features.chat.data.Message
import com.example.myapplication.ui.features.chat.di.ChatListActivityModule
import com.example.myapplication.ui.features.chat.di.DaggerChatListActivityComponent
import com.example.myapplication.ui.features.chat.presenter.IChatListActivityPresenter
import java.io.IOException
import java.util.*


class ChatListActivity :
    BaseMvpActivity<ActivityChatListBinding, IChatListView, IChatListActivityPresenter>(),
    IChatListView {

    private var messageAdapter: ChatAdapter? = null

    var categoryId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        messageAdapter = ChatAdapter(this)
        binding.messagesView.adapter = messageAdapter
        messageAdapter?.listener = ChatAdapter.Listener {
            if (!it.isChat) {
                presenter.createChat(it.text, "", it.parentId, categoryId)
            } else {
                presenter.getChatAndOpen(it.parentId)
            }
        }

        categoryId = intent.getStringExtra("id")

        presenter.onCreate(categoryId)
        title = "Список чатов"
    }


    override fun showChats(list: List<MicrochatBO>) {
        for (i in list) {
            val message = Message(
                i.parentId,
                i.id,
                i.title,
                MemberData(i.creator?.firstName, i.creator?.lastName, getRandomColor()),
                isBelongsToCurrentUser = false,
                isChat = true
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


    override fun openCreatedChat(chatBO: MicrochatBO) {
        startActivity(Intent(this, ChatActivity::class.java).apply {
            putExtra("microchat", chatBO)
        })
    }

    private fun testInitTestMessage() {
        val message = Message(
            "141",
            "141",
            "Тема 1",
            MemberData(getRandomName(), getRandomSurname(), getRandomColor()),
            isBelongsToCurrentUser = false,
            isChat = true
        )
        runOnUiThread {
            messageAdapter?.add(message)
            binding.messagesView.setSelection(binding.messagesView.count - 1)
        }

        val message1 = Message(
            "141",
            "141",
            "Тема 2",
            MemberData(getRandomName(), getRandomSurname(), getRandomColor()),
            false,
            isChat = true
        )
        runOnUiThread {
            messageAdapter?.add(message1)
            binding.messagesView.setSelection(binding.messagesView.count - 1)
        }

        val message3 = Message(
            "142",
            "142",
            "Тема 3",
            MemberData(getRandomName(), getRandomSurname(), getRandomColor()),
            isBelongsToCurrentUser = false,
            isChat = true
        )
        message3.fireCount = 3
        message3.forkCount = 2
        message3.peoplesCount = 50
        runOnUiThread {
            messageAdapter?.add(message3)
            binding.messagesView.setSelection(binding.messagesView.count - 1)
        }
    }

    fun createChat(view: View?) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Введите название чата")

        val customLayout: View =
            layoutInflater.inflate(R.layout.create_chat_dialog, null)
        builder.setView(customLayout)

        builder.setPositiveButton(
            "Создать"
        ) { dialog, which ->
            val editText = customLayout.findViewById<EditText>(R.id.editText)

            presenter.createChat((editText.text.toString()), "", null, categoryId)

        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
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
        get() = R.layout.activity_chat_list

    override fun injectDependencies() {
        DaggerChatListActivityComponent.builder()
            .applicationComponent(applicationComponent)
            .chatListActivityModule(ChatListActivityModule())
            .build()
            .inject(this)
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

    // API: get chat
    fun onMessageReceived(receivedMessage: Message?) {
        try {
            val message = receivedMessage?.text?.let {
                Message(
                    "146",
                    "146",
                    it,
                    MemberData(getRandomName(), getRandomSurname(), getRandomColor()),
                    isBelongsToCurrentUser = false,
                    isChat = true
                )
            }
            runOnUiThread {
                messageAdapter?.add(message)
                binding.messagesView.setSelection(binding.messagesView.count - 1)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}