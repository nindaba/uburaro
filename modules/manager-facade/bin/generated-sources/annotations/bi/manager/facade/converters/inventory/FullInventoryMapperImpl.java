package bi.manager.facade.converters.inventory;

import bi.manager.core.types.MBInventoryType;
import bi.manager.facade.data.MBInventoryData;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class FullInventoryMapperImpl implements FullInventoryMapper {

    @Override
    public MBInventoryData inventoryToData(MBInventoryType inventory) {
        if ( inventory == null ) {
            return null;
        }

        MBInventoryData mBInventoryData = new MBInventoryData();

        return mBInventoryData;
    }

    @Override
    public MBInventoryType inventoryToType(MBInventoryData inventory) {
        if ( inventory == null ) {
            return null;
        }

        MBInventoryType mBInventoryType = new MBInventoryType();

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
}
