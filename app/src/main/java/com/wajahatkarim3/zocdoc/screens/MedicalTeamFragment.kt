package com.wajahatkarim3.zocdoc.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cometchat.pro.constants.CometChatConstants
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.core.ConversationsRequest
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.Conversation
import com.cometchat.pro.models.User
import com.google.android.material.snackbar.Snackbar
import com.wajahatkarim3.zocdoc.R
import com.wajahatkarim3.zocdoc.databinding.FragmentMedicalTeamBinding
import com.wajahatkarim3.zocdoc.model.DoctorModel
import listeners.OnItemClickListener

class MedicalTeamFragment : Fragment() {

    lateinit var bi: FragmentMedicalTeamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bi = FragmentMedicalTeamBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViews()
        setLoggedInUI()
    }

    fun setupViews() {
        bi.btnFindDoctor.setOnClickListener {
            (activity as? MainActivity)?.selectTab(0)
        }

        bi.btnLogin.setOnClickListener {
            (activity as? MainActivity)?.selectTab(4)
        }

        bi.cometchatConversationList.setItemClickListener(object : OnItemClickListener<Conversation>() {
            override fun OnItemClick(conversation: Conversation?, position: Int) {
                var cometDoctor = conversation!!.conversationWith as User

                var doctor = DoctorModel(
                    doctorId = cometDoctor.uid,
                    name = cometDoctor.name
                )

                var intent = Intent(activity, ChatActivity::class.java)
                intent.putExtra("doctor", doctor)
                startActivity(intent)
            }
        })
    }

    fun setLoggedInUI() {
        if (CometChat.getLoggedInUser() != null) {
            bi.btnLogin.visibility = View.GONE
            bi.lblHaveAccount.visibility = View.GONE

            var conversationsRequest = ConversationsRequest.ConversationsRequestBuilder()
                .setLimit(50)
                .setConversationType(CometChatConstants.CONVERSATION_TYPE_USER)
                .build()

            conversationsRequest.fetchNext(object : CometChat.CallbackListener<List<Conversation>>() {
                override fun onSuccess(conversations: List<Conversation>?) {
                    if (conversations?.isEmpty() == true) {
                        bi.cardMedicalTeam.visibility = View.VISIBLE
                        bi.cometchatConversationList.visibility = View.GONE
                    } else {
                        bi.cardMedicalTeam.visibility = View.GONE
                        bi.cometchatConversationList.visibility = View.VISIBLE
                        bi.cometchatConversationList.setConversationList(conversations)
                    }
                }

                override fun onError(ex: CometChatException?) {
                    Snackbar.make(bi.root, ex?.localizedMessage ?: "Couldn't load conversations list", Snackbar.LENGTH_SHORT).show()
                }
            })
        }
    }
}