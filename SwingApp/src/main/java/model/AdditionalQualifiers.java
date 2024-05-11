/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fahad.Akhter
 */
public class AdditionalQualifiers {
    List<TransformNode> transformNodeList;

    public AdditionalQualifiers() {
        transformNodeList = new ArrayList<>();
    }

    public List<TransformNode> getTransformNodeList() {
        return transformNodeList;
    }

    public void setTransformNodeList(List<TransformNode> transformNodeList) {
        this.transformNodeList = transformNodeList;
    }
    
}
