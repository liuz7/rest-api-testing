package com.dianping.utils;

import com.dianping.model.config.Base;
import com.dianping.model.config.Deploy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.*;

import java.io.IOException;

/**
 * Created by georgeliu on 2017/5/3.
 */
public class Configuration {

    private Base base;
    private Deploy deploy;
    private static ObjectMapper objectMapper;
    private static Configuration configuration;

    private Configuration() {
        objectMapper = new ObjectMapper();
        Config config = ConfigFactory.load();
        ConfigObject configObject = config.root();
        ConfigValue deployConfig = configObject.get("deploy");
        ConfigValue baseConfig = configObject.get("base");
        try {
            this.base = objectMapper.readValue(baseConfig.render(ConfigRenderOptions.concise()), Base.class);
            this.deploy = objectMapper.readValue(deployConfig.render(ConfigRenderOptions.concise()), Deploy.class);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static Configuration getInstance() {
        if (configuration == null) {
            synchronized (Configuration.class) {
                if (configuration == null) {
                    configuration = new Configuration();
                }
            }
        }
        return configuration;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public Deploy getDeploy() {
        return deploy;
    }

    public void setDeploy(Deploy deploy) {
        this.deploy = deploy;
    }

}
