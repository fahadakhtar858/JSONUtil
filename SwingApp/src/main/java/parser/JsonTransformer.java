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
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import model.Node;
import model.TransformedNode;
import model.TransformerNodeAttribute;

/**
 *
 * @author Fahad.Akhter
 */
public class JsonTransformer {
    
    String specPath="";
    //final String schemaPath = "src/main/resources/SwaggerSchema.txt";
    HashMap<String, TransformerNodeAttribute> hmTranformerNodeAttrib = new HashMap<>();
    private static List<TransformedNode> transformedNodes = new ArrayList<>();
    
    public HashMap<String, TransformerNodeAttribute> getJsonMap(){
        return hmTranformerNodeAttrib;
    }
    public static List<TransformedNode> getTransformedNodes(){
        return transformedNodes;
    }




    public void executeParserSampleRequestToRequestBody(String filePath, String request, String schemaPath) {
        List<String> schemaContent = FileUtils.readFile(schemaPath);//readFile(schemaPath);


        String jsonStr="";

        try {
            List<String> fileContent = FileUtils.readFile(filePath);

            for(String str: fileContent) {
                jsonStr = jsonStr + str;
            }

            //JsonArray transformedJson = transformJson(new JsonArray(jsonStr.));
            //System.out.println(transformedJson.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        jsonStr = jsonStr.replaceAll("null", "\"\"");
        jsonStr = jsonStr.replaceAll("\"\"\"\"", "\"\"");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(jsonStr);
        transformedNodes = transformJson(jsonElement,0,schemaPath);///Use This one"FileBodyExtractRequest.json"
//        String transformedJsonStr = gson.toJson(transformedNodes);
//        System.out.println(transformedJsonStr);
//
//        FileUtils.printResultInFile(transformedJsonStr, request, "", false);
    }

    public void executeParserSampleResponseToResponseBody(String filePath, String response) {

        String jsonStr="";
        try {
            List<String> fileContent = FileUtils.readFile(filePath);

            for(String str: fileContent) {
                jsonStr = jsonStr + str;
            }

            //JsonArray transformedJson = transformJson(new JsonArray(jsonStr.));
            //System.out.println(transformedJson.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        jsonStr = jsonStr.replaceAll("null", "\"\"");
        jsonStr = jsonStr.replaceAll("\"\"\"\"", "\"\"");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(jsonStr);
        List<TransformedNode> transformedNodes = transformJson(jsonElement,0,"FileBodyExtractResponse.json");
        String transformedJsonStr = gson.toJson(transformedNodes);
        System.out.println(transformedJsonStr);

        FileUtils.printResultInFile(transformedJsonStr, response, "", false);

    }


    public void executeParser(String filePath, String tag, List<String>jsonObjects) throws Exception {

        String jsonStr="";
        try {
            List<String> fileContent = FileUtils.readFile(filePath);

            for(String str: fileContent) {
                jsonStr = jsonStr + str;
            }
            //JsonArray transformedJson = transformJson(new JsonArray(jsonStr.));
            //System.out.println(transformedJson.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            int lineNo = 0;
            JsonElement jsonElement = JsonParser.parseString(jsonStr);
            List<TransformedNode> transformedNodes = transformJson(jsonElement, lineNo,"");
            String transformedJsonStr = gson.toJson(transformedNodes);
            System.out.println(transformedJsonStr);

            if (filePath.contains("SampleRequest")) {
                FileUtils.printResultInFile(transformedJsonStr, "src/main/resources/SwaggerSchema.txt", tag, false);//RequestBody.json
            }

            if (filePath.contains("SampleResponse")) {
                FileUtils.printResultInFile(transformedJsonStr, "Responsebody.json", tag, false);
            }


//            if (true){
//                try {
//                    jsonStr="";
//                    List<String> fileContent = FileUtils.readFile(FileUtils.dirBasePath+"\\SampleRequest.json");
//                    for (String str : fileContent) {
//                        jsonStr = jsonStr + str;
//                    }
//                    String jsonExample = FileUtils.multipleLineToSingleLine(jsonStr);
//
//                    int api_id = Integer.parseInt("0000");
//                    String query = "UPDATE `apg_apidetails` SET `json_sample_request` = '" + jsonExample + "' WHERE (`api_id` in ( " + api_id + " ));";
//                    FileUtils.printResultInFile(query, "Query--Req.txt", "tag", Boolean.TRUE);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//
//                try {
//                    jsonStr="";
//                    List<String> fileContent = FileUtils.readFile(FileUtils.dirBasePath+"\\SampleResponse.json");
//                    for (String str : fileContent) {
//                        jsonStr = jsonStr + str;
//                    }
//                    String jsonExample = FileUtils.multipleLineToSingleLine(jsonStr);
//
//                    int api_id = Integer.parseInt("0000");
//                    String query = "UPDATE `apg_apidetails` SET `json_sample_response` = '" + jsonExample + "' WHERE (`api_id` in ( " + api_id + "));";
//                    FileUtils.printResultInFile(query, "Query--Req.txt", "tag", Boolean.TRUE);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//
//                try {
//                    jsonStr="";
//                    List<String> fileContent = FileUtils.readFile(FileUtils.dirBasePath+"\\RequestBody.json");
//                    for (String str : fileContent) {
//                        jsonStr = jsonStr + str;
//                    }
//                    String jsonExample = FileUtils.multipleLineToSingleLine(jsonStr);
//
//                    int api_id = Integer.parseInt("0000");
//                    String query = "UPDATE `apg_apidetails` SET `json_request_parameters` = '" + jsonExample + "' WHERE (`api_id` in ( " + api_id + "));";
//                    FileUtils.printResultInFile(query, "Query--Req.txt", "tag", Boolean.TRUE);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                try {
//                    jsonStr="";
//                    List<String> fileContent = FileUtils.readFile(FileUtils.dirBasePath+"\\ResponseBody.json");
//                    for (String str : fileContent) {
//                        jsonStr = jsonStr + str;
//                    }
//                    String jsonExample = FileUtils.multipleLineToSingleLine(jsonStr);
//
//                    int api_id = Integer.parseInt("0000");
//                    String query = "UPDATE `apg_apidetails` SET `json_response_parameters` = '" + jsonExample + "' WHERE (`api_id` in ( " + api_id + "));";
//                    FileUtils.printResultInFile(query, "Query--Req.txt", "tag", Boolean.TRUE);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                try {
//                    jsonStr="";
//                    List<String> fileContent = FileUtils.readFile(FileUtils.dirBasePath+"\\RequestHeader.json");
//                    for (String str : fileContent) {
//                        jsonStr = jsonStr + str;
//                    }
//                    String jsonExample = FileUtils.multipleLineToSingleLine(jsonStr);
//
//                    int api_id = Integer.parseInt("0000");
//                    String query = "UPDATE `apg_apidetails` SET `json_request_header` = '" + jsonExample + "' WHERE (`api_id` in ( " + api_id + "));";
//                    FileUtils.printResultInFile(query, "Query--Req.txt", "tag", Boolean.TRUE);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//                try {
//                    jsonStr="";
//                    List<String> fileContent = FileUtils.readFile(FileUtils.dirBasePath+"\\StatusCodes.json");
//                    for (String str : fileContent) {
//                        jsonStr = jsonStr + str;
//                    }
//                    String jsonExample = FileUtils.multipleLineToSingleLine(jsonStr);
//
//                    int api_id = Integer.parseInt("0000");
//                    String query = "UPDATE `apg_apidetails` SET `json_error_codes` = '" + jsonExample + "' WHERE (`api_id` in ( " + api_id + "));";
//                    FileUtils.printResultInFile(query, "Query--Req.txt", "tag", Boolean.TRUE);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TransformedNode> transformJson(JsonElement jsonElement, int lineNo, String extractFileName) {
        List<TransformedNode> transformedNodes = new ArrayList<>();

        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            transformJsonObject(jsonObject, transformedNodes, lineNo, extractFileName);
        } else if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            transformJsonArray(jsonArray, transformedNodes, lineNo, extractFileName);
        }

        return transformedNodes;
    }
    private void transformJsonObject(JsonObject jsonObject, List<TransformedNode> transformedNodes, int lineNo, String extractFileName) {
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();

            TransformerNodeAttribute trnNodeAttribute = FileUtils.attributePickerRequestProcess(key,lineNo,extractFileName);
            lineNo = trnNodeAttribute.getLineNo();
            hmTranformerNodeAttrib.put(key, trnNodeAttribute);
            List<TransformedNode> subNodes = new ArrayList<>();

            JsonElement value = entry.getValue();
            Node node= null;
            if (value.isJsonObject() || value.isJsonArray()) {
                subNodes = transformJson(value, trnNodeAttribute.getLineNo(), extractFileName);
                node = new Node(key, subNodes);
            } else {


                //            if (key !=null && value!=null ) {
                //                if (value.isJsonNull()) {
                //                    node = new Node(key, "");
                //                }
                //            } else {
                node = new Node(key, value.getAsString());
                //            }

            }

            TransformerNodeAttribute trnNodeAttribOut = hmTranformerNodeAttrib.get(node.getKey());
            if (trnNodeAttribOut.isMarkForDelete() == false){
                //find another key
                TransformedNode transformedNode = new TransformedNode(node, trnNodeAttribOut.getType(), trnNodeAttribOut.getMinLength(), trnNodeAttribOut.getMaxLength(), trnNodeAttribOut.getDesc(), trnNodeAttribOut.getReq(),subNodes, 0, 0);
                transformedNodes.add(transformedNode);
            }


            trnNodeAttribOut.setMarkForDelete(true);
        }
    }

    private void transformJsonArray(JsonArray jsonArray, List<TransformedNode> transformedNodes, int lineNo, String extractFileName){
        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                transformJsonObject(jsonObject, transformedNodes, lineNo, extractFileName);
            } else if (element.isJsonArray()) {
                JsonArray nestedJsonArray = element.getAsJsonArray();
                transformJsonArray(nestedJsonArray, transformedNodes, lineNo, extractFileName);
            } else {
                Node node = new Node("", element.getAsString());

                TransformedNode transformedNode = new TransformedNode(node, "string1", -1, -1, "-1", "false",transformedNodes, 0, 0);
                transformedNodes.add(transformedNode);
            }
        }
    }

    public TransformedNode parseJSONAttributes(TransformedNode transformedNode, String key, String transformedJsonString)throws Exception {
        try {

            JsonElement jsonElement = JsonParser.parseString(transformedJsonString);
            JsonObject openAPI = jsonElement.getAsJsonObject();

            // Extracting attributes for a single entity, e.g., "User"
            JsonObject userSchema = openAPI.getAsJsonObject("components")
                    .getAsJsonObject("schemas")
                    .getAsJsonObject(key);

            // Extract specific attributes
            Integer minLength = userSchema.getAsJsonObject("properties")
                    .getAsJsonObject(key)
                    .getAsJsonPrimitive("minLength")
                    .getAsInt();

            Integer maxLength = userSchema.getAsJsonObject("properties")
                    .getAsJsonObject(key)
                    .getAsJsonPrimitive("maxLength")
                    .getAsInt();

            String description = userSchema.getAsJsonObject("properties")
                    .getAsJsonObject(key)
                    .getAsJsonPrimitive("description")
                    .getAsString();

//            boolean required = userSchema.getAsJsonArray("required")
//                    .contains("username");

            transformedNode.setMinLength(minLength);
            transformedNode.setMaxLength(maxLength);
            transformedNode.setDesc(description);
            transformedNode.setReq("false");

            System.out.println("minLength: " + minLength);
            System.out.println("maxLength: " + maxLength);
            System.out.println("description: " + description);
            //System.out.println("required: " + required);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return transformedNode;
    }
    
}
