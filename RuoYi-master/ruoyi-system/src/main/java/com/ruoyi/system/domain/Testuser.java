package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.poi.ss.formula.functions.T;

/**
 * 用户测试对象 testuser
 *
 * @author ruoyi
 * @date 2022-12-20
 */
public class Testuser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private String id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别:0:男，1：女 */
    @Excel(name = "性别")
    private String sex;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 角色 */
    @Excel(name = "角色")
    private Long role;

    /** 用户角色表对应的角色表 */
    private Testrole testrole;

    public Testrole getTestrole() {
        return testrole;
    }

    public void setTestrole(Testrole testrole) {
        this.testrole = testrole;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getSex()
    {
        return sex;
    }
    public void setAge(Long age)
    {
        this.age = age;
    }

    public Long getAge()
    {
        return age;
    }
    public void setRole(Long role)
    {
        this.role = role;
    }

    public Long getRole()
    {
        return role;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("sex", getSex())
                .append("age", getAge())
                .append("role", getRole())
                .toString();
    }
}
