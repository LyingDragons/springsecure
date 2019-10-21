package com.mj.config;

import com.mj.entity.Permission;
import com.mj.handler.MyAuthenticationFailureHandler;
import com.mj.handler.MyAuthenticationSuccessHandler;
import com.mj.mapper.PermissionMapper;
import com.mj.security.MyUserDetailService;
import com.mj.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyAuthenticationSuccessHandler successHandler;
	@Autowired
	private MyAuthenticationFailureHandler failureHandler;
	@Autowired
	private MyUserDetailService myUserDetailsService;
	@Autowired
	private PermissionMapper permissionMapper;
	// 用户认证信息
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 设置用户账号信息和权限
//		auth.inMemoryAuthentication().withUser("admin").password("123456").authorities("showOrder","addOrder","updateOrder","deleteOrder");
//		auth.inMemoryAuthentication().withUser("user").password("123456").authorities("addOrder");


		auth.userDetailsService(myUserDetailsService).passwordEncoder(new PasswordEncoder() {
			//加密密码
			@Override
			public String encode(CharSequence charSequence) {
				return MD5Util.encode((String)charSequence);

			}
			//加密匹配密码
			@Override
			public boolean matches(CharSequence charSequence, String encodedPassword) {
                String encode = MD5Util.encode((String) charSequence);
                boolean equals = encode.equals(encodedPassword);
                return equals;
			}
		});


	}

	// 配置HttpSecurity 拦截资源
	protected void configure(HttpSecurity http) throws Exception {
//				http.authorizeRequests()
//				.antMatchers("/showOrder").hasAnyAuthority("showOrder")
//				.antMatchers("/addOrder").hasAnyAuthority("addOrder")
//				.antMatchers("/updateOrder").hasAnyAuthority("updateOrder")
//				.antMatchers("/deleteOrder").hasAnyAuthority("deleteOrder")
//				.antMatchers("/login").permitAll()
//				.antMatchers("/**").fullyAuthenticated().and().formLogin().loginPage("/login")
//				.successHandler(successHandler).failureHandler(failureHandler)
//				.and().csrf().disable();

		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
				authorizeRequests = http.authorizeRequests();
		List<Permission> allPermission = permissionMapper.findAllPermission();
		for (Permission permission : allPermission) {
			authorizeRequests.antMatchers(permission.getUrl()).hasAnyAuthority(permission.getPermTag());
		}
		authorizeRequests.antMatchers("/login").permitAll()
		.antMatchers("/**").fullyAuthenticated().and().formLogin().loginPage("/login")
		.and().csrf().disable();

//        http.authorizeRequests().antMatchers("/**").fullyAuthenticated().and().httpBasic();

	}

	//密码不想加密的话，需要加上这个bean
//	@Bean
//	public static NoOpPasswordEncoder passwordEncoder() {
//		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//	}
}
