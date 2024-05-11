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
public class TransformedNode {
     private Node node;
    private String type;
    private Integer minLength;
    private Integer maxLength;
    private String desc;
    private String req;
    private List<TransformedNode>subNodes;

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }
    private Integer row;
    private Integer col;
    


    public TransformedNode(Node node, String type, Integer minLength, Integer maxLength, String desc, String req, List<TransformedNode>subNodes, Integer row, Integer col){
        this.node = node;
        this.type = type;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.desc = desc;
        this.req = req;
        this.subNodes = subNodes;
        this.row = row;
        this.col = col;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
