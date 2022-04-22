package fr.legrain.bdg.lib.ecran;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import fr.legrain.bdg.R;


public class SmallCaptureActivity extends CaptureActivity {
    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.capture_small);
        return (DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
    }
}