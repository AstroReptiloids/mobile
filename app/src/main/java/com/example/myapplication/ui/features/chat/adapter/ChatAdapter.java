package com.example.myapplication.ui.features.chat.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.myapplication.R;
import com.example.myapplication.ui.features.chat.data.Message;
import com.example.myapplication.ui.features.chat.view.ChatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ChatAdapter extends BaseAdapter {

    List<Message> chats = new ArrayList<>();
    Context context;
    public ChatAdapter(Context context) {
        this.context = context;
    }


    public void add(Message message) {
        this.chats.add(message);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return chats.size();
    }

    @Override
    public Object getItem(int i) {
        return chats.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ChatViewHolder holder = new ChatViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Message message = chats.get(i);


            assert messageInflater != null;
            convertView = messageInflater.inflate(R.layout.chat_item, null);
            holder.name = convertView.findViewById(R.id.name);
            holder.messageBody = convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);

            holder.name.setText(message.getMemberData().getName() + " " + message.getMemberData().getSurname());
            holder.messageBody.setText(message.getText());

        convertView.setOnClickListener(v -> {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("Хотите обсудить эту тему?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Да",
                    (dialog, id) -> {
                        context.startActivity(new Intent(context, ChatActivity.class));
                        dialog.cancel();
                    });

            builder1.setNegativeButton(
                    "Нет",
                    (dialog, id) -> dialog.cancel());

            AlertDialog alert11 = builder1.create();
            alert11.show();

        });

        holder.chatInfoBlock = convertView.findViewById(R.id.chat_info_block);
        holder.fireCount = convertView.findViewById(R.id.fire_count);
        holder.peoplesCount = convertView.findViewById(R.id.peoples_count);
        holder.forkCount = convertView.findViewById(R.id.fork_count);

        if(message.isChat()) {
            holder.chatInfoBlock.setVisibility(View.VISIBLE);

            initChatBlock(holder, message);
        }else {
            holder.chatInfoBlock.setVisibility(View.GONE);
        }

        return convertView;
    }

    private void initChatBlock(ChatViewHolder holder, Message message) {
        holder.fireCount.setText(String.valueOf(message.getFireCount()));
        holder.peoplesCount.setText(String.valueOf(message.getPeoplesCount()));
        holder.forkCount.setText(String.valueOf(message.getForkCount()));
    }

    public static int dpToPx(int dp) {
        return Math.round(dp * getPixelScaleFactor());
    }

    private static float getPixelScaleFactor() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

}

class ChatViewHolder {
    public TextView name;
    public TextView messageBody;
    public LinearLayout chatInfoBlock;

    public TextView fireCount;
    public TextView peoplesCount;
    public TextView forkCount;
}