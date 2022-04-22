package fr.legrain.bdg.ui.bonliv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fr.legrain.bdg.R;
import fr.legrain.bdg.api.client.dto.LigneBonlivDTO;

/**
 * Created by nicolas on 18/09/17.
 */
public class LigneBLAdapter extends ArrayAdapter<LigneBonlivDTO> {

    public LigneBLAdapter(Context context, ArrayList<LigneBonlivDTO> lignes) {
        super(context, 0, lignes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        LigneBonlivDTO ligneBL = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_bl_liste_item, parent, false);
        }

        // Lookup view for data population
        TextView tvCodeBarre = (TextView) convertView.findViewById(R.id.textView7);
        TextView tvLibelleLot = (TextView) convertView.findViewById(R.id.textView8);
        TextView tvCodeArticle = (TextView) convertView.findViewById(R.id.textView9);
        TextView tvQunatite = (TextView) convertView.findViewById(R.id.textView21);
        TextView tvNumLot = (TextView) convertView.findViewById(R.id.textView31);

        // Populate the data into the template view using the data object
        tvCodeBarre.setText(ligneBL.getCodeBarre());
        tvCodeArticle.setText(ligneBL.getCodeArticle());
        tvLibelleLot.setText(ligneBL.getLibelleLot());
        tvQunatite.setText( String.valueOf(ligneBL.getQteLDocument()));
        tvNumLot.setText(ligneBL.getNumLot());

        // Return the completed view to render on screen
        return convertView;
    }
}
