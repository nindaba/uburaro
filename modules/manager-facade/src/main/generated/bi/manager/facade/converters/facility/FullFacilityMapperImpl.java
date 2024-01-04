package bi.manager.facade.converters.facility;

import bi.manager.core.types.MBCategoryType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.facade.converters.client.ClientMapper;
import bi.manager.facade.data.MBCategoryData;
import bi.manager.facade.data.MBClientData;
import bi.manager.facade.data.MBFacilityData;
import bi.uburaro.core.types.ModificationLogType;
import bi.uburaro.core.types.PrincipalType;
import bi.uburaro.facade.data.ModificationLogData;
import bi.uburaro.facade.data.PrincipalData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class FullFacilityMapperImpl implements FullFacilityMapper {

    @Autowired
    private FullCapitalMapper fullCapitalMapper;
    @Autowired
    private CategoryInventoryMapper categoryInventoryMapper;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public Collection<MBFacilityData> facilitiesToData(Collection<MBFacilityType> facilities) {
        if ( facilities == null ) {
            return null;
        }

        Collection<MBFacilityData> collection = new ArrayList<MBFacilityData>( facilities.size() );
        for ( MBFacilityType mBFacilityType : facilities ) {
            collection.add( facilityToData( mBFacilityType ) );
        }

        return collection;
    }

    @Override
    public MBFacilityData facilityToData(MBFacilityType facility) {
        if ( facility == null ) {
            return null;
        }

        MBFacilityData mBFacilityData = new MBFacilityData();

        mBFacilityData.setDateCreated( facility.getDateModified() );
        mBFacilityData.setCode( facility.getCode() );
        mBFacilityData.setActive( facility.isActive() );
        mBFacilityData.setVisible( facility.isVisible() );
        mBFacilityData.setName( facility.getName() );
        mBFacilityData.setAlias( facility.getAlias() );
        mBFacilityData.setAddress( facility.getAddress() );
        mBFacilityData.setCategories( mBCategoryTypeSetToMBCategoryDataSet( facility.getCategories() ) );
        mBFacilityData.setClients( mBClientTypeSetToMBClientDataSet( facility.getClients() ) );
        mBFacilityData.setCapital( fullCapitalMapper.capitalToData( facility.getCapital() ) );

        return mBFacilityData;
    }

    @Override
    public Collection<MBFacilityType> facilitiesToType(Collection<MBFacilityData> facilities) {
        if ( facilities == null ) {
            return null;
        }

        Collection<MBFacilityType> collection = new ArrayList<MBFacilityType>( facilities.size() );
        for ( MBFacilityData mBFacilityData : facilities ) {
            collection.add( facilityToType( mBFacilityData ) );
        }

        return collection;
    }

    @Override
    public MBFacilityType facilityToType(MBFacilityData facility) {
        if ( facility == null ) {
            return null;
        }

        MBFacilityType mBFacilityType = new MBFacilityType();

        mBFacilityType.setDateModified( facility.getDateCreated() );
        mBFacilityType.setCode( facility.getCode() );
        mBFacilityType.setVisible( facility.isVisible() );
        mBFacilityType.setActive( facility.isActive() );
        mBFacilityType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( facility.getModificationLogs() ) );
        mBFacilityType.setName( facility.getName() );
        mBFacilityType.setAlias( facility.getAlias() );
        mBFacilityType.setAddress( facility.getAddress() );
        mBFacilityType.setCategories( mBCategoryDataSetToMBCategoryTypeSet( facility.getCategories() ) );
        mBFacilityType.setClients( mBClientDataSetToMBClientTypeSet( facility.getClients() ) );
        mBFacilityType.setCapital( fullCapitalMapper.capitalToType( facility.getCapital() ) );

        return mBFacilityType;
    }

    protected Set<MBCategoryData> mBCategoryTypeSetToMBCategoryDataSet(Set<MBCategoryType> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBCategoryData> set1 = new LinkedHashSet<MBCategoryData>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBCategoryType mBCategoryType : set ) {
            set1.add( categoryInventoryMapper.categoryToData( mBCategoryType ) );
        }

        return set1;
    }

    protected Set<MBClientData> mBClientTypeSetToMBClientDataSet(Set<MBClientType> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBClientData> set1 = new LinkedHashSet<MBClientData>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBClientType mBClientType : set ) {
            set1.add( clientMapper.clientToData( mBClientType ) );
        }

        return set1;
    }

    protected Set<ModificationLogType> modificationLogDataListToModificationLogTypeSet(List<ModificationLogData> list) {
        if ( list == null ) {
            return null;
        }

        Set<ModificationLogType> set = new LinkedHashSet<ModificationLogType>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( ModificationLogData modificationLogData : list ) {
            set.add( modificationLogDataToModificationLogType( modificationLogData ) );
        }

        return set;
    }

    protected PrincipalType principalDataToPrincipalType(PrincipalData principalData) {
        if ( principalData == null ) {
            return null;
        }

        PrincipalType principalType = new PrincipalType();

        principalType.setCode( principalData.getCode() );
        principalType.setVisible( principalData.isVisible() );
        principalType.setActive( principalData.isActive() );
        principalType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( principalData.getModificationLogs() ) );
        principalType.setUsername( principalData.getUsername() );
        principalType.setPassword( principalData.getPassword() );

        return principalType;
    }

    protected ModificationLogType modificationLogDataToModificationLogType(ModificationLogData modificationLogData) {
        if ( modificationLogData == null ) {
            return null;
        }

        ModificationLogType modificationLogType = new ModificationLogType();

        modificationLogType.setCode( modificationLogData.getCode() );
        modificationLogType.setVisible( modificationLogData.isVisible() );
        modificationLogType.setActive( modificationLogData.isActive() );
        modificationLogType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( modificationLogData.getModificationLogs() ) );
        modificationLogType.setUser( principalDataToPrincipalType( modificationLogData.getUser() ) );
        modificationLogType.setModifiedItem( modificationLogData.getModifiedItem() );
        modificationLogType.setDateModified( modificationLogData.getDateModified() );
        modificationLogType.setPreviousValueCode( modificationLogData.getPreviousValueCode() );

        return modificationLogType;
    }

    protected Set<MBCategoryType> mBCategoryDataSetToMBCategoryTypeSet(Set<MBCategoryData> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBCategoryType> set1 = new LinkedHashSet<MBCategoryType>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBCategoryData mBCategoryData : set ) {
            set1.add( categoryInventoryMapper.categoryToType( mBCategoryData ) );
        }

        return set1;
    }

    protected Set<MBClientType> mBClientDataSetToMBClientTypeSet(Set<MBClientData> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBClientType> set1 = new LinkedHashSet<MBClientType>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBClientData mBClientData : set ) {
            set1.add( clientMapper.clientToType( mBClientData ) );
        }

        return set1;
    }
}
