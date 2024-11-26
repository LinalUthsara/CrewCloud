package com.darkgenesis.crewcloud;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.darkgenesis.crewcloud.entity.Department;
import com.darkgenesis.crewcloud.repository.DepartmentRepo;
import com.darkgenesis.crewcloud.service.serviceImpl.DepartmentServiceImpl;

public class DepartmentServiceTest {
    
   @Mock
    private DepartmentRepo departmentRepo;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        department = new Department();
        department.setDepartmentId(1L);
        department.setDepartmentName("HR");
        department.setEmployees(new ArrayList<>());
    }

    @Test
    void testCreateDepartment() {
        when(departmentRepo.save(any(Department.class))).thenReturn(department);

        Department createdDepartment = departmentService.createDepartment(department);

        assertNotNull(createdDepartment);
        assertEquals("HR", createdDepartment.getDepartmentName());
        verify(departmentRepo, times(1)).save(department);
    }

    @Test
    void testViewAllDepartments() {
        List<Department> departmentList = Arrays.asList(
            department,
            new Department() {{
                setDepartmentId(2L);
                setDepartmentName("Finance");
                setEmployees(new ArrayList<>());
            }}
        );
        when(departmentRepo.findAll()).thenReturn(departmentList);

        List<Department> result = departmentService.viewAllDepartment();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("HR", result.get(0).getDepartmentName());
        verify(departmentRepo, times(1)).findAll();
    }

    @Test
    void testViewDepartmentById() {
        when(departmentRepo.findById(1L)).thenReturn(Optional.of(department));

        Department result = departmentService.viewDepartmentById(1L);

        assertNotNull(result);
        assertEquals("HR", result.getDepartmentName());
        verify(departmentRepo, times(1)).findById(1L);
    }

    @Test
    void testUpdateDepartment() {
        Department updatedDepartment = new Department();
        updatedDepartment.setDepartmentId(1L);
        updatedDepartment.setDepartmentName("Operations");
        updatedDepartment.setEmployees(new ArrayList<>());

        when(departmentRepo.findById(1L)).thenReturn(Optional.of(department));
        when(departmentRepo.save(any(Department.class))).thenReturn(updatedDepartment);

        Department result = departmentService.updateDepartment(1L, updatedDepartment);

        assertNotNull(result);
        assertEquals("Operations", result.getDepartmentName());
        verify(departmentRepo, times(1)).findById(1L);
        verify(departmentRepo, times(1)).save(updatedDepartment);
    }

    @Test
    void testDeleteDepartment() {
        when(departmentRepo.findById(1L)).thenReturn(Optional.of(department));
        doNothing().when(departmentRepo).deleteById(1L);

        assertDoesNotThrow(() -> departmentService.deleteDepartment(1L));

        verify(departmentRepo, times(1)).deleteById(1L);
    }
}
