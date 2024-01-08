package com.users.users.service;

import com.users.users.model.Comments;
import com.users.users.repository.CommentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;

    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }
    @Transactional(readOnly = true)
    public void save(Comments comments){
        commentsRepository.save(comments);
    }
    @Transactional(readOnly = true)
    public List<Comments> findByIdPost(Integer idPost){
        return commentsRepository.findByIdPost(idPost);
    }
    @Transactional(readOnly = true)
    public Integer countCommentsByPost(Integer id) {
       return commentsRepository.countCommentsByPost(id);
    }
    @Transactional
    public void deleteByName(String name) {
        commentsRepository.deleteByName(name);
    }
    @Transactional
    public void deleteById(Integer id) {
        commentsRepository.deleteById(id);
    }
    @Transactional
    public void deleteByIdPosts(Integer id){
        commentsRepository.deleteByIdPosts(id);
    }
    @Transactional(readOnly = true)
    public Optional<Comments> findById(Integer id) {
        return commentsRepository.findById(id);
    }
    @Transactional
    public void updateInfo(String parentName, String newName){
        commentsRepository.updateInfo(parentName, newName);
    }
}
