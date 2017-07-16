package enterprise.lgm.jabber.entities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import enterprise.lgm.jabber.ChatActivity;
import enterprise.lgm.jabber.R;

/**
 * Created by Geosch on 16.07.2017.
 */

public class MessageAdapter extends ArrayAdapter<Message> {

    Context context;
    int resource;
    ArrayList<Message> messages=null;
    ChatActivity chat;

    public MessageAdapter(Context context,int resource,ArrayList<Message> messages)
    {
        super(context,resource,messages);
        this.context=context;
        this.resource=resource;
        this.messages=messages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Message message = messages.get(position);
        if(convertView==null)
        {
            if(getItemViewType(position)==0)
            {
                convertView= LayoutInflater.from(context).inflate( R.layout.message_list,parent,false);
            }
            else  if(getItemViewType(position)==1)
            {
                convertView= LayoutInflater.from(context).inflate( R.layout.message_list2,parent,false);
            }
        }
        TextView textView = (TextView) convertView.findViewById(R.id.message);
        textView.setText(messages.get(position).text+"  " +messages.get(position).date);
        return convertView;
    }

    @Override
    public int getViewTypeCount()
    {
        return 2;
    }

    @Override
    public int getItemViewType(int position)
    {
        Message message=messages.get(position);
        if(message.sender)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }

}
