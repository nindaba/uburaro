package bi.manager.facade.converters.order;

import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.core.types.MBInventoryType;
import bi.manager.core.types.client.MBClientType;
import bi.manager.facade.data.MBInventoryOrderData;
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
public class InventoryOrderMapperImpl implements InventoryOrderMapper {

    @Override
    public MBInventoryOrderData inventoryOrderToData(MBInventoryOrderType inventoryOrder) {
        if ( inventoryOrder == null ) {
            return null;
        }

        MBInventoryOrderData mBInventoryOrderData = new MBInventoryOrderData();

        mBInventoryOrderData.setClientName( inventoryOrderClientName( inventoryOrder ) );
        mBInventoryOrderData.setClientCode( inventoryOrderClientCode( inventoryOrder ) );
        mBInventoryOrderData.setItemName( inventoryOrderInventoryName( inventoryOrder ) );
        mBInventoryOrderData.setItemCode( inventoryOrderInventoryCode( inventoryOrder ) );
        mBInventoryOrderData.setCode( inventoryOrder.getCode() );
        mBInventoryOrderData.setActive( inventoryOrder.isActive() );
        mBInventoryOrderData.setVisible( inventoryOrder.isVisible() );
        mBInventoryOrderData.setPaid( inventoryOrder.isPaid() );
        mBInventoryOrderData.setOrderNumber( inventoryOrder.getOrderNumber() );
        mBInventoryOrderData.setQuantity( inventoryOrder.getQuantity() );
        mBInventoryOrderData.setUnit( inventoryOrder.getUnit() );
        mBInventoryOrderData.setOrderDate( inventoryOrder.getOrderDate() );
        mBInventoryOrderData.setCost( inventoryOrder.getCost() );
        mBInventoryOrderData.setOrderEntry( inventoryOrder.getOrderEntry() );

        return mBInventoryOrderData;
    }

    @Override
    public MBInventoryOrderType inventoryOrderToType(MBInventoryOrderData inventoryOrder) {
        if ( inventoryOrder == null ) {
            return null;
        }

        MBInventoryOrderType mBInventoryOrderType = new MBInventoryOrderType();

        mBInventoryOrderType.setClient( mBInventoryOrderDataToMBClientType( inventoryOrder ) );
        mBInventoryOrderType.setInventory( mBInventoryOrderDataToMBInventoryType( inventoryOrder ) );
        mBInventoryOrderType.setCode( inventoryOrder.getCode() );
        mBInventoryOrderType.setVisible( inventoryOrder.isVisible() );
        mBInventoryOrderType.setActive( inventoryOrder.isActive() );
        mBInventoryOrderType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( inventoryOrder.getModificationLogs() ) );
        mBInventoryOrderType.setOrderNumber( inventoryOrder.getOrderNumber() );
        mBInventoryOrderType.setQuantity( inventoryOrder.getQuantity() );
        mBInventoryOrderType.setUnit( inventoryOrder.getUnit() );
        mBInventoryOrderType.setOrderDate( inventoryOrder.getOrderDate() );
        mBInventoryOrderType.setCost( inventoryOrder.getCost() );
        mBInventoryOrderType.setPaid( inventoryOrder.isPaid() );
        mBInventoryOrderType.setOrderEntry( inventoryOrder.getOrderEntry() );

        return mBInventoryOrderType;
    }

    @Override
    public Collection<MBInventoryOrderData> inventoriesToData(Collection<MBInventoryOrderType> inventories) {
        if ( inventories == null ) {
            return null;
        }

        Collection<MBInventoryOrderData> collection = new ArrayList<MBInventoryOrderData>( inventories.size() );
        for ( MBInventoryOrderType mBInventoryOrderType : inventories ) {
            collection.add( inventoryOrderToData( mBInventoryOrderType ) );
        }

        return collection;
    }

    private String inventoryOrderClientName(MBInventoryOrderType mBInventoryOrderType) {
        if ( mBInventoryOrderType == null ) {
            return null;
        }
        MBClientType client = mBInventoryOrderType.getClient();
        if ( client == null ) {
            return null;
        }
        String name = client.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String inventoryOrderClientCode(MBInventoryOrderType mBInventoryOrderType) {
        if ( mBInventoryOrderType == null ) {
            return null;
        }
        MBClientType client = mBInventoryOrderType.getClient();
        if ( client == null ) {
            return null;
        }
        String code = client.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    private String inventoryOrderInventoryName(MBInventoryOrderType mBInventoryOrderType) {
        if ( mBInventoryOrderType == null ) {
            return null;
        }
        MBInventoryType inventory = mBInventoryOrderType.getInventory();
        if ( inventory == null ) {
            return null;
        }
        String name = inventory.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String inventoryOrderInventoryCode(MBInventoryOrderType mBInventoryOrderType) {
        if ( mBInventoryOrderType == null ) {
            return null;
        }
        MBInventoryType inventory = mBInventoryOrderType.getInventory();
        if ( inventory == null ) {
            return null;
        }
        String code = inventory.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    protected MBClientType mBInventoryOrderDataToMBClientType(MBInventoryOrderData mBInventoryOrderData) {
        if ( mBInventoryOrderData == null ) {
            return null;
        }

        MBClientType mBClientType = new MBClientType();

        mBClientType.setName( mBInventoryOrderData.getClientName() );
        mBClientType.setCode( mBInventoryOrderData.getClientCode() );

        return mBClientType;
    }

    protected MBInventoryType mBInventoryOrderDataToMBInventoryType(MBInventoryOrderData mBInventoryOrderData) {
        if ( mBInventoryOrderData == null ) {
            return null;
        }

        MBInventoryType mBInventoryType = new MBInventoryType();

        mBInventoryType.setName( mBInventoryOrderData.getItemName() );
        mBInventoryType.setCode( mBInventoryOrderData.getItemCode() );

        return mBInventoryType;
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
