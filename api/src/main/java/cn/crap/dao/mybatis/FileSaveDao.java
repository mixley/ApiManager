package cn.crap.dao.mybatis;

import cn.crap.model.FileSave;
import cn.crap.model.FileSaveCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @Author: 李志锐
 * @CreateTime: 2019-09-11 14:51
 * @Description:
 **/
public interface FileSaveDao extends BaseDao<FileSave>{
    long countByExample(FileSaveCriteria example);

    int deleteByExample(FileSaveCriteria example);

    int insert(FileSave record);

    int insertSelective(FileSave record);

    List<FileSave> selectByExampleWithBLOBs(FileSaveCriteria example);

    List<FileSave> selectByExample(FileSaveCriteria example);

    int updateByExampleSelective(@Param("record") FileSave record, @Param("example") FileSaveCriteria example);

    int updateByExampleWithBLOBs(@Param("record") FileSave record, @Param("example") FileSaveCriteria example);

    int updateByExample(@Param("record") FileSave record, @Param("example") FileSaveCriteria example);
}