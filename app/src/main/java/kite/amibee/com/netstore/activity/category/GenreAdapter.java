package kite.amibee.com.netstore.activity.category;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;


import java.util.List;

import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.model.pojo.category.SubCategory;


public class GenreAdapter extends ExpandableRecyclerViewAdapter<GenreViewHolder, ArtistViewHolder> {
  private static final String TAG = "GenreAdapter";
  Context context;
  public GenreAdapter(List<? extends ExpandableGroup> groups,Context context) {
    super(groups);
    this.context=context;
  }

  @Override
  public GenreViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item_genre, parent, false);
    return new GenreViewHolder(view);
  }

  @Override
  public ArtistViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item_artist, parent, false);
    return new ArtistViewHolder(view);
  }

  @Override
  public void onBindChildViewHolder(ArtistViewHolder holder, int flatPosition,
                                    ExpandableGroup group, int childIndex) {

    final SubCategory artist = ((Genre) group).getItems().get(childIndex);
    holder.setArtistName(artist.getCategoryName());
    holder.childTextView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Log.e(TAG, "onClick: sub id "+view.getId() );
      }
    });
  }

  @Override
  public void onBindGroupViewHolder(final GenreViewHolder holder, int flatPosition,
                                    ExpandableGroup group) {

    holder.setGenreTitle(group);
    holder.genreName.setId(flatPosition);
    holder.genreName.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Log.e(TAG, "onClick: id "+view.getId() );
        ((ShopByCateActivity)context).sendCatId(view.getId(),holder.genreName.getText().toString());
      }
    });
  }
}
