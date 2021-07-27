package softuni.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.exam.util.XmlParser;
import softuni.exam.util.impl.XmlParserImpl;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public XmlParser xmlParser() {
        return new XmlParserImpl();
    }
}
