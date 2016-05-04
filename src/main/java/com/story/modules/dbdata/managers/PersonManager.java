package com.story.modules.dbdata.managers;

import com.story.modules.dbWorker.IQueryExecutor;
import com.story.modules.dbdata.descriptor.DBTableDescriptor;
import com.story.modules.dbdata.descriptor.PersonPictureDescriptor;
import com.story.modules.dbdata.descriptor.PictureObjectDescriptor;
import com.story.modules.dbdata.managers.queryProcesses.PersonTableProcess;
import com.story.modules.dbdata.descriptor.PersonDescriptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 29.03.16.
 */
public class PersonManager implements IManager {
    private IQueryExecutor executor;

    public PersonManager(IQueryExecutor executor){
        this.executor = executor;
    }

    @Override
    public PersonDescriptor getData(int id) {
        PersonDescriptor p = this.getPerson(id);
        if (p == null){
            return null;
        }
        p.setPictureDescriptors(this.getPictureObject(this.getPictureDescriptor(p.getPictureDescriptorId())));

        return p;
    }

    @Override
    public List<DBTableDescriptor> getData(int[] ids) {
        return null;
    }

    private PersonDescriptor getPerson(int id){
        String query = "SELECT *  FROM "
                + PersonDescriptor.DBTableName + " WHERE " + PersonDescriptor.DBFieldId
                + "=" + id;

        PersonTableProcess process = new PersonTableProcess(query);
        this.executor.selectExecute(process);
        return process.getPlayer();
    }

    /**
     *
     * @param id
     * @return PersonPictureDescriptor for current person
     */
    private PersonPictureDescriptor getPictureDescriptor(int id){
        PersonPictureManager pmanager = new PersonPictureManager(this.executor);
        return (PersonPictureDescriptor) pmanager.getData(id);
    }

    /**
     * Get ArrayList of PictureObjectDescriptor and calculate side for each item
     * @param ppd PersonPictureDescriptor for current picture set
     * @return set of pictures
     */
    private ArrayList<PictureObjectDescriptor> getPictureObject(PersonPictureDescriptor ppd){
        if (ppd == null){
            return null;
        }

        PictureObjectManager poManager = new PictureObjectManager(this.executor);

        ArrayList<PictureObjectDescriptor> descriptors = new ArrayList<>();
        //Cast DBTableDescriptor to PersonPictureDescriptor and calculate side
        for (DBTableDescriptor d: poManager.getData(ppd.getIdsAsArray())) {
            PersonPictureDescriptor.ObjectSide tempSide = PersonPictureDescriptor.ObjectSide.BACK;

            if (d.getId() == ppd.getBackId()){
                tempSide = PersonPictureDescriptor.ObjectSide.BACK;
            }
            else if (d.getId() == ppd.getLeftId()){
                tempSide = PersonPictureDescriptor.ObjectSide.LEFT;
            }
            else if (d.getId() == ppd.getRightId()){
                tempSide = PersonPictureDescriptor.ObjectSide.RIGHT;
            }
            else if (d.getId() == ppd.getProfileId()){
                tempSide = PersonPictureDescriptor.ObjectSide.PROFILE;
            }

            ((PictureObjectDescriptor)d).setSide(tempSide);
            descriptors.add((PictureObjectDescriptor)d);
        }

        return descriptors;
    }
}