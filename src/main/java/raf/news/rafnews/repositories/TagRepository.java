package raf.news.rafnews.repositories;

import raf.news.rafnews.entities.Tag;

import java.util.List;

public interface TagRepository {

    List<Tag> allTags();

    Tag addTag(Tag tag);

    Tag deleteTag(int id);
}
