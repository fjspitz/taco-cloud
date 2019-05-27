package tacos.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.model.Ingredient;
import tacos.model.Order;
import tacos.model.Taco;
import tacos.model.Type;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	
	private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);
	
	private IngredientRepository ingredientRepo;
	
	private TacoRepository designRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
		this.ingredientRepo = ingredientRepo;
		this.designRepo = designRepo;
	}
	
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		
		Type[] types = Type.values();
		
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), 
					filterByType(ingredients, type));
		}
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
	public String showDesignForm(Model model) { 
		model.addAttribute("design", new Taco());
		
		return "design-a-taco";
	}
	
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, @ModelAttribute Order order) {
		if (errors.hasErrors()) {
			return "design-a-taco";
		}
		
		log.info("Processing design: {}", design);
		
		Taco saved = designRepo.save(design);
		order.addDesign(saved);
		
		return "redirect:/orders/current";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		
		List<Ingredient> result = new ArrayList<>();
		
		for (Ingredient ingredient : ingredients) {
			if (ingredient.getType().equals(type)) {
				result.add(ingredient);
			}
		}
		return result;
	}
}
