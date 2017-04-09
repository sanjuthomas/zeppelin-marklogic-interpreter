package org.sanju.zeppelin.marklogic.interpreter;

import org.sanju.zeppelin.marklogic.response.CollectionProcessor;
import org.sanju.zeppelin.marklogic.response.FacetProcessor;
import org.sanju.zeppelin.marklogic.response.Processor;

/**
 * 
 * @author Sanju Thomas
 *
 */
public enum Command {
  
    COLLECTION ("fn.collection", CollectionProcessor.class),
    FACETS ("jsearch.facets", FacetProcessor.class);
    
    private String command;
    private Class<? extends Processor> processor;
    
    public String getCommand() {
      return command;
    }

    public Class<? extends Processor> getProcessor() {
      return processor;
    }

    private Command(String command, Class<? extends Processor> processor){
      this.command = command;
      this.processor = processor;
    }
    
    public static Command get(String query){
      
      if(query.startsWith(COLLECTION.getCommand())){
        return COLLECTION;
      }else if(query.startsWith(FACETS.getCommand())){
        return FACETS;
      }
      throw new IllegalArgumentException("Unknown command: "+query);
    }
    
}
