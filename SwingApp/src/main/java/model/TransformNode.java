/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Fahad.Akhter
 */
public class TransformNode {
    private Node node;

    private TransformNodeAttributes nodeAttributes;




    public TransformNode(Node node, TransformNodeAttributes nodeAttributes, AdditionalQualifiers additionalQualifiers) {
        this.node = node;
        this.nodeAttributes = nodeAttributes;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public TransformNodeAttributes getNodeAttributes() {
        return nodeAttributes;
    }

    public void setNodeAttributes(TransformNodeAttributes nodeAttributes) {
        this.nodeAttributes = nodeAttributes;
    }
    
}
