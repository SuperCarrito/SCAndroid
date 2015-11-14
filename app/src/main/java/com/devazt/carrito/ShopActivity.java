package com.devazt.carrito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.sql.SQLOutput;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        onLaunchScanner();
    }

    private void onLaunchScanner(){
        try {
            IntentIntegrator intent = new IntentIntegrator(ShopActivity.this);
            intent.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
            intent.setPrompt("Escanea el codigo de barras");
            intent.initiateScan();
        }catch (Exception ex) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(intent != null) {
            IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
            if (scanningResult != null) {
                // codigo encontrado
                String scanContent = scanningResult.getContents();
                if (!scanContent.equals("")) {
                    // imprimimos el codigo de barras
                    System.out.println(scanContent);
                    // lanzamos una peticion al servicio web
                    onLaunchScanner();
                }else{
                    System.out.println("El codigo de barras esta vacio");
                }
            } else {
                System.out.println("No hay scanningResult");
            }
        }else{
            System.out.println("No hay data");
        }
    }
}
