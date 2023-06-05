package raf.news.rafnews.services;

import raf.news.rafnews.entities.Tag;
import raf.news.rafnews.repositories.TagRepository;

import javax.inject.Inject;
import java.util.List;

public class TagService {

    @Inject
    private TagRepository tagRepository;

    public TagService(){}

    public List<Tag> allTags(){
        return tagRepository.allTags();
    }

    public Tag addTag(Tag tag){
        return tagRepository.addTag(tag);
    }

    public Tag deleteTag(int id){
        return tagRepository.deleteTag(id);
    }
}
