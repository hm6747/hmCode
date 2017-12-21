package cn.hmst.dao;

import cn.hmst.pojo.SysDept;
import cn.hmst.pojo.SysDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDeptMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int countByExample(SysDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int deleteByExample(SysDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int insert(SysDept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int insertSelective(SysDept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    List<SysDept> selectByExample(SysDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    SysDept selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int updateByExampleSelective(@Param("record") SysDept record, @Param("example") SysDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int updateByExample(@Param("record") SysDept record, @Param("example") SysDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int updateByPrimaryKeySelective(SysDept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbggenerated Thu Dec 21 23:20:45 CST 2017
     */
    int updateByPrimaryKey(SysDept record);
}