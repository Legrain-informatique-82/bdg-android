package fr.legrain.bdg.ui.flash;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.legrain.bdg.R;
import fr.legrain.bdg.api.client.dto.LigneFlashDTO;

/**
 * Created by nicolas on 18/09/17.
 */
public class LigneFlashAdapter extends ArrayAdapter<LigneFlashDTO> {

    public interface IModifLigneFlashListener {
        public void onSupprimeLigneClick(LigneFlashDTO dialog);
    }
    private IModifLigneFlashListener mListener;

    public void setmListener(IModifLigneFlashListener mListener) {
        this.mListener = mListener;
    }

    public LigneFlashAdapter(Context context, ArrayList<LigneFlashDTO> lignes) {
        super(context, 0, lignes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        final LigneFlashDTO ligneFlash = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_flash_liste_item_flash, parent, false);
        }

        // Lookup view for data population
        TextView tvCodeBarre = (TextView) convertView.findViewById(R.id.textView7);
        TextView tvLibelleLot = (TextView) convertView.findViewById(R.id.textView8);
        TextView tvCodeArticle = (TextView) convertView.findViewById(R.id.textView9);
        TextView tvQuantite = (TextView) convertView.findViewById(R.id.textView21);
        TextView tvNumLot = (TextView) convertView.findViewById(R.id.textView31);

        // Populate the data into the template view using the data object
        tvCodeBarre.setText(ligneFlash.getCodeBarre());
        tvCodeArticle.setText(ligneFlash.getCodeArticle());
        tvLibelleLot.setText(ligneFlash.getLibelleLot());
        tvQuantite.setText( String.valueOf(ligneFlash.getQteLDocument()));
        tvNumLot.setText(ligneFlash.getNumLot());

        Button deleteImageView = (Button) convertView.findViewById(R.id.btnSupprLigneFlash);
        deleteImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getContext(), ligneFlash.getNumLot().toString(), Toast.LENGTH_SHORT).show();


                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Supression");
                alert.setMessage("Etes vous sur de vouloir supprimer cette ligne ?");

                alert.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onSupprimeLigneClick(ligneFlash);
                        dialog.dismiss();
                    }
                });

                alert.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}
