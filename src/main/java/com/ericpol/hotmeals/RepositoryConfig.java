package com.ericpol.hotmeals;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.ericpol.hotmeals.model.Category;
import com.ericpol.hotmeals.model.Supplier;

@Configuration
public class RepositoryConfig extends RepositoryRestMvcConfiguration {
    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Category.class);
        config.exposeIdsFor(Supplier.class);
    }
}