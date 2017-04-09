package org.sanju.zeppelin.marklogic.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.zeppelin.interpreter.Interpreter;
import org.apache.zeppelin.interpreter.InterpreterContext;
import org.apache.zeppelin.interpreter.InterpreterResult;
import org.apache.zeppelin.interpreter.thrift.InterpreterCompletion;
import org.sanju.zeppelin.marklogic.reader.MarkLogicReader;
import org.sanju.zeppelin.marklogic.reader.Reader;
import org.sanju.zeppelin.marklogic.response.Processor;

import com.fasterxml.jackson.databind.JsonNode;

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

  @Override
  public InterpreterResult interpret(String query, InterpreterContext arg1) {
    try {
      final Command command = Command.get(query);
      query = "var jsearch = require('/MarkLogic/jsearch.sjs');\n" + query;
      final JsonNode jsonNode = reader.query(query);
      return new InterpreterResult(InterpreterResult.Code.SUCCESS,
          InterpreterResult.Type.TABLE, Processor.getInstance(command).process(jsonNode));
    } catch (Exception e) {
      return new InterpreterResult(InterpreterResult.Code.ERROR, e.getMessage());
    }
  }

  @Override
  public void open() {

    reader = new MarkLogicReader(getProperty(MARKLOGIC_HOST),
        Integer.valueOf(getProperty(MARKLOGIC_PORT)),
        getProperty(MARKLOGIC_USERNAME), getProperty(MARKLOGIC_PASSWORD));
  }

  @Override
  public List<InterpreterCompletion> completion(final String s, final int i) {

    final List<InterpreterCompletion> suggestions = new ArrayList<>();
    for (final Command cmd : Command.values()) {
      if (cmd.getCommand().toLowerCase().contains(s)) {
        suggestions.add(new InterpreterCompletion(cmd.getCommand(), cmd
            .getCommand()));
      }
    }
    return suggestions;
  }

}
