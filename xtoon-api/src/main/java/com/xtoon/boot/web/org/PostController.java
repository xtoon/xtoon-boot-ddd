package com.xtoon.boot.web.org;

import com.xtoon.boot.common.AbstractController;
import com.xtoon.boot.common.Result;
import com.xtoon.boot.common.util.CommonConstant;
import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.util.log.SysLog;
import com.xtoon.boot.org.application.PostApplicationService;
import com.xtoon.boot.org.application.PostQueryService;
import com.xtoon.boot.org.application.dto.PostDTO;
import com.xtoon.boot.util.validator.ValidatorUtils;
import com.xtoon.boot.util.validator.group.AddGroup;
import com.xtoon.boot.util.validator.group.UpdateGroup;
import com.xtoon.boot.web.org.command.PostCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 岗位Controller
 *
 * @author haoxin
 * @date 2021-05-06
 **/
@Api(tags = "岗位管理")
@RestController
@RequestMapping("/org/post")
public class PostController extends AbstractController {

    @Autowired
    private PostApplicationService postApplicationService;

    @Autowired
    private PostQueryService postQueryService;

    /**
     * 岗位分页查询
     */
    @ApiOperation("岗位分页查询")
    @GetMapping("/list")
    @RequiresPermissions("org:post:list")
    public Result list(@RequestParam Map<String, Object> params){
        Page page = postQueryService.queryPage(params);
        return Result.ok().put(CommonConstant.PAGE, page);
    }

    /**
     * 岗位列表
     */
    @ApiOperation("岗位列表")
    @GetMapping("/select")
    @RequiresPermissions("sys:post:select")
    public Result select(){
        List<PostDTO> list = postQueryService.listAll();
        return Result.ok().put("list", list);
    }

    /**
     * 岗位信息
     */
    @ApiOperation("岗位信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("org:post:info")
    public Result info(@PathVariable("id") String id){
        PostDTO post = postQueryService.getById(id);
        return Result.ok().put("post", post);
    }


    /**
     * 保存岗位
     */
    @ApiOperation("保存岗位")
    @SysLog("保存岗位")
    @PostMapping("/save")
    @RequiresPermissions("org:post:save")
    public Result save(@RequestBody PostCommand postCommand){
        ValidatorUtils.validateEntity(postCommand, AddGroup.class);
        postApplicationService.saveOrUpdate(new PostDTO(postCommand.getId(), postCommand.getPostCode(), postCommand.getPostName(),
                postCommand.getPostType(), postCommand.getOrderNum(), postCommand.getRemarks()));
        return Result.ok();
    }

    /**
     * 修改岗位
     */
    @ApiOperation("修改岗位")
    @SysLog("修改岗位")
    @PostMapping("/update")
    @RequiresPermissions("org:post:update")
    public Result update(@RequestBody PostCommand postCommand){
        ValidatorUtils.validateEntity(postCommand, UpdateGroup.class);
        postApplicationService.saveOrUpdate(new PostDTO(postCommand.getId(), postCommand.getPostCode(), postCommand.getPostName(),
                postCommand.getPostType(), postCommand.getOrderNum(), postCommand.getRemarks()));
        return Result.ok();
    }

    /**
     * 删除岗位
     */
    @ApiOperation("删除岗位")
    @SysLog("删除岗位")
    @PostMapping("/delete")
    @RequiresPermissions("org:post:delete")
    public Result delete(@RequestBody String[] postIds){
        postApplicationService.deleteBatch(Arrays.asList(postIds));
        return Result.ok();
    }

    /**
     * 禁用岗位
     */
    @ApiOperation("禁用岗位")
    @SysLog("禁用岗位")
    @PostMapping("/disable/{id}")
    @RequiresPermissions("org:post:disable")
    public Result disable(@PathVariable("id") String id){
        postApplicationService.disable(id);
        return Result.ok();
    }
}
