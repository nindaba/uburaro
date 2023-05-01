package bi.manager.facade.converters.order;

import bi.manager.core.types.MBRentPropertyType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBRentOrderType;
import bi.manager.facade.data.MBRentOrderData;
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
public class RentOrderMapperImpl implements RentOrderMapper {

    @Override
    public MBRentOrderData rentOrderToData(MBRentOrderType inventoryOrder) {
        if ( inventoryOrder == null ) {
            return null;
        }

        MBRentOrderData mBRentOrderData = new MBRentOrderData();

        mBRentOrderData.setClientName( inventoryOrderClientName( inventoryOrder ) );
        mBRentOrderData.setClientCode( inventoryOrderClientCode( inventoryOrder ) );
        mBRentOrderData.setItemName( inventoryOrderRentPropertyName( inventoryOrder ) );
        mBRentOrderData.setItemCode( inventoryOrderRentPropertyCode( inventoryOrder ) );
        mBRentOrderData.setCode( inventoryOrder.getCode() );
        mBRentOrderData.setActive( inventoryOrder.isActive() );
        mBRentOrderData.setVisible( inventoryOrder.isVisible() );
        mBRentOrderData.setPaid( inventoryOrder.isPaid() );
        mBRentOrderData.setOrderNumber( inventoryOrder.getOrderNumber() );
        mBRentOrderData.setQuantity( inventoryOrder.getQuantity() );
        mBRentOrderData.setUnit( inventoryOrder.getUnit() );
        mBRentOrderData.setOrderDate( inventoryOrder.getOrderDate() );
        mBRentOrderData.setCost( inventoryOrder.getCost() );
        mBRentOrderData.setFrom( inventoryOrder.getFrom() );
        mBRentOrderData.setTo( inventoryOrder.getTo() );

        return mBRentOrderData;
    }

    @Override
    public MBRentOrderType rentOrderToType(MBRentOrderData inventoryOrder) {
        if ( inventoryOrder == null ) {
            return null;
        }

        MBRentOrderType mBRentOrderType = new MBRentOrderType();

        mBRentOrderType.setClient( mBRentOrderDataToMBClientType( inventoryOrder ) );
        mBRentOrderType.setRentProperty( mBRentOrderDataToMBRentPropertyType( inventoryOrder ) );
        mBRentOrderType.setCode( inventoryOrder.getCode() );
        mBRentOrderType.setVisible( inventoryOrder.isVisible() );
        mBRentOrderType.setActive( inventoryOrder.isActive() );
        mBRentOrderType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( inventoryOrder.getModificationLogs() ) );
        mBRentOrderType.setOrderNumber( inventoryOrder.getOrderNumber() );
        mBRentOrderType.setQuantity( inventoryOrder.getQuantity() );
        mBRentOrderType.setUnit( inventoryOrder.getUnit() );
        mBRentOrderType.setOrderDate( inventoryOrder.getOrderDate() );
        mBRentOrderType.setCost( inventoryOrder.getCost() );
        mBRentOrderType.setPaid( inventoryOrder.isPaid() );
        mBRentOrderType.setFrom( inventoryOrder.getFrom() );
        mBRentOrderType.setTo( inventoryOrder.getTo() );

        return mBRentOrderType;
    }

    @Override
    public Collection<MBRentOrderData> rentsToData(Collection<MBRentOrderType> inventories) {
        if ( inventories == null ) {
            return null;
        }

        Collection<MBRentOrderData> collection = new ArrayList<MBRentOrderData>( inventories.size() );
        for ( MBRentOrderType mBRentOrderType : inventories ) {
            collection.add( rentOrderToData( mBRentOrderType ) );
        }

        return collection;
    }

    private String inventoryOrderClientName(MBRentOrderType mBRentOrderType) {
        if ( mBRentOrderType == null ) {
            return null;
        }
        MBClientType client = mBRentOrderType.getClient();
        if ( client == null ) {
            return null;
        }
        String name = client.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String inventoryOrderClientCode(MBRentOrderType mBRentOrderType) {
        if ( mBRentOrderType == null ) {
            return null;
        }
        MBClientType client = mBRentOrderType.getClient();
        if ( client == null ) {
            return null;
        }
        String code = client.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    private String inventoryOrderRentPropertyName(MBRentOrderType mBRentOrderType) {
        if ( mBRentOrderType == null ) {
            return null;
        }
        MBRentPropertyType rentProperty = mBRentOrderType.getRentProperty();
        if ( rentProperty == null ) {
            return null;
        }
        String name = rentProperty.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String inventoryOrderRentPropertyCode(MBRentOrderType mBRentOrderType) {
        if ( mBRentOrderType == null ) {
            return null;
        }
        MBRentPropertyType rentProperty = mBRentOrderType.getRentProperty();
        if ( rentProperty == null ) {
            return null;
        }
        String code = rentProperty.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    protected MBClientType mBRentOrderDataToMBClientType(MBRentOrderData mBRentOrderData) {
        if ( mBRentOrderData == null ) {
            return null;
        }

        MBClientType mBClientType = new MBClientType();

        mBClientType.setName( mBRentOrderData.getClientName() );
        mBClientType.setCode( mBRentOrderData.getClientCode() );

        return mBClientType;
    }

    protected MBRentPropertyType mBRentOrderDataToMBRentPropertyType(MBRentOrderData mBRentOrderData) {
        if ( mBRentOrderData == null ) {
            return null;
        }

        MBRentPropertyType mBRentPropertyType = new MBRentPropertyType();

        mBRentPropertyType.setName( mBRentOrderData.getItemName() );
        mBRentPropertyType.setCode( mBRentOrderData.getItemCode() );

        return mBRentPropertyType;
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
}
