/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Fahad.Akhter
 */
public class TransformNodeAttributes {
     private int maxLength;
    private int minLength;
    private int lineNum;
    private String type;
    private String desc;
    private Boolean isReq;
    private Boolean markForDelete;




    public TransformNodeAttributes() {
        this.desc="";
        this.type="";
        this.maxLength=0;
        this.minLength=0;
        this.isReq=false;
        this.markForDelete=false;

    }
    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getReq() {
        return isReq;
    }

    public void setReq(Boolean req) {
        isReq = req;
    }

    public Boolean getMarkForDelete() {
        return markForDelete;
    }

    public void setMarkForDelete(Boolean markForDelete) {
        this.markForDelete = markForDelete;
    }
    
}
