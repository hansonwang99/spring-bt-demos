package cn.codesheep.springbt_es6_4_2.config;

import cn.codesheep.springbt_es6_4_2.model.dto.DocModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Configuration
public class DocFieldsConfig {

    @Value("${elasticsearch.docfields}")
    private String docfields;

    @Bean
    public DocModel docModel() {
        List<String> docFields = new ArrayList<>();
        String[] fields = docfields.split(",");
        for( int i=0; i<fields.length; ++i )
            docFields.add( fields[i] );
        return new DocModel( docFields );
    }

}
