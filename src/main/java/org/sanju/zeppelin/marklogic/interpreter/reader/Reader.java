package org.sanju.zeppelin.marklogic.interpreter.reader;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 
 * @author Sanju Thomas
 *
 */
public interface Reader {
  
	JsonNode query(String query);
	void close();

}
