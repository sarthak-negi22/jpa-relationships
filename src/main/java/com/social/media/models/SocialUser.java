package com.social.media.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class SocialUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "social_profile_id")     // Only used in the "owning" entity, since SocialUser owns the relationship
    private SocialProfile socialProfile;

    @OneToMany
    private List<SocialPost> socialPosts = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"),    // SocialUser
            inverseJoinColumns = @JoinColumn(name = "group_id") // SocialGroup
    )      // holds the foreign keys of both related entities
    private Set<SocialGroup> socialGroups = new HashSet<>();
}

// By default, in one to many mapping, JPA maps using a FK in the child(many) table, but if needed, we can have a separate join table using @JoinTable

// in one to one, JPA will replace the FK in the table of the entity that owns the relationship

// in many to many, JPA creates a join table to store associations