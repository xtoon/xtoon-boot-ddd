package com.xtoon.boot.web.org;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 员工Controller
 *
 * @author haoxin
 * @date 2021-05-06
 **/
@Api(tags = "员工管理")
@RestController
@RequestMapping("/org/employee")
public class EmployeeController {
}
