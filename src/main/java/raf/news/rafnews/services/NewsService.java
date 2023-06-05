package raf.news.rafnews.services;

import raf.news.rafnews.entities.News;
import raf.news.rafnews.repositories.NewsRepository;

import javax.inject.Inject;
import java.util.List;

public class NewsService {

    @Inject
    private NewsRepository newsRepository;

    public NewsService(){}

    public News addNews(News news){
        return newsRepository.addNews(news);
    }

    public News updateNews(News news){
        return newsRepository.updateNews(news);
    }

    public void increaseVisits(int id){
       newsRepository.increaseVisits(id);
    }

    public List<News> allNews(){
        return newsRepository.allNews();
    }

    public News deleteNews(int id){
        return newsRepository.deleteNews(id);
    }
}
