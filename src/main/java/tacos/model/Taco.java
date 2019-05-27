package tacos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Taco {
	private Long id;
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;
	@NotNull
	@Size(min = 1, message = "You must choose al least 1 ingredient")
	private List<Ingredient> ingredients;
	private Date createdAt;
	
	public Taco() { 
		this.createdAt = new Date();
		this.ingredients = new ArrayList<>();
	}

	public Taco(String name, List<Ingredient> ingredients) {
		super();
		this.name = name;
		this.ingredients = ingredients;
		this.createdAt = new Date();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Taco [id=" + id + ", name=" + name + ", ingredients=" + ingredients + ", createdAt=" + createdAt + "]";
	}
	
}
