package com.example.food_hub_application.controller;

import com.example.food_hub_application.grobal_data.GrobalData;
import com.example.food_hub_application.model.Product;
import com.example.food_hub_application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        GrobalData.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }
    @GetMapping("/cart")
    public String getCart(Model model){
        model.addAttribute("cartCount", GrobalData.cart.size());
        model.addAttribute("total", GrobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GrobalData.cart);
        return "cart";
    }
    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index){
        GrobalData.cart.remove(index);
        return "redirect:/cart";
    }
    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("total", GrobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }

}
