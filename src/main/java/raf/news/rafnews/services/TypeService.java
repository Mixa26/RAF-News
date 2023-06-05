package raf.news.rafnews.services;

import raf.news.rafnews.entities.Type;
import raf.news.rafnews.repositories.TypeRepository;

import javax.inject.Inject;
import java.util.List;

public class TypeService {

    public TypeService() {}

    @Inject
    private TypeRepository typeRepository;

    public List<Type> allTypes(){
        return typeRepository.allTypes();
    }
}
