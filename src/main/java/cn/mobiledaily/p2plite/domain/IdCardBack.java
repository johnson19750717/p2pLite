package cn.mobiledaily.p2plite.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by johnson on 14-10-6.
 */
@Document
public class IdCardBack {
    private String id;
    private String issueAuthority;
    private String validPeriod;
    private Date uploadDate;
    private String source;

    public String getId() {
        return id;
    }

    public IdCardBack(String issueAuthority, String validPeriod, String source) {
        this.issueAuthority = issueAuthority;
        this.validPeriod = validPeriod;
        uploadDate = new Date();
        this.source = source;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIssueAuthority() {
        return issueAuthority;
    }

    public void setIssueAuthority(String issueAuthority) {
        this.issueAuthority = issueAuthority;
    }

    public String getValidPeriod() {
        return validPeriod;
    }

    public void setValidPeriod(String validPeriod) {
        this.validPeriod = validPeriod;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
