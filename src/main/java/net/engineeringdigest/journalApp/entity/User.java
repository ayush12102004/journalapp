package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List;
import java.util.ArrayList;


@Document(collection = "user")
@Data
@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique= true)
    @NonNull
    private String userName;
    @NonNull
    private String password;

    private String email;

    private boolean sentimentAnalysis;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();

    private  List<String> roles;

}
