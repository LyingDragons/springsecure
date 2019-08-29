package com.mj.security;

import java.util.ArrayList;
import java.util.List;

import com.mj.entity.Permission;
import com.mj.entity.User;
import com.mj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	private UserMapper userMapper;

	// 查询用户信息
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 根据用户查询用户信息
		User user = userMapper.findByUsername(username);
		// 根据用户查询用户对应权限
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<Permission> listPermission = userMapper.findPermissionByUsername(username);
		for (Permission permission : listPermission) {
			authorities.add(new SimpleGrantedAuthority(permission.getPermTag()));
		}
		// 设置用户权限
		user.setAuthorities(authorities);
		return user;
	}

}
