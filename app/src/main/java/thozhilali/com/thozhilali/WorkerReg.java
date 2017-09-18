package thozhilali.com.thozhilali;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class WorkerReg extends AppCompatActivity {
    EditText name, phone, email, psw, cnf;
    Button register;
    String fullName, mobile, username, password, confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_reg);
        name = (EditText) findViewById(R.id.editText3);
        phone = (EditText) findViewById(R.id.editText4);
        email = (EditText) findViewById(R.id.editText5);
        register = (Button) findViewById(R.id.button7);
        psw = (EditText) findViewById(R.id.editText6);
        cnf = (EditText) findViewById(R.id.editText7);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullName = name.getText().toString();
                mobile = phone.getText().toString();
                username = email.getText().toString();
                password = psw.getText().toString();
                confirm = cnf.getText().toString();
                if (fullName.equals("") || mobile.equals("") || username.equals("") || password.equals("") || confirm.equals("")) {
                    Toast.makeText(getApplicationContext(), "All fields are mandatory", Toast.LENGTH_LONG).show();
                } else if (!password.equals(confirm)) {
                    Toast.makeText(WorkerReg.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                } else {
                    new RegAsync().execute();
                }


            }
        });


    }

    class RegAsync extends AsyncTask<String, String, JSONObject> {
        ProgressDialog pd;
        private static final String SIGNUP = "SignUp.php";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(WorkerReg.this);
            pd.setMessage("Loading..");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.show();


        }
        @Override
        protected JSONObject doInBackground(String... args) {
            HashMap<String,String>params=new HashMap<>();
            params.put("name", fullName);
            params.put("ph", mobile);
            params.put("email", username);
            params.put("psw", password);
            params.put("type","employer");
            return new JSONParser().makeHttpRequest(Constants.API_URL + SIGNUP, "POST", params);
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            int success;
            String message;
            pd.dismiss();
            try {
                if (json.length() > 0) {

                    success = json.getInt(Constants.TAG_SUCCESS);
                    message = json.getString(Constants.TAG_MESSAGE);
                    if (success == 1) {


                        Intent ii = new Intent(WorkerReg.this, Login.class);
                        finish();
                        startActivity(ii);
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
