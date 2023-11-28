package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Testrole;

/**
 * 用户角色Service接口
 * 
 * @author ruoyi
 * @date 2022-12-20
 */
public interface ITestroleService 
{
    /**
     * 查询用户角色
     * 
     * @param rid 用户角色主键
     * @return 用户角色
     */
    public Testrole selectTestroleByRid(Long rid);

    /**
     * 查询用户角色列表
     * 
     * @param testrole 用户角色
     * @return 用户角色集合
     */
    public List<Testrole> selectTestroleList(Testrole testrole);

    /**
     * 新增用户角色
     * 
     * @param testrole 用户角色
     * @return 结果
     */
    public int insertTestrole(Testrole testrole);

    /**
     * 修改用户角色
     * 
     * @param testrole 用户角色
     * @return 结果
     */
    public int updateTestrole(Testrole testrole);

    /**
     * 批量删除用户角色
     * 
     * @param rids 需要删除的用户角色主键集合
     * @return 结果
     */
    public int deleteTestroleByRids(String rids);

    /**
     * 删除用户角色信息
     * 
     * @param rid 用户角色主键
     * @return 结果
     */
    public int deleteTestroleByRid(Long rid);
}
