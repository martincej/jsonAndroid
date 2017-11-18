package com.example.positivo.json;

import android.graphics.Color;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button crear,leer;
    TextView texto;
    ListView list;

  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        crear = (Button) findViewById(R.id.crear);
        leer = (Button) findViewById(R.id.leer);
        texto = (TextView) findViewById(R.id.texto);
        list = (ListView) findViewById(R.id.list);


        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createJSON();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    readJSON();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void createJSON()throws JSONException, IOException{
        JSONArray jsonArray = new JSONArray();
        JSONObject item;

        item = new JSONObject();
        item.put("nombre:","mouse");
        item.put("cantidad:",10);
        jsonArray.put(item);

        item = new JSONObject();
        item.put("nombre:","cpu");
        item.put("cantidad:",40);
        jsonArray.put(item);

        item = new JSONObject();
        item.put("nombre:","notebook");
        item.put("cantidad:",20);
        jsonArray.put(item);

        String text = jsonArray.toString();

        FileOutputStream fo = openFileOutput("items",MODE_PRIVATE);
        fo.write(text.getBytes());
        fo.close();
        texto.setText(jsonArray.toString());
    }


    public void readJSON() throws IOException, JSONException {
        FileInputStream fi= openFileInput("items");
        BufferedInputStream bi = new BufferedInputStream(fi);
        StringBuffer buffer = new StringBuffer();

        while (bi.available()!=0){
            char c= (char) bi.read();
            buffer.append(c);
        }
        bi.close();
        fi.close();

        JSONArray jsonArray = new JSONArray(buffer.toString());
        StringBuffer itemsBuffer= new StringBuffer();
        String alert;
        for (int i = 0; i<jsonArray.length();i++) {
            String item = jsonArray.getJSONObject(i).getString("nombre:");
            itemsBuffer.append(item + "\n");
            texto.setText(itemsBuffer.toString());

        }


    }
}
