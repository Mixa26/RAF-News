package raf.news.rafnews.repositories;

import raf.news.rafnews.entities.Comment;

import java.util.List;

public interface CommentRepository {

    Comment addComment(Comment comment);

    List<Comment> allPostComments(int newsId);


    List<Comment> allComments();
}
