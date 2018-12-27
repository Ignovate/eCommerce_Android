package kite.amibee.com.netstore.util.api;

import com.google.gson.JsonObject;

import java.util.List;

import kite.amibee.com.netstore.model.pojo.AddressListModel;
import kite.amibee.com.netstore.model.pojo.AddressModel;
import kite.amibee.com.netstore.model.pojo.AddressSingleUserModel;
import kite.amibee.com.netstore.model.pojo.PlaceOrderModel;
import kite.amibee.com.netstore.model.pojo.addtocart.AddToCartDetailModel;
import kite.amibee.com.netstore.model.pojo.category.Categories;
import kite.amibee.com.netstore.model.pojo.home.HomeAllCategories;
import kite.amibee.com.netstore.model.pojo.orders.OrdersModel;
import kite.amibee.com.netstore.model.pojo.productAttr.Attributes;
import kite.amibee.com.netstore.model.pojo.productAttr.ProductFullViewModel;
import kite.amibee.com.netstore.model.pojo.signin.SignInModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @Headers({"Content-Type: application/json"})
    @GET("gaia-ecom-service/api/v1.0/menu")
    Call<List<Categories>> category();

    @Headers({"Content-Type: application/json"})
    @GET("gaia-ecom-service/api/v1.0/categories/filter")
    Call<List<Categories>> categorySearchList(@Query("name") String name);

    @Headers({"Content-Type: application/json"})
    @GET("gaia-ecom-service/api/v1.0/banners")
    Call<List<HomeAllCategories>> homeCategory();

    @Headers({"Content-Type: application/json"})
    @GET("gaia-ecom-service/api/v1.0/relatedproducts")
    Call<List<Attributes>> productList(@Query("categoryId") int categoryId);

    @Headers({"Content-Type: application/json"})
    @GET("gaia-ecom-service/api/v1.0/product/filter")
    Call<List<Attributes>> productPriceFilter(@Query("categoryId") int categoryId,
                                              @Query("minPrice") long minPrice,
                                              @Query("maxPrice") long maxPrice);
/*
    @Headers({"Content-Type: application/json"})
    @GET("gaia-ecom-service/api/v1.0/product/filter?categoryId")
    Call<List<Attributes>> productPriceFilter(@Query("categoryId") int categoryId,
                                              @Query("minPrice") long minPrice,
                                              @Query("maxPrice") long maxPrice);*/


    @Headers({"Content-Type: application/json"})
    @GET("gaia-ecom-service/api/v1.0/product/single")
    Call<ProductFullViewModel> productAttrSingle(@Query("productId") String categoryId);

    @Headers({"Content-Type: application/json"})
    @POST("gaia-ecom-service/api/v1.0/cart/new")
    Call<AddToCartDetailModel> addToCart(@Body JsonObject locationPost);

    @Headers({"Content-Type: application/json"})
    @POST("gaia-ecom-service/api/v1.0/cart/update")
    Call<AddToCartDetailModel> cartUpdateOrReplace(@Body JsonObject locationPost);

    @Headers({"Content-Type: application/json"})
    @GET("gaia-ecom-service/api/v1.0/cart/read")
    Call<AddToCartDetailModel> cartAll(@Query("quoteId") int quoteId);

    @Headers({"Content-Type: application/json"})
    @POST("gaia-ecom-service/api/v1.0/cart/delete")
    Call<AddToCartDetailModel> cartDelete(@Body JsonObject cartDeleteJson);

    @Headers({"Content-Type: application/json"})
    @GET("gaia-ecom-service/api/v1.0/salesorder/add")
    Call<PlaceOrderModel> placeOrder(@Query("quoteId") int quoteId);

    @Headers({"Content-Type: application/json"})
    @GET("gaia-ecom-service/api/v1.0/salesorders/{id}")
    Call<List<OrdersModel>> orders(@Path("id") int cusId);

    @Headers({"Content-Type: application/json"})
    @GET("gaia-ecom-service/api/v1.0/salesorder/{id}")
    Call<OrdersModel> ordersDetails(@Path("id") int orderId);

    @Headers({"Content-Type: application/json"})
    @POST("gaia-ecom-service/api/v1.0/signin")
    Call<SignInModel> signIn(@Body JsonObject locationPost);

    @Headers({"Content-Type: application/json"})
    @POST("gaia-ecom-service/api/v1.0/signup")
    Call<SignInModel> signUp(@Body JsonObject locationPost);

    @Headers({"Content-Type: application/json"})
    @POST("gaia-ecom-service/api/v1.0/customersaddress")
    Call<AddressModel> addressAdd(@Body JsonObject locationPost);

    @Headers({"Content-Type: application/json"})
    @GET("gaia-ecom-service/api/v1.0/customersaddress")
    Call<AddressListModel> addressList(@Query("custId") int cusId);

    @Headers({"Content-Type: application/json"})
    @GET("gaia-ecom-service/api/v1.0/fetchcustomersaddress")
    Call<List<AddressSingleUserModel>> addressSingleUserList(@Query("custId") int cusId);


}
