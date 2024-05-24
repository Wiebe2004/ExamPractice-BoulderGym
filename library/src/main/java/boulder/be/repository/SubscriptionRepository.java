package boulder.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import boulder.be.model.Subscription;
import boulder.be.model.User;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{

    List<Subscription> findByUser(User user);

    // void deleteSubscriptionByUser(User user);

    void deleteByUser(User user);
}
