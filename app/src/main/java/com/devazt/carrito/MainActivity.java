package com.devazt.carrito;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onLaunchScanner();

        /* Launch de camera */
        Button btnScanner = (Button) findViewById(R.id.btnScanner);
        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLaunchScanner();
            }
        });
    }

    private void onLaunchScanner(){
        try {
            IntentIntegrator intent = new IntentIntegrator(MainActivity.this);
            intent.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            intent.setPrompt("Escanea el código QR del carrito");
            intent.initiateScan();
        }catch (Exception ex) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(intent != null){
            IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
            if (scanningResult != null) {
                // codigo encontrado
                String scanContent = scanningResult.getContents();
                if(!scanContent.equals("")){
                    // iniciamos un activity
                    Intent goToActivity = new Intent(MainActivity.this,ShopActivity.class);
                    startActivity(goToActivity);
                }
            } else {
                System.out.println("No hay data");
            }
        }else{
            System.out.println("No hay data");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
