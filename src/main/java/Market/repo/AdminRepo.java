package Market.repo;

import Market.model.userTypes.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Long> {
    Admin findByUsername(String name);
    boolean existsByUsername(String username);
}
