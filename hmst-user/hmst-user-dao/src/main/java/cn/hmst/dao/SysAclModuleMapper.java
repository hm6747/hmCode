package cn.hmst.dao;

import cn.hmst.pojo.SysAclModule;
import cn.hmst.pojo.SysAclModuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAclModuleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl_module
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int countByExample(SysAclModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl_module
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int deleteByExample(SysAclModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl_module
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl_module
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int insert(SysAclModule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl_module
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int insertSelective(SysAclModule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl_module
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    List<SysAclModule> selectByExample(SysAclModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl_module
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    SysAclModule selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl_module
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int updateByExampleSelective(@Param("record") SysAclModule record, @Param("example") SysAclModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl_module
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int updateByExample(@Param("record") SysAclModule record, @Param("example") SysAclModuleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl_module
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int updateByPrimaryKeySelective(SysAclModule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_acl_module
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int updateByPrimaryKey(SysAclModule record);
}