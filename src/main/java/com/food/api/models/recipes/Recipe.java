package com.food.api.models.recipes;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import com.food.api.models.ingredients.Ingredient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity(name = "recipes")
public class Recipe {

	@Id
	@Basic(optional = true)
	@Getter
	private UUID id;

	@Getter
	@Setter
	@Basic(optional = false)
	@Column(name = "name", nullable = false)
	private String name;

	@Getter
	@Basic(optional = true)
	@Column(name = "created_at", nullable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Getter
	@Basic(optional = false)
	@Column(name = "updated_at", nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@Getter
	@Setter
	@ManyToMany
	@JoinTable(name = "recipes_ingredients", joinColumns = @JoinColumn(name = "recipe_id"),
			inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	Set<Ingredient> ingredients;


	@PrePersist
	protected void prePersist() {
		if (this.createdAt == null)
			createdAt = LocalDateTime.now();
		if (this.updatedAt == null)
			updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void preUpdated() {
		this.updatedAt = LocalDateTime.now();
	}

	public Recipe(String name) {
		this.id = UUID.randomUUID();
		this.name = name;
	}

}

