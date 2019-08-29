package com.mj.mapper;

import java.util.List;

import com.mj.entity.Permission;
import org.apache.ibatis.annotations.Select;

public interface PermissionMapper {

	@Select(" select * from sys_permission ")
	List<Permission> findAllPermission();

}
