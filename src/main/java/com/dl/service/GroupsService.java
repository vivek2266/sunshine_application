package com.dl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dl.entity.MyGroups;
import com.dl.entity.Permissions;
import com.dl.repository.GroupRepository;
import com.dl.repository.PermissionRepository;

import jakarta.transaction.Transactional;


@Service

@Transactional
public class GroupsService {
	@Autowired
	GroupRepository groupRepo;
	@Autowired
	PermissionRepository permissionRepo;

	public MyGroups addGroups( MyGroups groups) {

		return groupRepo.save(groups);
	}

	public List<MyGroups> getAllGroups() {
		return groupRepo.findAll();
	}

	public String deleteGroupsById(int groupId) {

		groupRepo.deleteById(groupId);
		return "deleted sucessfully";
	}

	public MyGroups updateGroups(int groupId, MyGroups groups) {

		MyGroups existingroups = groupRepo.findById(groupId).get();
		existingroups.setGroupName(groups.getGroupName());

		return groupRepo.save(existingroups);
	}

	public MyGroups assignPermissionsToGroups(int groupId,  List<Integer> permissionId) {

		MyGroups group = groupRepo.findById(groupId).get();
		List<Permissions> permissions = permissionRepo.findAllById(permissionId);
		group.setAssignPermissions(permissions);
		return groupRepo.save(group);
	}
	
	

	
	

}
