package service;

import model.Facultate;
import repositories.FacultateRepository;

public class FacultateService {
    private final FacultateRepository facultateRepository;

    public FacultateService() {
        facultateRepository = FacultateRepository.build(FacultateRepository.Type.FILE);
    }

    public void changeFacultate(String numeFacultate, int locuriBuget, int locuriTaxa){
        facultateRepository.changeFacultate(numeFacultate, locuriBuget, locuriTaxa);
    }

    public Facultate findFacultate(String numeFacultate){
        return facultateRepository.findFacultate(numeFacultate);
    }
}
