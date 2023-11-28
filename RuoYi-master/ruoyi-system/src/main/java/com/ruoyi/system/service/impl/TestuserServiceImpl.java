package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TestuserMapper;
import com.ruoyi.system.domain.Testuser;
import com.ruoyi.system.service.ITestuserService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户测试Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-20
 */
@Service
public class TestuserServiceImpl implements ITestuserService
{
    @Autowired
    private TestuserMapper testuserMapper;

    /**
     * 查询用户测试
     *
     * @param id 用户测试主键
     * @return 用户测试
     */
    @Override
    public Testuser selectTestuserById(String id)
    {
        return testuserMapper.selectTestuserById(id);
    }

    /**
     * 查询用户测试列表
     *
     * @param testuser 用户测试
     * @return 用户测试
     */
    @Override
    public List<Testuser> selectTestuserList(Testuser testuser)
    {

        return testuserMapper.selectTestuserList(testuser);
    }

    /**
     * 新增用户测试
     *
     * @param testuser 用户测试
     * @return 结果
     */
    @Override
    public int insertTestuser(Testuser testuser)
    {
        return testuserMapper.insertTestuser(testuser);
    }

    /**
     * 修改用户测试
     *
     * @param testuser 用户测试
     * @return 结果
     */
    @Override
    public int updateTestuser(Testuser testuser)
    {
        return testuserMapper.updateTestuser(testuser);
    }

    /**
     * 批量删除用户测试
     *
     * @param ids 需要删除的用户测试主键
     * @return 结果
     */
    @Override
    public int deleteTestuserByIds(String ids)
    {
        return testuserMapper.deleteTestuserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户测试信息
     *
     * @param id 用户测试主键
     * @return 结果
     */
    @Override
    public int deleteTestuserById(String id)
    {
        return testuserMapper.deleteTestuserById(id);
    }
}
