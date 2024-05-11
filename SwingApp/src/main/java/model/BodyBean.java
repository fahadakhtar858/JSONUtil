/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Fahad.Akhter
 */
public class BodyBean {
        private String key;

    private ArrayList<Node> value = new ArrayList<Node>();

    public ArrayList<Node> getValue() {
        return value;
    }

    public void setVaue(ArrayList<Node> nodeList) {
        this.value = nodeList;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
}
