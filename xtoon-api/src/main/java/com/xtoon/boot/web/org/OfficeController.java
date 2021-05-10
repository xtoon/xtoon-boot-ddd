package com.xtoon.boot.web.org;

import com.xtoon.boot.common.AbstractController;
import com.xtoon.boot.common.Result;
import com.xtoon.boot.common.util.CommonConstant;
import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.util.log.SysLog;
import com.xtoon.boot.org.application.OfficeApplicationService;
import com.xtoon.boot.org.application.OfficeQueryService;
import com.xtoon.boot.org.application.dto.OfficeDTO;
import com.xtoon.boot.util.validator.ValidatorUtils;
import com.xtoon.boot.util.validator.group.AddGroup;
import com.xtoon.boot.util.validator.group.UpdateGroup;
import com.xtoon.boot.web.org.command.OfficeCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 机构Controller
 *
 * @author haoxin
 * @date 2021-05-06
 **/
@Api(tags = "机构管理")
@RestController
@RequestMapping("/org/office")
public class OfficeController extends AbstractController {

    @Autowired
    private OfficeApplicationService officeApplicationService;

    @Autowired
    private OfficeQueryService officeQueryService;

    /**
     * 机构分页查询
     */
    @ApiOperation("机构分页查询")
    @GetMapping("/list")
    @RequiresPermissions("org:office:list")
    public Result list(@RequestParam Map<String, Object> params){
        Page page = officeQueryService.queryPage(params);
        return Result.ok().put(CommonConstant.PAGE, page);
    }

    /**
     * 机构列表
     */
    @ApiOperation("机构列表")
    @GetMapping("/select")
    @RequiresPermissions("sys:office:select")
    public Result select(){
        List<OfficeDTO> list = officeQueryService.listAll();
        return Result.ok().put("list", list);
    }

    /**
     * 机构信息
     */
    @ApiOperation("机构信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("org:office:info")
    public Result info(@PathVariable("id") String id){
        OfficeDTO office = officeQueryService.getById(id);
        return Result.ok().put("office", office);
    }


    /**
     * 保存机构
     */
    @ApiOperation("保存机构")
    @SysLog("保存机构")
    @PostMapping("/save")
    @RequiresPermissions("org:office:save")
    public Result save(@RequestBody OfficeCommand officeCommand){
        ValidatorUtils.validateEntity(officeCommand, AddGroup.class);
        officeApplicationService.saveOrUpdate(new OfficeDTO(officeCommand.getId(), officeCommand.getOfficeCode(), officeCommand.getOfficeName(),
                officeCommand.getOfficeType(), officeCommand.getOrderNum(), officeCommand.getParentId(), officeCommand.getRemarks()));
        return Result.ok();
    }

    /**
     * 修改机构
     */
    @ApiOperation("修改机构")
    @SysLog("修改机构")
    @PostMapping("/update")
    @RequiresPermissions("org:office:update")
    public Result update(@RequestBody OfficeCommand officeCommand){
        ValidatorUtils.validateEntity(officeCommand, UpdateGroup.class);
        officeApplicationService.saveOrUpdate(new OfficeDTO(officeCommand.getId(), officeCommand.getOfficeCode(), officeCommand.getOfficeName(),
                officeCommand.getOfficeType(), officeCommand.getOrderNum(), officeCommand.getParentId(), officeCommand.getRemarks()));
        return Result.ok();
    }

    /**
     * 删除机构
     */
    @ApiOperation("删除机构")
    @SysLog("删除机构")
    @PostMapping("/delete")
    @RequiresPermissions("org:office:delete")
    public Result delete(@RequestBody String[] officeIds){
        officeApplicationService.deleteBatch(Arrays.asList(officeIds));
        return Result.ok();
    }

    /**
     * 禁用机构
     */
    @ApiOperation("禁用机构")
    @SysLog("禁用机构")
    @PostMapping("/disable/{id}")
    @RequiresPermissions("org:office:disable")
    public Result disable(@PathVariable("id") String id){
        officeApplicationService.disable(id);
        return Result.ok();
    }
}
