package com.social.media.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    cascade specifies, that when we will be doing any operation with socialProfile along with SocialUser, the profile will be impacted as well. Persist means, only child entities are saved when parent is saved. Merge means about "updated". Delete means, the child entity is also deleted when parent's entity is deleted.
    @OneToOne(mappedBy = "user", cascade ={CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})     // multiple cascading types can happen for a single association
//    @JoinColumn(name = "social_profile_id")     // Only used in the "owning" entity, since SocialUser owns the relationship

    private SocialProfile socialProfile;

    @OneToMany(mappedBy =  "socialUser", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<SocialPost> socialPosts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)        // means the related entities will be loaded up immediately when parent entity is called, Default fetch type of many to many is lazy, like in one to many. But default fetch types of many to one and one to one is eager. "Eager" is used only when its needed, else it can have performance issues. Lazy is used, when data is needed only in demand and not needed to be load up before.
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"),    // SocialUser
            inverseJoinColumns = @JoinColumn(name = "group_id") // SocialGroup
    )      // holds the foreign keys of both related  entities

    private Set<SocialGroup> socialGroups = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setSocialProfile(SocialProfile socialProfile) {
        socialProfile.setUser(this);
        this.socialProfile = socialProfile;
    }
}

// By default, in one to many mapping, JPA maps using a FK in the child(many) table, but if needed, we can have a separate join table using @JoinTable

// in one to one, JPA will replace the FK in the table of the entity that owns the relationship

// in many to many, JPA creates a join table to store associations