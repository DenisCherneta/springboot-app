package spring.pro.springboot.jdbc.controllers;


import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import spring.pro.springboot.jdbc.dao.CustomerDao;
import spring.pro.springboot.jdbc.service.views.AuthView;

import javax.validation.Valid;

/*
* created from https://www.javadevjournal.com/spring/securing-a-restful-web-service-with-spring-security/
*/

@Api(value = "Authorization Controller", tags = {"Authorization"})
@SwaggerDefinition(tags = {@Tag(name="Auth Controller", description = "Use for obtaining Bearer token")})

@Slf4j
@RestController
@RequestMapping(path="/auth")
public class AuthController {

    @Autowired
    private CustomerDao customerDao;

    @ApiOperation(value="Auth Bearer token for header " + HttpHeaders.AUTHORIZATION, response = String.class, tags = "Authorization", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="a1234bc5-1234-12a3-a1b2-123a4b56cd78")
    })
    @PostMapping(path = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBearerToken(@Valid @RequestBody AuthView credentials){
        log.debug("Auth with: {}", credentials);
        String token= customerDao.login(credentials.getLogin() ,credentials.getPassword());
        if(StringUtils.isEmpty(token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return token;
    }
}
