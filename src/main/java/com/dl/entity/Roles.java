package com.dl.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	private String roleName;
	private boolean isActive = true;

	@JsonIgnore
	@ManyToMany(mappedBy = "assignRoles")
	private List<Users> users;

	@ManyToMany
	@JoinTable(name = "role_group", joinColumns = @JoinColumn(name = "roleId"), inverseJoinColumns = @JoinColumn(name = "groupId"))

	private List<MyGroups> assignGroups;

}
