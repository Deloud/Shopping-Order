package com.shopping.order.order.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Param {
//    private int user_id;
//    private int product_id;
//    private int count;
    private JSONObject carts;
}
