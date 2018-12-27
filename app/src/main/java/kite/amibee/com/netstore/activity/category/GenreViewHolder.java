package kite.amibee.com.netstore.activity.category;

import android.util.Log;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;


import kite.amibee.com.netstore.R;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class GenreViewHolder extends GroupViewHolder {
  private static final String TAG = "GenreViewHolder";
  public TextView genreName;
  public ImageView arrow;
  public ImageView icon;

  public GenreViewHolder(View itemView) {
    super(itemView);
    genreName = (TextView) itemView.findViewById(R.id.list_item_genre_name);
    arrow = (ImageView) itemView.findViewById(R.id.list_item_genre_arrow);
    icon = (ImageView) itemView.findViewById(R.id.list_item_genre_icon);
  }

  public void setGenreTitle(ExpandableGroup genre) {
    if (genre instanceof Genre) {
      genreName.setText(genre.getTitle());
      int subList=((Genre) genre).getSubList().size();
      Log.e(TAG, "setGenreTitle: subList size "+subList );
      if (subList == 0){
        arrow.setVisibility(View.INVISIBLE);
      }
      icon.setBackgroundResource(((Genre) genre).getIconResId());
    }

  }

  @Override
  public void expand() {
    Log.e(TAG, "view expand: " );
    animateExpand();
  }

  @Override
  public void collapse() {
    Log.e(TAG, "view collapse: " );
    animateCollapse();
  }

  private void animateExpand() {
    arrow.setImageResource(R.drawable.ic_expand_less_black_24dp);
  }

  private void animateCollapse() {
    arrow.setImageResource(R.drawable.ic_expand_more_black_24dp);
  }
}
