package com.example.mydonationapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.example.mydonationapp.MainActivity.cases;

public class FetchCasesTask extends AsyncTask<String,Void, ArrayList<Case>> {

    private final String LOG_TAG = FetchCasesTask.class.getSimpleName();
    public static final String SERVICE_BASE_URL = "http://my-json-server.typicode.com/";
    private CasesAdapter casesAdapter;

    public FetchCasesTask(CasesAdapter casesAdapter){
        this.casesAdapter = casesAdapter;

    }
    @Override
    protected ArrayList<Case> doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String caseJsonString = null;

        try {

            final String CASES_URL  = "DimitrisBougiouklis/MyDonationApp/db";

            Uri builtUri = Uri.parse(SERVICE_BASE_URL + CASES_URL);


            URL url = new URL(builtUri.toString());


            // Create the request to Yummy Wallet server, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                Log.e(LOG_TAG, "Error ");
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {

                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {

                return null;
            }
            caseJsonString = buffer.toString();

            Log.d("TEO " ,caseJsonString);

            return  getCasesFromJSON(caseJsonString);

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the data, there's no point in attempting to parse it.
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Case> cases) {
        if(cases != null && cases.size() > 0){
            this.casesAdapter.clear();
            for(Case aCase:cases){
                casesAdapter.add(aCase);
            }
        }
    }



    private ArrayList<Case> getCasesFromJSON(String responseJsonStr) throws JSONException {

        try{
            cases = new ArrayList<>();
            JSONObject response = new JSONObject(responseJsonStr);

            JSONArray casesJson = response.getJSONArray("cases");
            for(int i = 0 ; i<casesJson.length();i++){
                JSONObject caseJson = casesJson.getJSONObject(i);
                int id = caseJson.getInt("id");
                String name = caseJson.getString("FullName");
                String briefDesc= caseJson.getString("BriefDescription");
                String fullDesc = caseJson.getString("Description");
                String expDate = caseJson.getString("ExpirationDate");
                String amount = caseJson.getString("Amount");
                String iban = caseJson.getString("Iban");
                Log.d("TEO"," " + id + name + amount + iban);
                Case aCase = new Case(id,name,briefDesc ,fullDesc ,expDate, amount,iban);

                cases.add(aCase);


            }


            Log.d(LOG_TAG, "Case Fetching Complete. " + cases.size() + "cases inserted");

            return  cases;

        }catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
            return  cases;
        }
    }
}
