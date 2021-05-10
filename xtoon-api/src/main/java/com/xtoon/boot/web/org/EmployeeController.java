package com.xtoon.boot.web.org;

import com.xtoon.boot.common.AbstractController;
import com.xtoon.boot.common.Result;
import com.xtoon.boot.common.util.CommonConstant;
import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.util.log.SysLog;
import com.xtoon.boot.org.application.EmployeeApplicationService;
import com.xtoon.boot.org.application.EmployeeQueryService;
import com.xtoon.boot.org.application.dto.EmployeeDTO;
import com.xtoon.boot.util.validator.ValidatorUtils;
import com.xtoon.boot.util.validator.group.AddGroup;
import com.xtoon.boot.util.validator.group.UpdateGroup;
import com.xtoon.boot.web.org.command.EmployeeCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 员工Controller
 *
 * @author haoxin
 * @date 2021-05-06
 **/
@Api(tags = "员工管理")
@RestController
@RequestMapping("/org/employee")
public class EmployeeController extends AbstractController {

    @Autowired
    private EmployeeApplicationService employeeApplicationService;

    @Autowired
    private EmployeeQueryService employeeQueryService;

    /**
     * 员工分页查询
     */
    @ApiOperation("员工分页查询")
    @GetMapping("/list")
    @RequiresPermissions("org:employee:list")
    public Result list(@RequestParam Map<String, Object> params){
        Page page = employeeQueryService.queryPage(params);
        return Result.ok().put(CommonConstant.PAGE, page);
    }

    /**
     * 员工信息
     */
    @ApiOperation("员工信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("org:employee:info")
    public Result info(@PathVariable("id") String id){
        EmployeeDTO employee = employeeQueryService.getById(id);
        return Result.ok().put("employee", employee);
    }


    /**
     * 保存员工
     */
    @ApiOperation("保存员工")
    @SysLog("保存员工")
    @PostMapping("/save")
    @RequiresPermissions("org:employee:save")
    public Result save(@RequestBody EmployeeCommand employeeCommand){
        ValidatorUtils.validateEntity(employeeCommand, AddGroup.class);
        employeeApplicationService.save(new EmployeeDTO(employeeCommand.getId(), employeeCommand.getEmpNo(), employeeCommand.getEmpName(),
                employeeCommand.getEmpSex(), employeeCommand.getMobile(), employeeCommand.getEmail(), employeeCommand.getOfficeId(),
                employeeCommand.getPostIdList(), employeeCommand.getRemarks()));
        return Result.ok();
    }

    /**
     * 修改员工
     */
    @ApiOperation("修改员工")
    @SysLog("修改员工")
    @PostMapping("/update")
    @RequiresPermissions("org:employee:update")
    public Result update(@RequestBody EmployeeCommand employeeCommand){
        ValidatorUtils.validateEntity(employeeCommand, UpdateGroup.class);
        employeeApplicationService.update(new EmployeeDTO(employeeCommand.getId(), employeeCommand.getEmpNo(), employeeCommand.getEmpName(),
                employeeCommand.getEmpSex(), employeeCommand.getMobile(), employeeCommand.getEmail(), employeeCommand.getOfficeId(),
                employeeCommand.getPostIdList(), employeeCommand.getRemarks()));
        return Result.ok();
    }

    /**
     * 删除员工
     */
    @ApiOperation("删除员工")
    @SysLog("删除员工")
    @PostMapping("/delete")
    @RequiresPermissions("org:employee:delete")
    public Result delete(@RequestBody String[] employeeIds){
        employeeApplicationService.deleteBatch(Arrays.asList(employeeIds));
        return Result.ok();
    }

    /**
     * 禁用员工
     */
    @ApiOperation("禁用员工")
    @SysLog("禁用员工")
    @PostMapping("/disable/{id}")
    @RequiresPermissions("org:employee:disable")
    public Result disable(@PathVariable("id") String id){
        employeeApplicationService.disable(id);
        return Result.ok();
    }
}
