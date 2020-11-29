package cn.itsqh.ydma.dao;

import cn.itsqh.ydma.entity.Direction;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface DirectionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table direction
     *
     * @mbg.generated Mon Jul 08 11:29:23 CST 2019
     */
    @Delete({
        "delete from direction",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table direction
     *
     * @mbg.generated Mon Jul 08 11:29:23 CST 2019
     */
    @Insert({
        "insert into direction (name)",
        "values (#{name,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Direction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table direction
     *
     * @mbg.generated Mon Jul 08 11:29:23 CST 2019
     */
    @InsertProvider(type=DirectionSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Direction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table direction
     *
     * @mbg.generated Mon Jul 08 11:29:23 CST 2019
     */
    @Select({
        "select",
        "id, name",
        "from direction",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    Direction selectByPrimaryKey(Integer id);
    
    /**
     * 	查询所有的学科
     * @return
     */
    @Select({
        "select",
        "id, name",
        "from direction"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(property="subjects",column="id",
        	many=@Many(select="cn.itsqh.ydma.dao.SubjectMapper.selectByDirectionId"))
    })
    List<Direction> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table direction
     *
     * @mbg.generated Mon Jul 08 11:29:23 CST 2019
     */
    @UpdateProvider(type=DirectionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Direction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table direction
     *
     * @mbg.generated Mon Jul 08 11:29:23 CST 2019
     */
    @Update({
        "update direction",
        "set name = #{name,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Direction record);
}