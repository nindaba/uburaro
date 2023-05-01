package bi.manager.facade.converters.facility;

import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.enums.MBEntryEnum;
import bi.manager.facade.data.MBCapitalData;
import bi.manager.facade.data.MBCapitalEntryData;
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
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class FullCapitalMapperImpl implements FullCapitalMapper {

    @Override
    public Collection<MBCapitalEntryData> capitalEntriesToData(Collection<MBCapitalEntryType> entries) {
        if ( entries == null ) {
            return null;
        }

        Collection<MBCapitalEntryData> collection = new ArrayList<MBCapitalEntryData>( entries.size() );
        for ( MBCapitalEntryType mBCapitalEntryType : entries ) {
            collection.add( capitalEntryToData( mBCapitalEntryType ) );
        }

        return collection;
    }

    @Override
    public MBCapitalData capitalToData(MBCapitalType capitalType) {
        if ( capitalType == null ) {
            return null;
        }

        MBCapitalData mBCapitalData = new MBCapitalData();

        mBCapitalData.setDateCreated( capitalType.getDateModified() );
        mBCapitalData.setCode( capitalType.getCode() );
        mBCapitalData.setActive( capitalType.isActive() );
        mBCapitalData.setVisible( capitalType.isVisible() );
        mBCapitalData.setCurrentValue( capitalType.getCurrentValue() );
        mBCapitalData.setEntries( mBCapitalEntryTypeSetToMBCapitalEntryDataSet( capitalType.getEntries() ) );

        return mBCapitalData;
    }

    @Override
    public MBCapitalEntryData capitalEntryToData(MBCapitalEntryType entryType) {
        if ( entryType == null ) {
            return null;
        }

        MBCapitalEntryData mBCapitalEntryData = new MBCapitalEntryData();

        mBCapitalEntryData.setDateCreated( entryType.getDateModified() );
        mBCapitalEntryData.setCode( entryType.getCode() );
        mBCapitalEntryData.setActive( entryType.isActive() );
        mBCapitalEntryData.setVisible( entryType.isVisible() );
        mBCapitalEntryData.setAmount( entryType.getAmount() );
        if ( entryType.getEntryType() != null ) {
            mBCapitalEntryData.setEntryType( entryType.getEntryType().name() );
        }
        mBCapitalEntryData.setDescription( entryType.getDescription() );

        return mBCapitalEntryData;
    }

    @Override
    public MBCapitalType capitalToType(MBCapitalData capitalData) {
        if ( capitalData == null ) {
            return null;
        }

        MBCapitalType mBCapitalType = new MBCapitalType();

        mBCapitalType.setDateModified( capitalData.getDateCreated() );
        mBCapitalType.setCode( capitalData.getCode() );
        mBCapitalType.setVisible( capitalData.isVisible() );
        mBCapitalType.setActive( capitalData.isActive() );
        mBCapitalType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( capitalData.getModificationLogs() ) );
        mBCapitalType.setCurrentValue( capitalData.getCurrentValue() );
        mBCapitalType.setEntries( mBCapitalEntryDataSetToMBCapitalEntryTypeSet( capitalData.getEntries() ) );

        return mBCapitalType;
    }

    protected Set<MBCapitalEntryData> mBCapitalEntryTypeSetToMBCapitalEntryDataSet(Set<MBCapitalEntryType> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBCapitalEntryData> set1 = new LinkedHashSet<MBCapitalEntryData>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBCapitalEntryType mBCapitalEntryType : set ) {
            set1.add( capitalEntryToData( mBCapitalEntryType ) );
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

    protected MBCapitalEntryType mBCapitalEntryDataToMBCapitalEntryType(MBCapitalEntryData mBCapitalEntryData) {
        if ( mBCapitalEntryData == null ) {
            return null;
        }

        MBCapitalEntryType mBCapitalEntryType = new MBCapitalEntryType();

        mBCapitalEntryType.setCode( mBCapitalEntryData.getCode() );
        mBCapitalEntryType.setVisible( mBCapitalEntryData.isVisible() );
        mBCapitalEntryType.setActive( mBCapitalEntryData.isActive() );
        mBCapitalEntryType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( mBCapitalEntryData.getModificationLogs() ) );
        if ( mBCapitalEntryData.getAmount() != null ) {
            mBCapitalEntryType.setAmount( mBCapitalEntryData.getAmount() );
        }
        if ( mBCapitalEntryData.getEntryType() != null ) {
            mBCapitalEntryType.setEntryType( Enum.valueOf( MBEntryEnum.class, mBCapitalEntryData.getEntryType() ) );
        }
        mBCapitalEntryType.setDescription( mBCapitalEntryData.getDescription() );

        return mBCapitalEntryType;
    }

    protected Set<MBCapitalEntryType> mBCapitalEntryDataSetToMBCapitalEntryTypeSet(Set<MBCapitalEntryData> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBCapitalEntryType> set1 = new LinkedHashSet<MBCapitalEntryType>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBCapitalEntryData mBCapitalEntryData : set ) {
            set1.add( mBCapitalEntryDataToMBCapitalEntryType( mBCapitalEntryData ) );
        }

        return set1;
    }
}
