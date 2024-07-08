package com.example.bottomnavgoogle

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig

class LoginActivity : AppCompatActivity() {

    private lateinit var myUserId: EditText
    private lateinit var loginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        myUserId = findViewById(R.id.myUserId)
        loginButton= findViewById(R.id.LoginButton)

        loginButton.setOnClickListener {
            val myUserId = myUserId.text.toString()
            if(myUserId.isNotEmpty()){
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("UserID", myUserId)
                startActivity(intent)

                setupZegoUIkit(myUserId)
            }
        }
    }

    private fun setupZegoUIkit(userID:String){
        val application:Application = application
        val appId:Long =284312384
        val appSign :String= "16aec8b017b852d8135412415ee65d3f7d5fda61c9c7a7dcc55f85f8b69d1d12"
        val userName:String=userID
        val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()
        ZegoUIKitPrebuiltCallService.init(application,appId,appSign,userID,userName,callInvitationConfig)
    }

    override fun onDestroy() {
        super.onDestroy()
        ZegoUIKitPrebuiltCallService.unInit()
    }
}