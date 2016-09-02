package com.apppartner.androidprogrammertest.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apppartner.androidprogrammertest.R;
import com.apppartner.androidprogrammertest.models.ChatData;
import com.apppartner.androidprogrammertest.transformation.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created on 12/23/14.
 *
 * @author Thomas Colligan
 */
// 08/30 Add picasso library to parse image URLs

public class ChatsArrayAdapter extends ArrayAdapter<ChatData>
{
    public ChatsArrayAdapter(Context context, List<ChatData> objects)
    {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ChatCell chatCell = new ChatCell();

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.cell_chat, parent, false);

        chatCell.usernameTextView = (TextView) convertView.findViewById(R.id.usernameTextView);
        Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(),"fonts/Jelloween - Machinato.ttf");
        chatCell.usernameTextView.setTypeface(custom_font);
        chatCell.messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        Typeface custom_font2 = Typeface.createFromAsset(getContext().getAssets(),"fonts/Jelloween - Machinato Light.ttf");
        chatCell.messageTextView.setTypeface(custom_font2);
        chatCell.profilepicImageView=  (ImageView) convertView.findViewById(R.id.profilePic);
        ChatData chatData = getItem(position);

        chatCell.usernameTextView.setText(chatData.username);
        chatCell.messageTextView.setText(chatData.message);

        Picasso.with(getContext())
                .load(chatData.avatarURL).transform(new RoundedTransformation(50, 4))
                .into(chatCell.profilepicImageView);


        return convertView;
    }

    private static class ChatCell
    {
        TextView usernameTextView;
        TextView messageTextView;
        ImageView profilepicImageView;
    }
}
