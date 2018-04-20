package com.qnext.mac.macadd;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static android.R.attr.id;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static com.qnext.mac.macadd.R.id.DoubleBounce;
import static com.qnext.mac.macadd.R.id.macadd;
import static com.qnext.mac.macadd.R.id.string;
import static java.lang.Boolean.FALSE;

public class MainActivity extends AppCompatActivity {
    Toolbar mutoolbar;
    Spinner mySpinner;



    Button button= (Button) findViewById(R.id.button);
    final TextView s= (TextView) findViewById(R.id.string);
    public EditText e= (EditText)findViewById(R.id.rno);
    public  TextView d= (TextView) findViewById(R.id.macadd);
    public String a= e.getText().toString();
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
        setContentView(   R.layout.activity_main);
        String macAddress=getMacAddr();
        //d.setText(macAddress);
        //System.out.print(a);
        mutoolbar=(Toolbar)findViewById(R.id.toolbar) ;
        mutoolbar.setTitle(getResources().getString(R.string.app_name));
        mySpinner=(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> myadapter= new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myadapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,mySpinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                String macAddress=getMacAddr();
                d.setText("MacAddress is: "+macAddress);
                boolean b = Pattern.matches("\\d{4}", a);
                if(b==FALSE)
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Enter 4 digits!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }





        }




        });
    }

    ProgressBar prg= (ProgressBar)findViewById(R.id.prgbar);
    public class fetch extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prg.setVisibility(View.VISIBLE);




        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            prg.setVisibility(View.INVISIBLE);
        }

        @Override
        public String doInBackground(String... strings) {
            try {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String macAddress = getMacAddr().toString();

                String url = "https://android-club-project.herokuapp.com/upload_details?reg_no=" + a + "&mac=" + macAddress;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        s.setText(response.substring(0, 10));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        s.setText("Unable to fetch response");
                    }
                });
                queue.add(stringRequest);
// Access the RequestQueue through your singleton class.
            } catch (Exception e) {
                System.out.print(e);
            }


            return "";
        }

        protected void onPostExecute(Long result) {
            prg.setVisibility(View.INVISIBLE);

        }
    }

    private String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    //res1.append(Integer.toHexString(b & 0xFF) + ":");
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";

    }
    }






