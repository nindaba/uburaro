package bi.manager.facade.converters.rent;

import bi.manager.core.types.MBRentPropertyType;
import bi.manager.facade.data.MBRentPropertyData;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class FullRentPropertyMapperImpl implements FullRentPropertyMapper {

    @Override
    public MBRentPropertyData rentPropertyToData(MBRentPropertyType rent) {
        if ( rent == null ) {
            return null;
        }

        MBRentPropertyData mBRentPropertyData = new MBRentPropertyData();

        return mBRentPropertyData;
    }

    @Override
    public MBRentPropertyType rentPropertyToType(MBRentPropertyData rent) {
        if ( rent == null ) {
            return null;
        }

        MBRentPropertyType mBRentPropertyType = new MBRentPropertyType();

        return mBRentPropertyType;
    }

    @Override
    public Collection<MBRentPropertyData> rentPropertiesToData(Collection<MBRentPropertyType> rents) {
        if ( rents == null ) {
            return null;
        }

        Collection<MBRentPropertyData> collection = new ArrayList<MBRentPropertyData>( rents.size() );
        for ( MBRentPropertyType mBRentPropertyType : rents ) {
            collection.add( rentPropertyToData( mBRentPropertyType ) );
        }

        return collection;
    }
}
