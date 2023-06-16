package com.dicoding.c23_pc713.gihariapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.c23_pc713.gihariapp.databinding.ActivityChatBinding
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var messageAdapter: MessageAdapter
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://model-api-uiwtfdqmea-et.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }
    private val botApi: BotApi by lazy {
        retrofit.create(BotApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set onClickListener for back button
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        // Set onClickListener for send button
        binding.btnSend.setOnClickListener {
            val message = binding.etMessage.text.toString()
            addMessageToChat(message, true) // Add user message to chat
            binding.etMessage.text.clear()
            sendMessageToBot(message) // Send user message to the bot AI
        }

        // Initialize RecyclerView
        messageAdapter = MessageAdapter()
        binding.rvChatMessages.adapter = messageAdapter
        binding.rvChatMessages.layoutManager = LinearLayoutManager(this)

        // Dummy data for testing
        addMessageToChat("Hello!! how are you?", false) // Receiver message
        addMessageToChat("I'm fine", true) // Sender message
        addMessageToChat("How Was Today?", false) // Receiver message
    }

    private fun addMessageToChat(message: String, isSender: Boolean) {
        val newMessage = Message(message, isSender)
        messageAdapter.addMessage(newMessage)
        binding.rvChatMessages.smoothScrollToPosition(messageAdapter.itemCount - 1)
    }

    private fun sendMessageToBot(message: String) {
        val requestBody = RequestBody.create(MediaType.parse("application/json"), "{\"text\":\"$message\"}")
        val call = botApi.predictText(requestBody)
        call.enqueue(object : Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                if (response.isSuccessful) {
                    val botMessage = response.body()?.prediction
                    botMessage?.let {
                        addMessageToChat(it, false) // Add bot message to chat
                    }
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}




