package com.jakeanderton.dotagrab;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Jake on 24/08/2015.
 */
public class HeroDownloader extends AsyncTask<Void, Void, Void>
{
    public HeroDownloader(MainActivity m)
    {
        mainActivity = m;
    }
    public MainActivity mainActivity;
    public static JSONArray heroesObject;
    @Override
    @SuppressWarnings("deprecation")
    protected Void doInBackground(Void... params)
    {
        //String downloadUrl = "https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v0001/?key=" + DotaApiKey.STEAM_API_KEY + "&language=en_us";
        String downloadUrl = "https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v0001/?key="+DotaApiKey.STEAM_API_KEY+"&language=en_us";
        HttpClient httpClient = new DefaultHttpClient();

        HttpGet httpPost = new HttpGet(downloadUrl);

        httpPost.setHeader("Content-type", "application/json");
        InputStream inputStream = null;

        try
        {

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            inputStream = entity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null)
            {

                //System.out.println(line);
                sb.append(line + "\n");

            }

            String jsonDataString = sb.toString();
            //Log.i("jsonDataString: \n", jsonDataString);

            JSONObject jObject = new JSONObject(jsonDataString);
            JSONObject resultsObject = jObject.getJSONObject("result");
            heroesObject = resultsObject.getJSONArray("heroes");
            Log.i("length?",Integer.toString(heroesObject.length()));
            Log.i("success?",heroesObject.getString(0));

            mainActivity.populateImages();


            //Log.i("Heroes Array entries: ",(Integer.toString(heroesArray.length())));

            //JSONArray jArray = jObject.getJSONArray("heroes");
            //System.out.println(jArray.get(1));

            //outputHeroes(jArray);

        } catch (JSONException e)
        {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        } catch (ClientProtocolException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private void outputHeroes(JSONArray jArray)
    {
    }
}
