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
public class JsonObject {
     String name;
    private List<TransformNode> transformNodeList;
    private AdditionalQualifiers additionalQualifiers;

    public AdditionalQualifiers getAdditionalQualifiers() {
        return additionalQualifiers;
    }

    public void setAdditionalQualifiers(AdditionalQualifiers additionalQualifiers) {
        this.additionalQualifiers = additionalQualifiers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonObject() {

        this.name = "";
        this.transformNodeList = new ArrayList<>();
    }
    public List<TransformNode> getTransformNodeList() {
        return transformNodeList;
    }

    public void setTransformNodeList(List<TransformNode> transformNodeList) {
        this.transformNodeList = transformNodeList;
    }
    
}
