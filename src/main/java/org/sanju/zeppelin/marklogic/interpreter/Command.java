package org.sanju.zeppelin.marklogic.interpreter;

import org.sanju.zeppelin.marklogic.response.CollectionProcessor;
import org.sanju.zeppelin.marklogic.response.DefaultProcessor;
import org.sanju.zeppelin.marklogic.response.FacetProcessor;
import org.sanju.zeppelin.marklogic.response.Processor;

/**
 * 
 * @author Sanju Thomas
 *
 */
public enum Command {
  
    COLLECTION ("fn.collection", CollectionProcessor.class),
    JSEARCH_FACETS ("jsearch.facets", FacetProcessor.class),
    DEFAULT("default", DefaultProcessor.class);
    
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
      }else if(query.startsWith(JSEARCH_FACETS.getCommand())){
        return JSEARCH_FACETS;
      }else{
        return DEFAULT;
      }
    }
    
}
