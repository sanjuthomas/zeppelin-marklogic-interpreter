package org.sanju.zeppelin.marklogic.interpreter.reader;

/**
 * 
 * @author Sanju Thomas
 *
 */
public interface Reader {
  
	String query(String query);
	void close();

}
