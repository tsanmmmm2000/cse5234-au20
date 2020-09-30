package edu.osu.cse5234.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.osu.cse5234.model.Item;
import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.model.PaymentInfo;
import edu.osu.cse5234.model.ShippingInfo;


@Controller
@RequestMapping("/purchase")
public class Purchase {
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewOrderEntryForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Display items for purchase
		
		List<Item> items = new ArrayList<>();
		items.add(buildItem("club", "300", "2"));
		items.add(buildItem("ball", "10", "12"));
		items.add(buildItem("bag", "150", "1"));
		items.add(buildItem("shoes", "40", "1"));
		items.add(buildItem("polo", "30", "2"));
		
		Order order = new Order();		
		order.setItems(items);
		
		request.setAttribute("order", order);
		
		return "OrderEntryForm";
	}

	@RequestMapping(path = "/submitItems", method = RequestMethod.POST)
	public String submitItems(@ModelAttribute("order") Order order, HttpServletRequest request) {
		// Submit selected Items for purchase
		request.getSession().setAttribute("order", order);
		return "redirect:/purchase/paymentEntry";
	}
	
	@RequestMapping(path = "/paymentEntry", method = RequestMethod.GET)
	public String viewPaymentEntryForm(HttpServletRequest request, HttpServletResponse response) {
		// Display payment entry form			
		request.setAttribute("payment", new PaymentInfo());	
		return "PaymentEntryForm";
	}
	
	
	@RequestMapping(path = "/submitPayment", method = RequestMethod.POST)
	public String submitPayment(@ModelAttribute("payment") PaymentInfo payment, HttpServletRequest request) {
		// Submit Payment info
		request.getSession().setAttribute("payment", payment);
		return "redirect:/purchase/shippingEntry";
	}
	
	@RequestMapping(path = "/shippingEntry", method = RequestMethod.GET)
	public String shippingEntry(HttpServletRequest request, HttpServletResponse response) {
		// Display shipping entry form
		request.setAttribute("shipping", new ShippingInfo());	
		return "ShippingEntryForm";
	}
	
	@RequestMapping(path = "/submitShipping", method = RequestMethod.POST)
	public String submitShipping(@ModelAttribute("shipping") ShippingInfo shipping, HttpServletRequest request) {
		// Submit shipping info
		request.getSession().setAttribute("shipping", shipping);
		return "redirect:/purchase/viewOrder";
	}	
	
	@RequestMapping(path = "/viewOrder", method = RequestMethod.GET)
	public String viewOrderForm(HttpServletRequest request, HttpServletResponse response) {
		// Display complete order
		return "ViewOrder";
	}
	
	@RequestMapping(path = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrderForm(HttpServletRequest request) {
		// Confirm order
		return "redirect:/purchase/viewConfirmation";
	}	
	
	@RequestMapping(path = "/viewConfirmation", method = RequestMethod.GET)
	public String viewConfirmationForm(HttpServletRequest request, HttpServletResponse response) {
		// Display confirmation 
		return "Confirmation";
	}		

	private Item buildItem(String name, String price, String quantity) {
		Item item = new Item();
		item.setName(name);
		item.setPrice(price);
		item.setQuantity(quantity);
		return item;
	}
}