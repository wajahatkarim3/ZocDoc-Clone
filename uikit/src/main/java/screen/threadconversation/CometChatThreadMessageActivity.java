package screen.threadconversation;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.emoji.bundled.BundledEmojiCompatConfig;
import androidx.emoji.text.EmojiCompat;
import androidx.fragment.app.Fragment;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.models.BaseMessage;
import com.cometchat.pro.uikit.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapter.ThreadAdapter;
import constant.StringContract;
import listeners.MessageActionCloseListener;
import listeners.OnMessageLongClick;

import com.cometchat.pro.uikit.Reaction.model.Reaction;
import com.cometchat.pro.uikit.Settings.UISettings;

/**

* Purpose - CometChatMessageListActivity.class is a Activity used to display messages using CometChatMessageScreen.class. It takes
            parameter like TYPE to differentiate between User MessageScreen & Group MessageScreen.

            It passes parameters like UID (userID) ,AVATAR (userAvatar) ,NAME (userName) ,STATUS (userStatus) to CometChatMessageScreen.class
            if TYPE is CometChatConstant.RECEIVER_TYPE_USER

            It passes parameters like GUID (groupID) ,AVATAR (groupIcon) ,NAME (groupName) ,GROUP_OWNER (groupOwner) to CometChatMessageScreen.class
            if TYPE is CometChatConstant.RECEIVER_TYPE_GROUP

            @see CometChatConstants
            @see CometChatThreadMessageScreen


*/

public class CometChatThreadMessageActivity extends AppCompatActivity implements ThreadAdapter.OnMessageLongClick {

    private static final String TAG = "CometChatMessageListAct";

    private OnMessageLongClick messageLongClick;

    Fragment fragment = new CometChatThreadMessageScreen();

    private String avatar;

    private String name;

    private String uid;

    private String messageType;

    private String message;

    private String messagefileName;

    private String mediaUrl;

    private String mediaExtension;

    private int messageId;

    private int mediaSize;

    private String mediaMime;

    private String type;

    private String Id;

    private long sentAt;

    private String messageCategory;

    private double latitude;

    private double longitude;

    private int replyCount;

    private String conversationName;

    private String baseMessage;

    private String pollQuestion;

    private String pollOptions;

    private ArrayList<String> pollResult;

    private int voteCount;

    private HashMap<String,String> reactionInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cometchat_message_list);

        if (UISettings.getColor() !=null) {
            getWindow().setStatusBarColor(Color.parseColor(UISettings.getColor()));
        }
        EmojiCompat.Config config = new BundledEmojiCompatConfig(this);
        EmojiCompat.init(config);

         if (getIntent()!=null) {
             Bundle bundle = new Bundle();
//             if (getIntent().hasExtra(StringContract.IntentStrings.PARENT_BASEMESSAGE))
//                 baseMessage = getIntent().getStringExtra(StringContract.IntentStrings.PARENT_BASEMESSAGE);
             if (getIntent().hasExtra(StringContract.IntentStrings.MESSAGE_CATEGORY))
                 messageCategory = getIntent().getStringExtra(StringContract.IntentStrings.MESSAGE_CATEGORY);
             if (getIntent().hasExtra(StringContract.IntentStrings.LOCATION_LONGITUDE))
                 longitude = getIntent().getDoubleExtra(StringContract.IntentStrings.LOCATION_LONGITUDE,0);
             if (getIntent().hasExtra(StringContract.IntentStrings.LOCATION_LATITUDE))
                 latitude = getIntent().getDoubleExtra(StringContract.IntentStrings.LOCATION_LATITUDE,0);
             if (getIntent().hasExtra(StringContract.IntentStrings.CONVERSATION_NAME))
                 conversationName = getIntent().getStringExtra(StringContract.IntentStrings.CONVERSATION_NAME);
             if (getIntent().hasExtra(StringContract.IntentStrings.PARENT_ID))
                 messageId = getIntent().getIntExtra(StringContract.IntentStrings.PARENT_ID,0);
             if (getIntent().hasExtra(StringContract.IntentStrings.REPLY_COUNT))
                 replyCount = getIntent().getIntExtra(StringContract.IntentStrings.REPLY_COUNT,0);
             if (getIntent().hasExtra(StringContract.IntentStrings.AVATAR))
                 avatar = getIntent().getStringExtra(StringContract.IntentStrings.AVATAR);
             if (getIntent().hasExtra(StringContract.IntentStrings.NAME))
                 name = getIntent().getStringExtra(StringContract.IntentStrings.NAME);
             if (getIntent().hasExtra(StringContract.IntentStrings.MESSAGE_TYPE))
                 messageType = getIntent().getStringExtra(StringContract.IntentStrings.MESSAGE_TYPE);
             if (getIntent().hasExtra(StringContract.IntentStrings.UID))
                 uid = getIntent().getStringExtra(StringContract.IntentStrings.UID);
             if (getIntent().hasExtra(StringContract.IntentStrings.SENTAT))
                 sentAt = getIntent().getLongExtra(StringContract.IntentStrings.SENTAT,0);
             if (getIntent().hasExtra(StringContract.IntentStrings.TEXTMESSAGE))
                 message = getIntent().getStringExtra(StringContract.IntentStrings.TEXTMESSAGE);
             if (getIntent().hasExtra(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_NAME))
                 messagefileName = getIntent().getStringExtra(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_NAME);
             if (getIntent().hasExtra(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_SIZE))
                 mediaSize = getIntent().getIntExtra(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_SIZE,0);
             if (getIntent().hasExtra(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_URL))
                 mediaUrl = getIntent().getStringExtra(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_URL);
             if (getIntent().hasExtra(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_EXTENSION))
                 mediaExtension = getIntent().getStringExtra(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_EXTENSION);
             if (getIntent().hasExtra(StringContract.IntentStrings.TYPE))
                 type = getIntent().getStringExtra(StringContract.IntentStrings.TYPE);
             if (getIntent().hasExtra(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_MIME_TYPE))
                 mediaMime = getIntent().getStringExtra(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_MIME_TYPE);
             if (getIntent().hasExtra(StringContract.IntentStrings.POLL_QUESTION))
                 pollQuestion = getIntent().getStringExtra(StringContract.IntentStrings.POLL_QUESTION);
             if (getIntent().hasExtra(StringContract.IntentStrings.POLL_OPTION))
                 pollOptions = getIntent().getStringExtra(StringContract.IntentStrings.POLL_OPTION);
             if (getIntent().hasExtra(StringContract.IntentStrings.POLL_RESULT))
                 pollResult = getIntent().getStringArrayListExtra(StringContract.IntentStrings.POLL_RESULT);
             if (getIntent().hasExtra(StringContract.IntentStrings.POLL_VOTE_COUNT))
                 voteCount = getIntent().getIntExtra(StringContract.IntentStrings.POLL_VOTE_COUNT,0);
             if (type.equals(CometChatConstants.RECEIVER_TYPE_GROUP)) {
                 if (getIntent().hasExtra(StringContract.IntentStrings.GUID))
                     Id = getIntent().getStringExtra(StringContract.IntentStrings.GUID);
             } else{
                 if (getIntent().hasExtra(StringContract.IntentStrings.UID))
                     Id = getIntent().getStringExtra(StringContract.IntentStrings.UID);
             }
             bundle.putString(StringContract.IntentStrings.MESSAGE_CATEGORY,messageCategory);
             bundle.putString(StringContract.IntentStrings.ID,Id);
             bundle.putString(StringContract.IntentStrings.CONVERSATION_NAME,conversationName);
             bundle.putString(StringContract.IntentStrings.TYPE,type);
             bundle.putString(StringContract.IntentStrings.AVATAR, avatar);
             bundle.putString(StringContract.IntentStrings.NAME, name);
             bundle.putInt(StringContract.IntentStrings.PARENT_ID,messageId);
             bundle.putInt(StringContract.IntentStrings.REPLY_COUNT,replyCount);
             bundle.putString(StringContract.IntentStrings.MESSAGE_TYPE,messageType);
             bundle.putString(StringContract.IntentStrings.UID, uid);
             bundle.putLong(StringContract.IntentStrings.SENTAT, sentAt);

             if (getIntent().hasExtra(StringContract.IntentStrings.REACTION_INFO)) {
                 reactionInfo = (HashMap<String,String>) getIntent().getSerializableExtra(StringContract.IntentStrings.REACTION_INFO);
                 bundle.putSerializable(StringContract.IntentStrings.REACTION_INFO, reactionInfo);
             }

             if (messageType.equals(CometChatConstants.MESSAGE_TYPE_TEXT))
                  bundle.putString(StringContract.IntentStrings.TEXTMESSAGE,message);
             else if (messageType.equals(StringContract.IntentStrings.LOCATION)) {
                  bundle.putDouble(StringContract.IntentStrings.LOCATION_LATITUDE,latitude);
                  bundle.putDouble(StringContract.IntentStrings.LOCATION_LONGITUDE,longitude);
             } else if (messageType.equals(StringContract.IntentStrings.POLLS)) {
                  bundle.putStringArrayList(StringContract.IntentStrings.POLL_RESULT,pollResult);
                  bundle.putString(StringContract.IntentStrings.POLL_QUESTION,pollQuestion);
                  bundle.putString(StringContract.IntentStrings.POLL_OPTION,pollOptions);
                  bundle.putInt(StringContract.IntentStrings.POLL_VOTE_COUNT,voteCount);
             } else if (messageType.equals(StringContract.IntentStrings.STICKERS)) {
                  bundle.putString(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_URL,mediaUrl);
                  bundle.putString(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_NAME,messagefileName);
             } else if (messageType.equals(StringContract.IntentStrings.WHITEBOARD) ||
                      messageType.equals(StringContract.IntentStrings.WRITEBOARD)) {
                  bundle.putString(StringContract.IntentStrings.TEXTMESSAGE,message);
             } else {
                  bundle.putString(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_URL,mediaUrl);
                  bundle.putString(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_NAME,messagefileName);
                  bundle.putString(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_EXTENSION,mediaExtension);
                  bundle.putInt(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_SIZE,mediaSize);
                  bundle.putString(StringContract.IntentStrings.MESSAGE_TYPE_IMAGE_MIME_TYPE,mediaMime);
             }

             fragment.setArguments(bundle);
             getSupportFragmentManager().beginTransaction().replace(R.id.ChatFragment, fragment).commit();
         }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Log.d(TAG, "onActivityResult: ");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult: ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setLongMessageClick(List<BaseMessage> baseMessage) {
        if (fragment!=null)
        ((OnMessageLongClick)fragment).setLongMessageClick(baseMessage);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void handleDialogClose(DialogInterface dialog) {
        ((MessageActionCloseListener)fragment).handleDialogClose(dialog);
        dialog.dismiss();
    }
}
