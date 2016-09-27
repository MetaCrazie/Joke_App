package com.udacity.gradle.builditbigger;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.metacrazie.jokedisplaylib.DisplayJoke;
import com.udacity.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by praty on 12/09/2016.
 */
public class FetchJoke extends AsyncTask<Context, Void, String> {

    private static MyApi myApiService = null;
    private Context mContext;
    private ProgressBar mSpinner;

    public FetchJoke(Context context, ProgressBar spinner){
        mContext=context;
        mSpinner=spinner;
    }

    @Override
    protected String doInBackground(Context... contexts) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://jokeapp-143315.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }
        try {
            String joke=myApiService.fetchJoke().execute().getData();
            Log.d("AsyncTask", joke);
            return joke;
           /* return myApiService.sayHi(mName).execute().getData();*/
        } catch (IOException e) {
            Log.d("AsyncTask", e.getMessage());
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
       /* Log.d("onPostExecute", result);
        Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();*/
        Intent intent=new Intent(mContext, DisplayJoke.class);
        intent.putExtra("JOKE", result);
        mSpinner.setVisibility(View.GONE);
        mContext.startActivity(intent);
    }
}