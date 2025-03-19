package com.social.media.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    // excludes the field from serialization and deserialization when working with JSON, to avoid infinite circular references
    private SocialUser user;

    private String description;

//    created custom setters on both side of one to one mapping, to ensure cascading is working as intended, as lombok generates only simple boilerplate code, this ensures that relationship is established both sides.
    public void setSocialUser(SocialUser socialUser) {
        this.user = socialUser;
        if(user.getSocialProfile() != this)
            user.setSocialProfile(this);
    }
}


// cascading means, when we want to perform one operation on one entity, that operation is propagated to other entities related to it.
// Hibernate provides types for each operations like persist, merge, remove, refresh, detach to specify how they propagate from a parent entity to a child entities