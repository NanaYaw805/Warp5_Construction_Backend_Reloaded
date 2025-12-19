package com.warp5.warp5_construction_i.config;




import com.warp5.warp5_construction_i.helpers.StringToDoubleHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelperConfig {

    @Bean
    public StringToDoubleHelper stringToDoubleHelper() {
        return new StringToDoubleHelper();
    }
}
