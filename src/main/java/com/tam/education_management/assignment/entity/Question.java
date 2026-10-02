package com.tam.education_management.assignment.entity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tam.education_management.submission.entity.Answer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "questions",
    uniqueConstraints = @UniqueConstraint(
        name = "uq_questions_assignment_order",
        columnNames = {"assignment_id", "order_index"}
    )
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, length = 30)
    private String type;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal points;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "correct_answer", columnDefinition = "TEXT")
    private String correctAnswer;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @Builder.Default
    private List<QuestionOption> options = new ArrayList<>();

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Answer> answers = new ArrayList<>();
}