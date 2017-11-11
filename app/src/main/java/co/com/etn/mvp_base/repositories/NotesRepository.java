package co.com.etn.mvp_base.repositories;

import co.com.etn.mvp_base.helper.ServicesFactory;
import co.com.etn.mvp_base.helper.TypeDecryption;
import co.com.etn.mvp_base.models.Note;
import co.com.etn.mvp_base.services.IServices;
import retrofit.RetrofitError;

/**
 * co.com.etn.mvp_base.repositories
 * MVP_Base
 * Created by alexander.vasquez on 7/11/2017.8:13 PM
 */

public class NotesRepository implements INotesRepository {

    private IServices services;

    public NotesRepository(){
        ServicesFactory servicesFactory =new ServicesFactory(TypeDecryption.XML);
        services = (IServices)servicesFactory.getInstance(IServices.class);
    }
    @Override
    public Note getNote() throws RepositoryError{
        try {
            return  services.getNote();
        }catch (RetrofitError retrofitError){
            throw MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
        }
    }
}
