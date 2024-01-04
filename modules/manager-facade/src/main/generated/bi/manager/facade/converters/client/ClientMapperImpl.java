package bi.manager.facade.converters.client;

import bi.manager.core.types.MBCapitalEntryType;
import bi.manager.core.types.MBCapitalType;
import bi.manager.core.types.MBCategoryType;
import bi.manager.core.types.MBFacilityType;
import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.core.types.MBInventoryType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBInvoiceType;
import bi.manager.core.types.client.MBOrderType;
import bi.manager.core.types.client.MBRentContractType;
import bi.manager.core.types.client.MBRentOrderType;
import bi.manager.core.types.enums.MBEntryEnum;
import bi.manager.facade.data.MBCapitalData;
import bi.manager.facade.data.MBCapitalEntryData;
import bi.manager.facade.data.MBCategoryData;
import bi.manager.facade.data.MBClientData;
import bi.manager.facade.data.MBFacilityData;
import bi.manager.facade.data.MBInventoryData;
import bi.manager.facade.data.MBInventoryOrderData;
import bi.manager.facade.data.MBInvoiceData;
import bi.manager.facade.data.MBOrderData;
import bi.manager.facade.data.MBRentContractData;
import bi.manager.facade.data.MBRentOrderData;
import bi.manager.facade.data.NamedItemData;
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
public class ClientMapperImpl implements ClientMapper {

    @Override
    public MBClientData clientToData(MBClientType client) {
        if ( client == null ) {
            return null;
        }

        MBClientData mBClientData = new MBClientData();

        mBClientData.setCode( client.getCode() );
        mBClientData.setActive( client.isActive() );
        mBClientData.setVisible( client.isVisible() );
        mBClientData.setTotalDebt( client.getTotalDebt() );
        mBClientData.setName( client.getName() );
        mBClientData.setAddress( client.getAddress() );

        return mBClientData;
    }

    @Override
    public MBClientType clientToType(MBClientData client) {
        if ( client == null ) {
            return null;
        }

        MBClientType mBClientType = new MBClientType();

        mBClientType.setCode( client.getCode() );
        mBClientType.setVisible( client.isVisible() );
        mBClientType.setActive( client.isActive() );
        mBClientType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( client.getModificationLogs() ) );
        mBClientType.setName( client.getName() );
        mBClientType.setAddress( client.getAddress() );
        mBClientType.setTotalDebt( client.getTotalDebt() );
        mBClientType.setOrders( mBOrderDataSetToMBOrderTypeSet( client.getOrders() ) );
        mBClientType.setInvoices( mBInvoiceDataSetToMBInvoiceTypeSet( client.getInvoices() ) );
        mBClientType.setFacility( mBFacilityDataToMBFacilityType( client.getFacility() ) );
        mBClientType.setContracts( mBRentContractDataSetToMBRentContractTypeSet( client.getContracts() ) );

        return mBClientType;
    }

    @Override
    public Collection<MBClientData> clientsToData(Collection<MBClientType> clients) {
        if ( clients == null ) {
            return null;
        }

        Collection<MBClientData> collection = new ArrayList<MBClientData>( clients.size() );
        for ( MBClientType mBClientType : clients ) {
            collection.add( clientToData( mBClientType ) );
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

    protected MBOrderType mBOrderDataToMBOrderType(MBOrderData mBOrderData) {
        if ( mBOrderData == null ) {
            return null;
        }

        MBOrderType mBOrderType = new MBOrderType();

        mBOrderType.setCode( mBOrderData.getCode() );
        mBOrderType.setVisible( mBOrderData.isVisible() );
        mBOrderType.setActive( mBOrderData.isActive() );
        mBOrderType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( mBOrderData.getModificationLogs() ) );
        mBOrderType.setOrderNumber( mBOrderData.getOrderNumber() );
        mBOrderType.setQuantity( mBOrderData.getQuantity() );
        mBOrderType.setUnit( mBOrderData.getUnit() );
        mBOrderType.setOrderDate( mBOrderData.getOrderDate() );
        mBOrderType.setCost( mBOrderData.getCost() );
        mBOrderType.setPaid( mBOrderData.isPaid() );

        return mBOrderType;
    }

    protected Set<MBOrderType> mBOrderDataSetToMBOrderTypeSet(Set<MBOrderData> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBOrderType> set1 = new LinkedHashSet<MBOrderType>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBOrderData mBOrderData : set ) {
            set1.add( mBOrderDataToMBOrderType( mBOrderData ) );
        }

        return set1;
    }

    protected MBClientType namedItemDataToMBClientType(NamedItemData namedItemData) {
        if ( namedItemData == null ) {
            return null;
        }

        MBClientType mBClientType = new MBClientType();

        mBClientType.setCode( namedItemData.getCode() );
        mBClientType.setVisible( namedItemData.isVisible() );
        mBClientType.setActive( namedItemData.isActive() );
        mBClientType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( namedItemData.getModificationLogs() ) );
        mBClientType.setName( namedItemData.getName() );

        return mBClientType;
    }

    protected MBInvoiceType mBInvoiceDataToMBInvoiceType(MBInvoiceData mBInvoiceData) {
        if ( mBInvoiceData == null ) {
            return null;
        }

        MBInvoiceType mBInvoiceType = new MBInvoiceType();

        mBInvoiceType.setCode( mBInvoiceData.getCode() );
        mBInvoiceType.setVisible( mBInvoiceData.isVisible() );
        mBInvoiceType.setActive( mBInvoiceData.isActive() );
        mBInvoiceType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( mBInvoiceData.getModificationLogs() ) );
        mBInvoiceType.setClient( namedItemDataToMBClientType( mBInvoiceData.getClient() ) );
        mBInvoiceType.setOrders( mBOrderDataSetToMBOrderTypeSet( mBInvoiceData.getOrders() ) );
        mBInvoiceType.setAmount( mBInvoiceData.getAmount() );
        mBInvoiceType.setInvoiceNumber( mBInvoiceData.getInvoiceNumber() );
        mBInvoiceType.setPaymentMode( mBInvoiceData.getPaymentMode() );
        mBInvoiceType.setDescription( mBInvoiceData.getDescription() );

        return mBInvoiceType;
    }

    protected Set<MBInvoiceType> mBInvoiceDataSetToMBInvoiceTypeSet(Set<MBInvoiceData> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBInvoiceType> set1 = new LinkedHashSet<MBInvoiceType>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBInvoiceData mBInvoiceData : set ) {
            set1.add( mBInvoiceDataToMBInvoiceType( mBInvoiceData ) );
        }

        return set1;
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
            set1.add( clientToType( mBClientData ) );
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

    protected MBRentOrderType mBRentOrderDataToMBRentOrderType(MBRentOrderData mBRentOrderData) {
        if ( mBRentOrderData == null ) {
            return null;
        }

        MBRentOrderType mBRentOrderType = new MBRentOrderType();

        mBRentOrderType.setCode( mBRentOrderData.getCode() );
        mBRentOrderType.setVisible( mBRentOrderData.isVisible() );
        mBRentOrderType.setActive( mBRentOrderData.isActive() );
        mBRentOrderType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( mBRentOrderData.getModificationLogs() ) );
        mBRentOrderType.setOrderNumber( mBRentOrderData.getOrderNumber() );
        mBRentOrderType.setQuantity( mBRentOrderData.getQuantity() );
        mBRentOrderType.setUnit( mBRentOrderData.getUnit() );
        mBRentOrderType.setOrderDate( mBRentOrderData.getOrderDate() );
        mBRentOrderType.setCost( mBRentOrderData.getCost() );
        mBRentOrderType.setPaid( mBRentOrderData.isPaid() );
        mBRentOrderType.setFrom( mBRentOrderData.getFrom() );
        mBRentOrderType.setTo( mBRentOrderData.getTo() );

        return mBRentOrderType;
    }

    protected Set<MBRentOrderType> mBRentOrderDataSetToMBRentOrderTypeSet(Set<MBRentOrderData> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBRentOrderType> set1 = new LinkedHashSet<MBRentOrderType>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBRentOrderData mBRentOrderData : set ) {
            set1.add( mBRentOrderDataToMBRentOrderType( mBRentOrderData ) );
        }

        return set1;
    }

    protected MBRentContractType mBRentContractDataToMBRentContractType(MBRentContractData mBRentContractData) {
        if ( mBRentContractData == null ) {
            return null;
        }

        MBRentContractType mBRentContractType = new MBRentContractType();

        mBRentContractType.setCode( mBRentContractData.getCode() );
        mBRentContractType.setVisible( mBRentContractData.isVisible() );
        mBRentContractType.setActive( mBRentContractData.isActive() );
        mBRentContractType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( mBRentContractData.getModificationLogs() ) );
        mBRentContractType.setFrom( mBRentContractData.getFrom() );
        mBRentContractType.setTo( mBRentContractData.getTo() );
        mBRentContractType.setCostPerUnit( mBRentContractData.getCostPerUnit() );
        mBRentContractType.setUnit( mBRentContractData.getUnit() );
        mBRentContractType.setContractFileName( mBRentContractData.getContractFileName() );
        mBRentContractType.setNextOrderDate( mBRentContractData.getNextOrderDate() );
        mBRentContractType.setClient( namedItemDataToMBClientType( mBRentContractData.getClient() ) );
        mBRentContractType.setOrders( mBRentOrderDataSetToMBRentOrderTypeSet( mBRentContractData.getOrders() ) );

        return mBRentContractType;
    }

    protected Set<MBRentContractType> mBRentContractDataSetToMBRentContractTypeSet(Set<MBRentContractData> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBRentContractType> set1 = new LinkedHashSet<MBRentContractType>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBRentContractData mBRentContractData : set ) {
            set1.add( mBRentContractDataToMBRentContractType( mBRentContractData ) );
        }

        return set1;
    }
}
