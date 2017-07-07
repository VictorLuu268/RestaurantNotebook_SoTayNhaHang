package com.victor.sotaynhahang;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.victor.adapter.RestaurantAdapter;
import com.victor.model.Restaurant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView restaurantLV;
    ArrayList<Restaurant> listRestaurant;
    RestaurantAdapter adapterRestaurant;

    public static String DATABASE_NAME="dbRestaurant.sqlite";
    String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        restaurantLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // send restaurant to MapActivity
                Restaurant restaurant = listRestaurant.get(position);
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                intent.putExtra("T_RESTAURANT",restaurant);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        restaurantLV = (ListView) findViewById(R.id.restaurant_ListView);
        listRestaurant = getListRestaurant();
        adapterRestaurant = new RestaurantAdapter(
                MainActivity.this,
                R.layout.rowlistview,
                listRestaurant);
        restaurantLV.setAdapter(adapterRestaurant);
    }

    public ArrayList<Restaurant> getListRestaurant(){
        listRestaurant = new ArrayList<>();
        // get database from assests
        processGetDatabaseFromAsset();
        // open database
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);

        Cursor cursor = database.query("Restaurant",null,null,null,null,null,null);
        // other way: Cursor cursor1 = database.rawQuery("select * from Restaurant",null);
        listRestaurant.clear();
        while (cursor.moveToNext()){
            Restaurant res = new Restaurant();
            res.setId(cursor.getInt(0));
            res.setName(cursor.getString(1));
            // get bitmap from bytes
            byte []bytes = cursor.getBlob(2);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            res.setPicture(bitmap);
            res.setLongitude(cursor.getDouble(3));
            res.setLatitude(cursor.getDouble(4));
            listRestaurant.add(res);
        }
        cursor.close();

        return listRestaurant;
    }

    //region processGetDatabaseFromAssetToSystem
    private void processGetDatabaseFromAsset() {
        File dbFile = getDatabasePath(DATABASE_NAME);

        if(!dbFile.exists()){
            try{
                copyDataFromAsset();
                Log.e("SUCCESS_DATABASE - ","Copy database from Asset successed !!!");
            }catch (Exception e){
                Log.e("ERROR_DATABASE - ",e.toString());
            }
        }
    }

    private void copyDataFromAsset() {
        try{
            // myInput: get database from assest, myOutput: copy database from myInput
            InputStream myInput = getAssets().open(DATABASE_NAME);
            // Path to the just created empty db
            String outFileName = getPathDatabase();

            // if the path doesn't exist first, create it
            File f = new File(getApplicationInfo().dataDir+DB_PATH_SUFFIX);
            if(!f.exists())
                f.mkdir();

            // open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            // transfer bytes from the inputfile to outputfile
            byte[] buffer = new byte[1024];
            int length;
            while((length = myInput.read(buffer)) > 0){
                myOutput.write(buffer,0,length);
            }
            // Close connect
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e){
            Log.e("ERROR_DATABASE - ",e.toString());
        }
    }

    private String getPathDatabase(){
        // path Application/databases/dbRestaurant.sqlite
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }
    //endregion
}
