package com.adpushup.exercise.controller;

import com.adpushup.exercise.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

@RestController
public class WebsiteController {

    @Autowired
    private WebsiteService websiteService;

    @GetMapping("/advertiser-by-website/{website}")
    public Set<String> getAdvertisersByWebsite(@PathVariable("website") String website){
        return websiteService.findAdvertiserByWebsite(website);
    }

    @GetMapping("/website-by-advertiser/{advertiser}")
    public Set<String> getWebsiteByAdvertiser(@PathVariable("advertiser") String advertiser){
        return websiteService.findWebsiteByAdvertiser(advertiser);
    }

    @GetMapping("/advertisers")
    public Set<String> getUniqueAdvertisers(){
        return websiteService.findUniqueAdvertisers();
    }

    @GetMapping("/revenue")
    public Long getTotalRevenue(){
        return websiteService.getTotalRevenue();
    }

    @GetMapping("/revenue/{website}")
    public Long getTotalRevenue(@PathVariable("website") String website){
        return websiteService.getTotalRevenueByWebsite(website);
    }

}
