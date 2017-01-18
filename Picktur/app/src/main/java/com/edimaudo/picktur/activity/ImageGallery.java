package com.edimaudo.picktur.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.edimaudo.picktur.R;
import com.edimaudo.picktur.adapter.GalleryAdapter;
import com.edimaudo.picktur.app.AppController;
import com.edimaudo.picktur.model.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ImageGallery extends AppCompatActivity {

  private String TAG = ImageGallery.class.getSimpleName();
  private static final String endpoint = "http://api.androidhive.info/json/glide.json";
  private ArrayList<Image> images;
  private ProgressDialog pDialog;
  private GalleryAdapter mAdapter;
  private RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_images);

    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    pDialog = new ProgressDialog(this);
    images = new ArrayList<>();
    mAdapter = new GalleryAdapter(getApplicationContext(), images);

    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(mAdapter);

    fetchImages();

    /*
    recyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new GalleryAdapter.ClickListener() {
      @Override
      public void onClick(View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("images", images);
        bundle.putInt("position", position);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
        newFragment.setArguments(bundle);
        newFragment.show(ft, "slideshow");
      }

      @Override
      public void onLongClick(View view, int position) {

      }
    }));
    */
  }

  private void fetchImages() {

    pDialog.setMessage("Downloading json...");
    pDialog.show();

    JsonArrayRequest req = new JsonArrayRequest(endpoint,
            new Response.Listener<JSONArray>() {
              @Override
              public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                pDialog.hide();

                images.clear();
                for (int i = 0; i < response.length(); i++) {
                  try {
                    JSONObject object = response.getJSONObject(i);
                    Image image = new Image();
                    image.setName(object.getString("name"));

                    JSONObject url = object.getJSONObject("url");
                    image.setSmall(url.getString("small"));
                    image.setMedium(url.getString("medium"));
                    image.setLarge(url.getString("large"));
                    image.setTimestamp(object.getString("timestamp"));

                    images.add(image);

                  } catch (JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                  }
                }

                mAdapter.notifyDataSetChanged();
              }
            }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Log.e(TAG, "Error: " + error.getMessage());
        pDialog.hide();
      }
    });

    // Adding request to request queue
    AppController.getInstance().addToRequestQueue(req);
  }
}