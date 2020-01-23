package spring.pro.springboot.jdbc.controllers;

import io.swagger.annotations.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Health Controller", tags = {"Health"})
@SwaggerDefinition(tags = {@Tag(name = "Health Controller", description = "Use to check application status")})

@RestController
@RequestMapping(path="/health")
public class HealthController {

    @ApiOperation(value="Status of started application", response = String.class, tags = "Health", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="work")
    })
    @GetMapping(path = "/check", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getStatus(){
        return "work";
    }
}
