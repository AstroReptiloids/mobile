package com.example.myapplication.ui.features.chat.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.myapplication.R;
import com.example.myapplication.ui.features.chat.data.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static androidx.core.content.ContextCompat.getColor;


public class MessageAdapter extends BaseAdapter {

    List<Message> messages = new ArrayList<>();
    Context context;

    public MessageAdapter(Context context) {
        this.context = context;
    }


    public void add(Message message) {
        this.messages.add(message);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Message message = messages.get(i);

        if (message.isBelongsToCurrentUser()) {
            assert messageInflater != null;
            convertView = messageInflater.inflate(R.layout.my_message, null);
            holder.messageBody = convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);
            holder.messageBody.setText(message.getText());
        } else {
            assert messageInflater != null;
            convertView = messageInflater.inflate(R.layout.their_message, null);
            holder.avatar = convertView.findViewById(R.id.avatar);
            holder.name = convertView.findViewById(R.id.name);
            holder.messageBody = convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);

            holder.name.setText(message.getMemberData().getName() + " " + message.getMemberData().getSurname());
            holder.messageBody.setText(message.getText());
            GradientDrawable drawable = (GradientDrawable) holder.avatar.getBackground();
            drawable.setColor(Color.parseColor(message.getMemberData().getColor()));

            String name = message.getMemberData().getName();
            String surname = message.getMemberData().getSurname();


            name = name == null ? "?"
                    : name.substring(0, 1).toUpperCase(Locale.getDefault());
            surname = surname == null ? "?"
                    : surname.substring(0, 1).toUpperCase(Locale.getDefault());

            int size = dpToPx(35);
            TextDrawable drawable1 = TextDrawable.builder().beginConfig().width(size).height(size)
                    .useFont(Typeface.DEFAULT)
                    .fontSize(dpToPx(30)).endConfig()
                    .buildRound(name + surname, Color.parseColor(message.getMemberData().getColor()));
            holder.avatar.setImageDrawable(drawable1);
        }

        return convertView;
    }

    public static int dpToPx(int dp) {
        return Math.round(dp * getPixelScaleFactor());
    }

    private static float getPixelScaleFactor() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

}

class MessageViewHolder {
    public ImageView avatar;
    public TextView name;
    public TextView messageBody;
}