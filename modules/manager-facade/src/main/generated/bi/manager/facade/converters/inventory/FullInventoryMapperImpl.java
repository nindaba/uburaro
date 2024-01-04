package bi.manager.facade.converters.inventory;

import bi.manager.core.types.MBInventoryOrderType;
import bi.manager.core.types.MBInventoryType;
import bi.manager.facade.converters.category.CategoryMapper;
import bi.manager.facade.converters.order.InventoryOrderMapper;
import bi.manager.facade.data.MBInventoryData;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class FullInventoryMapperImpl implements FullInventoryMapper {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private InventoryOrderMapper inventoryOrderMapper;

    @Override
    public MBInventoryData inventoryToData(MBInventoryType inventory) {
        if ( inventory == null ) {
            return null;
        }

        MBInventoryData mBInventoryData = new MBInventoryData();

        mBInventoryData.setCode( inventory.getCode() );
        mBInventoryData.setActive( inventory.isActive() );
        mBInventoryData.setVisible( inventory.isVisible() );
        mBInventoryData.setInventoryOrders( mBInventoryOrderTypeSetToMBInventoryOrderDataSet( inventory.getInventoryOrders() ) );
        mBInventoryData.setCategory( categoryMapper.categoryToData( inventory.getCategory() ) );
        mBInventoryData.setName( inventory.getName() );
        mBInventoryData.setQuantity( inventory.getQuantity() );
        mBInventoryData.setCost( inventory.getCost() );
        mBInventoryData.setUnit( inventory.getUnit() );

        return mBInventoryData;
    }

    @Override
    public MBInventoryType inventoryToType(MBInventoryData inventory) {
        if ( inventory == null ) {
            return null;
        }

        MBInventoryType mBInventoryType = new MBInventoryType();

        mBInventoryType.setCode( inventory.getCode() );
        mBInventoryType.setVisible( inventory.isVisible() );
        mBInventoryType.setActive( inventory.isActive() );
        mBInventoryType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( inventory.getModificationLogs() ) );
        mBInventoryType.setInventoryOrders( mBInventoryOrderDataSetToMBInventoryOrderTypeSet( inventory.getInventoryOrders() ) );
        mBInventoryType.setCategory( categoryMapper.categoryToType( inventory.getCategory() ) );
        mBInventoryType.setName( inventory.getName() );
        mBInventoryType.setUnit( inventory.getUnit() );
        if ( inventory.getQuantity() != null ) {
            mBInventoryType.setQuantity( inventory.getQuantity() );
        }
        if ( inventory.getCost() != null ) {
            mBInventoryType.setCost( inventory.getCost() );
        }

        return mBInventoryType;
    }

    @Override
    public Collection<MBInventoryData> inventoriesToData(Collection<MBInventoryType> inventories) {
        if ( inventories == null ) {
            return null;
        }

        Collection<MBInventoryData> collection = new ArrayList<MBInventoryData>( inventories.size() );
        for ( MBInventoryType mBInventoryType : inventories ) {
            collection.add( inventoryToData( mBInventoryType ) );
        }

        return collection;
    }

    protected Set<MBInventoryOrderData> mBInventoryOrderTypeSetToMBInventoryOrderDataSet(Set<MBInventoryOrderType> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBInventoryOrderData> set1 = new LinkedHashSet<MBInventoryOrderData>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBInventoryOrderType mBInventoryOrderType : set ) {
            set1.add( inventoryOrderMapper.inventoryOrderToData( mBInventoryOrderType ) );
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

    protected Set<MBInventoryOrderType> mBInventoryOrderDataSetToMBInventoryOrderTypeSet(Set<MBInventoryOrderData> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBInventoryOrderType> set1 = new LinkedHashSet<MBInventoryOrderType>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBInventoryOrderData mBInventoryOrderData : set ) {
            set1.add( inventoryOrderMapper.inventoryOrderToType( mBInventoryOrderData ) );
        }

        return set1;
    }
}
