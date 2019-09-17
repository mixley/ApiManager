package cn.crap.dto;

import java.util.Date;

/**
 * Automatic generation by tools
 * dto: exchange data with view
 */
public class FilesaveDto {
	private String id;
	private String filename;
	private byte[] fileblob;
	private Date creatdate;

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}

	public void setFilename(String filename){
		this.filename=filename;
	}
	public String getFilename(){
		return filename;
	}

	public void setFileblob(byte[] fileblob){
		this.fileblob=fileblob;
	}
	public byte[] getFileblob(){
		return fileblob;
	}

	public void setCreatdate(Date creatdate){
		this.creatdate=creatdate;
	}
	public Date getCreatdate(){
		return creatdate;
	}


}
