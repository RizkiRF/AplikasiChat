package id.web.rizki.perbaikanuts;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    EditText namaPengguna, kontenChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        namaPengguna = (EditText) findViewById(R.id.judul_krm);
        kontenChat = (EditText) findViewById(R.id.pesan_krm);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Klik(View view){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Pengirim", namaPengguna.getText().toString());
            jsonObject.put("Content", kontenChat.getText().toString());
            jsonObject.put("Waktu", new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
            jsonObject.put("Foto", R.drawable.ic_launcher);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(preferences.contains("message")){
            String dataPesan = preferences.getString("message","");
            try {
                JSONArray jsonArray = new JSONArray(dataPesan);
                jsonArray.put(jsonObject);
                editor.putString("message", jsonArray.toString());
                editor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            editor.putString("message", jsonArray.toString());
            editor.apply();
        }
        finish();
    }

}