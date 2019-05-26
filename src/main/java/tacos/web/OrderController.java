package tacos.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.model.Order;

@Controller
@RequestMapping("/orders")
public class OrderController {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OrderController.class);

	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		return "order-form";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors) {
		
		if (errors.hasErrors()) {
			return "order-form";
		}
		
		log.info("Order submitted: {}", order);
		return "redirect:/";
	}
}
