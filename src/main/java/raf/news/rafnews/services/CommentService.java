package raf.news.rafnews.services;

import raf.news.rafnews.entities.Comment;
import raf.news.rafnews.repositories.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    @Inject
    private CommentRepository commentRepository;

    public CommentService() {}

    public Comment addComment(Comment comment){
        return commentRepository.addComment(comment);
    }

    public List<Comment> allPostComments(int id){
        return commentRepository.allPostComments(id);
    }

    public List<Comment> allComments(){
        return commentRepository.allComments();
    }
}
