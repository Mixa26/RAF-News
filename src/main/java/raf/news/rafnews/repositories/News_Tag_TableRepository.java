package raf.news.rafnews.repositories;

import raf.news.rafnews.entities.News_Tag_Table;

import java.util.List;

public interface News_Tag_TableRepository {

    List<News_Tag_Table> allEntities();

    News_Tag_Table addEntity(News_Tag_Table entity);

    News_Tag_Table deleteEntity(int newsId);
}
