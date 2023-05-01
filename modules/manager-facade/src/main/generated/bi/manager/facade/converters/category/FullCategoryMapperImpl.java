package bi.manager.facade.converters.category;

import bi.manager.core.types.MBCategoryType;
import bi.manager.core.types.MBInventoryType;
import bi.manager.facade.converters.inventory.InventoryMapper;
import bi.manager.facade.data.MBCategoryData;
import bi.manager.facade.data.MBInventoryData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class FullCategoryMapperImpl implements FullCategoryMapper {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public MBCategoryData categoryToData(MBCategoryType category) {
        if ( category == null ) {
            return null;
        }

        MBCategoryData mBCategoryData = new MBCategoryData();

        mBCategoryData.setCode( category.getCode() );
        mBCategoryData.setActive( category.isActive() );
        mBCategoryData.setVisible( category.isVisible() );
        mBCategoryData.setName( category.getName() );
        mBCategoryData.setInventories( mBInventoryTypeSetToMBInventoryDataSet( category.getInventories() ) );

        return mBCategoryData;
    }

    @Override
    public Collection<MBCategoryData> categoriesToData(Collection<MBCategoryType> categories) {
        if ( categories == null ) {
            return null;
        }

        Collection<MBCategoryData> collection = new ArrayList<MBCategoryData>( categories.size() );
        for ( MBCategoryType mBCategoryType : categories ) {
            collection.add( categoryToData( mBCategoryType ) );
        }

        return collection;
    }

    protected Set<MBInventoryData> mBInventoryTypeSetToMBInventoryDataSet(Set<MBInventoryType> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBInventoryData> set1 = new LinkedHashSet<MBInventoryData>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBInventoryType mBInventoryType : set ) {
            set1.add( inventoryMapper.inventoryToData( mBInventoryType ) );
        }

        return set1;
    }
}
