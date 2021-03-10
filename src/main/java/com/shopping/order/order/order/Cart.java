package com.shopping.order.order.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Integer id;

    private Integer totalprice;

    private JSONObject product_list;
}
