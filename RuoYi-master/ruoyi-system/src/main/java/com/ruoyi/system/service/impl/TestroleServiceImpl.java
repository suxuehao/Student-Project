package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TestroleMapper;
import com.ruoyi.system.domain.Testrole;
import com.ruoyi.system.service.ITestroleService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户角色Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-12-20
 */
@Service
public class TestroleServiceImpl implements ITestroleService 
{
    @Autowired
    private TestroleMapper testroleMapper;

    /**
     * 查询用户角色
     * 
     * @param rid 用户角色主键
     * @return 用户角色
     */
    @Override
    public Testrole selectTestroleByRid(Long rid)
    {
        return testroleMapper.selectTestroleByRid(rid);
    }

    /**
     * 查询用户角色列表
     * 
     * @param testrole 用户角色
     * @return 用户角色
     */
    @Override
    public List<Testrole> selectTestroleList(Testrole testrole)
    {
        return testroleMapper.selectTestroleList(testrole);
    }

    /**
     * 新增用户角色
     * 
     * @param testrole 用户角色
     * @return 结果
     */
    @Override
    public int insertTestrole(Testrole testrole)
    {
        return testroleMapper.insertTestrole(testrole);
    }

    /**
     * 修改用户角色
     * 
     * @param testrole 用户角色
     * @return 结果
     */
    @Override
    public int updateTestrole(Testrole testrole)
    {
        return testroleMapper.updateTestrole(testrole);
    }

    /**
     * 批量删除用户角色
     * 
     * @param rids 需要删除的用户角色主键
     * @return 结果
     */
    @Override
    public int deleteTestroleByRids(String rids)
    {
        return testroleMapper.deleteTestroleByRids(Convert.toStrArray(rids));
    }

    /**
     * 删除用户角色信息
     * 
     * @param rid 用户角色主键
     * @return 结果
     */
    @Override
    public int deleteTestroleByRid(Long rid)
    {
        return testroleMapper.deleteTestroleByRid(rid);
    }
}
