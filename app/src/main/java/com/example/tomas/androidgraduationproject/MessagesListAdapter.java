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

public class MessagesListAdapter extends ArrayAdapter<Messages> {

    private static final String TAG = "MessagesListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView Number;
        TextView Name;
        TextView Cost;
        TextView Street;
        TextView CID;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public MessagesListAdapter(Context context, int resource, ArrayList<Messages> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the Messages information
        String Number = getItem(position).getNumber();
        String Name = getItem(position).getName();
        String Cost = getItem(position).getCost();
        String Street = getItem(position).getStreet();
        String CID = getItem(position).getCID();

        //Create the Messages object with the information
        Messages messages = new Messages(Number,Name,Cost,Street,CID);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();

            holder.Number = (TextView) convertView.findViewById(R.id.textView1);
            holder.Name = (TextView) convertView.findViewById(R.id.textView2);
            holder.Cost = (TextView) convertView.findViewById(R.id.textView3);
            holder.Street = (TextView) convertView.findViewById(R.id.textView4);
            holder.CID = (TextView) convertView.findViewById(R.id.textView5);

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

        holder.Number.setText(messages.getNumber());
        holder.Name.setText(messages.getName());
        holder.Cost.setText(messages.getCost());
        holder.Street.setText(messages.getStreet());
        holder.CID.setText(messages.getCID());


        return convertView;
    }



}
