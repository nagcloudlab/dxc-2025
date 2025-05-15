package com.example.bar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// bar-service/src/main/java/com/example/bar/BarController.java
@RestController
@RequestMapping("/bar")
class BarController {

    private int counter = 0;

    @GetMapping
    public ResponseEntity<String> getBar() {
        counter++;
        if (counter % 3 == 0) {
            // Simulate failure every 3rd call
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Bar Service Failed!");
        }
        return ResponseEntity.ok("Response from Bar");
    }
}



@SpringBootApplication
public class BarApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarApplication.class, args);
	}

}
