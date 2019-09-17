package cn.crap.service;

import cn.crap.dao.mybatis.FileSaveDao;
import cn.crap.enu.TableId;
import cn.crap.framework.IdGenerator;
import cn.crap.model.FileSave;
import cn.crap.model.FileSaveCriteria;
import cn.crap.utils.TableField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * @Author: 李志锐 
 * @CreateTime: 2019-09-11 14:49
 * @Description: 
 * Automatic generation by tools
 * service
 **/
@Service
public class FileSaveService {
    @Autowired
    private FileSaveDao fileSaveDao;

    public List<FileSave> selectByExample(FileSaveCriteria example) {
        return fileSaveDao.selectByExample(example);
    }

    public List<FileSave> selectByExampleWithBLOBs(FileSaveCriteria example) {
        return fileSaveDao.selectByExampleWithBLOBs(example);
    }

    public long countByExample(FileSaveCriteria example) {
        return fileSaveDao.countByExample(example);
    }

    public FileSave selectByPrimaryKey(String id) {
        if (id == null){
            return null;
        }
        return fileSaveDao.selectByPrimaryKey(id);
    }

    public boolean insert(FileSave model) {
        if (model == null) {
            return false;
        }
        model.setId(IdGenerator.getId(TableId.FILE_SAVE));
        return fileSaveDao.insertSelective(model) > 0;
    }

    public boolean update(FileSave model) {
        if (model == null) {
            return false;
        }
        return fileSaveDao.updateByPrimaryKeySelective(model) > 0 ? true : false;
    }

    public boolean delete(String id) {
        Assert.notNull(id, "id can't be null");
        return fileSaveDao.deleteByPrimaryKey(id) > 0 ? true : false;
    }

}
