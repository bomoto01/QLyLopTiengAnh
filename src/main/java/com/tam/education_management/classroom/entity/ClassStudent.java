package com.tam.education_management.classroom.entity;
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
@Table(name = "class_students")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ClassStudent {

    @EmbeddedId
    private ClassStudentId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("classId")
    @JoinColumn(name = "class_id", nullable = false)
    private SchoolClass schoolClass;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("studentId")
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @Column(name = "joined_at", nullable = false, updatable = false)
    private LocalDateTime joinedAt;

    @Column(nullable = false, length = 20)
    @Builder.Default
    private String status = "ACTIVE";

    @Embeddable
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ClassStudentId implements java.io.Serializable {
        @Column(name = "class_id")
        private Long classId;

        @Column(name = "student_id")
        private Long studentId;
    }
}