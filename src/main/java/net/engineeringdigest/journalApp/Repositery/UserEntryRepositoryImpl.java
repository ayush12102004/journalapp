package net.engineeringdigest.journalApp.Repositery;

import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserEntryRepositoryImpl{

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUsersForSentimentAnalysis() {

        Criteria criteria = Criteria.where("email").exists(true).ne(null).and("sentimentAnalysis").is(true);

        Query query = new Query(criteria);

        return mongoTemplate.find(query, User.class);
    }
}
