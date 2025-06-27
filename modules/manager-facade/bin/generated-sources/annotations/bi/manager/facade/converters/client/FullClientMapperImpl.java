package bi.manager.facade.converters.client;

import bi.manager.core.types.client.MBClientType;
import bi.manager.facade.data.MBClientData;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class FullClientMapperImpl implements FullClientMapper {

    @Override
    public MBClientData clientToData(MBClientType client) {
        if ( client == null ) {
            return null;
        }

        MBClientData mBClientData = new MBClientData();

        return mBClientData;
    }

    @Override
    public MBClientType clientToType(MBClientData client) {
        if ( client == null ) {
            return null;
        }

        MBClientType mBClientType = new MBClientType();

        return mBClientType;
    }

    @Override
    public Collection<MBClientData> clientsToData(Collection<MBClientType> clients) {
        if ( clients == null ) {
            return null;
        }

        Collection<MBClientData> collection = new ArrayList<MBClientData>( clients.size() );
        for ( MBClientType mBClientType : clients ) {
            collection.add( clientToData( mBClientType ) );
        }

        return collection;
    }
}
