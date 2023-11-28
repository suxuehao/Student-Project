package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Testuser;

/**
 * 用户测试Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-20
 */
public interface TestuserMapper
{
    /**
     * 查询用户测试
     *
     * @param id 用户测试主键
     * @return 用户测试
     */
    public Testuser selectTestuserById(String id);

    /**
     * 查询用户测试列表
     *
     * @param testuser 用户测试
     * @return 用户测试集合
     */
    public List<Testuser> selectTestuserList(Testuser testuser);

    /**
     * 新增用户测试
     *
     * @param testuser 用户测试
     * @return 结果
     */
    public int insertTestuser(Testuser testuser);

    /**
     * 修改用户测试
     *
     * @param testuser 用户测试
     * @return 结果
     */
    public int updateTestuser(Testuser testuser);

    /**
     * 删除用户测试
     *
     * @param id 用户测试主键
     * @return 结果
     */
    public int deleteTestuserById(String id);

    /**
     * 批量删除用户测试
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTestuserByIds(String[] ids);
}
