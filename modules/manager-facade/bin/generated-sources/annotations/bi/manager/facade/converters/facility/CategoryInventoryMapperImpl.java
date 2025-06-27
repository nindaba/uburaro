package bi.manager.facade.converters.facility;

import bi.manager.core.types.MBCategoryType;
import bi.manager.facade.data.MBCategoryData;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class CategoryInventoryMapperImpl implements CategoryInventoryMapper {

    @Override
    public MBCategoryData categoryToData(MBCategoryType category) {
        if ( category == null ) {
            return null;
        }

        MBCategoryData mBCategoryData = new MBCategoryData();

        return mBCategoryData;
    }

    @Override
    public MBCategoryType categoryToType(MBCategoryData category) {
        if ( category == null ) {
            return null;
        }

        MBCategoryType mBCategoryType = new MBCategoryType();

        return mBCategoryType;
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
}
