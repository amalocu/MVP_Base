package co.com.etn.mvp_base.repositories;

import co.com.etn.mvp_base.models.Note;

/**
 * co.com.etn.mvp_base.repositories
 * MVP_Base
 * Created by alexander.vasquez on 7/11/2017.8:13 PM
 */

public interface INotesRepository {
    public Note getNote() throws RepositoryError;
}
