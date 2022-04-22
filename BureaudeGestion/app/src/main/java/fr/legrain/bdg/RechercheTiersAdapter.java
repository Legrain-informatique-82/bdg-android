package fr.legrain.bdg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RechercheTiersAdapter extends ArrayAdapter<String> {

    Context context;
    int resource, textViewResourceId;
    List<String> items, tempItems, suggestions;

    public RechercheTiersAdapter(Context context, int resource, int textViewResourceId, List<String> items) {
        super(context, resource, textViewResourceId, items);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
        tempItems = new ArrayList<String>(items); // this makes the difference.
        suggestions = new ArrayList<String>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.ligne_recherche_tiers, parent, false);
        }
        String names = items.get(position);
        if (names != null) {
            TextView lblName = (TextView) view.findViewById(R.id.tvAutoComplTiers);
            if (lblName != null)
                lblName.setText(names);
        }
        return view;
    }

    @Override
    public Filter getFilter()
    {
        return nameFilter;
    }

    private final Object lock = new Object();

    Filter nameFilter = new Filter()
    {
        @Override
        public CharSequence convertResultToString(Object resultValue)
        {
            String str = (String) resultValue;
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            if (constraint != null)
            {
                synchronized (lock) {
                    suggestions.clear();
                    for (String names : tempItems) {
                        if (names.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            suggestions.add(names);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            }
            else
            {
                synchronized (lock) {
                    suggestions.addAll(items);
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();
                    return filterResults;
                }
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            List<String> filterList = (ArrayList<String>) results.values;
            if (results != null && results.count > 0)
            {
                clear();
                synchronized (lock) {
                    for (String item : filterList) {
                        add(item);
                        notifyDataSetChanged();
                    }
                }
            }
        }
    };
}