package com.adpushup.exercise.service.impl;

import com.adpushup.exercise.model.Website;
import com.adpushup.exercise.repository.WebsiteRepository;
import com.adpushup.exercise.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WebsiteServiceImpl implements WebsiteService {

    @Autowired
    private WebsiteRepository websiteRepository;

    @Override
    public Set<String> findAdvertiserByWebsite(String website){
        return websiteRepository.findAdvertiserByWebsite(website);
    }

    @Override
    public Set<String> findWebsiteByAdvertiser(String advertiser){
        return websiteRepository.findWebsiteByAdvertiser(advertiser);
    }

    @Override
    public Set<String> findUniqueAdvertisers(){
        List<Website> websites = websiteRepository.findAll();
        return websites.stream().map(website -> website.getAdvertiser()).collect(Collectors.toSet());
    }

    @Override
    public Long getTotalRevenue() {
        return websiteRepository.getTotalRevenue();
    }

    @Override
    public Long getTotalRevenueByWebsite(String website) {
        return websiteRepository.getTotalRevenueByWebsite(website);
    }
}
