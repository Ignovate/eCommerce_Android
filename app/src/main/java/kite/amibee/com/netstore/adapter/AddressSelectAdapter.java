package kite.amibee.com.netstore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.SelectAddressActivity;
import kite.amibee.com.netstore.model.AddressSelectModel;
import kite.amibee.com.netstore.model.pojo.AddressListModel;
import kite.amibee.com.netstore.model.pojo.AddressModel;
import kite.amibee.com.netstore.model.pojo.AddressSingleUserModel;

public class AddressSelectAdapter extends
        RecyclerView.Adapter<AddressSelectAdapter.ViewHolder> {
    private static final String TAG = "AddressSelectAdapter";
    private List<AddressSingleUserModel> addresList;
    private Context context;

    private int lastSelectedPosition = -1;

    public AddressSelectAdapter(List<AddressSingleUserModel> addresList
            , Context ctx) {
        this.addresList = addresList;
        context = ctx;
    }

    @Override
    public AddressSelectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adap_address_select, parent, false);

        AddressSelectAdapter.ViewHolder viewHolder =
                new AddressSelectAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AddressSelectAdapter.ViewHolder holder,
                                 final int position) {
        Log.d(TAG, "onBindViewHolder: name "+addresList.get(position).getFirstname()+",\n"+" street "+addresList.get(position).getStreetname()+",\n"+" Postcode "+addresList.get(position).getPostcode());

        holder.tv_addr_addrss_default.setText(addresList.get(position).getFirstname()+",\n"+addresList.get(position).getStreetname()+",\n"+addresList.get(position).getPostcode());

        //since only one radio button is allowed to be selected,
        // this condition un-checks previous selections
        holder.rb_addre_default.setChecked(lastSelectedPosition == position);

        holder.tv_addr_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((SelectAddressActivity)context).addressEdit(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return addresList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_addr_addrss_default,tv_addr_edit;
        public TextView tv_addr_pincode;
        public RadioButton rb_addre_default;

        public ViewHolder(View view) {
            super(view);
            tv_addr_addrss_default = (TextView) view.findViewById(R.id.tv_addr_addrss_default);
            tv_addr_pincode = (TextView) view.findViewById(R.id.tv_addr_pincode);
            rb_addre_default = (RadioButton) view.findViewById(R.id.rb_addre_default);
            tv_addr_edit=view.findViewById(R.id.tv_addr_edit);
            rb_addre_default.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                    ((SelectAddressActivity)context).raddioStatusUpdate(lastSelectedPosition);


                }
            });
        }
    }

    public void updateAddresAdapter(List<AddressSingleUserModel> addresList){
        this.addresList=addresList;
        notifyDataSetChanged();
    }

    public void addressSelectStatus(boolean status){
        lastSelectedPosition=-1;
        notifyDataSetChanged();
    }


}
