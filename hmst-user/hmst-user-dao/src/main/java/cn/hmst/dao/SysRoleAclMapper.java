package cn.hmst.dao;

import cn.hmst.pojo.SysRoleAcl;
import cn.hmst.pojo.SysRoleAclExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleAclMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_acl
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int countByExample(SysRoleAclExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_acl
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int deleteByExample(SysRoleAclExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_acl
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_acl
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int insert(SysRoleAcl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_acl
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int insertSelective(SysRoleAcl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_acl
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    List<SysRoleAcl> selectByExample(SysRoleAclExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_acl
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    SysRoleAcl selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_acl
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int updateByExampleSelective(@Param("record") SysRoleAcl record, @Param("example") SysRoleAclExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_acl
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int updateByExample(@Param("record") SysRoleAcl record, @Param("example") SysRoleAclExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_acl
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int updateByPrimaryKeySelective(SysRoleAcl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_acl
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int updateByPrimaryKey(SysRoleAcl record);
}