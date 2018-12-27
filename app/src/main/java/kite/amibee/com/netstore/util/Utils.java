package kite.amibee.com.netstore.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import kite.amibee.com.netstore.R;

/**
 * Created by Shiburagi on 26/09/2016.
 */

public class Utils {
    Dialog dialog;
    public Utils(){

    }
    public static void showKeyBoard(View view) {
        if (view != null) {
            if (view instanceof EditText) {
                EditText editText = (EditText) view;
                editText.setSelection(editText.length());
            }
            InputMethodManager imm = (InputMethodManager)
                    view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void hideKeyBoard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    public void showMessage(final String paramString, final Activity context) {
        ((AppCompatActivity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, paramString, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void progressSet( Activity context){
        dialog = new Dialog(context, 0);
        View views = LayoutInflater.from(context).inflate(R.layout.progress_loading, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(views);


    }
    public void progressShow(){
        dialog.setCancelable(false);
        dialog.show();
    }
    public void progressHide(){
        dialog.setCancelable(true);
        dialog.cancel();
    }
    public String emailPattern(){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return emailPattern;
    }
}
