package hello;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.linecorp.armeria.internal.shaded.guava.collect.ImmutableList;
import com.linecorp.armeria.server.healthcheck.HealthChecker;
import com.linecorp.armeria.server.thrift.THttpService;
import com.linecorp.armeria.server.tomcat.TomcatService;
import com.linecorp.armeria.spring.ArmeriaServerConfigurator;
import com.linecorp.armeria.spring.ThriftServiceRegistrationBean;

@Configuration
public class ArmeriaConfig {

    private static Connector getConnector(ServletWebServerApplicationContext applicationContext) {
        final TomcatWebServer container = (TomcatWebServer) applicationContext.getWebServer();
        container.start();
        return container.getTomcat().getConnector();
    }

    @Bean
    public HealthChecker tomcatConnectorHealthChecker(ServletWebServerApplicationContext applicationContext) {
        final Connector connector = getConnector(applicationContext);
        return () -> connector.getState().isAvailable();
    }

    @Bean
    public TomcatService tomcatService(ServletWebServerApplicationContext applicationContext) {
        return TomcatService.forConnector(getConnector(applicationContext));
    }

    @Bean
    public ArmeriaServerConfigurator armeriaServiceInitializer(TomcatService tomcatService) {
        return sb -> sb.service("prefix:/", tomcatService);
    }

    @Bean
    public ThriftServiceRegistrationBean helloService(HelloService.Iface helloService) {
        return new ThriftServiceRegistrationBean()
                .setPath("/thrift/hello")
                .setService(THttpService.of(helloService))
                .setServiceName("HelloService")
                .setExampleRequests(ImmutableList.of(new HelloService.hello_args("foo")));
    }

    @Bean
    public ThriftServiceRegistrationBean asyncHelloService(HelloService.AsyncIface asyncHelloService) {
        return new ThriftServiceRegistrationBean()
                .setPath("/thrift/hello/async")
                .setService(THttpService.of(asyncHelloService))
                .setServiceName("asyncHelloService")
                .setExampleRequests(ImmutableList.of(new HelloService.hello_args("foo")));

    }
}