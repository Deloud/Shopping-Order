package com.shopping.order.order.order;

import org.json.simple.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class OrderController {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    List<Purchase> purchaseList;

    @PostConstruct
    public void init() {
        purchaseList = new ArrayList<>();
        purchaseList.add(new Purchase(1));
        purchaseList.add(new Purchase(2));
        purchaseList.add(new Purchase(3));
        purchaseList.add(new Purchase(4));
    }

    @GetMapping("")
    public List<Purchase> retrieveAllData(){
        return purchaseList;
    }

    @GetMapping("/{id}")
    public Purchase retrieveData(@PathVariable int id) {
        return purchaseList.get(id-1);
    }

    @GetMapping("/purchase/{user_id}")
    public String getCart(@PathVariable int user_id){
        Cart cart = restTemplate.getForObject("http://localhost:8089/".concat(Integer.toString(user_id)), Cart.class);
//        System.out.println(cart.getId());
//        System.out.println(cart.getTotalprice());
//        System.out.println(cart.getProduct_list());
        purchaseList.get(user_id-1).putInToCarts(cart.getId(), cart.getTotalprice(), cart.getProduct_list());
        restTemplate.delete("http://localhost:8089/".concat(Integer.toString(user_id)+"/all"), Cart.class);
        return "Post Successful!!!";
    }

}
