package com.jvoliveira.resource;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/greetings")
public class GreetingsController implements GreetingsControllerRoutes{

	@Value("${greeting.message}")
	private String greeting;
	
    @Get("/")
    public HttpStatus index() {
        return HttpStatus.OK;
    }

	@Override
	public HttpResponse<String> greeting(String name) {
		return HttpResponse.ok(greeting + ", " + name + "!");
	}
}