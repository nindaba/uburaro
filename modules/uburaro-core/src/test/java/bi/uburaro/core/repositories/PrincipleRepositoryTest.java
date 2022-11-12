package bi.uburaro.core.repositories;

import bi.uburaro.core.types.CustomerType;
import bi.uburaro.core.types.EmployeeType;
import bi.uburaro.core.types.PrincipalType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrincipleRepositoryTest {

    @Test
    void belongsTo() {
        assertTrue(PrincipalType.class.isAssignableFrom(CustomerType.class));
        assertTrue(PrincipalType.class.isAssignableFrom(EmployeeType.class));
    }
}
