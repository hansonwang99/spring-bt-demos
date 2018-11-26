package cn.codesheep.springbt_jwt_mybatis.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JUserFactory {

	final static String ROLE_PREFIX = "ROLE_" ;
    private JUserFactory() {
    }

    public static JUser create(User user) {

    	  return new JUser(
                  user.getUserId(),
                  user.getUserName(),
                  user.getPassword(),
                  mapToGrantedAuthorities(user),
                 null                
          );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(User user) {
    	
    	if(user == null)
    		return null ;

    	List<String> authorities = new ArrayList<String>();
    	List<Role> roles = user.getRoles();
    	
    	if(roles== null ||roles.isEmpty())
    		return null;
    	
    	for( Role role :roles ) {
    		List<Authority> auths = role.getAuths() ;
    		if(auths== null ||auths.isEmpty())continue;
    		for(Authority auth :auths) {
    			authorities.add( ROLE_PREFIX + auth.getAuthCode() );
    		}
        }
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}