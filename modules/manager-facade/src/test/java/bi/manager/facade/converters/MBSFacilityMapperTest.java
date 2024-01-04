package bi.manager.facade.converters;

import bi.manager.core.types.MBFacilityType;
import bi.manager.facade.converters.facility.FacilityMapper;
import bi.manager.facade.converters.facility.FacilityMapperImpl;
import bi.manager.facade.data.MBFacilityData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MBSFacilityMapperTest {
    FacilityMapper facilityMapper = new FacilityMapperImpl();
    final static MBFacilityData DATA = new MBFacilityData();
    final static MBFacilityType TYPE = new MBFacilityType();

    @BeforeAll
    void setup(){
        DATA.setName("a");
        DATA.setAlias("b");
        DATA.setActive(true);

        TYPE.setName("a");
        TYPE.setAlias("b");
        TYPE.setActive(true);
    }
    @Test
    void facilityToData() {
        MBFacilityData actual = facilityMapper.facilityToData(TYPE);
        assertEquals(DATA,actual);
        Collection<MBFacilityData> actualList = facilityMapper.facilitiesToData(List.of(TYPE));
        assertEquals(1,actualList.size());
    }

    @Test
    void facilityToType() {
        MBFacilityType expected = facilityMapper.facilityToType(DATA);
        assertEquals(expected.getName(),TYPE.getName());
        assertEquals(expected.getAlias(),TYPE.getAlias());
        assertEquals(expected.isActive(),TYPE.isActive());
    }
}