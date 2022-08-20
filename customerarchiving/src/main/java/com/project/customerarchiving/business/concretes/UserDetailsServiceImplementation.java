package com.project.customerarchiving.business.concretes;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.customerarchiving.dataAccess.abstracts.UserDao;
import com.project.customerarchiving.entities.concretes.User;
import com.project.customerarchiving.security.JwtUserDetails;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService{
	
	private UserDao userDao;
	
	public UserDetailsServiceImplementation(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUserName(username);
		return JwtUserDetails.create(user);
	}
	
	public UserDetails loadUserById(Long id) {
		User user = userDao.findById(id).get();
		return JwtUserDetails.create(user);
	}
	
}
