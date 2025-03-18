package com.social.media.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    mappedBy defines, that socialProfile field, which is defined in the SocialUser, its managed by SocialUser only. That means SocialProfile is not owning entity, and it makes use of "mappedBy", hence JPA doesnt create a FK column
    @OneToOne()       // defined one-to-one relationship
    @JoinColumn(name = "social_user")       // defines the foreign key column in the owning entity with a specified column name, to have a better control over it
    private SocialUser user;
}
