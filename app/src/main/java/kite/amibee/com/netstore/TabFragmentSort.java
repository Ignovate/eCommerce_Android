package kite.amibee.com.netstore;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import kite.amibee.com.netstore.activity.product.ProductListActivity;

public class TabFragmentSort extends Fragment {
    private static final String TAG = "TabFragmentSort";
    View view_g;
    RadioGroup rg_filter;
    RadioButton rb_selected;
    RelativeLayout lay_filterApply,lay_filter_cancel;
    private View.OnClickListener onClickListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_sort, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view_g=view;
        onclickListener();
        rg_filter=view.findViewById(R.id.rg_filter);
        lay_filterApply=view.findViewById(R.id.lay_bot_2);
        lay_filter_cancel=view.findViewById(R.id.lay_bot_1);
        lay_filter_cancel.setOnClickListener(onClickListener);
        lay_filterApply.setOnClickListener(onClickListener);

    }
    public void onclickListener(){
        onClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lay_filterApply.hashCode()==view.hashCode()){
                    int selectedId = rg_filter.getCheckedRadioButtonId();
                    rb_selected=(RadioButton)view_g.findViewById(selectedId);
                    if (rb_selected.getText().equals(" Price - Low to High")){
                        ((ProductListActivity)getActivity()).sortFilter("lowtohigh");
                    }else if (rb_selected.getText().equals(" Price - High to Low")){
                        ((ProductListActivity)getActivity()).sortFilter("hightolow");
                    }
                    Log.w(TAG, "onClick: selectedId "+selectedId +" text "+rb_selected.getText());
                }else if (lay_filter_cancel.hashCode()==view.hashCode()){
                    ((ProductListActivity)getActivity()).onBackPressed();
                }
            }
        };
    }
}
