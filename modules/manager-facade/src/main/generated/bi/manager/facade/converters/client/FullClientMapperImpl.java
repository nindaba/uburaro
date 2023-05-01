package bi.manager.facade.converters.client;

import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBInvoiceType;
import bi.manager.core.types.client.MBOrderType;
import bi.manager.core.types.client.MBRentContractType;
import bi.manager.facade.converters.facility.FacilityMapper;
import bi.manager.facade.data.MBClientData;
import bi.manager.facade.data.MBInvoiceData;
import bi.manager.facade.data.MBOrderData;
import bi.manager.facade.data.MBRentContractData;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class FullClientMapperImpl implements FullClientMapper {

    @Autowired
    private FacilityMapper facilityMapper;
    @Autowired
    private RentContractMapper rentContractMapper;

    @Override
    public MBClientData clientToData(MBClientType client) {
        if ( client == null ) {
            return null;
        }

        MBClientData mBClientData = new MBClientData();

        mBClientData.setCode( client.getCode() );
        mBClientData.setActive( client.isActive() );
        mBClientData.setVisible( client.isVisible() );
        mBClientData.setContracts( mBRentContractTypeSetToMBRentContractDataSet( client.getContracts() ) );
        mBClientData.setTotalDebt( client.getTotalDebt() );
        mBClientData.setName( client.getName() );
        mBClientData.setFacility( facilityMapper.facilityToData( client.getFacility() ) );
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
        mBClientType.setFacility( facilityMapper.facilityToType( client.getFacility() ) );
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

    protected Set<MBRentContractData> mBRentContractTypeSetToMBRentContractDataSet(Set<MBRentContractType> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBRentContractData> set1 = new LinkedHashSet<MBRentContractData>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBRentContractType mBRentContractType : set ) {
            set1.add( rentContractMapper.contractToData( mBRentContractType ) );
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
