package com.shopping.order.order.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
    private int id;
    private List<JSONObject> cart_list;
    private JSONObject cart = new JSONObject();
    private List<Integer> cart_ids;

    public Purchase(int id) {
        this.id = id;
        cart_list = new ArrayList<JSONObject>();
        cart_ids = new ArrayList<Integer>();
        cart_ids.add(1);
        cart_ids.add(1);
        cart_ids.add(1);
        cart_ids.add(1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //---

    public List<JSONObject> getCarts() {
        return cart_list;
    }

    public void putInToCarts(Integer cart_id, Integer total_price, JSONObject product_list){
        JSONObject new_cart = new JSONObject();
        new_cart.put("cart_id", cart_ids.get(cart_id-1));
        cart_ids.set(cart_id-1,cart_ids.get(cart_id-1)+1);
        new_cart.put("total_price", total_price);
        new_cart.put("product_list", product_list);
        cart_list.add(new_cart);
    }

//    public void removeFromCarts(Integer carts){
//        this.cart.remove(carts.toString());
//    }

}
