package guru.qa.configs;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:auth.properties"
})

public interface AuthConfig extends Config {

    @Key("browserstack.user")
    String getBrowserStackUser();

    @Key("browserstack.key")
    String getBrowserStackKey();

}
