package raf.news.rafnews.repositories;

import raf.news.rafnews.entities.News;

import java.util.List;

public interface NewsRepository {

    News addNews(News news);

    News updateNews(News news);

    void increaseVisits(int id);

    List<News> allNews();

    News deleteNews(int id);
}
