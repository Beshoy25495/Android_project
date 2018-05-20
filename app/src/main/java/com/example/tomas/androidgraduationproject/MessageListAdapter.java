package com.example.tomas.androidgraduationproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tomas on 5/9/2018.
 */

public class MessageListAdapter extends ArrayAdapter<Message> {

    private static final String TAG = "MessageListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView Name;
        TextView Cost;
        TextView Street;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public MessageListAdapter(Context context, int resource, ArrayList<Message> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the Message information
        String Name = getItem(position).getName();
        String Cost = getItem(position).getCost();
        String Street = getItem(position).getStreet();

        //Create the Message object with the information
        Message messages = new Message(Name,Cost,Street);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.Name = (TextView) convertView.findViewById(R.id.textView1);
            holder.Cost = (TextView) convertView.findViewById(R.id.textView2);
            holder.Street = (TextView) convertView.findViewById(R.id.textView3);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.Name.setText(messages.getName());
        holder.Cost.setText(messages.getCost());
        holder.Street.setText(messages.getStreet());


        return convertView;
    }



}
