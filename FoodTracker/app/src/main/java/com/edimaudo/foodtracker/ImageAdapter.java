package com.edimaudo.foodtracker;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by edima on 2017-03-18.
 */

public class ImageAdapter extends ArrayAdapter<Image> {
  private final int THUMBSIZE = 96;

  /**
   * applying ViewHolder pattern to speed up ListView, smoother and faster
   * item loading by caching view in A ViewHolder object
   */
  private static class ViewHolder {
    ImageView imgIcon;
    TextView foodName;
    RatingBar ratingBar;
  }

  public ImageAdapter(Context context, ArrayList<Image> images) {
    super(context, 0, images);
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    // view lookup cache stored in tag
    ViewHolder viewHolder;
    // Check if an existing view is being reused, otherwise inflate the item view
    if (convertView == null) {
      viewHolder = new ViewHolder();
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.food_item, parent, false);

      viewHolder.foodName = (TextView) convertView.findViewById(R.id.foodName);
      viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.imageInfo);
      viewHolder.ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);

      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }
    // Get the data item for this position
    Image image = getItem(position);
    // set description text
    viewHolder.foodName.setText(image.toString());
    // set image icon
    viewHolder.imgIcon.setImageBitmap(ThumbnailUtils
            .extractThumbnail(BitmapFactory.decodeFile(image.getImagePath()), THUMBSIZE, THUMBSIZE));
    //set rating Bar
    viewHolder.ratingBar.setNumStars(Integer.parseInt(image.getFoodRating()));
    // Return the completed view to render on screen
    return convertView;
  }
}
