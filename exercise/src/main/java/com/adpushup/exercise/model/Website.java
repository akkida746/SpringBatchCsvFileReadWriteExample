package com.adpushup.exercise.model;

import org.springframework.batch.item.ResourceAware;
import org.springframework.core.io.Resource;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Website.findAdvertiserByWebsite", query = "SELECT w.advertiser FROM Website w where w.name =:website"),
        @NamedQuery(name = "Website.findWebsiteByAdvertiser", query = "SELECT w.name FROM Website w where w.advertiser =:advertiser"),
        @NamedQuery(name = "Website.getTotalRevenue", query = "SELECT sum(w.revenue) FROM Website w where w.revenue is not null"),
        @NamedQuery(name = "Website.getTotalRevenueByWebsite", query = "SELECT sum(w.revenue) FROM Website w where w.name = :website and w.revenue is not null")})

public class Website implements ResourceAware {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "advertiser")
    private String advertiser;
    @Column(name = "revenue")
    private long revenue;

    public String getName() {
        return name;
    }

    public void setWebsite(String name) {
        this.name = name;
    }

    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "Website{" +
                "name='" + name + '\'' +
                ", advertiser='" + advertiser + '\'' +
                ", revenue=" + revenue +
                '}';
    }

    @Override
    public void setResource(Resource resource) {
        this.name = resource.getFilename();
    }
}
