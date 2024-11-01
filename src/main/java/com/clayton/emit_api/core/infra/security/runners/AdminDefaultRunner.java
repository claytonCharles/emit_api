package com.clayton.emit_api.core.infra.security.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.clayton.emit_api.auth.domain.entities.UserEntity;
import com.clayton.emit_api.auth.domain.repositories.UserRepository;

/**
 * Runner para a criação do primeiro usuário no sistema, ADMIN, visando que não é possível criar contas no sistema 
 * de forma externa, e apenas um admin como adicionar novas contas.
 * E necessário a alteração da senha, antes da alocação do sistema em produção, a mesma feita de forma genérica, 
 * apenas para o primeiro acesso ao sistema.
 * @author Clayton Charles
 * @version 0.1.0
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
