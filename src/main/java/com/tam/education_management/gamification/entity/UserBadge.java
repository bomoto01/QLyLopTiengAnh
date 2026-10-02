package com.tam.education_management.gamification.entity;
import java.time.LocalDateTime;

import com.tam.education_management.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_badges")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class UserBadge {

    @EmbeddedId
    private UserBadgeId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("badgeId")
    @JoinColumn(name = "badge_id", nullable = false)
    private Badge badge;

    @Column(name = "earned_at", nullable = false, updatable = false)
    private LocalDateTime earnedAt;

    @Embeddable
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UserBadgeId implements java.io.Serializable {
        @Column(name = "user_id")
        private Long userId;

        @Column(name = "badge_id")
        private Long badgeId;
    }
}