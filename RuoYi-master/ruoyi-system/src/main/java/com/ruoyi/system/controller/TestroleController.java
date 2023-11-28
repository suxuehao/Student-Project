package com.ruoyi.system.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ruoyi.system.domain.Testuser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Testrole;
import com.ruoyi.system.service.ITestroleService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.annotation.Resource;

/**
 * 用户角色Controller
 * 
 * @author ruoyi
 * @date 2022-12-20
 */
@Controller
@RequestMapping("/system/testrole")
public class TestroleController extends BaseController
{
    private String prefix = "system/testrole";

    @Autowired
    private ITestroleService testroleService;

    @Resource
    private RedisTemplate redisTemplate;

    @RequiresPermissions("system:testrole:view")
    @GetMapping()
    public String testrole()
    {
        return prefix + "/testrole";
    }

    /**
     * 查询用户角色列表
     */
    @RequiresPermissions("system:testrole:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Testrole testrole)
    {
        startPage();
        List<Testrole> list = testroleService.selectTestroleList(testrole);
        String cacheKey = "roles";

        ValueOperations<String, List<Testrole>> ops = redisTemplate.opsForValue();

        // 从缓存中获取数据
        if (redisTemplate.hasKey(cacheKey)) {
            return getDataTable(ops.get(cacheKey));
        }

        // 将查询结果放入缓存，设置过期时间为60秒
        ops.set(cacheKey, list, 60, TimeUnit.SECONDS);
        return getDataTable(list);
    }

    /**
     * 导出用户角色列表
     */
    @RequiresPermissions("system:testrole:export")
    @Log(title = "用户角色", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Testrole testrole)
    {
        List<Testrole> list = testroleService.selectTestroleList(testrole);
        ExcelUtil<Testrole> util = new ExcelUtil<Testrole>(Testrole.class);
        return util.exportExcel(list, "用户角色数据");
    }

    /**
     * 新增用户角色
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户角色
     */
    @RequiresPermissions("system:testrole:add")
    @Log(title = "用户角色", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Testrole testrole)
    {
        return toAjax(testroleService.insertTestrole(testrole));
    }

    /**
     * 修改用户角色
     */
    @RequiresPermissions("system:testrole:edit")
    @GetMapping("/edit/{rid}")
    public String edit(@PathVariable("rid") Long rid, ModelMap mmap)
    {
        Testrole testrole = testroleService.selectTestroleByRid(rid);
        mmap.put("testrole", testrole);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户角色
     */
    @RequiresPermissions("system:testrole:edit")
    @Log(title = "用户角色", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Testrole testrole)
    {
        return toAjax(testroleService.updateTestrole(testrole));
    }

    /**
     * 删除用户角色
     */
    @RequiresPermissions("system:testrole:remove")
    @Log(title = "用户角色", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(testroleService.deleteTestroleByRids(ids));
    }
}
