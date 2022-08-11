package com.illiapinchuk.learntests;

import com.illiapinchuk.learntests.model.Employee;
import com.illiapinchuk.learntests.repository.EmployeeRepository;
import com.illiapinchuk.learntests.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class LearnTestsApplicationTests {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Test findById Success")
    void testFindById() {
        Employee employee = new Employee(1L, "first name", "last name","email",1000);
        doReturn(Optional.of(employee)).when(employeeRepository).findById(1L);

        Optional<Employee> returnedWidget = employeeService.findById(1L);

        Assertions.assertTrue(returnedWidget.isPresent(), "Employee was not found");
        Assertions.assertSame(returnedWidget.get(), employee, "The Employee returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test findById Not Found")
    void testFindByIdNotFound() {
        doReturn(Optional.empty()).when(employeeRepository).findById(1L);

        Optional<Employee> returnedWidget = employeeService.findById(1L);

        Assertions.assertFalse(returnedWidget.isPresent(), "Employee should not be found");
    }

    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
        Employee employee1 = new Employee(1L, "first name", "last name","email",1000);
        Employee employee2 = new Employee(2L, "first name", "last name","email",1000);
        doReturn(Arrays.asList(employee1, employee2)).when(employeeRepository).findAll();

        List<Employee> employees = employeeService.findAll();

        Assertions.assertEquals(2, employees.size(), "findAll should return 2 widgets");
    }

    @Test
    @DisplayName("Test save employee")
    void testSave() {
        Employee employee = new Employee(1L, "first name", "last name","email",1000);
        doReturn(employee).when(employeeRepository).save(employee);

        Employee returnedEmployee = employeeService.save(employee);

        Assertions.assertNotNull(returnedEmployee, "The saved Employee should not be null");
        Assertions.assertEquals(1L, returnedEmployee.getId(), "The id should be 1L");
    }
}
