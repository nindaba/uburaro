package bi.manager.facade.converters.order;

import bi.manager.core.types.client.MBOrderType;
import bi.manager.facade.data.MBOrderData;
import java.util.ArrayList;
import java.util.Collection;
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

        return mBOrderData;
    }

    @Override
    public MBOrderType orderToType(MBOrderData order) {
        if ( order == null ) {
            return null;
        }

        MBOrderType mBOrderType = new MBOrderType();

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
}
