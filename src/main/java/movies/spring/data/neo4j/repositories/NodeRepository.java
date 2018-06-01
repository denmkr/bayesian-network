package movies.spring.data.neo4j.repositories;

import movies.spring.data.neo4j.domain.Node;
import movies.spring.data.neo4j.domain.QuestionNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by denis on 23/05/2018.
 */
public interface NodeRepository extends Neo4jRepository<Node, Long> {

    QuestionNode findQuestionByName(String name);
    Node findByName(String name);
    @Query("MATCH (m)<-[r:Relation]-(a:Node) RETURN m,r,a LIMIT {limit}")
    Collection<Node> graph(@Param("limit") int limit);

    @Query("MATCH (n) RETURN n")
    List<Node> getAll();


}