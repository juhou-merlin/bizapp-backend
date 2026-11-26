package com.example.bizapp.dto;

import com.example.bizapp.model.Project;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

/** 登録で受け付けるプロジェクト項目。 */
public record ProjectInput(@NotBlank String projectCode, @NotBlank String name,
        String clientName, String department, String status, LocalDate startDate, LocalDate endDate) {
    public Project toProject() {
        Project project = new Project();
        project.setProjectCode(projectCode);
        project.setName(name);
        project.setClientName(clientName);
        project.setDepartment(department);
        project.setStatus(status == null ? "計画中" : status);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        return project;
    }
}
