package hello;

import org.apache.thrift.TException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linecorp.armeria.client.Clients;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping("hello/{name}")
    String hello(@PathVariable String name) throws TException {
        final HelloService.Iface helloService = Clients.newClient(
                "tbinary+http://localhost:8080/thrift/hello",
                HelloService.Iface.class);
        return helloService.hello(name);
    }
}