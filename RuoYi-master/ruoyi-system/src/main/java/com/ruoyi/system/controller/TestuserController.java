package com.ruoyi.system.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ruoyi.system.domain.Testrole;
import com.ruoyi.system.service.ITestroleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.UrlResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Testuser;
import com.ruoyi.system.service.ITestuserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户测试Controller
 *
 * @author ruoyi
 * @date 2022-12-20
 */
@Controller
@RequestMapping("/system/testuser")
public class TestuserController extends BaseController
{
    private String prefix = "system/testuser";
    @Autowired
    private Environment environment;

    @Autowired
    private ITestuserService testuserService;

    @Autowired
    private ITestroleService testroleService;

    @Resource
    private RedisTemplate redisTemplate;

    Testrole testrole;
    @RequiresPermissions("system:testuser:view")
    @GetMapping()
    public String testuser()
    {
        return prefix + "/testuser";
    }

    /**
     * 查询用户测试列表
     */
    @RequiresPermissions("system:testuser:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Testuser testuser)
    {
        startPage();
        List<Testuser> list = testuserService.selectTestuserList(testuser);

        String cacheKey = "users";

        ValueOperations<String, List<Testuser>> ops = redisTemplate.opsForValue();

       // 从缓存中获取数据
        if (redisTemplate.hasKey(cacheKey)) {
            return getDataTable(ops.get(cacheKey));
        }

        // 将查询结果放入缓存，设置过期时间为60秒
        ops.set(cacheKey, list, 60, TimeUnit.SECONDS);
        return getDataTable(list);
    }

    /**
     * 导出用户测试列表
     */
    @RequiresPermissions("system:testuser:export")
    @Log(title = "用户测试", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Testuser testuser)
    {
        List<Testuser> list = testuserService.selectTestuserList(testuser);
        ExcelUtil<Testuser> util = new ExcelUtil<Testuser>(Testuser.class);
        return util.exportExcel(list, "用户测试数据");
    }

    /**
     * 新增用户测试
     */
    @GetMapping("/add")
    public String add(ModelMap map)
    {
        map.put("role",testroleService.selectTestroleList(testrole));
        return"/test/viode";
    }

    /**
     * 新增保存用户测试
     */
    @RequiresPermissions("system:testuser:add")
    @Log(title = "用户测试", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Testuser testuser)
    {
        return toAjax(testuserService.insertTestuser(testuser));
    }

    /**
     * 修改用户测试
     */
    @RequiresPermissions("system:testuser:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Testuser testuser = testuserService.selectTestuserById(id);
        mmap.put("testuser", testuser);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户测试
     */
    @RequiresPermissions("system:testuser:edit")
    @Log(title = "用户测试", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Testuser testuser)
    {
        return toAjax(testuserService.updateTestuser(testuser));
    }

    /**
     * 删除用户测试
     */
    @RequiresPermissions("system:testuser:remove")
    @Log(title = "用户测试", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(testuserService.deleteTestuserByIds(ids));
    }


    /**
     * 获取视屏
     */
    @GetMapping("/paper/questions/getVideo")
    @ResponseBody
    public void getVideo(HttpServletRequest request, HttpServletResponse response)
    {
        //视频资源存储信息
        response.reset();
        //获取从那个字节开始读取文件
        String rangeString = request.getHeader("Range");
        try {
            //获取响应的输出流
            OutputStream outputStream = response.getOutputStream();
            //读取本地视频
            File file = new File("D:\\video\\folder\\filename.mp4");
            if(file.exists()){
                RandomAccessFile targetFile = new RandomAccessFile(file, "r");
                long fileLength = targetFile.length();
                //播放
                if(rangeString != null){
                    long range = Long.valueOf(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
                    //设置内容类型
                    response.setHeader("Content-Type", "video/mp4");
                    //设置此次相应返回的数据长度
                    response.setHeader("Content-Length", String.valueOf(fileLength - range));
                    //设置此次相应返回的数据范围
                    response.setHeader("Content-Range", "bytes "+range+"-"+(fileLength-1)+"/"+fileLength);
                    //返回码需要为206，而不是200
                    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                    //设定文件读取开始位置（以字节为单位）
                    targetFile.seek(range);
                }else {//下载
                    //设置响应头，把文件名字设置好
                    response.setHeader("Content-Disposition", "attachment; filename=video.mp4" );
                    //设置文件长度
                    response.setHeader("Content-Length", String.valueOf(fileLength));
                    //解决编码问题
                    response.setHeader("Content-Type","video/mp4");
                }
                byte[] cache = new byte[1024 * 300];
                int flag;
                while ((flag = targetFile.read(cache))!=-1){
                    outputStream.write(cache, 0, flag);
                }
            }else {
                String message = "file: not exists";
                //解决编码问题
                response.setHeader("Content-Type","application/json");
                outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {

        }
    }


}
