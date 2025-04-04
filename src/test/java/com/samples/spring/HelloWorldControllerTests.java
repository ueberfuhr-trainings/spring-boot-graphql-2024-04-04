package com.samples.spring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class HelloWorldControllerTests {

  @Test
  void shouldReturnHelloMessage() {
    var controller = new HelloWorldController();
    
    var result = controller.sayHello("Tom");
    
    assertThat(result)
      .isEqualTo("Hello Tom");
    
  }

  @Disabled // funktioniert nicht
  @Test
  void shouldReturnHelloWorldMessage() {
    var controller = new HelloWorldController();
    
    var result = controller.sayHello(null);
    
    assertThat(result)
      .isEqualTo("Hello World");
    
  }

}
