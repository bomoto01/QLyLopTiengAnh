package com.tam.education_management.assignment.entity;
import java.time.LocalDateTime;

import com.tam.education_management.classroom.entity.SchoolClass;

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
@Table(name = "assignment_classes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class AssignmentClass {

    @EmbeddedId
    private AssignmentClassId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("assignmentId")
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("classId")
    @JoinColumn(name = "class_id", nullable = false)
    private SchoolClass schoolClass;

    @Column(name = "assigned_at", nullable = false, updatable = false)
    private LocalDateTime assignedAt;

    @Embeddable
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor @Builder
    public static class AssignmentClassId implements java.io.Serializable {
        @Column(name = "assignment_id")
        private Long assignmentId;

        @Column(name = "class_id")
        private Long classId;
    }
}