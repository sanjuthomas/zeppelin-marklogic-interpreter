package org.sanju.zeppelin.marklogic.response;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wnameless.json.flattener.JsonFlattener;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class CollectionProcessor implements Processor {
  
  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Override
  public String process(JsonNode jsonNode) throws JsonProcessingException {
    final StringBuilder builder = new StringBuilder();
    if(null != jsonNode){
      final String jsonText = MAPPER.writeValueAsString(jsonNode);
      final Map<String, Object>  flatMap = JsonFlattener.flattenAsMap(jsonText);
      final List<String> header = createHeader(flatMap);
     
      header.forEach(h -> {
        builder.append(h);
        builder.append("\t");
      });
      
      builder.replace(builder.lastIndexOf("\t"), builder.lastIndexOf("\t") + 1, "\n");
      for (final Iterator<String> iterator = flatMap.keySet().iterator(); iterator.hasNext(); ) {
        for(int i = 0; i < header.size(); i++){
          builder.append(flatMap.get(iterator.next()));
          builder.append("\t");
        }
        builder.replace(builder.lastIndexOf("\t"), builder.lastIndexOf("\t") + 1, "\n");
      }
    }
    return builder.toString();
  }

  private List<String> createHeader(final Map<String, Object>  flatMap) {
    
    final List<String> keys = new ArrayList<String>();
    for (final Iterator<String> i = flatMap.keySet().iterator(); i.hasNext(); ) {
      final String header = i.next();
      if(header.startsWith("[0].")){
        keys.add(header.substring(4));
      }else{
        return keys;
      }
    }
    return keys;
  }

}
