package com.jakeanderton.dotagrab;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity
{

    GridLayout gridLayout;
    ArrayList<ImageView> imageViewArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        new ImageDownloader(imageView).execute("http://cdn.dota2.com/apps/dota2/images/heroes/drow_ranger_full.png");
        new HeroDownloader(this).execute();
       // populateImages();

    }

    public void populateImages()
    {
        imageViewArrayList = new ArrayList<ImageView>();
        int i;

        for (i = 0; i <= HeroDownloader.heroesObject.length(); i++)
        {

            try
            {
               // ImageView imTemp;
                JSONObject jsonObject = HeroDownloader.heroesObject.getJSONObject(i);
                String heroName = jsonObject.getString("name");
                heroName = heroName.substring(14);

                Log.i("hero name: ",heroName);








               // gridLayout.addView(imTemp, i);





            } catch (JSONException e)
            {
                e.printStackTrace();
            }



        }

        //HeroDownloader.heroesObject;

    }

}
