package com.mj.mapper;

import java.util.List;

import com.mj.entity.Permission;
import com.mj.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface UserMapper {
	// 查询用户信息
	@Select(" select * from sys_user where username = #{userName}")
	User findByUsername(@Param("userName") String userName);

	// 查询用户的权限
	@Select(" select permission.* from sys_user user" + " inner join sys_user_role user_role"
			+ " on user.id = user_role.user_id inner join "
			+ "sys_role_permission role_permission on user_role.role_id = role_permission.role_id "
			+ " inner join sys_permission permission on role_permission.perm_id = permission.id where user.username = #{userName};")
	List<Permission> findPermissionByUsername(@Param("userName") String userName);

	@Insert("insert into sys_user(username,password) values(#{username},#{password})")
	int insert(@Param("username") String username,@Param("password") String password);
}
