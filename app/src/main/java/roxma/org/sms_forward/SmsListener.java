package roxma.org.sms_forward;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsListener extends BroadcastReceiver {

    public SmsListener() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // throw new UnsupportedOperationException("Not yet implemented");
        Log.d("sms-receive", "on receive," + intent.getAction());
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {

                String messageBody = smsMessage.getMessageBody();
                String address = smsMessage.getOriginatingAddress();
                // default message filter
                String message = "[" + "TEST" + "] " + messageBody;

                String dst_number = context.getSharedPreferences("sms-forward-data", Context.MODE_PRIVATE).getString("dst_number", "");
                if(dst_number == "") {
                    Log.d("sms-check","Destination phone number not set in preferences. Ignore this one.");
                    return;
                }

                String src_number = context.getSharedPreferences("sms-forward-data", Context.MODE_PRIVATE).getString("src_number", "");
                if(src_number == "") {
                    Log.d("sms-check","Source phone number not set in preferences. Ignore this one.");
                    return;
                }

                String filter = context.getSharedPreferences("sms-forward-data", Context.MODE_PRIVATE).getString("filter", "");

                Log.d("sms-check", "source number: " + address);
                Log.d("sms-check", "message: " + messageBody);
                Log.d("sms-check", "forward to: " + dst_number);
                Log.d("sms-check", "source filter: " + src_number);
                Log.d("sms-check", "message filter: " + filter);

                if((address.equals(src_number)) && (messageBody.contains(filter))) {
                    Log.d("sms-send", "send to " + dst_number);
                    Log.d("sms-send", "message sent:" + message);
                    SmsManager.getDefault().sendTextMessage(dst_number, null, message, null, null);
                }

            }
        }
    }
}
