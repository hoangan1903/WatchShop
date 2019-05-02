package com.seuit.spring.watchshop.service;

import com.seuit.spring.watchshop.entity.*;
import com.seuit.spring.watchshop.repository.*;
import javassist.NotFoundException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveOrUpdateEmployee(EmployeeApi employeeApi,Integer id) {
        if(id==null){
            User user = new User();
            Employee employee = new Employee();
            setUserAndEmployee(employeeApi, user, employee);
            user.setEmployee(employee);
            employee.setUser(user);
            userService.addUser(user,"employee");
        }else{
            try {
            	
                Optional<Employee> employeePersis = employeeRepository.findById(id);
                employeePersis.orElseThrow(()->new NotFoundException("Cant find employee"));
                if(employeePersis.isPresent()==true){
                    User userPersis = employeePersis.get().getUser();
                    setUserAndEmployee(employeeApi,userPersis,employeePersis.get());
                    userService.addUser(userPersis,"employee");
                }

            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void setUserAndEmployee(EmployeeApi employeeApi, User user, Employee employee) {
        user.setUsername(employeeApi.getUser().getUsername());
        user.setPassword(employeeApi.getUser().getPassword());
        user.setEmail(employeeApi.getUser().getEmail());
        employee.setName(employeeApi.getEmployee().getName());
        employee.setGender(employeeApi.getEmployee().getGender());
        employee.setBirthdate(employeeApi.getEmployee().getBirthdate());
    }


    @Override
    @Transactional
    public List<Employee> listEmployee( ) {
        // TODO Auto-generated method stub
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Employee getEmployeeById(Integer id) throws NotFoundException {
        // TODO Auto-generated method stub
        return employeeRepository.findById(id).map((x) -> {
            return x;
        }).orElseThrow(() -> new NotFoundException("Cant find product with id : " + id));
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Employee> findPaginated(Integer page, Integer size) {
        Session session = getSession();
        String sql = "FROM Employee";
        Query query = session.createQuery(sql).setFirstResult(page*size).setMaxResults(size);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Long countEmployee() {
        // TODO Auto-generated method stub
        Session session = getSession();
        String sqlCount = "SELECT count(p.id) FROM Employee p";
        Query queryCount = session.createQuery(sqlCount);
        Long count =  (Long) queryCount.getSingleResult();
        logger.info(count.toString());
        return count;

    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Employee> getListEmployeeBykeyword(String keyword) {
        // TODO Auto-generated method stub
        Session session = getSession();
        String sql = "SELECT e FROM Employee e WHERE e.name like :code";
        javax.persistence.Query query = session.createQuery(sql);
        query.setParameter("code", "%" + keyword + "%");
        return query.getResultList();
    }
}
