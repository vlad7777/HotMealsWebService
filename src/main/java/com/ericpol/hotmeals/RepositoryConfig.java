package com.ericpol.hotmeals;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.ericpol.hotmeals.model.Category;
import com.ericpol.hotmeals.model.Dish;
import com.ericpol.hotmeals.model.Receipt;
import com.ericpol.hotmeals.model.Supplier;
import com.ericpol.hotmeals.model.User;

@Configuration
public class RepositoryConfig extends RepositoryRestMvcConfiguration {
    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Category.class);
        config.exposeIdsFor(Supplier.class);
        config.exposeIdsFor(Dish.class);
        config.exposeIdsFor(User.class);
        config.exposeIdsFor(Receipt.class);
    }
}