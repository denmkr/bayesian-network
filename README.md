# Thesis app back

## Initial queries for Neo4j Database

CREATE (:Node { name: 'Competence 1', type: 'Competence' })

CREATE (:Node { name: 'Topic 1', type: 'Topic' })
CREATE (:Node { name: 'Topic 2', type: 'Topic' })

CREATE (:Node { name: 'Subtopic 1', type: 'Subtopic' })
CREATE (:Node { name: 'Subtopic 2', type: 'Subtopic' })
CREATE (:Node { name: 'Subtopic 3', type: 'Subtopic' })

CREATE (:Node { name: 'Test 1', type: 'Test' })
CREATE (:Node { name: 'Test 2', type: 'Test' })
CREATE (:Node { name: 'Test 3', type: 'Test' })
CREATE (:Node { name: 'Test 4',type: 'Test' })

CREATE (:Node { name: 'Question 1', type: 'Question' })
CREATE (:Node { name: 'Question 2', type: 'Question' })
CREATE (:Node { name: 'Question 3', type: 'Question' })
CREATE (:Node { name: 'Question 4',type: 'Question' })
CREATE (:Node { name: 'Question 5', type: 'Question' })


MATCH (a),(b)
WHERE a.name = 'Competence 1' AND b.name = 'Topic 1' 
CREATE (a)-[:Relation {weight:'0.5'}]->(b)
MATCH (c),(d)
WHERE c.name = 'Competence 1' AND d.name = 'Topic 2'
CREATE (c)-[:Relation {weight:'0.5'}]->(d)

MATCH (a),(b)
WHERE a.name = 'Topic 1' AND b.name = 'Subtopic 1'
CREATE (a)-[:Relation {weight:'0.3'}]->(b)
MATCH (c),(d)
WHERE c.name = 'Topic 1' AND d.name = 'Subtopic 2'
CREATE (c)-[:Relation {weight:'0.7'}]->(d)
MATCH (e),(f)
WHERE e.name = 'Topic 2' AND f.name = 'Subtopic 3'
CREATE (e)-[:Relation {weight:'1.0'}]->(f)

MATCH (a),(b)
WHERE a.name = 'Subtopic 1' AND b.name = 'Test 1'
CREATE (a)-[:Relation {weight:'0.5'}]->(b)
MATCH (a),(b)
WHERE a.name = 'Subtopic 1' AND b.name = 'Test 2'
CREATE (a)-[:Relation {weight:'0.5'}]->(b)
MATCH (a),(b)
WHERE a.name = 'Subtopic 2' AND b.name = 'Test 3'
CREATE (a)-[:Relation {weight:'1.0'}]->(b)
MATCH (a),(b)
WHERE a.name = 'Subtopic 3' AND b.name = 'Test 3'
CREATE (a)-[:Relation {weight:'0.3'}]->(b)
MATCH (a),(b)
WHERE a.name = 'Subtopic 3' AND b.name = 'Test 4'
CREATE (a)-[:Relation {weight:'0.7'}]->(b)

MATCH (a),(b)
WHERE a.name = 'Test 1' AND b.name = 'Question 1'
CREATE (a)-[:Relation {weight:'0.4'}]->(b)
MATCH (a),(b)
WHERE a.name = 'Test 1' AND b.name = 'Question 2'
CREATE (a)-[:Relation {weight:'0.6'}]->(b)
MATCH (a),(b)
WHERE a.name = 'Test 2' AND b.name = 'Question 2'
CREATE (a)-[:Relation {weight:'0.8'}]->(b)
MATCH (a),(b)
WHERE a.name = 'Test 2' AND b.name = 'Question 3'
CREATE (a)-[:Relation {weight:'0.2'}]->(b)
MATCH (a),(b)
WHERE a.name = 'Test 3' AND b.name = 'Question 4'
CREATE (a)-[:Relation {weight:'1.0'}]->(b)
MATCH (a),(b)
WHERE a.name = 'Test 4' AND b.name = 'Question 5'
CREATE (a)-[:Relation {weight:'1.0'}]->(b)
