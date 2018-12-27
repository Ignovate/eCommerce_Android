package kite.amibee.com.netstore;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kite.amibee.com.netstore.adapter.LotionBrandFilterAdapter;

public class TabFragmentBrand extends Fragment {
    RecyclerView lotion_rv_brand;
    LotionBrandFilterAdapter lotionBrandFilterAdapter;
    List<String> brandList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_brand, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        brandList=new ArrayList<>();
        for (int i=0;i<10;i++){
            brandList.add("Test "+i);
        }
        lotion_rv_brand=(RecyclerView)view.findViewById(R.id.lotion_rv_brand);
        lotion_rv_brand.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        lotion_rv_brand.setItemAnimator(new DefaultItemAnimator());
        lotion_rv_brand.addItemDecoration(new SpacesItemDecoration(0,0, 0, 0));
        lotionBrandFilterAdapter = new LotionBrandFilterAdapter(getActivity(), brandList);
        lotion_rv_brand.setAdapter(lotionBrandFilterAdapter);
    }
}
