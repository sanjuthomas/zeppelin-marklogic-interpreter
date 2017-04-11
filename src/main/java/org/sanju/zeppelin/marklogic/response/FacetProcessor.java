package org.sanju.zeppelin.marklogic.response;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class FacetProcessor implements Processor {

  @SuppressWarnings("unchecked")
  @Override
  public String process(JsonNode result) {

    final StringBuilder b = new StringBuilder();
    if (null != result) {
      final JsonNode facets = result.get("facets");
      if (null != facets) {
        b.append("Facet Name\t");
        b.append("Facet Value\t");
        b.append("count\n");
        final Map<String, Object> facetsMap = MAPPER.convertValue(facets,
            Map.class);
        facetsMap
            .keySet()
            .forEach(
                facetName -> {
                  final Map<String, Object> facetValues = (Map<String, Object>) facetsMap
                      .get(facetName);
                  if (null != facetValues) {
                    facetValues.forEach((k, v) -> {
                      b.append(facetName);
                      b.append("\t");
                      b.append(k);
                      b.append("\t");
                      b.append(v);
                      b.append("\n");
                    });
                  }
                });
      }
    }
    return b.toString();
  }

}
