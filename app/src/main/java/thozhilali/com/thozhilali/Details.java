package thozhilali.com.thozhilali;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class Details extends Fragment {
    TextView name,mail,age,address,streetName,pinCode,ph,skills;
    String username;


    public Details() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        name=(TextView)view.findViewById(R.id.textView28);
        mail=(TextView)view.findViewById(R.id.textView30);
        age=(TextView)view.findViewById(R.id.textView32);
        address=(TextView)view.findViewById(R.id.textView34);
        streetName=(TextView)view.findViewById(R.id.textView37);
        pinCode=(TextView)view.findViewById(R.id.textView38);
        ph=(TextView)view.findViewById(R.id.textView40);
        skills=(TextView)view.findViewById(R.id.textView42);
        return view;
    }
    class GetDataAsync extends AsyncTask<String, String, JSONObject> {
        private static final String LOGIN_URL = "Login.php";
        ProgressDialog pd;

        protected void onPreExecute() {
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading...");
            pd.setIndeterminate(false);
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            HashMap<String, String> params = new HashMap<>();
            params.put("email", username);
            Log.d("request", params.toString());
            return new JSONParser().makeHttpRequest(Constants.API_URL + LOGIN_URL, "POST", params);


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
//                        Intent ii = new Intent(Login.this, Workerview.class);
//                        finish();
//                        startActivity(ii);
                    } else {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "error server down", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    }


}
