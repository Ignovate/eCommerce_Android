package kite.amibee.com.netstore.activity.category;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kite.amibee.com.netstore.activity.addToCart.MyCartActivity;
import kite.amibee.com.netstore.activity.product.ProductListActivity;
import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.model.pojo.category.Categories;
import kite.amibee.com.netstore.model.pojo.category.SubCategory;
import kite.amibee.com.netstore.util.PreferencesHelper;
import kite.amibee.com.netstore.util.api.Api;


public class ShopByCateActivity extends AppCompatActivity {
    private static final String TAG = "ShopByCateActivity";
    /*toolbar*/
    ImageView toolbar_menu_iv,toolbar_logo_iv,toolbar_back_iv,toolbar_cart_iv;
    TextView toolbar_tv_title,toolbar_cart_no_tv;
    RelativeLayout toolbar_lay_cart;
    SearchView home_search_view;
    RecyclerView rv_category;
    Api apical;
    //toolbar
    public GenreAdapter adapter;
    private View.OnClickListener onClickListener;
    private SearchView.OnCloseListener onCloseListener;
    List<Categories> categoriesList;
    List<SubCategory> subCategoriesList = new ArrayList<>();
    PreferencesHelper preferencesHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_by_cate);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    public void init(){
        preferencesHelper= new PreferencesHelper(this);
        apical = new Api(this,"");
        apical.cateList();

        onClickListener();
        onCloseListener();
        //toolbar
        toolbar_menu_iv=(ImageView)findViewById(R.id.toolbar_menu);
        toolbar_logo_iv=(ImageView)findViewById(R.id.toolbar_logo);
        toolbar_back_iv=(ImageView)findViewById(R.id.toolbar_back);
        toolbar_tv_title=(TextView)findViewById(R.id.toolbar_tv_title);
        toolbar_lay_cart=(RelativeLayout)findViewById(R.id.toolbar_lay_cart);
        home_search_view=(SearchView)findViewById(R.id.home_search_view);
        toolbar_cart_iv=(ImageView)findViewById(R.id.toolbar_cart);
        rv_category = (RecyclerView) findViewById(R.id.shop_cate_rv);
        toolbar_cart_no_tv=findViewById(R.id.toolbar_cart_no_tv);

        toolbar_tv_title.setText("Shop By Category");

        toolbar_menu_iv.setVisibility(View.GONE);
        toolbar_logo_iv.setVisibility(View.GONE);
        toolbar_back_iv.setVisibility(View.VISIBLE);
        toolbar_tv_title.setVisibility(View.VISIBLE);
        toolbar_lay_cart.setVisibility(View.VISIBLE);
        home_search_view.setVisibility(View.VISIBLE);

        toolbar_back_iv.setOnClickListener(onClickListener);
        home_search_view.setOnCloseListener(onCloseListener);
        toolbar_cart_iv.setOnClickListener(onClickListener);
        home_search_view.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar_tv_title.setVisibility(View.GONE);
            }
        });
        home_search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e(TAG, "onQueryTextSubmit: query "+query );
                apical.cateSearchList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //toolbar




        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator animator = rv_category.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        rv_category.setLayoutManager(layoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        int cartCount=preferencesHelper.getCartCount();
        toolbar_cart_no_tv.setText(""+cartCount);
    }

    public void onClickListener(){
        onClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.hashCode() == toolbar_back_iv.hashCode()){
                    finish();
                }else if (view.hashCode() ==toolbar_cart_iv.hashCode()){
                    Intent intent = new Intent(ShopByCateActivity.this, MyCartActivity.class);
                    startActivity(intent);
                }
            }
        };
    }

    public void onCloseListener(){
        onCloseListener = new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                toolbar_tv_title.setVisibility(View.VISIBLE);
                return false;
            }
        };
    }

    public void apiResponse(List<Categories> categories){
        categoriesList = categories;
        List<Genre> catelist = new ArrayList<>();
        for (int i=0;i < categoriesList.size();i++){
            subCategoriesList =categoriesList.get(i).getSubCategories();
            if (subCategoriesList!=null){
                subCategoriesList = new ArrayList<>();
            }
            catelist.add(new Genre(categoriesList.get(i).getCategoryName(), subCategoriesList, R.drawable.fav_select));

        }
        adapter = new GenreAdapter(catelist,this);
        rv_category.setAdapter(adapter);

    }

    public void apiResponseSearch(List<Categories> categories){
        categoriesList = categories;

        subCategoriesList = new ArrayList<>();
        List<Genre> catelist = new ArrayList<>();
        for (int i=0;i < categoriesList.size();i++){
            catelist.add(new Genre(categoriesList.get(i).getName(), subCategoriesList, R.drawable.fav_select));

        }
        Log.e(TAG, "apiResponseSearch: cateSearchlist "+catelist.size() );
        adapter = new GenreAdapter(catelist,this);
        rv_category.setAdapter(adapter);

    }

    public void sendCatId(int id,String catName){
        String catId =categoriesList.get(id).getCategoryId();
        Log.e(TAG, "sendCatId: catId "+catId );
        Intent intent = new Intent(ShopByCateActivity.this, ProductListActivity.class);
        intent.putExtra("catId",catId);
        intent.putExtra("catName",catName);
        startActivity(intent);
    }
}
