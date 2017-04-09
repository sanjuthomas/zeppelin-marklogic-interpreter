package org.sanju.zeppelin.marklogic.interpreter.reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.marklogic.client.eval.ServerEvaluationCall;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class MarkLogicReader implements Reader{
	
	private final DatabaseClient client;
	
	public MarkLogicReader(String host, int port, String username, String password){
		
		client = DatabaseClientFactory.newClient(host, port, username, password, Authentication.DIGEST);
	}

	/**
	 * @param query jsearch query as string
	 */
	public JsonNode query(String query) {
		
		final ServerEvaluationCall evalCall = client.newServerEval().javascript(query);
		return evalCall.evalAs(JsonNode.class);
	}
	
	public void close(){
		this.client.release();
	}
}
