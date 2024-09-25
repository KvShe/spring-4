package ru.kvshe.homework.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users_project")
@Getter
@Setter
@NoArgsConstructor
public class UsersProject extends EntityWithRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "project_id")
    private Long projectId;
    @Column(name = "user_id")
    private Long userId;

    public UsersProject(Long projectId, Long userId) {
        this.projectId = projectId;
        this.userId = userId;
    }
}
