package com.mirandasidney.pdv.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Sidney Miranda.
 * Componente que configura um carregador de contexto no caso de carregar
 * o repository sem a anotação @Autowired
 */

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationContextLoad implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
