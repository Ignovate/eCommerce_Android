package kite.amibee.com.netstore.activity.register;
import android.content.Intent;
        import android.support.design.widget.TextInputLayout;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextUtils;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.EditText;

        import com.google.gson.JsonObject;

import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.activity.home.HomeActivity;
        import kite.amibee.com.netstore.activity.register.SignInActivity;
        import kite.amibee.com.netstore.activity.register.SignUpActivity;
        import kite.amibee.com.netstore.model.pojo.signin.SignInDetailsModel;
        import kite.amibee.com.netstore.model.pojo.signin.SignInModel;
        import kite.amibee.com.netstore.util.PreferencesHelper;
        import kite.amibee.com.netstore.util.Utils;
        import kite.amibee.com.netstore.util.api.Api;

public class Forgot_password extends AppCompatActivity {
    private static final String TAG = "ForgotPassword";
    Button forgot_bt_submit;
    EditText et_forgot_email;
    TextInputLayout ti_forgot_email;
    private View.OnClickListener onClickListener;
    View.OnFocusChangeListener onFocusChangeListener;
    TextWatcher textWatcher;
    Editable editable;
    String s_email;
    Utils utils;
    Api api;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }
    public void init() {
        preferencesHelper = new PreferencesHelper(this);
        api = new Api(this, "");
        utils = new Utils();

        onFocusChangeListener();
        textWatcher();
        onClick();
        forgot_bt_submit = (Button) findViewById(R.id.forgot_bt_submit);
        et_forgot_email = findViewById(R.id.et_forgot_email);
        ti_forgot_email = findViewById(R.id.ti_forgot_email);

        forgot_bt_submit.setOnClickListener(onClickListener);
        et_forgot_email.addTextChangedListener(textWatcher);
        et_forgot_email.setOnFocusChangeListener(onFocusChangeListener);
    }
    public void validationEnable(Editable s, String msg){

        if (!TextUtils.isEmpty(s) && msg==null ) {


            if(et_forgot_email.getText().hashCode()==s.hashCode()){
                ti_forgot_email.setError(null);
            }
        }else if (TextUtils.isEmpty(s) && msg!=null){
            Log.e(TAG, "validationEnable: else if 1 " );
            if(et_forgot_email.getText().hashCode()==s.hashCode()){
                ti_forgot_email.setError(msg);
            }
        }
        else{
            Log.e(TAG, "validationEnable: else " );
            if(et_forgot_email.getText().hashCode()==s.hashCode()){
                ti_forgot_email.setError(msg);
            }
        }
    }
    public void onFocusChangeListener(){
        onFocusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    Log.e(TAG, "onFocusChange: " );
                    if (((EditText)view).getText().length()==0){
                        if(et_forgot_email.getText().hashCode()==view.hashCode()){
                            validationEnable(((EditText)view).getText(),getResources().getString(R.string.err_valid_email));
                        }
                    }else {
                        validationEnable(((EditText)view).getText(),null);
                    }

                }else {

                }
            }
        };
    }
    public void textWatcher(){
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable edit) {
                editable=edit;
                if (edit.length()==0){
                    if(et_forgot_email.getText().hashCode()==edit.hashCode()){
                        validationEnable(edit,getResources().getString(R.string.err_valid_email));
                    }
                }else {
                    validationEnable(edit,null);
                }

            }
        };
    }
    public void onClick() {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (forgot_bt_submit.hashCode() == view.hashCode()) {
                    s_email = et_forgot_email.getText().toString();

                    if (s_email.isEmpty()){
                        ti_forgot_email.setError(getResources().getString(R.string.err_valid_email));
                    }

                    if (!s_email.isEmpty() ) {
                        if (s_email.matches(utils.emailPattern())){
                        }else {
                            ti_forgot_email.setError(getResources().getString(R.string.err_valid_email_pattern));
                        }


                    }


                }
                if (forgot_bt_submit.hashCode() == view.hashCode()) {
                    Intent intent = new Intent(Forgot_password.this, Validate_otp.class);
                    startActivity(intent);
                    finish();
                }
              /*  Intent intent = new Intent(Forgot_password.this, Validate_otp.class);
                startActivity(intent);*/
            }
        };
    }

    public void signIn(String email, String pass) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", email);

        api.signIn(jsonObject);
    }

    public void apiResponse(SignInModel apiResponse) {
        String msg = apiResponse.getMessage();

        try{
            if (msg == null) {
                SignInDetailsModel signInDetailsModel = apiResponse.getCustomer();
                String userActive = signInDetailsModel.getActive();
                String cusId = signInDetailsModel.getCustomerId();
                String quoteId = signInDetailsModel.getQuoteId();
                String userName = signInDetailsModel.getUsername();
                String email = signInDetailsModel.getEmail();
                String mobileNum=signInDetailsModel.getMobile();
                int mobile=0;
                if (mobileNum!=null && !mobileNum.isEmpty()){
                    mobile = Integer.parseInt(signInDetailsModel.getMobile());
                }

                Log.e(TAG, "apiResponse: signin userActive "+userActive );
                Log.e(TAG, "apiResponse: signin cusId "+cusId );
                Log.e(TAG, "apiResponse: signin quoteId "+quoteId );
                Log.e(TAG, "apiResponse: signin userName "+userName );
                Log.e(TAG, "apiResponse: signin email "+email );
                Log.e(TAG, "apiResponse: signin mobile "+mobile );
                if (cusId==null){
                    cusId="0";
                }
                preferencesHelper.putUserRegister(Boolean.parseBoolean(userActive));
                preferencesHelper.setCusId(Integer.parseInt(cusId));

//            preferencesHelper.setQuoteId(Integer.parseInt(quoteId));
                preferencesHelper.setUserName(userName);
                preferencesHelper.setEmail(email);
                preferencesHelper.setMobile(mobile);
                Log.d(TAG, "apiResponse: signin after cusId "+preferencesHelper.getCusId() );
                //just temp ..after you should  delete
//                preferencesHelper.setAddressId(34);

            /*CusID=preferencesHelper.getCusId();
            api.addressList(CusID);*/

                Intent intent = new Intent(getApplicationContext(), Validate_otp.class);
                startActivity(intent);
                finish();
            } else {
                utils.showMessage(msg, Forgot_password.this);
            }

        }catch (Exception e){
            Log.e(TAG, "apiResponse: Exception "+e );
        }



    }
}

