package org.formation.service;

import java.util.function.Supplier;

import org.formation.config.LiftUserDetails;
import org.formation.model.Member;
import org.formation.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;

//@Override
//public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    Supplier<UsernameNotFoundException> s =
//            () -> new UsernameNotFoundException("Problem during authentication!");
//
//    Member m = memberRepository.findByLogin(username).orElseThrow(s);
//        
//    List<SimpleGrantedAuthority> authList = new ArrayList<>();
//    
//  
//    
//    authList.add(new SimpleGrantedAuthority(m.getRoles()..getName()));
//    
//    User user = new User(username, m.getPassword(), authList);
//
//    return user;
//   }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Problem during authentication!");

		Member user = memberRepository.findByLogin(username).orElseThrow(s);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user with that username");
		}

		return new LiftUserDetails(user);
	}

}