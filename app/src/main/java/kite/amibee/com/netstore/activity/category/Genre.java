package kite.amibee.com.netstore.activity.category;


import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import kite.amibee.com.netstore.model.pojo.category.SubCategory;

public class Genre extends ExpandableGroup<SubCategory> {

  private int iconResId;
  List<SubCategory> subList;

  public List<SubCategory> getSubList() {
    return subList;
  }

  public void setSubList(List<SubCategory> subList) {
    this.subList = subList;
  }

  public Genre(String title, List<SubCategory> items, int iconResId) {
    super(title, items);
    this.iconResId = iconResId;
    this.subList=items;
  }

  public int getIconResId() {
    return iconResId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Genre)) return false;

    Genre genre = (Genre) o;

    return getIconResId() == genre.getIconResId();

  }

  @Override
  public int hashCode() {
    return getIconResId();
  }
}

