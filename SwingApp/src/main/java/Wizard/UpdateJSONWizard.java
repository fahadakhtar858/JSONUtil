package Wizard;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import model.TransformedNode;
import model.TransformerNodeAttribute;
import parser.FileUtils;
import parser.JsonTransformer;


/**
 *
 * @author Fahad.Akhter
 */
public class UpdateJSONWizard extends JFrame {
    private JPanel contentPanel;
    private JPanel[] panels = new JPanel[2];
    private JButton nextButton;
    private JButton cancelButton;
    private JButton previousButton;
    private StepTwoPanel stepTwoPanel;
    private String selectedJsonFilePath;
    private String selectedSchemaFilePath;
    private final String outputFilePath = "D:\\output.json";
    StepOnePanel stepOnePanel;
    
    HashMap<String, TransformerNodeAttribute> hmTranformerNodeAttrib = new HashMap<>();
    private static List <TransformedNode> transformedNodes = new ArrayList<>();
    
    private static List<String> jsonObjects = new ArrayList<>();
    
        
    int currentPanelIndex = 0 ;
    
    
    
    public UpdateJSONWizard(){
        setTitle("Update JSON");
        //setSize(700,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        panels[0] = new StepOnePanel();
        panels[0].setName("StepOnePanel");  // Set the name for the first panel
        panels[1] = new StepTwoPanel();
        panels[1].setName("StepTwoPanel");
    
        contentPanel = new JPanel(new CardLayout());
        for (JPanel panel : panels) {
            contentPanel.add(panel, panel.getName());
        }
        
            // Add content panel to frame
        add(contentPanel, BorderLayout.CENTER);

        // Create navigation buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        nextButton = new JButton("Next");
        cancelButton = new JButton("Cancel");
        previousButton = new JButton("Previous");
        // Disable previous button by default
        previousButton.setEnabled(false);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //showPreviousPanel();
                dispose();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPanelIndex == panels.length - 1) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    List<TransformedNode> updatedNodes = stepTwoPanel.getUpdatedNodes();
                    String transformedJsonStr = gson.toJson(updatedNodes);
                    System.out.println(transformedJsonStr);

                    FileUtils.printResultInFile(transformedJsonStr, outputFilePath, "", false);
                }else{
                    stepOnePanel = (StepOnePanel) panels[0];
                    selectedJsonFilePath = stepOnePanel.getSelectedJsonFilePath();
                    selectedSchemaFilePath = stepOnePanel.getselectedSchemaFilePath();
                    FileUtils.readJsonObjects(selectedJsonFilePath);
                    jsonObjects = FileUtils.getJsonObjects();
                    JsonTransformer jsonTransformer = new JsonTransformer();
                    jsonTransformer.executeParserSampleRequestToRequestBody(selectedJsonFilePath, outputFilePath, selectedSchemaFilePath);
                    hmTranformerNodeAttrib = jsonTransformer.getJsonMap();
                    transformedNodes = jsonTransformer.getTransformedNodes();
                    
                    stepTwoPanel = (StepTwoPanel) panels[1];
                    //stepTwoPanel.populateTree(selectedJsonFilePath);
                    //stepTwoPanel.populateTree(hmTranformerNodeAttrib);
                    stepTwoPanel.populateTree(transformedNodes);
                }
                
                showNextPanel();
                previousButton.setEnabled(true);
                
                
            }
        });
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                showPreviousPanel();
                previousButton.setEnabled(false);
            }
        });
        
        buttonPanel.add(previousButton);    
        buttonPanel.add(nextButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);
        Dimension preferredSize1 = panels[0].getPreferredSize();
        Dimension preferredSize2 = panels[1].getPreferredSize();
        
        int maxWidth = Math.max((int)preferredSize1.getWidth(), (int)preferredSize2.getWidth()) + 10;
        int totalHeight = Math.max((int)preferredSize1.getHeight(),(int)preferredSize2.getHeight()) + 65; // Adding some extra height for the button panel

        setSize(maxWidth, totalHeight);

        showPanel(0); // Show the first panel by default
    }

    private JPanel createPanel1() {
        JPanel panel = new JPanel();
        panel.setName("Panel1");
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("This is Panel 1");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createPanel2() {
        JPanel panel = new JPanel();
        panel.setName("Panel2");
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("This is Panel 2");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        return panel;
    }

    private void showPanel(int index) {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, panels[index].getName());
        currentPanelIndex = index;
        
        // Change button text to "Finish" when displaying the second panel
        if (currentPanelIndex == panels.length - 1) {
            nextButton.setText("Finish");
        } else {
            nextButton.setText("Next");
        }
    }

    private void showNextPanel() {
        if (currentPanelIndex < panels.length - 1) {
            showPanel(currentPanelIndex + 1);
        } else {
            JOptionPane.showMessageDialog(this, "JSON Updated Successfully!");
            dispose(); // Close the wizard
        }
    }

    private void showPreviousPanel() {
        if (currentPanelIndex > 0) {
            showPanel(currentPanelIndex - 1);
        }
    }
    
    
    
}
