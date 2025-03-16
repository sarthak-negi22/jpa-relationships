package com.social.media.models;

import jakarta.persistence.*;

@Entity
public class SocialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne       // defined one-to-one relationship
    @JoinColumn(name = "social_user")       // defines the foreign key column in the owning entity with a specified column name, to have a better control over that.
    private SocialUser user;
}
