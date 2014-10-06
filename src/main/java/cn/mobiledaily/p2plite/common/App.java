package cn.mobiledaily.p2plite.common;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by johnson on 14-10-6.
 */
public final class App {
    private static final App INSTANCE = new App();
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static PropertiesConfiguration CONFIG;

    static {
        try {
            CONFIG = new PropertiesConfiguration();
            CONFIG.setEncoding("GBK");
            CONFIG.setFileName("config.properties");
            CONFIG.load();
        } catch (ConfigurationException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private App() {
    }

    public static App getInstance() {
        return INSTANCE;
    }

    public String get(final String key) {
        return CONFIG.getString(key);
    }

    public String getImageStore() {
        return get("image.store");
    }

    public String getOcrServerHost() {
        return get("ocr.host");
    }

    public int getOcrServerPort() {
        return Integer.valueOf(get("ocr.port"));
    }
}