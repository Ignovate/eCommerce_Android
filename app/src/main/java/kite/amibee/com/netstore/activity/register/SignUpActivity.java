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
import android.widget.TextView;

import com.google.gson.JsonObject;

import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.activity.home.HomeActivity;
import kite.amibee.com.netstore.model.pojo.signin.SignInDetailsModel;
import kite.amibee.com.netstore.model.pojo.signin.SignInModel;
import kite.amibee.com.netstore.util.PreferencesHelper;
import kite.amibee.com.netstore.util.Utils;
import kite.amibee.com.netstore.util.api.Api;

import static kite.amibee.com.netstore.util.Pattern.PASSWORD_VALIDE;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";
    TextView tv_signup_signin;
    EditText et_signup_name, et_signup_email, et_signup_pass, et_signup_repass;
    TextInputLayout ti_signup_name, ti_signup_email, ti_signup_pass, ti_signup_repass;
    Button btn_signup_submit;
    View.OnClickListener onClickListener;
    View.OnFocusChangeListener onFocusChangeListener;
    TextWatcher textWatcher;
    Editable editable;
    String s_email, s_pass, s_repass, s_name;
    Utils utils;
    Api api;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
    }

    public void init() {
        onFocusChangeListener();
        textWatcher();
        onClickListener();
        api = new Api(this);
        preferencesHelper = new PreferencesHelper(this);
        utils = new Utils();
        ti_signup_name = findViewById(R.id.ti_signup_name);
        ti_signup_email = findViewById(R.id.ti_signup_email);
        ti_signup_pass = findViewById(R.id.ti_signup_pass);
        ti_signup_repass = findViewById(R.id.ti_signup_repass);
        tv_signup_signin = findViewById(R.id.tv_signup_signin);
        et_signup_name = findViewById(R.id.et_signup_name);
        et_signup_email = findViewById(R.id.et_signup_email);
        et_signup_pass = findViewById(R.id.et_signup_pass);
        et_signup_repass = findViewById(R.id.et_signup_repass);

        btn_signup_submit = findViewById(R.id.btn_signup_submit);

        et_signup_name.addTextChangedListener(textWatcher);
        et_signup_email.addTextChangedListener(textWatcher);
        et_signup_pass.addTextChangedListener(textWatcher);
        et_signup_repass.addTextChangedListener(textWatcher);

        et_signup_name.setOnFocusChangeListener(onFocusChangeListener);
        et_signup_email.setOnFocusChangeListener(onFocusChangeListener);
        et_signup_pass.setOnFocusChangeListener(onFocusChangeListener);
        et_signup_repass.setOnFocusChangeListener(onFocusChangeListener);

        btn_signup_submit.setOnClickListener(onClickListener);
        tv_signup_signin.setOnClickListener(onClickListener);
    }

    public void validationEnable(Editable s, String msg) {

        if (!TextUtils.isEmpty(s) && msg == null) {


            if (et_signup_name.getText().hashCode() == s.hashCode()) {
                ti_signup_name.setError(null);
            } else if (et_signup_email.getText().hashCode() == s.hashCode()) {
                ti_signup_email.setError(null);
            } else if (et_signup_pass.getText().hashCode() == s.hashCode()) {
                ti_signup_pass.setError(null);
            } else if (et_signup_repass.getText().hashCode() == s.hashCode()) {
                ti_signup_repass.setError(null);
            }

        } else if (TextUtils.isEmpty(s) && msg != null) {
            Log.e(TAG, "validationEnable: else if 1 ");
            if (et_signup_name.getText().hashCode() == s.hashCode()) {
                ti_signup_name.setError(msg);
            } else if (et_signup_email.getText().hashCode() == s.hashCode()) {
                ti_signup_email.setError(msg);
            } else if (et_signup_pass.getText().hashCode() == s.hashCode()) {
                ti_signup_pass.setError(msg);
            } else if (et_signup_repass.getText().hashCode() == s.hashCode()) {
                ti_signup_repass.setError(msg);
            }
        } else {
            Log.e(TAG, "validationEnable: else ");
            if (et_signup_email.getText().hashCode() == s.hashCode()) {
                ti_signup_email.setError(msg);
            } else if (et_signup_pass.getText().hashCode() == s.hashCode()) {
                ti_signup_pass.setError(msg);

                 //et_signup_repass.setError("");
            }else if (et_signup_repass.getText().hashCode() == s.hashCode()) {
                ti_signup_repass.setError(msg);

               // et_signup_repass.setError("");
            }
        }
    }

    public void onFocusChangeListener() {
        onFocusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    Log.e(TAG, "onFocusChange: ");
                    if (((EditText) view).getText().length() == 0) {
                        if (et_signup_name.getText().hashCode() == view.hashCode()) {
                            validationEnable(((EditText) view).getText(), getResources().getString(R.string.err_valid_name));
                        } else if (et_signup_email.getText().hashCode() == view.hashCode()) {
                            validationEnable(((EditText) view).getText(), getResources().getString(R.string.err_valid_email));
                        } else if (et_signup_pass.getText().hashCode() == view.hashCode()) {
                            validationEnable(((EditText) view).getText(), getResources().getString(R.string.err_valid_pass));
                        } else if (et_signup_repass.getText().hashCode() == view.hashCode()) {
                            validationEnable(((EditText) view).getText(), getResources().getString(R.string.err_valid_repass));
                        }

                    } else {
                        validationEnable(((EditText) view).getText(), null);
                    }

                } else {

                }
            }
        };
    }

    private void onClickListener() {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (btn_signup_submit.hashCode() == view.hashCode()) {
                    s_name = et_signup_name.getText().toString();
                    s_email = et_signup_email.getText().toString();
                    s_pass = et_signup_pass.getText().toString();
                    s_repass = et_signup_repass.getText().toString();


                    Log.d(TAG, "onClick: s_email " + s_email + " s_pass " + s_pass + " s_repass " + s_repass);


                    if (!s_name.isEmpty() ||
                            !s_email.isEmpty() ||
                            !s_pass.isEmpty() ||
                            !s_repass.isEmpty()) {
                        Log.e(TAG, "onClick: email status " + s_email.matches(utils.emailPattern()));

                        if (s_name.isEmpty()) {
                            ti_signup_name.setError(getResources().getString(R.string.err_valid_name));
                        }

                        if (s_email.isEmpty()) {
                            ti_signup_email.setError(getResources().getString(R.string.err_valid_email));
                        }

                        if (s_pass.isEmpty()) {
                            ti_signup_pass.setError(getResources().getString(R.string.err_valid_pass));
                        }
                        if (s_repass.isEmpty()) {
                            ti_signup_repass.setError(getResources().getString(R.string.err_valid_repass));
                        }

                        if (!s_name.isEmpty() &&
                                !s_email.isEmpty() &&
                                !s_pass.isEmpty() &&
                                !s_repass.isEmpty()) {

                            if (s_email.matches(utils.emailPattern())) {
                                validationEnable(et_signup_email.getText(), null);

                                if (s_pass.equals(s_repass)) {
                                    validationEnable(editable, null);

                                    signUp();

                                } else {
                                    Log.e(TAG, "onClick: MISMATCH");
                                    validationEnable(et_signup_repass.getText(), getResources().getString(R.string.err_valid_pass_pattern));

                                }


                            } else {
                                Log.e(TAG, "onClick: emaiL NOT Valid");
                                validationEnable(et_signup_email.getText(), getResources().getString(R.string.err_valid_email_pattern));

                            }
                        }

                    } else {
                        Log.d(TAG, "onClick: else cal ");
                        ti_signup_name.setError(getResources().getString(R.string.err_valid_name));
                        ti_signup_email.setError(getResources().getString(R.string.err_valid_email));
                        ti_signup_pass.setError(getResources().getString(R.string.err_valid_pass));
                        ti_signup_repass.setError(getResources().getString(R.string.err_valid_repass));

                    }
                }
                if (tv_signup_signin.hashCode() == view.hashCode()) {
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }
                String pass = et_signup_pass.getText().toString();
                if(TextUtils.isEmpty(pass) || pass.length() < 6)
                {
                    ti_signup_pass.setError("You must have 6 characters in your password");
                    return;
                }


               /* else if (tv_signup_signin.hashCode() == view.hashCode()) {
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }*/



            }

        };
    }
  /*  public void onClickListener(View v)
    {
        String pass = et_signup_pass.getText().toString();
        if(TextUtils.isEmpty(pass) || pass.length() < 6)
        {
            et_signup_pass.setError("You must have 6 characters in your password");
            return;
        }

        //continue processing

    }*/

    public void textWatcher() {
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable edit) {
                editable = edit;
                if (edit.length() == 0) {
                    if (et_signup_name.getText().hashCode() == edit.hashCode()) {
                        validationEnable(edit, getResources().getString(R.string.err_valid_name));
                    } else if (et_signup_email.getText().hashCode() == edit.hashCode()) {
                        validationEnable(edit, getResources().getString(R.string.err_valid_email));
                    } else if (et_signup_pass.getText().hashCode() == edit.hashCode()) {
                        validationEnable(edit, getResources().getString(R.string.err_valid_pass));
                    } else if (et_signup_repass.getText().hashCode() == edit.hashCode()) {
                        validationEnable(edit, getResources().getString(R.string.err_valid_repass));
                    }


                } else {
                    validationEnable(edit, null);
                }

            }
        };
    }

    public void signUp() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("firstName", "");
        jsonObject.addProperty("lastName", "");
        jsonObject.addProperty("gender", "");
        jsonObject.addProperty("email", s_email);
        jsonObject.addProperty("mobile", "");
        jsonObject.addProperty("username", s_name);
        jsonObject.addProperty("password", s_pass);
        jsonObject.addProperty("active", 1);
        jsonObject.addProperty("createdAt", "");
        jsonObject.addProperty("updatedAt", "");

        api.signUp(jsonObject);
    }

    public void apiResponse(SignInModel apiResponse) {
        String msg = apiResponse.getMessage();

        if (msg == null) {
            SignInDetailsModel signInDetailsModel = apiResponse.getCustomer();
            String userActive = signInDetailsModel.getActive();
            String cusId = signInDetailsModel.getCustomerId();
            String quoteId = signInDetailsModel.getQuoteId();
            String userName = signInDetailsModel.getUsername();
            String email = signInDetailsModel.getEmail();
            String mobile = signInDetailsModel.getMobile();
            int mobileNum = 0;
            if (mobile != null && !mobile.isEmpty()) {
                mobileNum = Integer.parseInt(signInDetailsModel.getMobile());
            }

            Log.e(TAG, "apiResponse: signup userActive " + userActive);
            Log.e(TAG, "apiResponse: signup cusId " + cusId);
            Log.e(TAG, "apiResponse: signup quoteId " + quoteId);
            Log.e(TAG, "apiResponse: signup userName " + userName);
            Log.e(TAG, "apiResponse: signup email " + email);
            Log.e(TAG, "apiResponse: signup mobile " + mobile);

            if (cusId == null) {
                cusId = "0";
            }/*else if (quoteId==null){
                quoteId="0";
            }*/
            preferencesHelper.putUserRegister(Boolean.parseBoolean(userActive));
            preferencesHelper.setCusId(Integer.parseInt(cusId));
//            preferencesHelper.setQuoteId(Integer.parseInt(quoteId));
            preferencesHelper.setUserName(userName);
            preferencesHelper.setEmail(email);
            preferencesHelper.setMobile(mobileNum);
            Log.e(TAG, "apiResponse: signin after cusId " + preferencesHelper.getCusId());


            /*CusID=preferencesHelper.getCusId();
            api.addressList(CusID);*/

            Intent in = new Intent(SignUpActivity.this, HomeActivity.class);
            startActivity(in);
            overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
            finish();

        } else {
            utils.showMessage(msg, SignUpActivity.this);
        }
    }

}


