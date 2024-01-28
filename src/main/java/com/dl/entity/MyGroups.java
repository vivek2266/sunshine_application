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
public class MyGroups  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int groupId;

	private String groupName;
	private boolean isActive = true;

	@JsonIgnore
	@ManyToMany(mappedBy = "assignGroups")
	private List<Roles> roles;

	@ManyToMany
	@JoinTable(name = "group_Permission", joinColumns = @JoinColumn(name = "groupId"), inverseJoinColumns = @JoinColumn(name = "permissionId"))

	private List<Permissions> assignPermissions;

}