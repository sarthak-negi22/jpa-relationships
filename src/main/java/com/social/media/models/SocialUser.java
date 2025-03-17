package com.social.media.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SocialUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "social_profile_id")     // Only used in the "owning" entity, since SocialUser owns the relationship
    private SocialProfile socialProfile;

    @OneToMany(mappedBy = "socialUser")
    private List<SocialPost> socialPosts = new ArrayList<>();
}
