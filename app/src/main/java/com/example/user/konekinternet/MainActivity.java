package com.example.user.konekinternet;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Socket;

public class MainActivity extends AppCompatActivity {


    ConectInternetTask c1;
    static TextView myText;

    ConnectivityManager myConManager;
    NetworkInfo myInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText = (TextView)findViewById(R.id.myResult);
        final String[] pilihan={"http://","https://"};

        //cek koneksi
        //myConManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
       // myInfo=myConManager.getActiveNetworkInfo();
        final EditText edt=(EditText) findViewById(R.id.text_edit);
        Spinner spin=(Spinner) findViewById(R.id.spinner_txt);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_string, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position){
                    case 0:
                        //Toast.makeText(getApplicationContext(),"http selected",Toast.LENGTH_SHORT).show();
                        myText.setText("");
                        edt.setText(""+pilihan[position]);

                        break;

                    case 1:
                        //Toast.makeText(getApplicationContext(),"http selected",Toast.LENGTH_SHORT).show();
                        myText.setText("");
                        edt.setText(""+pilihan[position]);

                        break;

                    default:
                        edt.setText("");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private boolean isNetworkOn(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public void doSomething(View view) {
        c1 = new ConectInternetTask(this);
        //c1.execute("http://www.google.com");
        EditText editText = (EditText) findViewById(R.id.text_edit);

      if(isNetworkOn()){
            if(editText != null){
                String showString = editText.getText().toString();
                c1.execute(showString);
            }else{
                Toast.makeText(this,"Please Enter URL", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"Please Check Your Network", Toast.LENGTH_SHORT).show();
            myText.setText("");
        }

      /* if (myInfo!=null && myInfo.isConnected()) {
            if (editText != null) {
                String showString = editText.getText().toString();
                c1.execute(showString);

                //Toast.makeText(this,showString, Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(getApplicationContext(),"No Internet Access", Toast.LENGTH_SHORT).show();
        }*/
      //Close keyboard
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    }
