package com.adpushup.exercise.configuration;

import com.adpushup.exercise.model.Website;
import com.adpushup.exercise.repository.WebsiteRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbWriter implements ItemWriter<Website> {

    @Autowired
    private WebsiteRepository websiteRepository;

    @Override
    public void write(List<? extends Website> users) throws Exception{
        websiteRepository.saveAll(users);
    }
}
