package org.socialnetwork.config;

import org.socialnetwork.ValidationAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class ValidatorAutoConfig {

    @Bean
    public ValidationAspect validationAspect() {
        return new ValidationAspect();
    }
}
