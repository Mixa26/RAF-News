package raf.news.rafnews.services;

import raf.news.rafnews.entities.News_Tag_Table;
import raf.news.rafnews.repositories.News_Tag_TableRepository;

import javax.inject.Inject;
import java.util.List;

public class News_Tag_TableService {

    @Inject
    private News_Tag_TableRepository newsTagTableRepository;

    public News_Tag_TableService(){}

    public List<News_Tag_Table> allEntities(){
        return newsTagTableRepository.allEntities();
    }

    public News_Tag_Table addEntity(News_Tag_Table entity){
        return newsTagTableRepository.addEntity(entity);
    }

    public News_Tag_Table deletetEntity(int newsId){
        return newsTagTableRepository.deleteEntity(newsId);
    }
}
