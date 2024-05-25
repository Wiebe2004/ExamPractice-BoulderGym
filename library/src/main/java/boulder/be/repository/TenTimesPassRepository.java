package boulder.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import boulder.be.model.TenTimesPass;
import boulder.be.model.User;

@Repository
public interface TenTimesPassRepository extends JpaRepository<TenTimesPass, Long> {

    List<TenTimesPass> findByUser(User user);

    void deleteByUser(User user);

}
