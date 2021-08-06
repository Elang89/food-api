package com.food.api.models.ingredients;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity(name = "ingredients")
public class Ingredient {

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
	@Basic(optional = true)
	@Column(name = "updated_at", nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedAt;

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

	public Ingredient(String name) {
		this.id = UUID.randomUUID();
		this.name = name;
	}
}
