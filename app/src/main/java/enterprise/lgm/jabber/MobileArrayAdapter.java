package enterprise.lgm.jabber;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

public class MobileArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MobileArrayAdapter(Context context, String[] values) {
        super(context, R.layout.chat_list, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.chat_list, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        textView.setText(values[position]);

        // Change icon based on name
        String s = values[position];

        System.out.println(s);

        String initial = Character.toString(s.charAt(0)).toUpperCase();
        int color = ColorGenerator.MATERIAL.getColor(initial);
        TextDrawable iconInitial = TextDrawable.builder().buildRound(initial, color);
        imageView.setImageDrawable(iconInitial);

      /*  if (s.equals("WindowsMobile")) {
            imageView.setImageResource(R.drawable.icon);
        } else if (s.equals("iOS")) {
            imageView.setImageResource(R.drawable.icon);
        } else if (s.equals("Blackberry")) {
            imageView.setImageResource(R.drawable.icon);
        } else {
            imageView.setImageResource(R.drawable.icon);
        }
*/
        return rowView;
    }
}