package roxma.org.sms_forward;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String dst_number = getSharedPreferences("sms-forward-data", Context.MODE_PRIVATE).getString("dst_number", "");
        String src_number = getSharedPreferences("sms-forward-data", Context.MODE_PRIVATE).getString("src_number", "");
        String filter = getSharedPreferences("sms-forward-data", Context.MODE_PRIVATE).getString("filter", "");
        Log.d("read-prefs","destination number: " + dst_number);
        Log.d("read-prefs","source number: " + src_number);
        Log.d("read-prefs","filter: " + filter);
        EditText editText1 = (EditText) findViewById(R.id.edit_src_number);
        editText1.setText(dst_number, TextView.BufferType.EDITABLE);
        EditText editText2 = (EditText) findViewById(R.id.edit_dst_number);
        editText2.setText(src_number, TextView.BufferType.EDITABLE);
        EditText editText3 = (EditText) findViewById(R.id.edit_filter);
        editText3.setText(filter, TextView.BufferType.EDITABLE);
    }

    public void save_prefs(View v)
    {
        EditText editText1 = (EditText) findViewById(R.id.edit_dst_number);
        String dst_number = editText1.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.edit_src_number);
        String src_number = editText2.getText().toString();
        EditText editText3 = (EditText) findViewById(R.id.edit_filter);
        String filter = editText3.getText().toString();

        SharedPreferences.Editor editor = getSharedPreferences("sms-forward-data", Context.MODE_PRIVATE).edit();
        editor.putString("dst_number", dst_number);
        editor.putString("src_number", src_number);
        editor.putString("filter", filter);
        editor.commit();

        Log.d("write-prefs","destination number: " + dst_number);
        Log.d("write-prefs","source number: " + src_number);
        Log.d("write-prefs","filter: " + filter);

    }

}
