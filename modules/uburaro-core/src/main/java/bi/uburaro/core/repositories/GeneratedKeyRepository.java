package bi.uburaro.core.repositories;

import bi.uburaro.core.types.GeneratedKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneratedKeyRepository extends JpaRepository<GeneratedKey, Long> {
}
