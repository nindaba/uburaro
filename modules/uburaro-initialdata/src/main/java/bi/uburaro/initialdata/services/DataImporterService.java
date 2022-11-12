package bi.uburaro.initialdata.services;

public interface DataImporterService {

    /**
     * Imports the current patch folder and saves the data to the database
     */
    void importCurrent();
    void importFromDir(String dir);
}
