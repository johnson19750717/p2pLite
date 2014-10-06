package cn.mobiledaily.p2plite.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by johnson on 14-10-6.
 */
@Document
public class IdCardFront {
    @Id
    @JsonIgnore
    private String id;
    private String name;
    private String cardNo;
    private String sex;
    private String folk;
    private String birthday;
    private String address;
    private Date uploadDate;
    private String source;

    public IdCardFront(String name, String cardNo, String sex, String folk, String birthday, String address, String source) {
        this.name = name;
        this.cardNo = cardNo;
        this.sex = sex;
        this.folk = folk;
        this.birthday = birthday;
        this.address = address;
        uploadDate = new Date();
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFolk() {
        return folk;
    }

    public void setFolk(String folk) {
        this.folk = folk;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
