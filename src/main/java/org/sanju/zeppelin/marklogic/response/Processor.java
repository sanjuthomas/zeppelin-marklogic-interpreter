package org.sanju.zeppelin.marklogic.response;

import java.util.HashMap;
import java.util.Map;

import org.sanju.zeppelin.marklogic.interpreter.Command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Sanju Thomas
 *
 */
public interface Processor {
  
    static final ObjectMapper MAPPER = new ObjectMapper();
    static final Map<Command, Processor> processors = new HashMap<>();
    
    static Processor getInstance(Command command) throws InstantiationException, IllegalAccessException{
      Processor processor = processors.get(command);
      if(null == processor){
        processor = command.getProcessor().newInstance();
        processors.put(command, processor);
      }
      return processor;
    }
  
    String process(JsonNode jsonNode) throws JsonProcessingException;
}
