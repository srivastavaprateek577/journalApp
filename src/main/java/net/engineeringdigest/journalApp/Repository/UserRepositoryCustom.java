package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> getUsersForSentimentAnalysis();
}
