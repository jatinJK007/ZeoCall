package com.example.bottomnavgoogle

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoCallButton
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton
import com.zegocloud.uikit.service.defines.ZegoUIKitUser

class MainActivity : AppCompatActivity() {

    private lateinit var targetUserId : EditText
    private lateinit var myUserIdText:TextView
    private lateinit var videoCallButton:ZegoSendCallInvitationButton
    private lateinit var voiceCallButton:ZegoSendCallInvitationButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        targetUserId= findViewById(R.id.targetUserId)
        myUserIdText= findViewById(R.id.myUserIdText)
        videoCallButton = findViewById(R.id.videoCallButton)
        voiceCallButton= findViewById(R.id.voiceCallButton)

        val myUserId = intent.getStringExtra("UserID")
        myUserIdText.text = "HI $myUserId \n Whom Do you wanna Call"

        targetUserId.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val targetUserID= targetUserId.text.toString()
                if (targetUserID.isNotEmpty()){
                    startVideoCall(targetUserID)
                    startVoiceCall(targetUserID)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun startVideoCall(targetUserID:String){
        val targetUsrName :String= targetUserID
        videoCallButton.setIsVideoCall(true)
        videoCallButton.resourceID= "zego_uikit_call"
        videoCallButton.setInvitees(listOf(ZegoUIKitUser(targetUserID,targetUsrName)))
    }

    private fun startVoiceCall(targetUserID:String){
        val targetUsrName :String= targetUserID
        voiceCallButton.setIsVideoCall(false)
        voiceCallButton.resourceID= "zego_uikit_call"
        voiceCallButton.setInvitees(listOf(ZegoUIKitUser(targetUserID,targetUsrName)))
    }
}