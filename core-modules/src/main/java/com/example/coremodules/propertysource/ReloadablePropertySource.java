package com.example.coremodules.propertysource;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.env.PropertySource;

public class ReloadablePropertySource extends PropertySource<Object> {

    private final PropertiesConfiguration configuration;

    public ReloadablePropertySource(String name, String path) {
        super(StringUtils.isEmpty(name) ? path : name);

        try {
            this.configuration = new PropertiesConfiguration(path);
            this.configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
            this.configuration.setEncoding("UTF-8");
            this.configuration.refresh();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Object getProperty(String s) {
        return configuration.getProperty(s);
    }
}
