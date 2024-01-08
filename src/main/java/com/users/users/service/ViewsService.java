package com.users.users.service;

import com.users.users.model.Views;
import com.users.users.repository.ViewsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ViewsService {

    private final ViewsRepository viewsRepository;

    public ViewsService(ViewsRepository viewsRepository) {
        this.viewsRepository = viewsRepository;
    }

    @Transactional
    public void save(Views views){
        viewsRepository.save(views);
    }
    @Transactional(readOnly = true)
    public Integer findByRoles(Integer idRoles){
        return viewsRepository.findByRole(idRoles);
    }
    @Transactional
    public void update(Integer count, Integer id) {
        viewsRepository.update(count, id);
    }
}
