package bi.manager.facade.converters.client;

import bi.manager.core.types.client.MBClientType;
import bi.manager.core.types.client.MBInvoiceType;
import bi.manager.core.types.client.MBOrderType;
import bi.manager.facade.converters.order.OrderMapper;
import bi.manager.facade.data.MBInvoiceData;
import bi.manager.facade.data.MBOrderData;
import bi.manager.facade.data.NamedItemData;
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
public class InvoiceMapperImpl implements InvoiceMapper {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public MBInvoiceData invoiceToDate(MBInvoiceType invoice) {
        if ( invoice == null ) {
            return null;
        }

        MBInvoiceData mBInvoiceData = new MBInvoiceData();

        mBInvoiceData.setDateCreated( invoice.getDateModified() );
        mBInvoiceData.setCode( invoice.getCode() );
        mBInvoiceData.setActive( invoice.isActive() );
        mBInvoiceData.setVisible( invoice.isVisible() );
        mBInvoiceData.setClient( clientToData( invoice.getClient() ) );
        mBInvoiceData.setOrders( mBOrderTypeSetToMBOrderDataSet( invoice.getOrders() ) );
        mBInvoiceData.setAmount( invoice.getAmount() );
        mBInvoiceData.setInvoiceNumber( invoice.getInvoiceNumber() );
        mBInvoiceData.setPaymentMode( invoice.getPaymentMode() );
        mBInvoiceData.setDescription( invoice.getDescription() );

        return mBInvoiceData;
    }

    @Override
    public MBInvoiceType invoiceToType(MBInvoiceData invoice) {
        if ( invoice == null ) {
            return null;
        }

        MBInvoiceType mBInvoiceType = new MBInvoiceType();

        mBInvoiceType.setDateModified( invoice.getDateCreated() );
        mBInvoiceType.setCode( invoice.getCode() );
        mBInvoiceType.setVisible( invoice.isVisible() );
        mBInvoiceType.setActive( invoice.isActive() );
        mBInvoiceType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( invoice.getModificationLogs() ) );
        mBInvoiceType.setClient( namedItemDataToMBClientType( invoice.getClient() ) );
        mBInvoiceType.setOrders( mBOrderDataSetToMBOrderTypeSet( invoice.getOrders() ) );
        mBInvoiceType.setAmount( invoice.getAmount() );
        mBInvoiceType.setInvoiceNumber( invoice.getInvoiceNumber() );
        mBInvoiceType.setPaymentMode( invoice.getPaymentMode() );
        mBInvoiceType.setDescription( invoice.getDescription() );

        return mBInvoiceType;
    }

    @Override
    public Collection<MBInvoiceData> invoicesToData(Collection<MBInvoiceType> invoices) {
        if ( invoices == null ) {
            return null;
        }

        Collection<MBInvoiceData> collection = new ArrayList<MBInvoiceData>( invoices.size() );
        for ( MBInvoiceType mBInvoiceType : invoices ) {
            collection.add( invoiceToDate( mBInvoiceType ) );
        }

        return collection;
    }

    protected Set<MBOrderData> mBOrderTypeSetToMBOrderDataSet(Set<MBOrderType> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBOrderData> set1 = new LinkedHashSet<MBOrderData>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBOrderType mBOrderType : set ) {
            set1.add( orderMapper.orderToData( mBOrderType ) );
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

    protected MBClientType namedItemDataToMBClientType(NamedItemData namedItemData) {
        if ( namedItemData == null ) {
            return null;
        }

        MBClientType mBClientType = new MBClientType();

        mBClientType.setCode( namedItemData.getCode() );
        mBClientType.setVisible( namedItemData.isVisible() );
        mBClientType.setActive( namedItemData.isActive() );
        mBClientType.setModificationLogs( modificationLogDataListToModificationLogTypeSet( namedItemData.getModificationLogs() ) );
        mBClientType.setName( namedItemData.getName() );

        return mBClientType;
    }

    protected Set<MBOrderType> mBOrderDataSetToMBOrderTypeSet(Set<MBOrderData> set) {
        if ( set == null ) {
            return null;
        }

        Set<MBOrderType> set1 = new LinkedHashSet<MBOrderType>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MBOrderData mBOrderData : set ) {
            set1.add( orderMapper.orderToType( mBOrderData ) );
        }

        return set1;
    }
}
