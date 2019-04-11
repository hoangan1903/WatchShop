package com.seuit.spring.watchshop.entity;

public class EmployeeApi {
    private User user;
    private Employee employee;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeApi() {
    }

    @Override
    public String toString() {
        return "EmployeeApi{" +
                "user=" + user +
                ", employee=" + employee +
                '}';
    }
}
