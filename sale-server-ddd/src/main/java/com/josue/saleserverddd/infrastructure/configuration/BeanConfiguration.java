package com.josue.saleserverddd.infrastructure.configuration;

import com.josue.saleserverddd.SaleServerApplication;
import com.josue.saleserverddd.domain.repository.UserRepository;
import com.josue.saleserverddd.domain.service.UserService;
import com.josue.saleserverddd.domain.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SaleServerApplication.class)
public class BeanConfiguration {

    @Bean
    public UserService userServiceBean(
            UserRepository userRepository
    ) {
        return new UserServiceImpl(userRepository);
    }

}
