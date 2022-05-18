package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperty;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
//@RefreshScope
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
//    @Value("${pattern.dateformat}")
//    private String dateformat;
    @Autowired
    private PatternProperty property;

    @GetMapping("prop")
    public PatternProperty property(){
        return property;
    }
    @GetMapping("now")
    public String now(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(property.getDateformat()));
    }
    /**
     * 路径： /user/110
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id,
    @RequestHeader(value = "Truth",required = false)String truth) {
        System.out.println("truth:"+truth);
        return userService.queryById(id);
    }
}
