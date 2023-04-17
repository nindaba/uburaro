package bi.uburaro.core.repositories;

import bi.uburaro.core.types.PrimaryKeyType;
import bi.uburaro.core.types.importer.BatchType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<BatchType, PrimaryKeyType> {
}
