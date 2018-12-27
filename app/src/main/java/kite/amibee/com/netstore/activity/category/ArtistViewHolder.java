package kite.amibee.com.netstore.activity.category;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import kite.amibee.com.netstore.R;


public class ArtistViewHolder extends ChildViewHolder {

  public TextView childTextView;

  public ArtistViewHolder(View itemView) {
    super(itemView);
    childTextView = (TextView) itemView.findViewById(R.id.list_item_artist_name);
  }

  public void setArtistName(String name) {
    childTextView.setText(name);
  }
}
