package com.devazt.carrito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
    }

    private void onLaunchScanner(){
        try {
            IntentIntegrator intent = new IntentIntegrator(MainActivity.this);
            intent.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            intent.setPrompt("Escanea el codigo de barras");
            intent.initiateScan();
        }catch (Exception ex) {

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                // contents contains whatever the code was
                String contents = intent.getStringExtra("SCAN_RESULT");

                // Format contains the type of code i.e. UPC, EAN, QRCode etc...
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                // Handle successful scan. In this example add contents to ArrayList
                // Añadir a carrito de compra

                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_FORMATS", "PRODUCT_MODE,CODE_39,CODE_93,CODE_128,DATA_MATRIX,ITF");
                startActivityForResult(intent, 0); // Siguiente

            } else if (resultCode == RESULT_CANCELED) {
                // Aquí regresamos todo lo que compro
            }
        }
    }
}
