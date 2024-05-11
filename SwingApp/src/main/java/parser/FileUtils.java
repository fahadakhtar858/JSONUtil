/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.BodyBean;
import model.Node;
import model.TransformerNodeAttribute;

/**
 *
 * @author Fahad.Akhter
 */
public class FileUtils {
    
    public static String currentResource = "";
    //public static Map<String, Schema> definitions;
    public static String dirBasePath = "";

    public static List<String> jsonObjects;// = getJsonObjects();

    public static void printObjects(List<String> objectList){
        System.out.println("------------------------------------------List of Json Objects----------------------------------");
        int i = 0;
        for (String object:objectList){
            System.out.println(objectList.get(i));
            i++;
        }
    }


    public static void printResultInFile(String data, String fileName, String tag, boolean append) {
        PrintWriter printWriter = null;
        try {

            String directory = FileUtils.dirBasePath + tag;

            File dirFile = new File(directory);

            Path path = Paths.get(dirFile.getPath());

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            File file = new File(fileName);//(dirFile.getPath() + "\\" + fileName);

            //if (!file.exists()) {
            printWriter = new PrintWriter(new FileWriter(file, append));
            printWriter.println(data);
            //}

            //	file.create
            // true if the directory was created, false otherwise
            //	if (file.mkdirs()){
            //	System.out.println("Directory Created");
            //	}
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
    public static ArrayList<String> readFile(String readFileName) {
        ArrayList<String> dataArray = new ArrayList<>();
        FileReader fr;
        try {
            fr = new FileReader(new File(readFileName));
            BufferedReader br = new BufferedReader(fr);
            String strCurrentLine;

            while ((strCurrentLine = br.readLine()) != null) {
                dataArray.add(strCurrentLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataArray;
    }
    public static String generateJson(Object obj) {
        String strJson = null;
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            Gson gson = builder.setPrettyPrinting().create();

            strJson = gson.toJson(obj);
        } catch (Exception e) {
            System.err.println();
        }

        return strJson;

    }
    public static String fetchConditional(String key, String tag) {

        try {
            Node selectedConditionNode = null;

            ArrayList<String> conditionsList = FileUtils.readFile(FileUtils.dirBasePath + "ConditionAppendix.json");
            StringBuilder conditionBuilder = new StringBuilder("");
            for (String con : conditionsList) {
                conditionBuilder = conditionBuilder.append(con);
            }

            Gson gson = new Gson();
            BodyBean[] bodyBeans = gson.fromJson(conditionBuilder.toString(), BodyBean[].class);

            for (BodyBean bodyBean : bodyBeans) {
                if (bodyBean.getKey().equals("Conditions")) {
                    List<Node> conditionalList = (List<Node>) bodyBean.getValue();
                    for (Node node : conditionalList) {
                        if (node.getKey().equalsIgnoreCase(tag)) {
                            selectedConditionNode = node;
                            break;
                        }
                    }
                }
            }

            if (selectedConditionNode != null) {
                if (selectedConditionNode.getValue().toString().contains(key)) {
                    return "Conditional";
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "false";
    }
    public static String fetchAppendix(String key, String tag) {
        try {
            Node selectedAppendixNode = null;

            ArrayList<String> appendixList = FileUtils.readFile(FileUtils.dirBasePath + "ConditionAppendix.json");
            StringBuilder appendixBuilder = new StringBuilder("");
            for (String con : appendixList) {
                appendixBuilder = appendixBuilder.append(con);
            }

            Gson gson = new Gson();
            BodyBean[] bodyBeans = gson.fromJson(appendixBuilder.toString(), BodyBean[].class);

            for (BodyBean bodyBean : bodyBeans) {
                if (bodyBean.getKey().equals("Appendix")) {
                    List<Node> appendList = (List<Node>) bodyBean.getValue();
                    for (Node node : appendList) {
                        if (tag.contains(node.getKey())) {
                            selectedAppendixNode = node;
                            break;
                        }
                    }
                }
            }

            if (selectedAppendixNode != null) {
                String[] rowAppends = selectedAppendixNode.getValue().toString().split(",");
                for (String row : rowAppends) {
                    if (row.contains(key)) {
                        String[] splitArrowAppends = row.split("::");
                        for (String arrowAppend : splitArrowAppends) {
                            if (arrowAppend.contains("Appendix")) {
                                return arrowAppend;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "";
    }
    public static String multipleLineToSingleLine(String str) {
        Scanner scanner = new Scanner(str);

        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break; // Stop reading input if an empty line is entered
            }
            sb.append(line.trim()); // Append trimmed line to the StringBuilder
        }

        // Convert multiple lines to a single line
        String singleLine = sb.toString().replaceAll("\\s+", " ");

        // Print the result
        System.out.println("Multiple lines converted to a single line:");
        System.out.println(singleLine);

        return singleLine;
    }

    public static String findByApiId(String tag) {
        String csvFile = FileUtils.dirBasePath + "\\" + "Data.csv";
        String line = "";
        String tagKey = "000";
        String cvsSplitBy = ",";
        Map<String, String> keyValueMap = new HashMap<>();

        try ( BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                keyValueMap.put(data[0], data[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, String> entry : keyValueMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            //System.out.println("Key: " + key + ", Value: " + value);

            if (value.toLowerCase().contains(tag.toLowerCase())) {
                System.out.println(key + "-matched-" + tag);
                tagKey = key;
                break;
            }
        }
        return tagKey;
    }

    public static TransformerNodeAttribute attributePickerRequestProcess(String key, int lineNumber, String fileBodyExtract) {
        String filePath = fileBodyExtract;//FileUtils.dirBasePath + "\\" + fileBodyExtract; // Replace with the path to your file
        TransformerNodeAttribute trnNodeAttrib = null;
        try {
            trnNodeAttrib = findLineNumber(filePath, key, lineNumber);
            if (trnNodeAttrib != null) {
                System.out.println(trnNodeAttrib);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trnNodeAttrib;
    }


    public static TransformerNodeAttribute findLineNumber(String filePath, String key, int lineNumber) throws IOException {
        TransformerNodeAttribute trnNodeAttrib = new TransformerNodeAttribute();
        //printObjects(jsonObjects);
        List<String> jsonObjects = getJsonObjects();
        String curObject = jsonObjects.get(0);
        String nextObject = "";
        if(jsonObjects.size()>1) {
            nextObject = jsonObjects.get(1);
        }else {
            nextObject = "";
        }

        try ( BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNo = 0;

            outerLoop:
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("null", "\"\"");
                line = line.replaceAll("\"\"\"\"", "\"\"");
                lineNo++;
                if (lineNo <= lineNumber) {
                    continue;
                }
                if(key.equals(curObject) && line.contains(key)){
                    if(line.contains("*")&& !line.contains("**")){
                        trnNodeAttrib.setReq("true");
                    }if(line.contains("description")){
                        String description = br.readLine();
                        lineNo++;
                        trnNodeAttrib.setDesc(description);
                    }
                    if(line.contains("object")||line.contains("string")||line.contains("array")||line.contains("boolean")
                    ||line.contains("integer")||line.contains("number")||line.contains("date")||line.contains("date-time")){
                        if(line.split("\t").length>1){
                            trnNodeAttrib.setType(line.split("\t")[1].trim());
                        }

                    }
                    if(line.contains(nextObject)&& !nextObject.equals("")){
                        lineNo--;
                        jsonObjects.remove(0);
                        setJsonObjects(jsonObjects);
                        break outerLoop;
                    }
                    while ((line = br.readLine())!=null){
                        line = line.replaceAll("null", "\"\"");
                        line = line.replaceAll("\"\"\"\"", "\"\"");
                        lineNo++;
                        if(line.contains(nextObject)&& !nextObject.equals("")){
                            lineNo--;
                            jsonObjects.remove(0);
                            setJsonObjects(jsonObjects);
                            break outerLoop;
                        }
                        if(line.contains("*")&& !line.contains("**")){
                            trnNodeAttrib.setReq("true");
                        }
                        if(line.contains("description")){
                            String description = br.readLine();
                            lineNo++;
                            trnNodeAttrib.setDesc(description);
                        }
                        if(line.contains("object")||line.contains("string")||line.contains("array")||line.contains("boolean")
                                ||line.contains("integer")||line.contains("number")||line.contains("date")||line.contains("date-time")){
                            if(line.split("\t").length > 1){
                                trnNodeAttrib.setType(line.split("\t")[1].trim());
                            }

                        }
                        if(line.contains("minLength")){
                            int startIndex = line.indexOf(":");
                            int lastIndex = line.length();//line.lastIndexOf(",");
                            String minLengthValue = line.substring(startIndex + 1, lastIndex);
                            if (minLengthValue != null && minLengthValue.length() > 0 && (!minLengthValue.isEmpty())) {
                                trnNodeAttrib.setMinLength(Integer.valueOf(minLengthValue.trim()));
                            }
//                            trnNodeAttrib.setLineNo(lineNo);
                            continue;
                        }if(line.contains("maxLength")){
                            int startIndex = line.indexOf(":");
                            int lastIndex = line.length();//line.lastIndexOf(",");
                            String maxLengthValue = line.substring(startIndex + 1, lastIndex);
                            if (maxLengthValue != null && maxLengthValue.length() > 0 && (!maxLengthValue.isEmpty())) {
                                trnNodeAttrib.setMaxLength(Integer.valueOf(maxLengthValue.trim()));
                            }
//                            trnNodeAttrib.setLineNo(lineNo);
                            continue;
                        }
                        if(!line.equals("")&&!line.contains("description")&&!line.contains("minLength")&&!line.contains("maxLength")&&!line.contains("{")&& !line.contains("}")&&!line.contains("[")&&!line.contains("]")&&!line.contains("oneOf")){
                            trnNodeAttrib.setDesc(line);
                        }
                    }
                }
            }
            trnNodeAttrib.setLineNo(lineNo);
        }
        //trnNodeAttrib.setLineNo(l);
        return trnNodeAttrib; // Entry not found in the file
    }
    
    private static void getJsonObj(JsonElement jsonElement){
        if(jsonElement.isJsonObject()){
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            readObjectAttrib(jsonObject);
        } else if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();

        }
    }
    private static void readObjectAttrib(JsonObject jsonObject){
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()){
            String key = entry.getKey();
            System.out.println(key);
            if(jsonObjects == null){
                jsonObjects = new ArrayList<>();
                jsonObjects.add(key);
            }else{
                jsonObjects.add(key);
            }
            
            JsonElement value = entry.getValue();
            if(value.isJsonArray()||value.isJsonObject()){
                getJsonObj(value);
            }
        }
    }
    private static void readJsonArray(JsonArray jsonArray){
        for(JsonElement element : jsonArray){
            if(element.isJsonObject()){
                JsonObject object = element.getAsJsonObject();
                readObjectAttrib(object);
            }else if(element.isJsonArray()){
                JsonArray nestedArray = element.getAsJsonArray();
                readJsonArray(nestedArray);
            }
        }
    }

    public static void setJsonObjects(List<String> jsonObjects) {
        jsonObjects = jsonObjects;
    }
    
    public static List<String> getJsonObjects() {
        return jsonObjects;
    }
    public static void readJsonObjects(String path){
        String jsonStr = "";
        try{
            List<String> fileContent = FileUtils.readFile(path);
            for(String str:fileContent){
                jsonStr = jsonStr + str;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try{
            int lineNumber = 0;
            JsonElement jsonElement = JsonParser.parseString(jsonStr);
            getJsonObj(jsonElement);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
        
        
        
    
    
}
