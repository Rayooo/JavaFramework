package com.ray;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * Create by Ray 2017/4/25 16:34
 */
public class Initializer extends AbstractHttpSessionApplicationInitializer {

    public Initializer() {
        super(Config.class);
    }

}
