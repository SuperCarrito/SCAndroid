package com.devazt.carrito;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ShopActivity extends AppCompatActivity {

    ShopItemAdapter adapter;
    ArrayList<ShopItem> shopElements;
    ListView shopListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        shopListItems = (ListView) findViewById(R.id.shopListItems);
        shopElements = new ArrayList<ShopItem>();
        adapter = new ShopItemAdapter(ShopActivity.this, shopElements);
        shopListItems.setAdapter(adapter);
        //onLaunchScanner();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
                final String scanContent = scanningResult.getContents();
                if (!scanContent.equals("")) {
                    // imprimimos el codigo de barras
                    System.out.println(scanContent);
                    // lanzamos una peticion al servicio web
                    adapter = null;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://10.49.86.154:8080")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            IProduct iProduct = retrofit.create(IProduct.class);
                            iProduct.getProductos().enqueue(new Callback<List<Producto>>() {
                                @Override
                                public void onResponse(Response<List<Producto>> response, Retrofit retrofit) {
                                    List<Producto> productos = response.body();
                                    for (Producto product : productos){
                                        if(product.getBarCode().equals(scanContent)){
                                            shopElements.add(new ShopItem(product.getBarCode(), String.valueOf(product.getPrice())));
                                            adapter = new ShopItemAdapter(ShopActivity.this, shopElements);
                                            shopListItems.setAdapter(adapter);
                                            adapter.notifyDataSetChanged();
                                            double price = 0;
                                            for (ShopItem item : shopElements){
                                                String cost = item.getPrice();
                                                price += Double.parseDouble(cost);
                                            }
                                            TextView tv = (TextView) findViewById(R.id.tvCostoTotal);
                                            tv.setText(String.valueOf(price));
                                            break;
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Throwable t) {

                                }
                            });
                        }
                    }).start();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_add:
                onLaunchScanner();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
