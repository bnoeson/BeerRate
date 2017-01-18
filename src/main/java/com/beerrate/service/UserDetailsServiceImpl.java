package com.beerrate.service;

import com.beerrate.model.Role;
import com.beerrate.model.User;
import com.beerrate.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        
		if (user!=null) {
	        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	    	for (Role role : user.getRoles()){
	            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
	        }
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    	
    }
}