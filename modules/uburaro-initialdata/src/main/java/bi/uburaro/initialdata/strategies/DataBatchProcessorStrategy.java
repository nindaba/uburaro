package bi.uburaro.initialdata.strategies;

import bi.uburaro.core.types.ItemType;
import bi.uburaro.initialdata.data.BatchData;
import bi.uburaro.initialdata.mappers.Mapper;

public interface DataBatchProcessorStrategy {

    /**
     * Maps the values to the target and import them in the database,
     * if the item is new it gets inserted, otherwise it is updated
     *
     * @param batch to be processed
     * @return true if the batch was successfully processed
     */
    boolean processBatch(BatchData batch);

}
