package fr.legrain.bdg.ui.flash;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.math.BigDecimal;

import androidx.fragment.app.DialogFragment;
import fr.legrain.bdg.R;
import fr.legrain.bdg.api.client.dto.LigneFlashDTO;

public class LigneFlashDialogFragment extends DialogFragment implements DialogInterface.OnShowListener {

    private EditText etDialogueLigneFlashQte;

    @Override
    public void onShow(DialogInterface dialog) {
        etDialogueLigneFlashQte.requestFocus();
        etDialogueLigneFlashQte.performClick();
        etDialogueLigneFlashQte.setSelection(0,etDialogueLigneFlashQte.getText().length());

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etDialogueLigneFlashQte, InputMethodManager.SHOW_IMPLICIT);

        etDialogueLigneFlashQte.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etDialogueLigneFlashQte, 0);
            }
        },200);
    }

    public interface LigneFlashDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, LigneFlashDTO ligne);
        public void onDialogNegativeClick(DialogFragment dialog, LigneFlashDTO ligne);
    }

    // Use this instance of the interface to deliver action events
    private LigneFlashDialogListener listener;


    private LigneFlashDTO ligne;

    public LigneFlashDialogFragment(LigneFlashDTO ligne) {
        super();
        this.ligne = ligne;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_ligne_flash, null);

        etDialogueLigneFlashQte = (EditText) v.findViewById(R.id.etDialogueLigneFlashQte);

        etDialogueLigneFlashQte.setText(ligne.getQteLDocument().toString());
        etDialogueLigneFlashQte.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(v)
               .setMessage("Lot : "+ligne.getNumLot())
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       ligne.setQteLDocument(new BigDecimal(etDialogueLigneFlashQte.getText().toString()));
                       listener.onDialogPositiveClick(LigneFlashDialogFragment.this, ligne);

                   }
               })
               .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       listener.onDialogNegativeClick(LigneFlashDialogFragment.this, ligne);

                   }
               });

        // Create the AlertDialog object and return it

        Dialog dialog = builder.create();
        dialog.setOnShowListener(this);

        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (LigneFlashDialogListener) /*context;*/ getFragmentManager().findFragmentById(R.id.activity_main_frame_layout);
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(LigneFlashDialogFragment.class.toString() + " must implement LigneFlashDialogListener");
        }
    }

}
