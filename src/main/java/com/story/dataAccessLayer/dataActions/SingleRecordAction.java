package com.story.dataAccessLayer.dataActions;

import com.story.dataAccessLayer.dbLevel.IQueryExecutor;
import com.story.dataAccessLayer.dbLevel.QueryDescriptor;
import com.story.dataAccessLayer.dbLevel.QueryExecutor;
import com.story.system.IDisposable;
import com.story.utils.GlobalHelper;
import com.story.utils.customException.InvalidDescriptor;
import com.story.utils.log.Trace;

/**
 * Created by alex on 13.07.16.
 * Class should be contained logic for work with single record
 */
abstract class SingleRecordAction implements IDisposable {
    /**
     * The executor which handled queries
     */
    private IQueryExecutor queryExecutor;

    /**
     * Initialize new instance of SingleRecordAction and set url
     * @param url path to database
     */
    protected SingleRecordAction(String url){
        this.queryExecutor = new QueryExecutor(url);
    }

    /**
     * Execute query
     * @param descriptor the query descriptor
     */
    protected final void execute(QueryDescriptor descriptor){
        try {
            this.validateDescriptor(descriptor);
        } catch (InvalidDescriptor e) {
            Trace.info(e.getMessage(), e);
            return;
        }

        this.queryExecutor.selectExecute(descriptor);
    }

    /**
     * Method checking the descriptor on correct of fields
     * @param descriptor the object
     * @throws InvalidDescriptor exception that catch when descriptor have invalid params
     */
    private void validateDescriptor(QueryDescriptor descriptor) throws InvalidDescriptor {
        if (descriptor == null){
            throw new InvalidDescriptor("Descriptor is null");
        }

        if (GlobalHelper.isNullOrEmpty(descriptor.getQuery())){
            throw new InvalidDescriptor("Query from descriptor is null or empty");
        }
    }

    @Override
    public void dispose(){
        this.queryExecutor = null;
    }
}
