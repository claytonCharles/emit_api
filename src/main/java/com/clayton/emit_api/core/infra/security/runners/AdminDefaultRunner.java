package com.clayton.emit_api.core.infra.security.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.clayton.emit_api.auth.domain.entities.UserEntity;
import com.clayton.emit_api.auth.domain.repositories.UserRepository;

/**
 * 
 */
@Component
public class AdminDefaultRunner implements ApplicationRunner {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.findByMail("admin@admin.com") != null) {
            return;
        }
        
        String passEncode = new BCryptPasswordEncoder().encode("admin123");
        UserEntity user = new UserEntity("admin@admin.com", "Admin Default", passEncode, true, (long) 1);
        this.userRepository.save(user);
    }
    
}
