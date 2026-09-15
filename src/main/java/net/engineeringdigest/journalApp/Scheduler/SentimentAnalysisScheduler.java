package net.engineeringdigest.journalApp.Scheduler;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Repositery.UserEntryRepositoryImpl;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.Sentiment;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SentimentAnalysisScheduler {

    @Autowired
    private UserEntryRepositoryImpl userEntryRepositoryImpl;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 9 ? * SUN")
    public void fetchUsersAndSendSentimentEmail() {

        List<User> users = userEntryRepositoryImpl.getUsersForSentimentAnalysis();

        for (User user : users) {

            List<JournalEntry> entries = user.getJournalEntries();

            // Group entries by sentiment and count each
            Map<Sentiment, Long> sentimentCount = entries.stream()
                    .filter(entry -> entry.getSentiment() != null)
                    .collect(Collectors.groupingBy(
                            JournalEntry::getSentiment,
                            Collectors.counting()
                    ));

            if (sentimentCount.isEmpty()) {
                log.info("No sentiment data for user: {}", user.getUserName());
                continue;
            }

            // Find the most frequent sentiment
            Sentiment dominantSentiment = Collections.max(
                    sentimentCount.entrySet(),
                    Map.Entry.comparingByValue()
            ).getKey();

            // Send email
            emailService.sendEmail(
                    user.getEmail(),
                    "Your Weekly Sentiment Report 📔",
                    "Hi " + user.getUserName() + ",\n\n" +
                            "Based on your journal entries this week, your dominant sentiment was: "
                            + dominantSentiment + "\n\nKeep journaling! 🌟"
            );

            log.info("Sent sentiment email to: {}", user.getEmail());
        }
    }
}
