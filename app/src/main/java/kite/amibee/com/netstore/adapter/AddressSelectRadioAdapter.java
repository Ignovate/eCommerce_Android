package kite.amibee.com.netstore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import kite.amibee.com.netstore.R;

public abstract class  AddressSelectRadioAdapter<T> extends RecyclerView.Adapter<AddressSelectRadioAdapter.ViewHolder> {
    public int mSelectedItem = -1;
    public List<T> mItems;
    private Context mContext;

    public AddressSelectRadioAdapter(Context context, List<T> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public void onBindViewHolder(AddressSelectRadioAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.rb_addre_default.setChecked(i == mSelectedItem);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        final View view = inflater.inflate(R.layout.adap_address_select, viewGroup, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public RadioButton rb_addre_default;
        public TextView mText;

        public ViewHolder(final View inflate) {
            super(inflate);
            mText = (TextView) inflate.findViewById(R.id.text);
            rb_addre_default = (RadioButton) inflate.findViewById(R.id.rb_addre_default);
            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    notifyDataSetChanged();
                }
            };
            itemView.setOnClickListener(clickListener);
            rb_addre_default.setOnClickListener(clickListener);
        }
    }
}
