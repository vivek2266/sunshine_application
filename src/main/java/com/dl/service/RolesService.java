package com.dl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dl.entity.MyGroups;
import com.dl.entity.Roles;
import com.dl.repository.GroupRepository;
import com.dl.repository.RoleRepository;

@Service
public class RolesService {

	@Autowired
	RoleRepository rolesRepo;
	@Autowired
	GroupRepository groupRepo;

	public Roles addRoles( Roles roles) {

		return rolesRepo.save(roles);
	}

	public List<Roles> getAllRoles() {
		return rolesRepo.findAll();
	}

	public String deleteRolesById(int roleId) {

		rolesRepo.deleteById(roleId);
		return "deleted sucessfully";
	}

	public Roles updateRoles(int roleId, Roles roles) {
		Roles existingRoles = rolesRepo.findById(roleId).get();
		existingRoles.setRoleName(roles.getRoleName());

		return rolesRepo.save(existingRoles);
	}

	public Roles assignGroupsToRoles(int roleId,  List<Integer> groupId) {

		Roles roles = rolesRepo.findById(roleId).get();
		List<MyGroups> groups = groupRepo.findAllById(groupId);
		roles.setAssignGroups(groups);
		return rolesRepo.save(roles);
	}

	public List<Object[]> getRolesGroupsPermissionsByRolesId(int rolesId) {

		return rolesRepo.getRolesGroupsPermissionsByRolesId(rolesId);
	}
}
