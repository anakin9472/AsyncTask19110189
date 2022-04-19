package com.android.simpleasynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String>
{
    //define member variables mTextView and progressBar
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> progressBar;

    //Implement a constructor for AsyncTask that takes TextView, ProgressBar as parameters
    // and creates new weak references for that TextView, ProgressBar
    public SimpleAsyncTask(TextView textView, ProgressBar progressBar1)
    {
        mTextView = new WeakReference<TextView>(textView);
        progressBar = new WeakReference<ProgressBar>(progressBar1);
    }

    int s = 60;

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids)
    {
        // Sleep for the amount of time
        for(int i = 0; i < s; i++)
        {
            publishProgress((i*100)/s);
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        return "Awake at last after sleeping for " + s + " seconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        super.onProgressUpdate(values);
        progressBar.get().setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String result)
    {
        progressBar.get().setProgress(0);
        mTextView.get().setText(result);
    }
}

