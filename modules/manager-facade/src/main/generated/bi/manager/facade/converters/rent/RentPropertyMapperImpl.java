package bi.manager.facade.converters.rent;

import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.MBCategoryType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.core.types.MBInventoryType;
import bi.manager.core.types.MBRentPropertyType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBRentContractType;
import bi.manager.core.types.enums.MBEntryEnum;
import bi.manager.facade.converters.client.ClientMapper;
import bi.manager.facade.converters.client.RentContractMapper;
import bi.manager.facade.data.MBCapitalData;
import bi.manager.facade.data.MBCapitalEntryData;
import bi.manager.facade.data.MBCategoryData;
import bi.manager.facade.data.MBClientData;
import bi.manager.facade.data.MBFacilityData;
import bi.manager.facade.data.MBInventoryData;
import bi.manager.facade.data.MBInventoryOrderData;
import bi.manager.facade.data.MBRentContractData;
import bi.manager.facade.data.MBRentPropertyData;
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
public class RentPropertyMapperImpl implements RentPropertyMapper {

    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private RentContractMapper rentContractMapper;

    @Override
    public MBRentPropertyData rentPropertyToData(MBRentPropertyType rent) {
        if ( rent == null ) {
            return null;
        }

        MBRentPropertyData mBRentPropertyData = new MBRentPropertyData();

        mBRentPropertyData.setCode( rent.getCode() );
        mBRentPropertyData.setActive( rent.isActive() );
        mBRentPropertyData.setVisible( rent.isVisible() );
        mBRentPropertyData.setCurrentContract( rentContractMapper.contractToData( rent.getCurrentContract() ) );
        mBRentPropertyData.setName( rent.getName() );
        mBRentPropertyData.setUnit( rent.getUnit() );
        mBRentPropertyData.setCost( rent.getCost() );
        mBRentPropertyData.setAddress( rent.getAddress() );

        return mBRentPropertyData;
    }

    @Override
    public MBRentPropertyType rentPropertyToType(MBRentPropertyData rent) {
        if ( rent == null ) {
            return null;
        }

        MBRentPropertyType mBRentPropertyType = new MBRentPropertyType();

        mBRentPropertyType.setCode( rent.getCode() );
        mBRentPropertyType.setVisible( rent.isVisible() );
        mBRentPropertyType.setActive( rent.isActive() );
        mBRentPropertyType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( rent.getModificationLogs() ) );
        mBRentPropertyType.setCurrentContract( rentContractMapper.contractToType( rent.getCurrentContract() ) );
        mBRentPropertyType.setFacility( mBFacilityDataToMBFacilityType( rent.getFacility() ) );
        mBRentPropertyType.setContracts( mBRentContractDataSetToMBRentContractTypeSet( rent.getContracts() ) );
        mBRentPropertyType.setName( rent.getName() );
        mBRentPropertyType.setUnit( rent.getUnit() );
        mBRentPropertyType.setCost( rent.getCost() );
        mBRentPropertyType.setAddress( rent.getAddress() );

        return mBRentPropertyType;
    }

    @Override
    public Collection<MBRentPropertyData> rentPropertiesToData(Collection<MBRentPropertyType> rents) {
        if ( rents == null ) {
            return null;
        }

        Collection<MBRentPropertyData> collection = new ArrayList<MBRentPropertyData>( rents.size() );
        for ( MBRentPropertyType mBRentPropertyType : rents ) {
            collection.add( rentPropertyToData( mBRentPropertyType ) );
        }

        return collection;
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

    protected MBInventoryOrderType mBInventoryOrderDataToMBInventoryOrderType(MBInventoryOrderData mBInventoryOrderData) {
        if ( mBInventoryOrderData == null ) {
            return null;
        }

        MBInventoryOrderType mBInventoryOrderType = new MBInventoryOrderType();

        mBInventoryOrderType.setCode( mBInventoryOrderData.getCode() );
        mBInventoryOrderType.setVisible( mBInventoryOrderData.isVisible() );
        mBInventoryOrderType.setActive( mBInventoryOrderData.isActive() );
        mBInventoryOrderType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( mBInventoryOrderData.getModificationLogs() ) );
        mBInventoryOrderType.setOrderNumber( mBInventoryOrderData.getOrderNumber() );
        mBInventoryOrderType.setQuantity( mBInventoryOrderData.getQuantity() );
        mBInventoryOrderType.setUnit( mBInventoryOrderData.getUnit() );
        mBInventoryOrderType.setOrderDate( mBInventoryOrderData.getOrderDate() );
        mBInventoryOrderType.setCost( mBInventoryOrderData.getCost() );
        mBInventoryOrderType.setPaid( mBInventoryOrderData.isPaid() );
        mBInventoryOrderType.setOrderEntry( mBInventoryOrderData.getOrderEntry() );

        return mBInventoryOrderType;
    }

    protected Set<MBInventoryOrderType> mBInventoryOrderDataSetToMBInventoryOrderTypeSet(Set<MBInventoryOrderData> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBInventoryOrderType> set1 = new LinkedHashSet<MBInventoryOrderType>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBInventoryOrderData mBInventoryOrderData : set ) {
            set1.add( mBInventoryOrderDataToMBInventoryOrderType( mBInventoryOrderData ) );
        }

        return set1;
    }

    protected MBInventoryType mBInventoryDataToMBInventoryType(MBInventoryData mBInventoryData) {
        if ( mBInventoryData == null ) {
            return null;
        }

        MBInventoryType mBInventoryType = new MBInventoryType();

        mBInventoryType.setCode( mBInventoryData.getCode() );
        mBInventoryType.setVisible( mBInventoryData.isVisible() );
        mBInventoryType.setActive( mBInventoryData.isActive() );
        mBInventoryType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( mBInventoryData.getModificationLogs() ) );
        mBInventoryType.setInventoryOrders( mBInventoryOrderDataSetToMBInventoryOrderTypeSet( mBInventoryData.getInventoryOrders() ) );
        mBInventoryType.setCategory( mBCategoryDataToMBCategoryType( mBInventoryData.getCategory() ) );
        mBInventoryType.setName( mBInventoryData.getName() );
        mBInventoryType.setUnit( mBInventoryData.getUnit() );
        if ( mBInventoryData.getQuantity() != null ) {
            mBInventoryType.setQuantity( mBInventoryData.getQuantity() );
        }
        if ( mBInventoryData.getCost() != null ) {
            mBInventoryType.setCost( mBInventoryData.getCost() );
        }

        return mBInventoryType;
    }

    protected Set<MBInventoryType> mBInventoryDataSetToMBInventoryTypeSet(Set<MBInventoryData> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBInventoryType> set1 = new LinkedHashSet<MBInventoryType>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBInventoryData mBInventoryData : set ) {
            set1.add( mBInventoryDataToMBInventoryType( mBInventoryData ) );
        }

        return set1;
    }

    protected MBCategoryType mBCategoryDataToMBCategoryType(MBCategoryData mBCategoryData) {
        if ( mBCategoryData == null ) {
            return null;
        }

        MBCategoryType mBCategoryType = new MBCategoryType();

        mBCategoryType.setCode( mBCategoryData.getCode() );
        mBCategoryType.setVisible( mBCategoryData.isVisible() );
        mBCategoryType.setActive( mBCategoryData.isActive() );
        mBCategoryType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( mBCategoryData.getModificationLogs() ) );
        mBCategoryType.setName( mBCategoryData.getName() );
        mBCategoryType.setInventories( mBInventoryDataSetToMBInventoryTypeSet( mBCategoryData.getInventories() ) );
        mBCategoryType.setFacility( mBFacilityDataToMBFacilityType( mBCategoryData.getFacility() ) );

        return mBCategoryType;
    }

    protected Set<MBCategoryType> mBCategoryDataSetToMBCategoryTypeSet(Set<MBCategoryData> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBCategoryType> set1 = new LinkedHashSet<MBCategoryType>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBCategoryData mBCategoryData : set ) {
            set1.add( mBCategoryDataToMBCategoryType( mBCategoryData ) );
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

    protected MBCapitalType mBCapitalDataToMBCapitalType(MBCapitalData mBCapitalData) {
        if ( mBCapitalData == null ) {
            return null;
        }

        MBCapitalType mBCapitalType = new MBCapitalType();

        mBCapitalType.setCode( mBCapitalData.getCode() );
        mBCapitalType.setVisible( mBCapitalData.isVisible() );
        mBCapitalType.setActive( mBCapitalData.isActive() );
        mBCapitalType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( mBCapitalData.getModificationLogs() ) );
        mBCapitalType.setCurrentValue( mBCapitalData.getCurrentValue() );
        mBCapitalType.setEntries( mBCapitalEntryDataSetToMBCapitalEntryTypeSet( mBCapitalData.getEntries() ) );

        return mBCapitalType;
    }

    protected MBFacilityType mBFacilityDataToMBFacilityType(MBFacilityData mBFacilityData) {
        if ( mBFacilityData == null ) {
            return null;
        }

        MBFacilityType mBFacilityType = new MBFacilityType();

        mBFacilityType.setCode( mBFacilityData.getCode() );
        mBFacilityType.setVisible( mBFacilityData.isVisible() );
        mBFacilityType.setActive( mBFacilityData.isActive() );
        mBFacilityType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( mBFacilityData.getModificationLogs() ) );
        mBFacilityType.setName( mBFacilityData.getName() );
        mBFacilityType.setAlias( mBFacilityData.getAlias() );
        mBFacilityType.setAddress( mBFacilityData.getAddress() );
        mBFacilityType.setCategories( mBCategoryDataSetToMBCategoryTypeSet( mBFacilityData.getCategories() ) );
        mBFacilityType.setClients( mBClientDataSetToMBClientTypeSet( mBFacilityData.getClients() ) );
        mBFacilityType.setCapital( mBCapitalDataToMBCapitalType( mBFacilityData.getCapital() ) );

        return mBFacilityType;
    }

    protected Set<MBRentContractType> mBRentContractDataSetToMBRentContractTypeSet(Set<MBRentContractData> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBRentContractType> set1 = new LinkedHashSet<MBRentContractType>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBRentContractData mBRentContractData : set ) {
            set1.add( rentContractMapper.contractToType( mBRentContractData ) );
        }

        return set1;
    }
}
