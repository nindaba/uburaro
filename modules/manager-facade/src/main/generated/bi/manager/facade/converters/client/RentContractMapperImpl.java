package bi.manager.facade.converters.client;

import bi.manager.core.types.MBRentPropertyType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBRentContractType;
import bi.manager.core.types.client.MBRentOrderType;
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
public class RentContractMapperImpl implements RentContractMapper {

    @Override
    public MBRentContractData contractToData(MBRentContractType contract) {
        if ( contract == null ) {
            return null;
        }

        MBRentContractData mBRentContractData = new MBRentContractData();

        mBRentContractData.setProperty( rentPropertyToData( contract.getRentProperty() ) );
        mBRentContractData.setCode( contract.getCode() );
        mBRentContractData.setActive( contract.isActive() );
        mBRentContractData.setVisible( contract.isVisible() );
        mBRentContractData.setContractFileName( contract.getContractFileName() );
        mBRentContractData.setFrom( contract.getFrom() );
        mBRentContractData.setNextOrderDate( contract.getNextOrderDate() );
        mBRentContractData.setTo( contract.getTo() );
        mBRentContractData.setCostPerUnit( contract.getCostPerUnit() );
        mBRentContractData.setUnit( contract.getUnit() );
        mBRentContractData.setClient( clientToData( contract.getClient() ) );

        mBRentContractData.setHasOrders( !contract.getOrders().isEmpty() );

        return mBRentContractData;
    }

    @Override
    public MBRentContractType contractToType(MBRentContractData contract) {
        if ( contract == null ) {
            return null;
        }

        MBRentContractType mBRentContractType = new MBRentContractType();

        mBRentContractType.setRentProperty( namedItemDataToMBRentPropertyType( contract.getProperty() ) );
        mBRentContractType.setCode( contract.getCode() );
        mBRentContractType.setVisible( contract.isVisible() );
        mBRentContractType.setActive( contract.isActive() );
        mBRentContractType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( contract.getModificationLogs() ) );
        mBRentContractType.setFrom( contract.getFrom() );
        mBRentContractType.setTo( contract.getTo() );
        mBRentContractType.setCostPerUnit( contract.getCostPerUnit() );
        mBRentContractType.setUnit( contract.getUnit() );
        mBRentContractType.setContractFileName( contract.getContractFileName() );
        mBRentContractType.setNextOrderDate( contract.getNextOrderDate() );
        mBRentContractType.setClient( namedItemDataToMBClientType( contract.getClient() ) );
        mBRentContractType.setOrders( mBRentOrderDataSetToMBRentOrderTypeSet( contract.getOrders() ) );

        return mBRentContractType;
    }

    @Override
    public Collection<MBRentContractData> contractsToData(Collection<MBRentContractType> contracts) {
        if ( contracts == null ) {
            return null;
        }

        Collection<MBRentContractData> collection = new ArrayList<MBRentContractData>( contracts.size() );
        for ( MBRentContractType mBRentContractType : contracts ) {
            collection.add( contractToData( mBRentContractType ) );
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

    protected MBRentPropertyType namedItemDataToMBRentPropertyType(NamedItemData namedItemData) {
        if ( namedItemData == null ) {
            return null;
        }

        MBRentPropertyType mBRentPropertyType = new MBRentPropertyType();

        mBRentPropertyType.setCode( namedItemData.getCode() );
        mBRentPropertyType.setVisible( namedItemData.isVisible() );
        mBRentPropertyType.setActive( namedItemData.isActive() );
        mBRentPropertyType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( namedItemData.getModificationLogs() ) );
        mBRentPropertyType.setName( namedItemData.getName() );

        return mBRentPropertyType;
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
}
