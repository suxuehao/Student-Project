package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户角色对象 testrole
 * 
 * @author ruoyi
 * @date 2022-12-20
 */
public class Testrole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 角色编号 */
    private Long rid;

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String rname;

    public void setRid(Long rid) 
    {
        this.rid = rid;
    }

    public Long getRid() 
    {
        return rid;
    }
    public void setRname(String rname) 
    {
        this.rname = rname;
    }

    public String getRname() 
    {
        return rname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("rid", getRid())
            .append("rname", getRname())
            .toString();
    }
}
