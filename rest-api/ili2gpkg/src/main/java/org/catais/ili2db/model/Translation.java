package org.catais.ili2db.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Translation {
	
	private Integer id;
	
	private String filePath;
	
	private Date uploadDate;
	
	private String downloadUrl;
	
	public Translation() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public Date getUploadDate() {
		return uploadDate;
	}
	
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	public String getDownloadUrl() {
		return downloadUrl;
	}
	
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	
    @Override
    public String toString() {
        return new StringBuffer("ID : ").append(this.id)
        		.append("\nFile Path : ").append(this.filePath)
        		.append("\nUpload Date : ").append(this.uploadDate)
        		.append("\nDownload URL : ").append(this.downloadUrl).toString();
    }

}
