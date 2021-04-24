package com.pkl.boot.web;

import com.pkl.boot.common.config.FirstGroup;
import com.pkl.boot.entity.User;
import com.pkl.boot.service.IHelloService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.oas.annotations.EnableOpenApi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description 用户接口
 */

@RestController
@RequestMapping("hello")
public class HelloController {
    @Autowired
    private IHelloService helloService;

    /**
     * 用户列表Get
     * @decription 用户列表get
     * @param name 姓名
     * @param age 年龄
     * @return
     */
    @GetMapping("webGet")
    @ApiOperation(value = "select1请求",notes = "多个参数，多种的查询参数类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="姓名",required=true),
            @ApiImplicitParam(paramType="query",name="age",value="年龄",required=true),
    })
    public String helloGet(@RequestParam(required = true)  String name, String age){
        return "访问web成功....";
    }

    /**
     * 用户列表Post
     * @param request
     * @param response
     * @param user 用户对象
     * @return
     */
    @PostMapping("webPost")
    public User helloPost(HttpServletRequest request, HttpServletResponse response, @Validated( { FirstGroup.class})@RequestBody(required = true)User user){
        return new User();
    }
    @RequestMapping(value = "/getFile", method = RequestMethod.POST)
    public ResponseEntity getFile(HttpServletResponse response) {
        File file = new File("C:\\Users\\pc\\Desktop\\测试\\xx.xlsx");
        if(file.exists() && !file.isDirectory()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition", "attachment; filename=" + file.getName());
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Last-Modified", new Date().toString());
            headers.add("ETag", String.valueOf(System.currentTimeMillis()));
            return  ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new FileSystemResource(file));
        }else {
            return new ResponseEntity<>("not content 204", HttpStatus.NO_CONTENT);
        }
    }
    /**
     * 用户列表Map
     * @param request
     * @param response
     * @param ids id列表
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name="ids",value="id集合",required=true),
    })
    @GetMapping("webMap")
    public User helloMap(HttpServletRequest request, HttpServletResponse response, @RequestParam List<String> ids){
        return new User();
    }

    /**
     * jsql测试
     * @param request
     * @param response
     * @param ids id列表
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name="ids",value="id集合",required=true),
    })
    @GetMapping("jsql")
    public User jsqlTest(HttpServletRequest request, HttpServletResponse response){
        return helloService.getUser("123").get(0);
    }
}
