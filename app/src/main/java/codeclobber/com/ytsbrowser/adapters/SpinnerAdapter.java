package codeclobber.com.ytsbrowser.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import codeclobber.com.ytsbrowser.R;


public class SpinnerAdapter extends ArrayAdapter<String> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (State)
    private String[] values;

    public SpinnerAdapter(Context context, int textViewResourceId,
                          String[] values) {
        super(context, textViewResourceId, values);
        int mTextViewResourceId = textViewResourceId;
        this.context = context;
        this.values = values;
    }

    public int getCount() {
        return values.length;
    }

    public String getItem(int position) {
        return values[position];
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView textView = (TextView) super.getView(position, convertView, parent);
        textView.setTextColor(Color.WHITE);
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item_dropdown_layout, parent, false);
        TextView chooser = (TextView) convertView.findViewById(R.id.tv_spinnerValue);
        chooser.setText(values[position]);
        return chooser;
    }
}