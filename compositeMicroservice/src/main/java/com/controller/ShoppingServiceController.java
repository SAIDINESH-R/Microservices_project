package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.ref.CustomerCart;
import com.ref.CustomerOrder;
import com.ref.CustomerOrderres;
import com.ref.cart.Cart;
import com.ref.customer.Customer;
import com.ref.customer.LineItems;
import com.ref.inventory.Inventory;
import com.ref.order.Orders;
import com.ref.product.Product;
import com.ref.product.ProductInventory;
import com.ref.product.ProductRequest;
import com.ref.repository.CartCustomer;
import com.ref.service.CompositeService;
@RestController
//@RequestMapping("/")
@RefreshScope
public class ShoppingServiceController {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EurekaClient eurekaClient;

	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	
	@Autowired
	CompositeService cservice;
	@Autowired
	CartCustomer crepo;

	@GetMapping("/")
	public String getWelcome() {
		return "Welcome Greeting!";
	}

	@GetMapping("/check")
	public String message() {
		return "message";
	}

	// Hystrix
	@GetMapping("/greettt")
	public String greetString() {
		return "Hello use /greet/{username} to see response !!!!!!";
	}

	@GetMapping("/greettt/{username}")
	public String greetString(@PathVariable("username") String username) {
		return String.format("Hello %s!-- fallback method", username);
	}

	// 1st ->create product through comp. microservice -> update the inventory
	@PostMapping("/products")
	public ResponseEntity<?> productandinventory(@RequestBody ProductRequest p) {
		// JSONPObject product=new JSONPObject(null, p);
		// Map<String, String> r=new HashMap<String, String>();
		// r.put(null, null)
		System.out.println("product " + p);
		ResponseEntity<Product> s = restTemplate.postForEntity("http://product/create", p, Product.class);
		System.out.println(s.getBody().getPid());
		// JSONObject j=new JSONObject();
		// j.put("productId", s.getBody().getProductId());
		// j.put("quantity", p.getQuantity());
		Inventory j = new Inventory();
		j.setPid(s.getBody().getPid());
		j.setQuantity(p.getQuantity());
		ResponseEntity<Inventory> i = restTemplate.postForEntity("http://inventory/create", j, Inventory.class);
		ProductInventory result = new ProductInventory();
		result.setP(s.getBody());
		result.setI(i.getBody());
		System.out.println(i);
		return new ResponseEntity<ProductInventory>(result, HttpStatus.CREATED);

	}

	// 2nd part ->shopping service ->composite service -> create customer -> that
	// should create empty cart->map customer id with cart id


	@PostMapping("/customer")
	public ResponseEntity<?> customerandcart(@RequestBody Customer c) {
		Customer customer = restTemplate.postForObject("http://user/create", c, Customer.class);
		System.out.println("cusotmerre   " + customer);
		Cart ct = new Cart();
		Cart cart = restTemplate.postForObject("http://cart/addcart", ct, Cart.class);
		System.out.println("carttt   " + cart);
		CustomerCart cc = new CustomerCart();
		cc.setCustomerId(customer.getUid());
		cc.setCartId(cart.getCartId());
		cservice.create(cc);
		return new ResponseEntity<CustomerCart>(cc, HttpStatus.CREATED);

	}

	// 3rd ->same as 2nd ->but update option
	@PutMapping("/customer/customerId={customerId}/cart")
	public ResponseEntity<?> addcartitems(@PathVariable("customerId") int uid, @RequestBody Cart c) {
		CustomerCart cc = crepo.findBycustomerId(uid);
		if (cc == null)
			return new ResponseEntity<String>("no customer and cart generated", HttpStatus.NOT_FOUND);
		int cartId = cc.getCartId();
		restTemplate.put("http://cart/updatecart/" + cartId, c);
		Cart cart = restTemplate.getForObject("http://cart/getitems/" + cartId, Cart.class);
		return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
	}

//	// 4th -> same as 3rd ->once order placed, empty cart and update inventory
	@PostMapping("/customer/customerId={uid}/order")
	public ResponseEntity<?> placeorder(@PathVariable int uid) {
		CustomerCart cc = crepo.findBycustomerId(uid);
		if (cc == null)
			return new ResponseEntity<String>("no customer and cart generated", HttpStatus.NOT_FOUND);
		int cartId = cc.getCartId();
		Orders o = restTemplate.postForObject("http://order/addorder/cartId=" + cartId, "null", Orders.class);
		CustomerOrder co = new CustomerOrder();
		System.out.println(co.getCustomerId());
		System.out.println(co.getOrderId());
		co.setCustomerId(uid);
		co.setOrderId(o.getOrderId());
		System.out.println(co);
		cservice.createorder(co);
		Cart emptycart = new Cart();
		restTemplate.put("http://cart/updatecart/" + cartId, emptycart);
		for (LineItems i : o.getItems()) {
			int p = i.getProductId();
			int q = i.getQuantity();
			Inventory inv = restTemplate.getForObject("http://inventory/getinventorybyproduct/" + p, Inventory.class);
			Map<String, Integer> ibody = new HashMap<String, Integer>();
			ibody.put("productId", p);
			ibody.put("quantity", inv.getQuantity() - q);
			restTemplate.put("http://inventory/update/" + inv.getIid(), ibody);
		}
		return new ResponseEntity<Orders>(o, HttpStatus.CREATED);

	}

//	// 5th part ->getOrders
	@GetMapping("/customer/customerId={customerId}/order")
	public ResponseEntity<?> getorders(@PathVariable("customerId") int customerId) {
		List<Orders> olist = new ArrayList<Orders>();
		List<CustomerOrder> l = cservice.findorders(customerId);
		if (l == null)
			return new ResponseEntity<String>("no orders for this customer", HttpStatus.CREATED);
		Customer c = restTemplate.getForObject("http://user/getuser/" + customerId, Customer.class);
		System.out.print(l);
		for (CustomerOrder customerOrder : l) {
			int i = customerOrder.getOrderId();
			Orders o = restTemplate.getForObject("http://order/getorder/" + i, Orders.class);
			olist.add(o);
		}
		CustomerOrderres response = new CustomerOrderres();
		response.setCustomer(c);
		response.setOds(olist);
		return new ResponseEntity<CustomerOrderres>(response, HttpStatus.CREATED);

	}

}
