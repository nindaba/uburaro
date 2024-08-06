package bi.manager.facade.converters.modification;

import bi.uburaro.core.types.ModificationLogType;
import bi.uburaro.facade.data.ModificationLogData;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ModificationLogMapperImpl implements ModificationLogMapper {

    @Override
    public ModificationLogData mapToData(ModificationLogType type) {
        if ( type == null ) {
            return null;
        }

        ModificationLogData modificationLogData = new ModificationLogData();

        modificationLogData.setModifiedProperty( type.getModifiedProperty() );
        modificationLogData.setDateModified( type.getDateModified() );
        modificationLogData.setPreviousValueCode( type.getPreviousValueCode() );
        modificationLogData.setPreviousValue( type.getPreviousValue() );
        modificationLogData.setValueCode( type.getValueCode() );
        modificationLogData.setValue( type.getValue() );

        return modificationLogData;
    }

    @Override
    public Set<ModificationLogData> mapToDataCollection(Set<ModificationLogType> types) {
        if ( types == null ) {
            return null;
        }

        Set<ModificationLogData> set = new LinkedHashSet<ModificationLogData>( Math.max( (int) ( types.size() / .75f ) + 1, 16 ) );
        for ( ModificationLogType modificationLogType : types ) {
            set.add( mapToData( modificationLogType ) );
        }

        return set;
    }
}
