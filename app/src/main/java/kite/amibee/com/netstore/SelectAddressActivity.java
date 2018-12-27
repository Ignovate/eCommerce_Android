package kite.amibee.com.netstore;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kite.amibee.com.netstore.adapter.AddressSelectAdapter;
import kite.amibee.com.netstore.model.AddressSelectModel;
import kite.amibee.com.netstore.model.pojo.AddressListModel;
import kite.amibee.com.netstore.model.pojo.AddressModel;
import kite.amibee.com.netstore.model.pojo.AddressSingleUserModel;
import kite.amibee.com.netstore.util.PreferencesHelper;
import kite.amibee.com.netstore.util.api.Api;

public class SelectAddressActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "SelectAddressActivity";
    /*toolbar*/
    ImageView toolbar_menu_iv,toolbar_logo_iv,toolbar_back_iv,toolbar_cart_iv;
    TextView toolbar_tv_title,tv_addr_pincode,tv_addr_addrss_default,tv_address_title;
    RelativeLayout toolbar_lay_cart;
    RadioButton rb_addre_new;
    TextInputEditText et_addnew_altername_ph,et_addnew_land,et_addnew_city,et_addnew_str,et_add_newaddre_ph,
            et_add_newaddre_name;
    EditText et_addnew_addre_addre;
    RelativeLayout lay_addre_savedelivery,lay_addre_cancel,lay_add_new_address;
    RecyclerView rv_address_select;
    AddressSelectAdapter addressSelectAdapter;
    List<AddressSingleUserModel> addressList = new ArrayList<>();
    int addressDefault=-1;

    //toolbar
    //ui
    String state;
    String country;
    TextView s_addr_tv_agree;
    private Spinner dropdown_state_spinner,dropdown_country_spinner;
    private static final String[] state_list = {"state 1", "state 2", "state 3"};
    private static final String[] country_list = {"country 1", "country 2", "country 3"};
    private View.OnClickListener onClickListener;
    Api api;
    PreferencesHelper preferencesHelper;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    int customerID;
    int addressId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }
    public void init(){
        preferencesHelper= new PreferencesHelper(this);
        api = new Api(this);
        customerID=preferencesHelper.getCusId();
        Log.w(TAG, "init: customerID "+customerID );
        api.addressSingleUser(customerID);

        onClick();
        rbListener();
        //toolbar
        toolbar_menu_iv=(ImageView)findViewById(R.id.toolbar_menu);
        toolbar_logo_iv=(ImageView)findViewById(R.id.toolbar_logo);
        toolbar_back_iv=(ImageView)findViewById(R.id.toolbar_back);
        toolbar_tv_title=(TextView)findViewById(R.id.toolbar_tv_title);
        toolbar_cart_iv=(ImageView)findViewById(R.id.toolbar_cart);
        tv_address_title=findViewById(R.id.tv_address_title);
        toolbar_cart_iv.setVisibility(View.GONE);
        toolbar_tv_title.setText("Select Address");
        toolbar_menu_iv.setVisibility(View.GONE);
        toolbar_logo_iv.setVisibility(View.GONE);
        toolbar_back_iv.setVisibility(View.VISIBLE);
        toolbar_tv_title.setVisibility(View.VISIBLE);
        toolbar_lay_cart=(RelativeLayout)findViewById(R.id.toolbar_lay_cart);
        toolbar_lay_cart.setVisibility(View.GONE);
        //toolbar
        s_addr_tv_agree=(TextView)findViewById(R.id.s_addr_tv_agree);
        String first = "I agree to ";
        String last=" Privacy and Policy ";
        String mid = "<font color='#4285F4'>PDC</font> ";
        s_addr_tv_agree.setText(Html.fromHtml(first + mid + last));


        dropdown_state_spinner = (Spinner)findViewById(R.id.dropdown_state_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectAddressActivity.this,
                android.R.layout.simple_spinner_item,state_list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_state_spinner.setAdapter(adapter);
        dropdown_state_spinner.setOnItemSelectedListener(this);

        dropdown_country_spinner = (Spinner)findViewById(R.id.dropdown_country_spinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(SelectAddressActivity.this,
                android.R.layout.simple_spinner_item,country_list);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_country_spinner.setAdapter(adapter1);
        dropdown_country_spinner.setOnItemSelectedListener(this);

        rv_address_select= findViewById(R.id.rv_address_select);
          rv_address_select.setLayoutManager(new LinearLayoutManager(this));
        addressSelectAdapter =new AddressSelectAdapter(addressList,this );
        rv_address_select.setAdapter(addressSelectAdapter);

        rb_addre_new=findViewById(R.id.rb_addre_new);

        et_addnew_altername_ph=findViewById(R.id.et_addnew_altername_ph);
        et_add_newaddre_ph=findViewById(R.id.et_add_newaddre_ph);
        et_addnew_addre_addre=findViewById(R.id.et_addnew_addre_addre);
        et_addnew_city=findViewById(R.id.et_addnew_city);
        et_addnew_land=findViewById(R.id.et_addnew_land);
        et_addnew_str=findViewById(R.id.et_addnew_str);
        et_add_newaddre_name=findViewById(R.id.et_add_newaddre_name);
        lay_addre_cancel=findViewById(R.id.lay_addre_cancel);
        lay_addre_savedelivery=findViewById(R.id.lay_addre_savedelivery);
        tv_addr_addrss_default=findViewById(R.id.tv_addr_addrss_default);
        tv_addr_pincode=findViewById(R.id.tv_addr_pincode);
        lay_add_new_address=findViewById(R.id.lay_add_new_address);
        lay_add_new_address.setVisibility(View.GONE);

        lay_addre_savedelivery.setOnClickListener(onClickListener);
        lay_addre_cancel.setOnClickListener(onClickListener);
        tv_address_title.setOnClickListener(onClickListener);
        toolbar_back_iv.setOnClickListener(onClickListener);
        rb_addre_new.setOnCheckedChangeListener(onCheckedChangeListener);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (dropdown_country_spinner.hashCode()==adapterView.hashCode()){
            country=adapterView.getSelectedItem().toString();
        }else if (dropdown_state_spinner.hashCode()==adapterView.hashCode()){
            state=adapterView.getSelectedItem().toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void rbListener(){
        onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                /*if (rb_addre_default.hashCode() == compoundButton.hashCode()){

                    if (rb_addre_default.isChecked()){
                        rb_addre_new.setChecked(false);
                        lay_add_new_address.setVisibility(View.GONE);
                    }
                }else if (rb_addre_new.hashCode() == compoundButton.hashCode()){

                    if (rb_addre_new.isChecked()){
                        rb_addre_default.setChecked(false);
                        lay_add_new_address.setVisibility(View.VISIBLE);
                    }

                }*/

                if (rb_addre_new.isChecked()){
                    addressSelectAdapter.addressSelectStatus(false);
                    lay_add_new_address.setVisibility(View.VISIBLE);
                }
            }
        };
    }
    private void onClick(){
         onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lay_addre_savedelivery.hashCode() == view.hashCode()){
                    if (rb_addre_new.isChecked()){
                        addressDefault=-1;
                    }

                    if (addressDefault >=0){

                        String addre=addressList.get(addressDefault).getFirstname();
                        String pincode=addressList.get(addressDefault).getPostcode();

                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("custId", customerID);
                        jsonObject.addProperty("firstname", addre);
                        jsonObject.addProperty("streetname", addre);
                        jsonObject.addProperty("countryId", 1);
                        jsonObject.addProperty("regionId", 1);
                        jsonObject.addProperty("areaId", 1);
                        jsonObject.addProperty("postcode", pincode);
                        Log.w(TAG, "onClick: default address "+jsonObject );
                        api.addressAdd(jsonObject);

                    }else if (rb_addre_new.isChecked()){

                        String name=et_add_newaddre_name.getText().toString();
                        String ph=et_add_newaddre_ph.getText().toString();
                        String address=et_addnew_addre_addre.getText().toString();
                        String street=et_addnew_str.getText().toString();
                        String city=et_addnew_city.getText().toString();
                        String landmark=et_addnew_land.getText().toString();
                        String alter_ph=et_addnew_altername_ph.getText().toString();

                        JsonObject jsonObject = new JsonObject();
                        if (tv_address_title.getText().equals("Edit Address")){
                            jsonObject.addProperty("addressId", addressId);
                        }


                        jsonObject.addProperty("customerId", customerID);
                        jsonObject.addProperty("firstname", name);
                        jsonObject.addProperty("streetname", street);
                        jsonObject.addProperty("countryId", 1);
                        jsonObject.addProperty("regionId", 1);
                        jsonObject.addProperty("areaId", 1);
                        jsonObject.addProperty("postcode", 1);
                        Log.w(TAG, "onClick: add addredd "+jsonObject );
                        api.addressAdd(jsonObject);
                    }else {
                        Toast.makeText(SelectAddressActivity.this,
                                "Please select any one address " ,
                                Toast.LENGTH_LONG).show();
                    }

                }else if (toolbar_back_iv.hashCode() ==view.hashCode()){
                    finish();
                }

            }
        };
    }

    public void apiResponseAddAdress(AddressModel addressModel){
        if (addressModel!=null){
            int addressId= addressModel.getId();
            String addressName=addressModel.getFirstname();
            String addStreetName=addressModel.getStreetname();
            Log.w(TAG, "apiResponseAddAdress: addressId "+addressId );
            Log.w(TAG, "apiResponseAddAdress: addressName "+addressName );
            Log.w(TAG, "apiResponseAddAdress: addStreetName "+addStreetName );
            preferencesHelper.setAddressId(addressId);
            finish();
        }


        /*Intent intent = new Intent(SelectAddressActivity.this,PaymentActivity.class);
        startActivity(intent);*/

    }

    public void apiResponseAdressList(List<AddressSingleUserModel> apiDataAddreList){
        if (apiDataAddreList!=null){
                addressList=apiDataAddreList;
                if (addressList.size() >0){
                    rv_address_select.setVisibility(View.VISIBLE);
                    addressSelectAdapter.updateAddresAdapter(addressList);
                }else {
                    Log.d(TAG, "apiResponseAdressList: address list "+addressList.size());
                    rv_address_select.setVisibility(View.GONE);
                }

        }else {
            rv_address_select.setVisibility(View.GONE);
        }

    }

    public void raddioStatusUpdate(int pos){
        addressDefault=pos;
        Log.w(TAG, "raddioStatusUpdate: pos "+pos );
        if (addressDefault >= 0){
            rb_addre_new.setChecked(false);
            tv_address_title.setText("Add New Address");
            et_addnew_str.setText("");
            et_addnew_addre_addre.setText("");
            et_add_newaddre_name.setText("");
        }
    }

    public void addressEdit(int pos){
        addressDefault=-1;
        rb_addre_new.setChecked(true);
        tv_address_title.setText("Edit Address");
        et_addnew_str.setText("");
        et_addnew_addre_addre.setText("");
        et_add_newaddre_name.setText("");
        addressId=addressList.get(pos).getAddressId();
        Log.w(TAG, "addressEdit: addressId "+addressId );
        String firstName=addressList.get(pos).getFirstname();
        String address=addressList.get(pos).getStreetname();
        String streetName=addressList.get(pos).getStreetname();
        String cityName=addressList.get(pos).getAreaId();
        et_addnew_str.setText(streetName);
        et_addnew_addre_addre.setText(address);
        et_add_newaddre_name.setText(firstName);
    }
}
