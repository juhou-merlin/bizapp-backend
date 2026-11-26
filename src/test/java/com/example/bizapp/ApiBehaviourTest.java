package com.example.bizapp;

import com.example.bizapp.controller.*;
import com.example.bizapp.dto.*;
import com.example.bizapp.model.*;
import com.example.bizapp.repository.*;
import com.example.bizapp.service.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/** 検証用PRで既存APIの正常系・異常系を確認する。 */
class ApiBehaviourTest {
    @Test
    void employeeOperations() {
        EmployeeRepository repository = mock(EmployeeRepository.class);
        EmployeeService service = new EmployeeService(repository);
        EmployeeController controller = new EmployeeController(service);
        EmployeeInput input = new EmployeeInput("E01", "社員", "開発", "担当", "test@example.com", "在籍");
        Employee employee = input.toEmployee();
        employee.setId(1L);
        when(repository.findAll()).thenReturn(List.of(employee));
        when(repository.findById(1L)).thenReturn(Optional.of(employee));
        when(repository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(repository.findByDepartment("開発")).thenReturn(List.of(employee));
        assertEquals(1, controller.getAll().size());
        assertSame(employee, controller.getById(1L));
        assertEquals(201, controller.create(input).getStatusCode().value());
        assertEquals("社員", controller.update(1L, input).getName());
        assertEquals(1, service.findByDepartment("開発").size());
        assertEquals(204, controller.delete(1L).getStatusCode().value());
        verify(repository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> service.findById(9L));
        assertEquals("在籍", new EmployeeInput("E02", "社員", null, null, null, null).toEmployee().getStatus());
    }

    @Test
    void projectOperations() {
        ProjectRepository repository = mock(ProjectRepository.class);
        ProjectService service = new ProjectService(repository);
        ProjectController controller = new ProjectController(service);
        LocalDate start = LocalDate.of(2026, 1, 1);
        ProjectInput input = new ProjectInput("P01", "案件", "取引先", "開発", "進行中", start, start.plusDays(1));
        Project project = input.toProject();
        project.setId(1L);
        when(repository.findAll()).thenReturn(List.of(project));
        when(repository.findById(1L)).thenReturn(Optional.of(project));
        when(repository.save(any(Project.class))).thenAnswer(invocation -> invocation.getArgument(0));
        assertEquals(1, controller.getAll().size());
        assertSame(project, controller.getById(1L));
        assertEquals(201, controller.create(input).getStatusCode().value());
        assertEquals(204, controller.delete(1L).getStatusCode().value());
        verify(repository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> service.findById(9L));
        assertEquals("計画中", new ProjectInput("P02", "案件", null, null, null, null, null).toProject().getStatus());
    }

    @Test
    void dashboardCounts() {
        EmployeeRepository employees = mock(EmployeeRepository.class);
        ProjectRepository projects = mock(ProjectRepository.class);
        when(employees.count()).thenReturn(2L);
        when(employees.findByStatus("在籍")).thenReturn(List.of(new Employee()));
        when(projects.count()).thenReturn(3L);
        when(projects.findByStatus("進行中")).thenReturn(List.of(new Project()));
        DashboardController controller = new DashboardController(employees, projects);
        assertEquals(2L, controller.getSummary().get("totalEmployees"));
        assertEquals(1L, controller.getSummary().get("activeEmployees"));
        assertEquals(3L, controller.getSummary().get("totalProjects"));
        assertEquals(1L, controller.getSummary().get("activeProjects"));
    }
}
