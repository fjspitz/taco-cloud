package tacos.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tacos.data.OrderRepository;
import tacos.model.Order;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	private OrderRepository orderRepo;
	
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}

	@GetMapping("/current")
	public String orderForm(Model model) {
		return "order-form";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
		
		if (errors.hasErrors()) {
			return "order-form";
		}
		
		log.info("Order submitted: {}", order);
		
		orderRepo.save(order);
		
		sessionStatus.setComplete();
		
		return "redirect:/";
	}
}
