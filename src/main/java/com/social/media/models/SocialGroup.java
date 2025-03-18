package com.social.media.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class SocialGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "socialGroups")      // making SocialUser as the owner of the ManyToMany mapping
    private Set<SocialUser> socialUsers = new HashSet<>();

}

