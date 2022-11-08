package com.cognizant.truyum.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.CartService;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);

	@GetMapping("/add-to-cart")
	public String addToCart(@RequestParam long menuItemId, ModelMap model) {
		LOGGER.info("Start - addToCart");
		cartService.addCartItem(1, menuItemId);
		model.addAttribute("addCartStatus", true);

		LOGGER.info("End - addToCart");
		return "forward:/show-menu-list-customer";
	}

	@GetMapping(value = "/show-cart")
	public String showCart(@RequestParam long userId, ModelMap model) {

		LOGGER.info("Start - showCart");
		if (userId != 0) {
			try {
				List<MenuItem> cartItems = cartService.getAllCartItems(userId);
				Collections.sort(cartItems);
				model.addAttribute("cartItems", cartItems);
				model.addAttribute("userId", userId);
				float total = 0f;
				for (MenuItem item : cartItems) {
					total += item.getPrice();
				}
				model.addAttribute("total", total);
				LOGGER.info("End - showCart");
				return "cart";
			} catch (CartEmptyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LOGGER.info("End - showCart");
				return "cart-empty";

			}
		} else {
			return "cart";
		}
	}

	@GetMapping(value = "cart")
	public String showCart(ModelMap model) {
		List<MenuItem> cartItems;
		try {
			cartItems = cartService.getAllCartItems(1);
			Collections.sort(cartItems);
			model.addAttribute("cartItems", cartItems);
			model.addAttribute("userId", 1);
			return "cart";
		} catch (CartEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "cart";
	}

	@GetMapping(value = "/remove-cart")
	public String removeCart(@RequestParam long userId, @RequestParam long menuItemId, ModelMap model) {

		cartService.removeCartItem(userId, menuItemId);

		List<MenuItem> cartItems;
		try {
			cartItems = cartService.getAllCartItems(userId);
			Collections.sort(cartItems);
			model.addAttribute("userId", userId);
			model.addAttribute("cartItems", cartItems);

			float total = 0f;
			for (MenuItem item : cartItems) {
				total += item.getPrice();
			}
			model.addAttribute("total", total);
		} catch (CartEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("End - removeCart");
		return "forward:/cart";
	}

}
