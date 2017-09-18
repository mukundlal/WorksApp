package thozhilali.com.thozhilali;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {
    Button sn;
    EditText nm, phn, eml, pss, cfmpss;
    CheckBox checkBox;
    String username, password, mobile, fullName, adrs, confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        sn = (Button) findViewById(R.id.sn);
        nm = (EditText) findViewById(R.id.nm);
        phn = (EditText) findViewById(R.id.phn);
        eml = (EditText) findViewById(R.id.eml);
        pss = (EditText) findViewById(R.id.pss);
        cfmpss = (EditText) findViewById(R.id.cnfpss);


        sn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fullName = nm.getText().toString();
                mobile = phn.getText().toString();
                username = eml.getText().toString();
                password = pss.getText().toString();
                confirm = cfmpss.getText().toString();

                if (fullName.equals("") || mobile.equals("") || username.equals("") ||  confirm.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "All fields are mandatory", Toast.LENGTH_LONG).show();

                } else if (!password.equals(confirm)) {
                    Toast.makeText(SignUp.this, "Password mismatch", Toast.LENGTH_SHORT).show();

                } else {
                    new SignUpAsync().execute();
                }
            }
        });
    }

    private class SignUpAsync extends AsyncTask<String, String, JSONObject> {
        ProgressDialog pd;
        private static final String SIGNUP = "SignUpuser.php";

        protected void onPreExecute() {
            pd = new ProgressDialog(SignUp.this);
            pd.setMessage("Wait...");
            pd.setIndeterminate(false);
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            HashMap<String, String> params = new HashMap<>();
            params.put("name", fullName);
            params.put("ph", mobile);
            params.put("email", username);
            params.put("psw", password);
            params.put("type","employer");

            return new JSONParser().makeHttpRequest(Constants.API_URL + SIGNUP, "POST", params);


        }


        protected void onPostExecute(JSONObject json) {
            int success;
            String message;
            pd.dismiss();
            try {
                if (json.length() > 0) {

                    success = json.getInt(Constants.TAG_SUCCESS);
                    message = json.getString(Constants.TAG_MESSAGE);
                    if (success == 1) {


                        Intent ii = new Intent(SignUp.this, Login.class);
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
