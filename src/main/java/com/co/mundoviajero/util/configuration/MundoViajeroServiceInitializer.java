package com.co.mundoviajero.util.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MundoViajeroServiceInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {MundoViajeroServiceConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
