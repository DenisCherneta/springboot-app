package spring.pro.springboot.jdbc.controllers;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import spring.pro.springboot.jdbc.dao.SingerDao;
import spring.pro.springboot.jdbc.dao.entities.Singer;
import spring.pro.springboot.jdbc.service.exeptions.BadRequestApplicationException;
import spring.pro.springboot.jdbc.service.mappers.SingerMapper;
import spring.pro.springboot.jdbc.service.views.SingerView;

import java.util.Set;
import java.util.stream.Collectors;

@Api(value = "Singer Controller", tags = {"Singers"})
@SwaggerDefinition(tags = {@Tag(name = "Singer Controller", description = "Available actions with Entity 'Singer '")})

@Slf4j
@RestController
@RequestMapping("/api/singers")
public class SingerController {

    private SingerDao singerDao;
    private SingerMapper singerMapper;

    @Autowired
    public void setSingerDao(SingerDao singerDao) {
        this.singerDao = singerDao;
    }

    @Autowired
    public void setSingerMapper(SingerMapper singerMapper) {
        this.singerMapper = singerMapper;
    }

    @ApiOperation(value="Collection of all Singers", response = Set.class, tags = "Singers", produces = MediaType.APPLICATION_JSON_VALUE )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Collection of singers")
    })
    @GetMapping(path = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Set<SingerView> getAll(){
        Set results = singerDao.findAll().stream().collect(Collectors.toSet());
        return  singerMapper.convertToViewSet(results);
    }

    @ApiOperation(value="Get info about Singer by Id", response =  SingerView.class, tags = "Singers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Entity"),
            @ApiResponse(code = 404, message="NOT FOUND")
    })
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public SingerView getById(@PathVariable Long id){
        Singer result = singerDao.findById(id);
        return singerMapper.convertToView(result);
    }

    @BasicAuthDefinition(key=("Bearer a1234bc5-1234-12a3-a1b2-123a4b56cd78"), description = "Header " + HttpHeaders.AUTHORIZATION)
    @ApiOperation(value="Update entity Singer", response = SingerView.class, tags = "Singers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Updated Entity View")
    })
    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public SingerView update(@PathVariable Long id, @RequestBody SingerView view){
        if (id != view.getId()) {
            log.error("Param id [%d] not equals updating Singer Id [%d]!", id, view.getId());
            throw new BadRequestApplicationException("Param id [%d] not equals updating Singer id!", id);
        }
        Singer entity = singerMapper.convertToEntity(view);
        return singerMapper.convertToView(singerDao.update(entity));
    }

    @ApiOperation(value="Create entity Singer", response = SingerView.class, tags = "Singers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Created Entity View"),
            @ApiResponse(code = 404, message="Entity Not Found"),
            @ApiResponse(code = 500, message="Creation Error: %error_message%")
    })
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public SingerView create(@RequestBody SingerView singer){
        Singer entity = singerMapper.convertToEntity(singer);
        return singerMapper.convertToView(singerDao.create(entity));
    }

    @ApiOperation(value="Delete entity Singer by Id", tags = "Singers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message=""),
            @ApiResponse(code = 404, message="NOT FOUND")
    })
    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void delete(@PathVariable Long id){
        singerDao.deleteById(id);
    }
}
