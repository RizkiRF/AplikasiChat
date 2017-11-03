package id.web.rizki.perbaikanuts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by RN on 11/3/2017.
 */

public class ChatAdaptor extends RecyclerView.Adapter<ChatAdaptor.ChatHolder>{
    JSONArray jsonArray;

    public ChatAdaptor(JSONArray jsonArray){
        this.jsonArray = jsonArray;
    }


    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat,parent,false);
        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            holder.foto.setImageResource(jsonObject.getInt("Foto"));
            holder.pengirim.setText(jsonObject.getString("Pengirim"));
            holder.content.setText(jsonObject.getString("Content"));
            holder.waktu.setText(jsonObject.getString("Waktu"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    };

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class ChatHolder extends RecyclerView.ViewHolder {
        ImageView foto;
        TextView pengirim, content, waktu;
        public ChatHolder(View view) {
            super(view);
            foto = (ImageView) view.findViewById(R.id.img_ava);
            pengirim = (TextView) view.findViewById(R.id.judul);
            content = (TextView) view.findViewById(R.id.deskripsi);
            waktu = (TextView) view.findViewById(R.id.tanggal);
        }
    }
}
