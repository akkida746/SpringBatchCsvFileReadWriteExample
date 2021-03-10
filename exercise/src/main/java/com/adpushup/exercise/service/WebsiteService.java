package com.adpushup.exercise.service;

import java.util.Set;

public interface WebsiteService {

    Set<String> findAdvertiserByWebsite(String website);
    Set<String> findWebsiteByAdvertiser(String advertiser);
    Set<String> findUniqueAdvertisers();
    Long getTotalRevenue();
    Long getTotalRevenueByWebsite(String website);
}
