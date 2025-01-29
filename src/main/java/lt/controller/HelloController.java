package lt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return """
        <html>
          <head>
            <title>Hello</title>
          </head>
          <body>
            <h1>Hello, World!</h1>
            <input type="text" placeholder="Enter your name">
          </body>
        </html>
        """;
  }
}
