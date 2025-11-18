package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
@Repository
public interface UserRepository extends JpaRepository<User, Long>,  UserRepositoryCustom {
    User findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}
