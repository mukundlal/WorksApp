package thozhilali.com.thozhilali;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.R.attr.type;

public class Login extends AppCompatActivity {
    EditText t1;
    EditText t2;
    Button b1;
    Button b2;
    String username, password;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b1 = (Button) findViewById(R.id.log);
        b2 = (Button) findViewById(R.id.sin);
        t1 = (EditText) findViewById(R.id.led1);
        t2 = (EditText) findViewById(R.id.led2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = t1.getText().toString();
                password = t2.getText().toString();
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "please enter valid username and password", Toast.LENGTH_LONG).show();

                } else {
                    new LoginAsync().execute();
                }
            }

        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });
    }

    class LoginAsync extends AsyncTask<String, String, JSONObject> {
        private static final String LOGIN_URL = "Login.php";

        protected void onPreExecute() {
            pd = new ProgressDialog(Login.this);
            pd.setMessage("Wait...");
            pd.setIndeterminate(false);
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            HashMap<String, String> params = new HashMap<>();
            params.put("email", username);
            params.put("psw", password);
            Log.d("request", params.toString());


            return new JSONParser().makeHttpRequest(Constants.API_URL + LOGIN_URL, "POST", params);


        }


        protected void onPostExecute(JSONObject json) {
            int success,type;
            String message;
            pd.dismiss();
            try {
                if (json.length() > 0) {

                    success = json.getInt(Constants.TAG_SUCCESS);
                    message = json.getString(Constants.TAG_MESSAGE);
                    type = json.getInt(Constants.ACC_TYPE);

                    if (success == 1) {
                        if (type==1) {
                            Intent ii = new Intent(Login.this, Home.class);
                            finish();
                            startActivity(ii);

                        }
                        else startActivity(new Intent(Login.this,WorkerProfile.class));
                    } else {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "error server down", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    }
}





