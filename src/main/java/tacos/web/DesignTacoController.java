package tacos.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.data.IngredientRepository;
import tacos.model.Ingredient;
import tacos.model.Taco;
import tacos.model.Type;

@Controller
@RequestMapping("/design")
public class DesignTacoController {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);
	
	private IngredientRepository ingredientRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
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
	
	@GetMapping
	public String showDesignForm(Model model) { 
		model.addAttribute("design", new Taco());
		
		return "design-a-taco";
	}
	
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "design-a-taco";
		}
		
		log.info("Processing design: {}", design);
		
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
