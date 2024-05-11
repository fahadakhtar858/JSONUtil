/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author Fahad.Akhter
 */
public class TransformerNodeAttribute {
    private Integer minLength;
    private Integer maxLength;
    private String desc;
    private String req;
    private String type;
    private int lineNo = 0;
    private boolean markForDelete=false;
    private List<TransformedNode> subNodes; // Added field for sub-nodes
    

    public TransformerNodeAttribute() {
        this.minLength=0;
        this.maxLength=0;
        this.type="";
        this.desc="";
        this.req="";
    }

    public boolean isMarkForDelete() {
        return markForDelete;
    }

    public void setMarkForDelete(boolean markForDelete) {
        this.markForDelete = markForDelete;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }
        public List<TransformedNode> getSubNodes() {
        return subNodes;
    }

    public void setSubNodes(List<TransformedNode> subNodes) {
        this.subNodes = subNodes;
    }
    
}
