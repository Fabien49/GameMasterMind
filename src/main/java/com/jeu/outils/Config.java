package com.jeu.outils;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;


public class Config {

    private final static Logger log = Logger.getLogger(Config.class);

    /**
     * Effectue un try catch en cas de fichier mal nommé ou absent
     * @param cleConfig
     * @return
     */

    public static String getConfigValue (String cleConfig)  {

        String valeurConfig = null;
        InputStream inputStream=null;

        try {

            Properties prop = new Properties();
            String propFileName = null;

            propFileName = "config.properties";
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propFileName);


            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                log.error("property file '" + propFileName + "' not found in the classpath");
            }

            // get the property value and print it out
            valeurConfig = prop.getProperty(cleConfig);


            inputStream.close();

        }catch (Exception e) {
            log.error("Le fichier est incorrect" + e.getMessage());
        }
        return valeurConfig;

    }
}
