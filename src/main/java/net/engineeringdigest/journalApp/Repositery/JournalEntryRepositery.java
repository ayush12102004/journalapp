package net.engineeringdigest.journalApp.Repositery;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepositery extends MongoRepository<JournalEntry, ObjectId> {
}
