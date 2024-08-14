package com.netbeans.projet01.controllers.test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class FirstController {
    @GetMapping("/hi")
    public String sayHello(){
        return "Hello from my fist controller";
    }
    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String sayHello2(){
        return "hello2";
    }
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public String post(@RequestBody String message){
        return "Request accepted : "+message;
    }
    @PostMapping("/order")
    public String postOrder(@RequestBody Order order){
        return "Details: "+order.toString();
    }

    @PostMapping("/order-record")
    public String postOrderRecord(@RequestBody OrderRecord order){
        return "Details: "+order.toString();
    }

    @GetMapping("/hello/{user-name}")
    @ResponseStatus(HttpStatus.OK)
    public String pathVar(@PathVariable("user-name") String userName){
        return "user name: "+userName;
    }

    @GetMapping("/hello2")
    @ResponseStatus(HttpStatus.OK)
    public String pathRequestParams(@RequestParam("firstName") String fistName,@RequestParam("lastName") String lastName){
        return "user details : "+fistName+" "+ lastName;
    }
}
