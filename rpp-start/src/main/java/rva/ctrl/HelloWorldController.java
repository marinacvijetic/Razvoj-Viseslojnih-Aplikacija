package rva.ctrl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HelloWorldController {
	
	@RequestMapping("/")
	public String helloWorld() {
	return "Hello World!";
	}
	
	
	@RequestMapping("/zbir")
	public String zbir() {
		long x = Math.round(Math.random()*10);
		long y = Math.round(Math.random()*10);
		
		return x + "+" + y + "=" + (x+y);
	}

}
