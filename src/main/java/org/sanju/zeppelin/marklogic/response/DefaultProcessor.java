package org.sanju.zeppelin.marklogic.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class DefaultProcessor extends CollectionProcessor {

  @Override
  public String process(JsonNode jsonNode) throws JsonProcessingException {

    final JsonNode results = jsonNode.get("results");
    if (null != results) {
      return super.process(results);
    } else {
      return super.process(jsonNode);
    }
  }

}
