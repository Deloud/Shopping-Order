package com.shopping.shoppingmallorder.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class OrderJpaController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @GetMapping("")
    public List<Cart> retrieveAllCarts(){
        return cartRepository.findAll();
    }

    @GetMapping("/all")
    public List<Purchase> retrieveAllCart(){
        return purchaseRepository.findAll();
    }

    //post

    // 카트를 주문 목록으로 옮기기!. 주믄으로 추가됨
    @GetMapping("purchase/{id}")
    public String getCartByid(@PathVariable int id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);

        if (!purchase.isPresent()) {
            throw new PurchaseNotFoundException(String.format("ID[%s} not found", id));
        }

        List<Cart> oldcart = purchase.get().getCarts();
        Cart selected = null;
        for(int j=0; j<oldcart.size(); j++){
            if (oldcart.get(j).getId() == id){
                selected = oldcart.get(j);
            }
        }

        //---
        Cart cart = restTemplate.getForObject("http://localhost:8091/".concat(Integer.toString(id)), Cart.class);
        cart.setPurchase(purchase.get());
        cart.setId(id);
        //product추가부분 실패
        List<Product> test = cart.getProducts();
        for (int i =0; i<test.size(); i++){
            test.get(i).setCart(selected);
//            System.out.println(test.get(i).getCart()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
//        System.out.println(cart.getProducts()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (cart.getTotal_price() != 0){
            cartRepository.save(cart);
        }
        restTemplate.delete("http://localhost:8091/".concat(Integer.toString(id)+"/all"), Cart.class);
        return "Order Successfully";
    }

    // id에 따른 주문 내역 보여주기
    @GetMapping("/{id}")
    public Optional<Purchase> getOrderByid(@PathVariable int id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);

        if (!purchase.isPresent()) {
            throw new PurchaseNotFoundException(String.format("ID[%s} not found", id));
        }
        return purchase;
    }

}