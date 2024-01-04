package bi.manager.facade.converters.order;

import bi.manager.core.types.client.MBOrderType;
import bi.manager.facade.data.MBOrderData;
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
public class OrderMapperImpl implements OrderMapper {

    @Override
    public MBOrderData orderToData(MBOrderType order) {
        if ( order == null ) {
            return null;
        }

        MBOrderData mBOrderData = new MBOrderData();

        mBOrderData.setCode( order.getCode() );
        mBOrderData.setActive( order.isActive() );
        mBOrderData.setVisible( order.isVisible() );
        mBOrderData.setPaid( order.isPaid() );
        mBOrderData.setOrderNumber( order.getOrderNumber() );
        mBOrderData.setQuantity( order.getQuantity() );
        mBOrderData.setUnit( order.getUnit() );
        mBOrderData.setOrderDate( order.getOrderDate() );
        mBOrderData.setCost( order.getCost() );

        return mBOrderData;
    }

    @Override
    public MBOrderType orderToType(MBOrderData order) {
        if ( order == null ) {
            return null;
        }

        MBOrderType mBOrderType = new MBOrderType();

        mBOrderType.setCode( order.getCode() );
        mBOrderType.setVisible( order.isVisible() );
        mBOrderType.setActive( order.isActive() );
        mBOrderType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( order.getModificationLogs() ) );
        mBOrderType.setOrderNumber( order.getOrderNumber() );
        mBOrderType.setQuantity( order.getQuantity() );
        mBOrderType.setUnit( order.getUnit() );
        mBOrderType.setOrderDate( order.getOrderDate() );
        mBOrderType.setCost( order.getCost() );
        mBOrderType.setPaid( order.isPaid() );

        return mBOrderType;
    }

    @Override
    public Collection<MBOrderType> ordersToType(Collection<MBOrderData> orders) {
        if ( orders == null ) {
            return null;
        }

        Collection<MBOrderType> collection = new ArrayList<MBOrderType>( orders.size() );
        for ( MBOrderData mBOrderData : orders ) {
            collection.add( orderToType( mBOrderData ) );
        }

        return collection;
    }

    @Override
    public Collection<MBOrderData> ordersToData(Collection<MBOrderType> orders) {
        if ( orders == null ) {
            return null;
        }

        Collection<MBOrderData> collection = new ArrayList<MBOrderData>( orders.size() );
        for ( MBOrderType mBOrderType : orders ) {
            collection.add( orderToData( mBOrderType ) );
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
}
