package utility;

import java.util.HashMap;
import java.util.Map;

public class ColladaData {
    Map<String, String> nodes = new HashMap<>();
    Map<String, Map<String, String>> attributes = new HashMap<>();
    Map<String, String> texts = new HashMap<>();

    public void addNode(String key, String value){
        nodes.put(key,value);
    }

    public void addAttributes(String key, Map<String,String> attrs){
        attributes.put(key, attrs);
    }

    public void addTextContent(String key, String value){
        texts.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        nodes.forEach((key, value) -> {
            sb.append(key + " : " + value + "\n");
            if(attributes.containsKey(key)){
                Map<String, String> attrs = attributes.get(key);
                attrs.forEach((akey, avalue) -> {
                    sb.append("\t\t" + akey + " : " + avalue + "\n");
                });
            }
            if(texts.containsKey(key)) sb.append("\t\t" + "TextContent : " + texts.get(key) + "\n");
        });
        return sb.toString();
    }
}
