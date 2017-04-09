package org.sanju.zeppelin.marklogic.interpreter;

import java.util.Map;
import java.util.Properties;

import org.apache.zeppelin.interpreter.Interpreter;
import org.apache.zeppelin.interpreter.InterpreterContext;
import org.apache.zeppelin.interpreter.InterpreterResult;
import org.sanju.zeppelin.marklogic.interpreter.reader.MarkLogicReader;
import org.sanju.zeppelin.marklogic.interpreter.reader.Reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class MarkLogicInterpreter extends Interpreter {
	
	private static final String MARKLOGIC_HOST = "ml.host";
	private static final String MARKLOGIC_PORT = "ml.port";
	private static final String MARKLOGIC_USERNAME = "ml.username";
	private static final String MARKLOGIC_PASSWORD = "ml.password";
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	 
	private Reader reader;

	public MarkLogicInterpreter(Properties property) {
		super(property);
	}

	@Override
	public void cancel(InterpreterContext arg0) {

	}

	@Override
	public void close() {
		reader.close();
	}

	@Override
	public FormType getFormType() {
		return FormType.SIMPLE;
	}

	@Override
	public int getProgress(InterpreterContext arg0) {
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public InterpreterResult interpret(String query, InterpreterContext arg1) {
		try {
			final StringBuilder b = new StringBuilder();
			final JsonNode result = reader.query(query);
			if(null != result){
				final JsonNode facets = result.get("facets");
				if(null != facets){
					
					final Map<String, Object> facetsMap = MAPPER.convertValue(facets, Map.class);
					facetsMap.keySet().forEach(facetName -> {
						b.append(facetName);
						b.append("\t");
						b.append("count\n");
						Map<String, Object> facetValues = (Map<String, Object>) facetsMap.get(facetName);
						facetValues.forEach( (k, v) -> {b.append(k);b.append("\t");b.append(v);b.append("\n");});
					});
					
				}
			}
			return new InterpreterResult(InterpreterResult.Code.SUCCESS, InterpreterResult.Type.TABLE, b.toString());
		} catch (Exception e) {
			return new InterpreterResult(InterpreterResult.Code.ERROR, e.getMessage());
		}
	}

	@Override
	public void open() {
		reader = new MarkLogicReader(getProperty(MARKLOGIC_HOST), Integer.valueOf(getProperty(MARKLOGIC_PORT)),
				getProperty(MARKLOGIC_USERNAME), getProperty(MARKLOGIC_PASSWORD));
	}

}
