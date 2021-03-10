package com.adpushup.exercise.repository;

import com.adpushup.exercise.model.Website;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface WebsiteRepository extends JpaRepository<Website, Long> {

    Set<String> findAdvertiserByWebsite(String website);
    Set<String> findWebsiteByAdvertiser(String advertiser);
    Long getTotalRevenue();
    Long getTotalRevenueByWebsite(String website);
}
