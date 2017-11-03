package id.web.rizki.perbaikanuts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    RecyclerView listChat;
    LinearLayoutManager linier;

    public static String mainPers = "file.main.message";

    ChatAdaptor ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listChat = (RecyclerView) findViewById(R.id.item_list);
        linier = new LinearLayoutManager(this);
        listChat.setLayoutManager(linier);

        SharedPreferences shard = getSharedPreferences(mainPers, 0);
        String itemPesan = shard.getString("message","");
        try{
            JSONArray jsonArray = new JSONArray(itemPesan);
            ad = new ChatAdaptor(jsonArray);
            listChat.setAdapter(ad);
            ad.notifyDataSetChanged();
        }catch (JSONException e) {
            e.printStackTrace();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(view.getContext(),ChatActivity.class);
                view.getContext().startActivity(it);
            }
        });
    }


}
