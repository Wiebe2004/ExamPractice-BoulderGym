package boulder.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import boulder.be.model.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{}
