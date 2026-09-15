package net.engineeringdigest.journalApp.Cache;

import net.engineeringdigest.journalApp.Repositery.ConfigJournalAppRepository;
import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Component
public class AppCache {

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public HashMap<String, String> APP_CACHE;

    @PostConstruct
    public void init()
    {
        APP_CACHE = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity : all)
        {
            APP_CACHE.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }
    }
}
