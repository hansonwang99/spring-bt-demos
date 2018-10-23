package cn.codesheep.springbt_security_jwt.service;

import cn.codesheep.springbt_security_jwt.model.entity.Role;
import cn.codesheep.springbt_security_jwt.model.entity.User;
import cn.codesheep.springbt_security_jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("该用户名不存在");
        }

        System.out.println("s:"+s);
        System.out.println("username:"+((User) user).getUsername()+";password:"+user.getPassword());
        System.out.println("size:"+user.getRoles().size());

        for( Role item : user.getRoles() ) {
            System.out.println( "role:" + item.getName() );
        }

        return user;
    }

}
