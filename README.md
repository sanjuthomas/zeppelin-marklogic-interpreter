# Apache Zeppelin interpreter for MarkLogic

Apache Zeppelin interpreter for MarkLogic let you execute MarkLogic JSearch queries from Zeppelin notebook against MarkLogic cluster. You can make use MarkLogic distributed computing capabilities and Zeppelin visualization components together.

## What is Apache Zeppelin?
Apache Zeppelin is a web-based notebook that enables interactive data analytics. You can make beautiful data-driven, interactive and collaborative documents with SQL, Scala and more. Fore more details, please refer to Apache Zeppelin [home page](http://zeppelin-project.org/).

## When to use Zeppelin interpreter for MarkLogic?
To execute exploratory queries against MarkLogic and visualize the result in the Apache Zeppelin notebook.

## High Level Architecture

![Apache Zeppelin interpreter for MarkLogic](zepplin-with-ml-interperter-arch.png)

## What is currently supported?
MarkLogic [JSearch](https://docs.marklogic.com/js/jsearch) aggregation/facet queries only. I'm in the process of adding the entire JSarch query support. Please stay tuned.

Sample documents - 
```
{
"RDBMS": "MYSQL"
}
```
Example query - 
```
var jsearch = require('/MarkLogic/jsearch.sjs');
jsearch.facets([ jsearch.facet('RDBMS', 'RDBMS') ]).result()
```
A range index for JSON element 'RDBMS'is expected for the above query.

## Zeppelin MarkLogic Notebook Screenshot

![Apache Zeppelin interpreter for MarkLogic](zepplin-with-ml-interperter-1.png)

## Questions?
Please create an issue in GitHub.


