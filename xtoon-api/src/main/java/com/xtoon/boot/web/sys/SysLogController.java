package com.xtoon.boot.web.sys;

import com.xtoon.boot.common.AbstractController;
import com.xtoon.boot.common.Result;
import com.xtoon.boot.common.util.CommonConstant;
import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.sys.facade.LogFacadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 系统日志Controller
 *
 * @author haoxin
 * @date 2021-02-04
 **/
@Api(tags = "日志管理")
@RestController
@RequestMapping("/sys/log")
public class SysLogController extends AbstractController {

    @Autowired
    private LogFacadeService logFacadeService;

    /**
     * 列表
     */
    @ApiOperation("分页查询日志")
    @GetMapping("/list")
    @RequiresPermissions("sys:log:list")
    public Result list(@RequestParam Map<String, Object> params){
        Page page = logFacadeService.queryPage(params);
        return Result.ok().put(CommonConstant.PAGE, page);
    }
}
