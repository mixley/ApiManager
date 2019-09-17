package cn.crap.model;

import java.util.Date;
/**
 * @Author: 李志锐
 * @CreateTime: 2019-09-11 14:56
 * @Description:
 **/
public class FileSave  {
    private String id;

    private String filename;

    private Date creatdate;

    private byte[] fileblob;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }

    public byte[] getFileblob() {
        return fileblob;
    }

    public void setFileblob(byte[] fileblob) {
        this.fileblob = fileblob;
    }
}